package net.murat.uikit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import android.widget.TextView;

/**
 * MEditor
 *
 * Read-only scrollable text viewer with a custom scrollbar and a draggable
 * thumb on the right edge.
 *
 * IMPORTANT DESIGN NOTE:
 * TextView has its own internal scroll mechanism that fights with View.scrollTo().
 * To avoid that fight, this class takes full control of scrolling:
 *   - super.onDraw() is NOT called (it would apply TextView's own offset).
 *   - The text is drawn manually via getLayout().draw() after translating
 *     the canvas by -currentScrollY.
 *   - All scroll input (drag on content, drag on thumb, fling) is handled here.
 *
 * This guarantees that the text and the custom scrollbar always stay in sync,
 * which is impossible to achieve reliably with TextView's built-in scrolling.
 */
public class MEditor extends TextView {
  
  /** Scrollbar thumb width (px). */
  private final int barW;
  /** Scrollbar right margin (px). */
  private final int barM;
  /** Minimum thumb height (px). */
  private final int minThumbPx;
  /** Extra touch slop around the thumb (px). */
  private final int thumbSlopPx;
  
  private final Paint barPaint;
  private final Paint trackPaint;
  private final Paint textPaint;   // used only for background fill check
  
  /** Current scroll offset in pixels (authoritative). */
  private int scrollY = 0;
  
  /** Max scroll offset for the current content and size. */
  private int maxScrollY = 0;
  
  /** True while the user is dragging the thumb. */
  private boolean draggingThumb = false;
  /** Finger offset inside the thumb when the drag started. */
  private float thumbGrabOffset = 0f;
  
  /** True while the user is dragging the content (not the thumb). */
  private boolean draggingContent = false;
  /** Last finger Y during content drag. */
  private float lastTouchY = 0f;
  
  private OverScroller scroller;
  private VelocityTracker velocityTracker;
  private int minFlingVelocity;
  private int maxFlingVelocity;
  private int touchSlop;
  
  public MEditor(Context c) { this(c, null); }
  public MEditor(Context c, AttributeSet a) { this(c, a, 0); }
  
  public MEditor(Context c, AttributeSet a, int d) {
    super(c, a, d);
    
    float density = getResources().getDisplayMetrics().density;
    
    barW = (int) (4 * density);
    barM = (int) (6 * density);
    minThumbPx = (int) (24 * density);
    thumbSlopPx = (int) (24 * density);
    
    barPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    barPaint.setColor(0xCC3333DC);
    
    trackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    trackPaint.setColor(0x22000000);
    
    textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    
    // We handle everything ourselves.
    setVerticalScrollBarEnabled(false);
    setHorizontallyScrolling(false);
    setClickable(true);
    setLongClickable(true);
    setFocusable(true);
    setFocusableInTouchMode(false);
    
    scroller = new OverScroller(c);
    ViewConfiguration vc = ViewConfiguration.get(c);
    minFlingVelocity = vc.getScaledMinimumFlingVelocity();
    maxFlingVelocity = vc.getScaledMaximumFlingVelocity();
    touchSlop = vc.getScaledTouchSlop();
  }
  
  // ---------------------------------------------------------------------------
  // Public API
  // ---------------------------------------------------------------------------
  
  public void setEditorText(String text) {
    setText(text == null ? "" : text);
  }
  
  public String getFullText() {
    return getText() == null ? "" : getText().toString();
  }
  
  public int getEditorScrollY() {
    return scrollY;
  }
  
  public void setEditorScrollY(int y) {
    if (scroller != null && !scroller.isFinished()) scroller.abortAnimation();
    recomputeMax();
    if (y < 0) y = 0;
    if (y > maxScrollY) y = maxScrollY;
    scrollY = y;
    invalidate();
  }
  
  @Override
  public int getLineCount() {
    CharSequence t = getText();
    if (t == null || t.length() == 0) return 0;
    String s = t.toString();
    int n = 1, i = 0;
    while ((i = s.indexOf('\n', i)) != -1) { n++; i++; }
      return n;
  }
  
  public int getCaretLine() {
    CharSequence t = getText();
    int len = t == null ? 0 : t.length();
    if (len < 1) return 1;
    int pos = getSelectionStart();
    if (pos < 0) return 1;
    int line = 1;
    for (int i = 0; i < len; i++) {
      if (i < pos && t.charAt(i) == '\n') line++;
    }
    return line;
  }
  
  public void setExternalReadOnly(boolean ro) {
    setTextIsSelectable(ro);
  }
  
  public void resetFirstTapGuard() { scrollToTop(); }
  
  public void scrollToTop() {
    if (scroller != null && !scroller.isFinished()) scroller.abortAnimation();
    scrollY = 0;
    invalidate();
  }
  
  public void scrollToBottom() {
    if (scroller != null && !scroller.isFinished()) scroller.abortAnimation();
    recomputeMax();
    scrollY = maxScrollY;
    invalidate();
  }
  
  // ---------------------------------------------------------------------------
  // Layout / size
  // ---------------------------------------------------------------------------
  
  /**
   * Recomputes the maximum scroll offset.
   *
   * Content height is taken from the layout (which includes soft wrapping),
   * plus vertical padding. Visible height excludes padding.
   */
  private void recomputeMax() {
    Layout l = getLayout();
    if (l == null) { maxScrollY = 0; return; }
      int content = l.getHeight() + getPaddingTop() + getPaddingBottom();
    int visible = getHeight() - getPaddingTop() - getPaddingBottom();
    maxScrollY = Math.max(0, content - visible);
    if (scrollY > maxScrollY) scrollY = maxScrollY;
    if (scrollY < 0) scrollY = 0;
  }
  
  @Override
  protected void onSizeChanged(int w, int h, int ow, int oh) {
    super.onSizeChanged(w, h, ow, oh);
    recomputeMax();
  }
  
  @Override
  public void setText(CharSequence text, BufferType type) {
    super.setText(text, type);
    post(new Runnable() {
        @Override public void run() {
          recomputeMax();
          scrollY = 0;
          invalidate();
        }
    });
  }
  
  // ---------------------------------------------------------------------------
  // Thumb geometry
  // ---------------------------------------------------------------------------
  
  private int thumbHeight() {
    int vh = getHeight();
    Layout l = getLayout();
    if (l == null || vh <= 0) return 0;
    int content = l.getHeight() + getPaddingTop() + getPaddingBottom();
    if (content <= 0) return 0;
    int th = (int) ((float) vh / content * vh);
    return Math.min(Math.max(th, minThumbPx), vh);
  }
  
  private int thumbTop() {
    if (maxScrollY <= 0) return 0;
    int th = thumbHeight();
    int range = getHeight() - th;
    if (range <= 0) return 0;
    float r = (float) scrollY / maxScrollY;
    if (r < 0f) r = 0f;
    if (r > 1f) r = 1f;
    return (int) (r * range);
  }
  
  // ---------------------------------------------------------------------------
  // Draw
  // ---------------------------------------------------------------------------
  
  /**
   * We deliberately do NOT call super.onDraw(), because TextView would apply
   * its own internal scroll offset on top of ours. Instead we draw the layout
   * ourselves, translated by -scrollY, clipped to the view bounds.
   */
  @Override
  protected void onDraw(Canvas canvas) {
    Layout layout = getLayout();
    if (layout == null) return;
    
    layout.getPaint().setColor(getCurrentTextColor());
    
    int vw = getWidth();
    int vh = getHeight();
    int pt = getPaddingTop();
    int pl = getPaddingLeft();
    
    int save = canvas.save();
    
    // Clip to the view so scrolled-out text doesn't bleed.
    canvas.clipRect(0, 0, vw, vh);
    
    // Translate upward by scrollY.
    canvas.translate(pl, pt - scrollY);
    
    // Draw the text layout. getLayout() is positioned relative to the
    // TextView's content area, so we've already accounted for padding above.
    layout.draw(canvas);
    
    canvas.restoreToCount(save);
    
    // Draw the scrollbar on top, in unscrolled coordinates.
    if (maxScrollY > 0) {
      int x = vw - barM - barW;
      int th = thumbHeight();
      int ty = thumbTop();
      canvas.drawRoundRect(x, 0, x + barW, vh, barW / 2f, barW / 2f, trackPaint);
      canvas.drawRoundRect(x, ty, x + barW, ty + th, barW / 2f, barW / 2f, barPaint);
    }
  }
  
  // ---------------------------------------------------------------------------
  // Touch
  // ---------------------------------------------------------------------------
  
  @Override
  public boolean onTouchEvent(MotionEvent e) {
    recomputeMax();
    if (maxScrollY <= 0) return super.onTouchEvent(e);
    
    if (velocityTracker == null) velocityTracker = VelocityTracker.obtain();
    velocityTracker.addMovement(e);
    
    float x = e.getX();
    float y = e.getY();
    int vw = getWidth();
    int vh = getHeight();
    
    switch (e.getActionMasked()) {
      case MotionEvent.ACTION_DOWN: {
        if (!scroller.isFinished()) scroller.abortAnimation();
        
        int th = thumbHeight();
        int ty = thumbTop();
        int xLeft = vw - barM - barW - thumbSlopPx;
        int xRight = vw - barM + thumbSlopPx;
        
        // Hit-test the thumb first.
        if (x >= xLeft && x <= xRight
          && y >= ty - thumbSlopPx && y <= ty + th + thumbSlopPx) {
          draggingThumb = true;
          draggingContent = false;
          thumbGrabOffset = y - ty;
          return true;
        }
        
        // Otherwise, start content drag.
        draggingThumb = false;
        draggingContent = true;
        lastTouchY = y;
        return true;
      }
      
      case MotionEvent.ACTION_MOVE: {
        if (draggingThumb) {
          int th = thumbHeight();
          int range = vh - th;
          if (range <= 0) return true;
          
          float newTop = y - thumbGrabOffset;
          if (newTop < 0) newTop = 0;
          if (newTop > range) newTop = range;
          
          int target = (int) (newTop / range * maxScrollY);
          if (target < 0) target = 0;
          if (target > maxScrollY) target = maxScrollY;
          scrollY = target;
          invalidate();
          return true;
        }
        
        if (draggingContent) {
          float dy = lastTouchY - y;
          lastTouchY = y;
          
          int newScroll = scrollY + (int) dy;
          if (newScroll < 0) newScroll = 0;
          if (newScroll > maxScrollY) newScroll = maxScrollY;
          scrollY = newScroll;
          invalidate();
          return true;
        }
        return true;
      }
      
      case MotionEvent.ACTION_UP:
      case MotionEvent.ACTION_CANCEL: {
        if (draggingThumb) {
          draggingThumb = false;
          recycleTracker();
          return true;
        }
        
        if (draggingContent) {
          draggingContent = false;
          
          // Fling on release, using the tracked velocity.
          velocityTracker.computeCurrentVelocity(1000, maxFlingVelocity);
          int vy = (int) velocityTracker.getYVelocity();
          recycleTracker();
          
          if (Math.abs(vy) > minFlingVelocity) {
            scroller.fling(0, scrollY, 0, -vy, 0, 0, 0, maxScrollY);
            postInvalidateOnAnimation();
          }
          return true;
        }
        recycleTracker();
        return true;
      }
    }
    
    return true;
  }
  
  private void recycleTracker() {
    if (velocityTracker != null) {
      velocityTracker.recycle();
      velocityTracker = null;
    }
  }
  
  @Override
  public void computeScroll() {
    super.computeScroll();
    if (scroller.computeScrollOffset()) {
      int y = scroller.getCurrY();
      if (y < 0) y = 0;
      if (y > maxScrollY) y = maxScrollY;
      scrollY = y;
      postInvalidateOnAnimation();
    }
  }
  
  @Override
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    recycleTracker();
    if (scroller != null && !scroller.isFinished()) scroller.abortAnimation();
  }
  
}
