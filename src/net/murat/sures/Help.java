/*
 * @(#) Help.java
 *
 * Murat iNAN, http://www.dilmerkezi.net
 *             muratsivas76@gmail.com
 */

package net.murat.sures;

/**
 * Helper class for generating the Sura list and the Settings dialog text.
 *
 * NOTE:
 * The Spanish translation of the Quran was originally sourced from
 * islamelsalvador.com / islamelsalvador@gmail.com (historical reference).
 *
 * The "short" settings text (used in the Settings dialog) is NOT static
 * anymore. It reflects the current font name, font style and font size,
 * which are set by Coran.java whenever they change (on startup, on user
 * settings change, etc.).
 *
 * @author Murat iNAN, muratsivas76@gmail.com
 *         www.dilmerkezi.net/eng.html
 */
public final class Help extends Object implements java.io.Serializable {
  /** Serial version UID (for Serializable contract). */
  private static final long serialVersionUID = 1L;
  
  // ---------------------------------------------------------------------------
  // Dynamic state (reflected in the Settings dialog)
  // ---------------------------------------------------------------------------
  
  /** Current font name: "Default" or a TTF file name like "xyz.ttf". */
  private static String fontName = "Default";
  
  /** Current font style: 0=Plain, 1=Bold, 2=Italic, 3=BoldItalic. */
  private static int fontStyle = 0;
  
  /** Current font size in sp (e.g. 28.0). */
  private static float fontSize = 28.0f;
  
  /** Current background color in #AARRGGBB format. */
  private static String bgColor = "#FFFFFFFF";
  
  /** Current foreground color in #AARRGGBB format. */
  private static String fgColor = "#FF000000";
  
  // ---------------------------------------------------------------------------
  // Constructor
  // ---------------------------------------------------------------------------
  
  /** Private constructor: utility class, no instances. */
  private Help() {
  }
  
  // ---------------------------------------------------------------------------
  // Dynamic state accessors
  // ---------------------------------------------------------------------------
  
  public static String getFontName() {
    return fontName;
  }
  
  public static void setFontName(String name) {
    if (name == null || name.length() == 0) name = "Default";
    fontName = name;
  }
  
  public static int getFontStyle() {
    return fontStyle;
  }
  
  public static void setFontStyle(int style) {
    if (style < 0 || style > 3) style = 0;
    fontStyle = style;
  }
  
  public static float getFontSize() {
    return fontSize;
  }
  
  public static void setFontSize(float size) {
    if (size <= 0f) size = 28.0f;
    fontSize = size;
  }
  
  public static String getBGColor() {
    return bgColor;
  }
  
  public static void setBGColor(String color) {
    if (color == null || color.length() == 0) color = "#FFFFFFFF";
    bgColor = color;
  }
  
  public static String getFGColor() {
    return fgColor;
  }
  
  public static void setFGColor(String color) {
    if (color == null || color.length() == 0) color = "#FF000000";
    fgColor = color;
  }
  
  // ---------------------------------------------------------------------------
  // Static data: Sura list
  // ---------------------------------------------------------------------------
  
  /** Sura list with Turkish names, format: "NNN-Name". */
  private static final String[] strs = {
    "001-Fatiha",
    "002-Bakara",
    "003-Al-i \u0130mran",
    "004-Nisa",
    "005-Maide",
    "006-Enam",
    "007-Araf",
    "008-Enfal",
    "009-Tevbe",
    "010-Yunus",
    "011-Hud",
    "012-Yusuf",
    "013-Rad",
    "014-\u0130brahim",
    "015-Hicr",
    "016-Nahl",
    "017-\u0130sra",
    "018-Kehf",
    "019-Meryem",
    "020-Taha",
    "021-Enbiya",
    "022-Hac",
    "023-M\u00FCminun",
    "024-Nur",
    "025-Furkan",
    "026-\u015Euara",
    "027-Neml",
    "028-Kasas",
    "029-Ankebut",
    "030-Rum",
    "031-Lokman",
    "032-Secde",
    "033-Ahzab",
    "034-Sebe",
    "035-Fat\u0131r",
    "036-Yasin",
    "037-Saffat",
    "038-Sad",
    "039-Z\u00FCmer",
    "040-M\u00FCmin",
    "041-Fussilet",
    "042-\u015Eura",
    "043-Zuhruf",
    "044-Duhan",
    "045-Casiye",
    "046-Ahkaf",
    "047-Muhammed",
    "048-Fetih",
    "049-Hucurat",
    "050-Kaf",
    "051-Zariyat",
    "052-Tur",
    "053-Necm",
    "054-Kamer",
    "055-Rahman",
    "056-Vak\u0131a",
    "057-Hadid",
    "058-M\u00FCcadele",
    "059-Ha\u015Fr",
    "060-M\u00FCmtehine",
    "061-Saf",
    "062-Cuma",
    "063-M\u00FCnafikun",
    "064-Tegab\u00FCn",
    "065-Talak",
    "066-Tahrim",
    "067-M\u00FClk",
    "068-Kalem",
    "069-Hakka",
    "070-Mearic",
    "071-Nuh",
    "072-Cin",
    "073-M\u00FCzzemmil",
    "074-M\u00FCddessir",
    "075-K\u0131yamet",
    "076-\u0130nsan",
    "077-M\u00FCrselat",
    "078-Nebe",
    "079-Naziat",
    "080-Abese",
    "081-Tekvir",
    "082-\u0130nfitar",
    "083-Mutaffifin",
    "084-\u0130n\u015Fikak",
    "085-Buruc",
    "086-Tar\u0131k",
    "087-A'la",
    "088-Ga\u015Fiye",
    "089-Fecr",
    "090-Beled",
    "091-\u015Eems",
    "092-Leyl",
    "093-Duha",
    "094-\u0130n\u015Firah",
    "095-Tin",
    "096-Alak",
    "097-Kadir",
    "098-Beyyine",
    "099-Zilzal",
    "100-Adiyat",
    "101-Karia",
    "102-Tekas\u00FCr",
    "103-Asr",
    "104-H\u00FCmeze",
    "105-Fil",
    "106-Kurey\u015F",
    "107-Maun",
    "108-Kevser",
    "109-Kafirun",
    "110-Nasr",
    "111-Tebbet",
    "112-\u0130hlas",
    "113-Felak",
    "114-Nas"
  };
  
  // ---------------------------------------------------------------------------
  // Generators
  // ---------------------------------------------------------------------------
  
  /**
   * Returns the full Sura list (one per line) as a single string.
   * Used to display the Sura names in the main view.
   */
  public static String generate() {
    StringBuffer sb = new StringBuffer();
    final int len = strs.length;
    
    for (int i = 0x0000; i < len; i++) {
      sb.append(strs[i]);
      sb.append("\n");
    }
    
    return sb.toString();
  }
  
  /**
   * Returns the Settings dialog text, reflecting the CURRENT font name,
   * font style and font size.
   *
   * Layout:
   *   Line 1: Font name (dynamic)
   *   Line 2: Font style 0-3 (dynamic)
   *   Line 3: Font size float (dynamic, e.g. "28.0F")
   *   Line 4: Background color (static default)
   *   Line 5: Foreground color (static default)
   *   Line 6: "##########"
   *   Lines 7-11: Explanations
   *   Line 12: "##########"
   */
  public static String getShortGenerate() {
    StringBuffer sb = new StringBuffer();
    
    // Dynamic values
    sb.append(fontName).append("\n");
    sb.append(fontStyle).append("\n");
    sb.append(fontSize).append("F").append("\n");
    
    // Dynamic colors
    sb.append(bgColor).append("\n");
    sb.append(fgColor).append("\n");
    
    // Separator + explanations
    sb.append("##########").append("\n");
    sb.append("1. Line: Default (Default font or ttf file in Download/carets/ttf)").append("\n");
    sb.append("2. Line: 0||1||2||3 [normal, bold, italic, bold_italic]").append("\n");
    sb.append("3. Line: Font size float").append("\n");
    sb.append("4. Line: Background color (#AARRGGBB)").append("\n");
    sb.append("5. Line: Foreground color (#AARRGGBB)").append("\n");
    sb.append("##########").append("\n");
    
    return sb.toString();
  }
  
  // ---------------------------------------------------------------------------
  // Misc
  // ---------------------------------------------------------------------------
  
  /** @return Empty string (per original contract). */
  @Override
  public String toString() {
    return ("");
  }
  
} // class end
