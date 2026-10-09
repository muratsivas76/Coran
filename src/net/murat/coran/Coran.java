package net.murat.coran;

// Android imports
import android.app.Activity;
import android.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;

import android.graphics.Color;
import android.graphics.Typeface;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;

import android.view.inputmethod.InputMethodManager;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;

// Java imports
import java.io.BufferedReader;
import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.Reader;
import java.io.FileReader;
import java.io.StringReader;
import java.io.IOException;
import java.io.PrintStream;

// Custom
import net.murat.sures.*;
import net.murat.uikit.MEditor;

public class Coran extends Activity {
  private final Handler uiHandler = new Handler(Looper.getMainLooper());
  
  private String TEXT = "Select a Sura!\n";
  private String HELPTEXT = "Help";
  
  private EditText shtime = null;
  
  private EditText enter_tv = null;
  
  private Button set_button = null;
  private Button help_button = null;
  private Button write_button = null;
  private Button exit_button = null;
  private Button hifz_button = null;
  
  private MEditor tv = null;
  
  private EditText successText = null;
  
  private final int CERO = 0x0000;
  private final int UNO = 0x0001;
  private final int DOS = 0x0002;
  private final int TRES = 0x0003;
  
  private final int YYO = 114;
  
  private int NUMERO = CERO;
  private int PGSYC = UNO;
  
  private int savedLineForRestore = 0;
  private boolean hasSavedLine = false;
  
  private final float VEINTEF = 28F;
  
  private int savedScrollY = 0;  // For storing scroll position from file
  
  private AlertDialog setPane = null;
  private AlertDialog successPane = null;
  
  private String caretFileName = "";
  private String PATH = "";
  
  private String currentFontName = "Default";
  private String currentBG = "#FFFFFFFF";
  private String currentFG = "#FF000000";
  
  private final String TIRE = "-";
  private final String MUSTBEERROR = "Sure Number Must be 1-114!";
  private final String SELECTSURE = "Select a Sura!";
  
  private final KSure[] SURELER = new KSure[] {
    new Sure_001(), new Sure_002(), new Sure_003(), new Sure_004(),
    new Sure_005(), new Sure_006(), new Sure_007(), new Sure_008(),
    new Sure_009(), new Sure_010(), new Sure_011(), new Sure_012(),
    new Sure_013(), new Sure_014(), new Sure_015(), new Sure_016(),
    new Sure_017(), new Sure_018(), new Sure_019(), new Sure_020(),
    new Sure_021(), new Sure_022(), new Sure_023(), new Sure_024(),
    new Sure_025(), new Sure_026(), new Sure_027(), new Sure_028(),
    new Sure_029(), new Sure_030(), new Sure_031(), new Sure_032(),
    new Sure_033(), new Sure_034(), new Sure_035(), new Sure_036(),
    new Sure_037(), new Sure_038(), new Sure_039(), new Sure_040(),
    new Sure_041(), new Sure_042(), new Sure_043(), new Sure_044(),
    new Sure_045(), new Sure_046(), new Sure_047(), new Sure_048(),
    new Sure_049(), new Sure_050(), new Sure_051(), new Sure_052(),
    new Sure_053(), new Sure_054(), new Sure_055(), new Sure_056(),
    new Sure_057(), new Sure_058(), new Sure_059(), new Sure_060(),
    new Sure_061(), new Sure_062(), new Sure_063(), new Sure_064(),
    new Sure_065(), new Sure_066(), new Sure_067(), new Sure_068(),
    new Sure_069(), new Sure_070(), new Sure_071(), new Sure_072(),
    new Sure_073(), new Sure_074(), new Sure_075(), new Sure_076(),
    new Sure_077(), new Sure_078(), new Sure_079(), new Sure_080(),
    new Sure_081(), new Sure_082(), new Sure_083(), new Sure_084(),
    new Sure_085(), new Sure_086(), new Sure_087(), new Sure_088(),
    new Sure_089(), new Sure_090(), new Sure_091(), new Sure_092(),
    new Sure_093(), new Sure_094(), new Sure_095(), new Sure_096(),
    new Sure_097(), new Sure_098(), new Sure_099(), new Sure_100(),
    new Sure_101(), new Sure_102(), new Sure_103(), new Sure_104(),
    new Sure_105(), new Sure_106(), new Sure_107(), new Sure_108(),
    new Sure_109(), new Sure_110(), new Sure_111(), new Sure_112(),
    new Sure_113(), new Sure_114()
  }; //Bu iki nokta ust uste burada sart.
  
  private final int SURELEN = SURELER.length;
  
  private InputMethodManager imm = null;
  
  @SuppressWarnings("deprecation")
  @Override
  public void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    
    setContentView(R.layout.main);
    
    PATH = getMainPathName();//(Environment.getExternalStorageDirectory()).getAbsolutePath();
    
    File ff = new File(PATH + "/carets");
    if (ff.exists() == false) {
      try {
        ff.mkdir();
      } catch (SecurityException se) {}
    }
    
    File ffsv = new File(PATH + "/carets/suver");
    if (ffsv.exists() == false) {
      try {
        ffsv.mkdir();
      } catch (SecurityException se) {}
    }
    
    File ttfDir = new File(PATH + "/carets/ttf");
    if (ttfDir.exists() == false) {
      try {
        ttfDir.mkdir();
      } catch (SecurityException se) {}
    }
    
    shtime = new EditText(this);
    
    tv = (MEditor) findViewById(R.id.tv);
    //tv.setExternalReadOnly(true);
    
    InputMethodManager immx = null;
    
    try {
      immx = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      } catch (Exception e) {
      immx = null;
    }
    
    imm = immx;
    
    for (int i = CERO; i < SURELEN; i++) {
      SURELER[i].generate();//Generate Ayetes
    }
    
    enter_tv = (EditText) findViewById(R.id.enter_tv);
    enter_tv.setInputType(android.text.InputType.TYPE_CLASS_PHONE);
    enter_tv.setFilters(new android.text.InputFilter[] { new android.text.InputFilter.LengthFilter(7) });
    
    //////////////////////
    caretFileName = (PATH + "/carets/kuran.txt");
    ff = new File(caretFileName);
    
    if (ff.exists()) {
      try {
        BufferedReader br = new BufferedReader(new FileReader(ff));
        
        // Line 1: data
        String line = br.readLine();
        if (line != null) line = line.trim();
        
        // Skip comment lines (those starting with '#')
        // (Safety for future case where data might be below comments)
        // Here we only read the first line; the rest are comments.
        
        if ((line != null) && (line.length() > 0) && (line.contains(":"))) {
          String[] parts = line.split(":");
          
          if (parts.length >= 7) {
            try {
              PGSYC = Integer.parseInt(parts[0].trim());
              
              int savedLineNumber = Integer.parseInt(parts[1].trim());
              
              float savedFontSizeInSp =
              Float.parseFloat(parts[2].trim().replace("F", "").replace("f", ""));
              
              int savedStyle = Integer.parseInt(parts[3].trim());
              
              String savedFontName = parts[4].trim();
              String savedBG = parts[5].trim();
              String savedFG = parts[6].trim();
              
              currentFontName = savedFontName;
              currentBG = savedBG;
              currentFG = savedFG;
              
              // Sync Help state so the Settings dialog reflects the loaded values
              Help.setFontName(savedFontName);
              Help.setFontStyle(savedStyle);
              Help.setFontSize(savedFontSizeInSp);
              Help.setBGColor(savedBG);
              Help.setFGColor(savedFG);
              
              // Apply font and style
              Typeface baseFace = resolveTypeface(savedFontName);
              
              int style = Typeface.NORMAL;
              if (savedStyle == UNO) {
                style = Typeface.BOLD;
                } else if (savedStyle == DOS) {
                style = Typeface.ITALIC;
                } else if (savedStyle == TRES) {
                style = Typeface.BOLD_ITALIC;
                } else {
                style = Typeface.NORMAL;
              }
              
              tv.setTypeface(Typeface.create(baseFace, style));
              
              // Apply font size
              tv.setTextSize(savedFontSizeInSp);
              
              // Apply colors
              int bg = 0xFFFFFFFF;
              int fg = 0xFF000000;
              
              try {
                bg = Color.parseColor(savedBG);
                } catch (IllegalArgumentException iae) {
                bg = 0xFFFFFFFF;
              }
              
              try {
                fg = Color.parseColor(savedFG);
                } catch (IllegalArgumentException iae) {
                fg = 0xFF000000;
              }
              
              tv.setBackgroundColor(bg);
              tv.setTextColor(fg);
              
              // Store for restore
              savedLineForRestore = savedLineNumber;
              hasSavedLine = true;
              } catch (NumberFormatException nfe) {
              PGSYC = UNO;
            }
          }
        }
        
        br.close();
        } catch (IOException ioe) {
        ioe.printStackTrace();
      }
      } else {
      // New file: 1 data line + comment lines
      try {
        PrintStream ps = new PrintStream(new FileOutputStream(ff), true, "UTF-8");
        
        // Line 1: data (Sure:Line:FontSize:FontStyle:FontName:BGColor:FGColor)
        ps.println("1:0:28.0F:0:Default:#FFFFFFFF:#FF000000");
        
        // Line 2: format description
        ps.println("# Format: SureNumber:LineNumber:FontSize:FontStyle:FontName:BGColor:FGColor");
        
        // Line 3: field description
        ps.println("# FontStyle: 0=Plain 1=Bold 2=Italic 3=BoldItalic | FontName: 'Default' or 'xyz.ttf' | Colors: #AARRGGBB");
        
        ps.flush();
        ps.close();
        } catch (IOException ioe) {
        ioe.printStackTrace();
      }
    }
    //////////////////////
    
    //////////////////////
    AlertDialog.Builder salert = new AlertDialog.Builder(this);
    salert = salert.setTitle("Settings");
    
    shtime.setText(Help.getShortGenerate());
    
    salert.setView(shtime);
    
    salert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int wh) {
          String res = (shtime.getText().toString());
          
          if (res.length() < 20) return;
          
          cinit(res);
          
          enter_tv.clearFocus();
          
          return;
        }
    });
    
    salert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int wh) {
          enter_tv.clearFocus();
          return;
        }
    });
    
    setPane = salert.create();
    ///////////////////
    
    ///////////////////
    AlertDialog.Builder successAlert = new AlertDialog.Builder(this);
    successAlert = successAlert.setTitle("Successfull/Warning");
    
    successText = new EditText(this);
    successText.setFocusable(false);
    successText.setFocusableInTouchMode(false);
    successText.setClickable(false);
    
    successAlert.setView(successText);
    
    successAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int wh) {
        }
    });
    
    successPane = successAlert.create();
    //////////////////
    
    set_button = (Button) findViewById(R.id.set_button);
    set_button.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
          // Refresh Help state from the live MEditor values before showing the dialog
          float liveSize = tv.getTextSize() / getResources().getDisplayMetrics().scaledDensity;
          Help.setFontName(currentFontName);
          Help.setFontSize(liveSize);
          
          // Sync style from the actual Typeface
          Typeface tf = tv.getTypeface();
          int styleInt = CERO;
          if (tf != null) {
            int s = tf.getStyle();
            if (s == Typeface.BOLD) {
              styleInt = UNO;
              } else if (s == Typeface.ITALIC) {
              styleInt = DOS;
              } else if (s == Typeface.BOLD_ITALIC) {
              styleInt = TRES;
            }
          }
          Help.setFontStyle(styleInt);
          Help.setBGColor(currentBG);
          Help.setFGColor(currentFG);
          
          // Refresh dialog text with the up-to-date values
          shtime.setText(Help.getShortGenerate());
          
          setPane.show();
          return;
        }//onclick end
    });
    
    HELPTEXT = Help.generate();
    
    help_button = (Button) findViewById(R.id.help_button);//Help
    help_button.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
          tv.setEditorText(HELPTEXT);
          return;
        }//onclick end
    });
    
    write_button = (Button) findViewById(R.id.write_button);//Show
    write_button.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
          String m = (enter_tv.getText()).toString();
          
          if (m == null) {
            hideTeclado(view);
            return;
          }
          
          m = m.trim();
          if (m.length() < UNO) {
            hideTeclado(view);
            tv.setEditorText(SELECTSURE);
            return;
          }
          
          int mindex = m.indexOf(TIRE);
          if (mindex >= 0) {
            m = m.substring(0, mindex);
          }
          
          try {
            NUMERO = Integer.parseInt(m);
            } catch (NumberFormatException nfe) {
            hideTeclado(view);
            tv.setEditorText("Error: " + nfe.getMessage());
            return;
          }
          
          if ((NUMERO < UNO) || (NUMERO > YYO)) {
            hideTeclado(view);
            tv.setEditorText(MUSTBEERROR);
            return;
          }
          
          PGSYC = NUMERO;
          
          TEXT = (SURELER[NUMERO - UNO]).getAllSure();
          
          enter_tv.clearFocus();
          hideTeclado(view);
          
          tv.setEditorText(TEXT);
          
          return;
        }//onclick end
    });
    
    exit_button = (Button) findViewById(R.id.exit_button);
    exit_button.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
          //onDestroy();
          lastWork();
          finishAndRemoveTask();
        }
    });
    
    hifz_button = (Button) findViewById(R.id.hifz_button);
    hifz_button.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
          String res = (enter_tv.getText().toString()).trim();
          
          if (res.length() < UNO) return;
          
          int strt = UNO;
          int end = UNO;
          
          if (res.indexOf(TIRE) > 0) {
            String[] split = res.split(TIRE);
            if (split.length >= 2) {
              try {
                strt = Integer.parseInt(split[0]);
                end = Integer.parseInt(split[1]);
                } catch (NumberFormatException nfe) {
                return;
              }
            }
            } else {
            try {
              strt = Integer.parseInt(res);
              end = strt;
              } catch (NumberFormatException nfe) {
              return;
            }
          }
          
          if (strt < CERO) strt = CERO;
          if (strt > YYO) strt = YYO;
          if (end < CERO) end = CERO;
          if (end > YYO) end = YYO;
          
          if (strt > end) return;
          //if(end < strt) return;
          
          try {
            copyContent(strt, end);
          } catch (IOException ioe) {}
          
          return;
        }
    });
    
    // AUTOMATIC LOAD AT STARTUP - Load the saved sura
    if (PGSYC >= UNO && PGSYC <= YYO) {
      enter_tv.setText(Integer.toString(PGSYC));
      TEXT = (SURELER[PGSYC - UNO]).getAllSure();
      tv.setEditorText(TEXT);
      
      if (hasSavedLine) {
        final int targetLine = savedLineForRestore;
        tv.post(new Runnable() {
            @Override
            public void run() {
              if (tv.getLayout() != null) {
                int scrollPosition = tv.getLayout().getLineTop(targetLine);
                tv.setEditorScrollY(scrollPosition);
              }
            }
        });
      }
    }
  }//oncreate end
  
  private final String getMainPathName() {
    File path = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
    String px = path.getAbsolutePath();
    
    String exclude = "Android/data/" + getPackageName() + "/files/";
    px = px.replace(exclude, "");
    
    return px;
  }
  
  /**
   * Resolves a {@link Typeface} from a settings token. If the token names a
   * TTF file under {@code carets/ttf}, it is loaded; otherwise a default is
   * returned. Never throws.
   */
  private Typeface resolveTypeface(String token) {
    if (token == null || token.length() == 0 || token.equalsIgnoreCase("Default")) {
      return Typeface.DEFAULT;
    }
    
    try {
      File fontFile = new File(PATH + "/carets/ttf/" + token);
      if (fontFile.exists()) {
        return Typeface.createFromFile(fontFile);
      }
      } catch (RuntimeException re) {
      //Log.w(TAG, "Cannot load font: " + token, re);
    }
    
    return Typeface.DEFAULT;
  }
  
  private final void copyContent(int strt, int end) throws IOException {
    File fd = new File(PATH + "/carets/suver/sura_" + strt + TIRE + end + ".txt");
    
    if (fd.exists()) {
      //successAlert.setTitle("Warning");
      successText.setText("Warning: Already exists carets/suver " + strt + TIRE + end + " suras.");
      
      runOnUiThread(new Runnable() {
          @Override
          public void run() {
            if (successPane != null) {
              successPane.show();
            }
          }
      });
      
      return;
    }
    
    OutputStream fos = new FileOutputStream(fd);
    PrintStream ps = new PrintStream(fos, true, "UTF-8");
    
    String aleyas = "";
    
    for (int i = strt; i <= end; i++) {
      aleyas = (SURELER[i - UNO]).getAllSure();
      ps.print(aleyas);
      
      ps.print("\n   ");
      ps.print(Integer.toString(i) + ". SURA");
      ps.println("\n");
      //System.out.println("Added: " + i + "/" + MAX);
    }
    
    ps.flush();
    ps.close();
    fos.flush();
    fos.close();
    
    //successAlert.setTitle("Successfull");
    successText.setText("Successfull: Saved to carets/suver " + strt + TIRE + end + " suras.");
    
    runOnUiThread(new Runnable() {
        @Override
        public void run() {
          if (successPane != null) {
            successPane.show();
          }
        }
    });
    
    return;
  }
  
  private final void hideTeclado(View view) {
    if (imm == null) return;
    
    try {
      imm.hideSoftInputFromWindow(view.getWindowToken(), CERO);
    } catch (Exception e) {}
  }
  
  //CINIT START
  private void cinit(String text) {
    try {
      Reader isr = new StringReader(text);
      BufferedReader br = new BufferedReader(isr);
      
      Typeface SCTP = Typeface.DEFAULT;
      Typeface tpf = Typeface.DEFAULT;
      
      //Default (or Download/carets/ttf/xyz.ttf)
      String line = br.readLine().trim();//1 //Font name line
      currentFontName = line;
      tpf = resolveTypeface(line);
      
      //0; Font style(0, 1, 2, 3)(plain, bold, italic, boldItalic)
      line = br.readLine().trim();//2
      
      int num = 0;
      
      try {
        num = Integer.parseInt(line);
        } catch (NumberFormatException nfe) {
        num = 0;
      }
      
      int style = Typeface.NORMAL;
      
      if (num == CERO) {
        style = Typeface.NORMAL;
        } else if (num == UNO) {
        style = Typeface.BOLD;
        } else if (num == DOS) {
        style = Typeface.ITALIC;
        } else if (num == TRES) {
        style = Typeface.BOLD_ITALIC;
        } else {
        style = Typeface.NORMAL;
      }
      
      SCTP = Typeface.create(tpf, style);
      tv.setTypeface(SCTP);
      
      //28F; Text size
      line = br.readLine();//3
      
      float size = VEINTEF;
      
      try {
        size = Float.parseFloat(line);
        } catch (NumberFormatException nfe) {
        size = VEINTEF;
      }
      
      tv.setTextSize(size);
      
      //#FFFFFFFF; BG color
      line = br.readLine();//4
      
      int bg = 0xFFFFFFFF;
      
      try {
        bg = Color.parseColor(line);
        currentBG = line;
        } catch (IllegalArgumentException iae) {
        bg = Color.WHITE;
        currentBG = "#FFFFFFFF";
      }
      
      tv.setBackgroundColor(bg);
      
      //#FF000000; FG color
      line = br.readLine();//5
      
      int fg = Color.BLACK;
      
      try {
        fg = Color.parseColor(line);
        currentFG = line;
        } catch (IllegalArgumentException iae) {
        fg = Color.BLACK;
        currentFG = "#FF000000";
      }
      
      tv.setTextColor(fg);
      
      // Sync Help state AFTER all values are read
      Help.setFontName(currentFontName);
      Help.setFontStyle(num);
      Help.setFontSize(size);
      Help.setBGColor(currentBG);
      Help.setFGColor(currentFG);
      shtime.setText(Help.getShortGenerate());
      
      //hideTeclado(view);
    } catch (IOException ioe) {}
  }
  //CINIT END
  
  @Override
  public void onResume() {
    super.onResume();
  }
  
  @Override
  public void onPause() {
    super.onPause();
  }
  
  @SuppressWarnings("deprecation")
  private void lastWork() {
    if ((PGSYC >= UNO) && (PGSYC <= YYO)) {
      try {
        // First visible line index
        int firstVisibleLine = 0;
        
        if (tv.getLayout() != null) {
          firstVisibleLine = tv.getLayout().getLineForVertical(tv.getEditorScrollY());
        }
        
        // Font size in sp
        float currentFontSizeInSp =
        tv.getTextSize() / getResources().getDisplayMetrics().scaledDensity;
        
        // Resolve font style from current Typeface (0=Plain 1=Bold 2=Italic 3=BoldItalic)
        int styleInt = CERO;
        Typeface tf = tv.getTypeface();
        if (tf != null) {
          int s = tf.getStyle();
          if (s == Typeface.BOLD) {
            styleInt = UNO;
            } else if (s == Typeface.ITALIC) {
            styleInt = DOS;
            } else if (s == Typeface.BOLD_ITALIC) {
            styleInt = TRES;
            } else {
            styleInt = CERO;
          }
        }
        
        // Current font name ("Default" or "xyz.ttf")
        // NOTE: requires a "currentFontName" field on Coran, updated wherever
        // the typeface is set (onCreate / cinit).
        String fontName = currentFontName;
        
        // Format: SureNumber:LineNumber:FontSize:FontStyle:FontName:BGColor:FGColor
        String dataToSave = PGSYC + ":" + firstVisibleLine + ":"
        + currentFontSizeInSp + "F:"
        + styleInt + ":" + fontName + ":"
        + currentBG + ":" + currentFG;
        
        PrintStream ps = new PrintStream(new FileOutputStream(new File(caretFileName)), true, "UTF-8");
        
        // Line 1: data
        ps.println(dataToSave);
        
        // Line 2: format description
        ps.println("# Format: SureNumber:LineNumber:FontSize:FontStyle:FontName:BGColor:FGColor");
        
        // Line 3: field description
        ps.println("# FontStyle: 0=Plain 1=Bold 2=Italic 3=BoldItalic | FontName: 'Default' or 'xyz.ttf' | Colors: #AARRGGBB");
        
        ps.flush();
        ps.close();
        } catch (IOException ioe) {
        //ioe.printStackTrace();
      }
    }
  }
  
  @Override
  protected void onDestroy() {
    //lastWork();
    super.onDestroy();
    
    //uiHandler.postDelayed(new Runnable() {
    //    @Override
    //    public void run() {
    //      android.os.Process.killProcess(android.os.Process.myPid());
    //      System.exit(0);
    //    }
    //}, 250);
    
  }
  
} //End of class
