//#112=Al-Ijls=La Fe Pura=Mecana=4
/*
 * @(#)KSure.java    Prs 05 Kas 2009 19:14:44 CET
 *
 * Murat iNAN, http://www.dilmerkezi.net, muratsivas76@gmail.com
 */
package net.murat.sures;

/**
 * Sure arabirimi
 *
 * @author Murat iNAN, www.dilmerkezi.net
 */
public interface KSure
{
  //Variables
  public static final String NL="\n";
  public static final int CERO=0x0000;
  
  //Methods
  public String getName ();
  //public String [] getAyetes ();
  public void generate ();
  public String getAllSure ();
}
