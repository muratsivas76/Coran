//ARAC
import java.util.Calendar;
import java.util.Date;

public class FindTarih
extends Object
implements java.io.Serializable
{

    public FindTarih ()
    {
	super ();
    }
    
    final
    private static String getDayNameTurkish (int mday, int mmonth, int myear)
    {
       Calendar cal=Calendar.getInstance ();
       
       cal.set (myear, mmonth, mday);
       
       StringBuffer sb=new StringBuffer ();
       sb.append (Integer.toString (mday));
       sb.append (" ");
       
       int month=mmonth+1;
       
       if (mday < 1 || mday > 31 || month < 1 || month > 12 || myear < 1 || myear > 9999)
       {
        return "Non existing date!";
       }
       
       if ((month == 2) && ((myear%4)!=0x0000) && (mday>28))
       {
        return "Non existing date!";
       }
       
       if ((month == 1) && (mday>31))
       {
        return "Non existing date!";
       }
       
       if ((month == 2) && (mday>29))
       {
        return "Non existing date!";
       }
       
       if ((month == 3) && (mday>31))
       {
        return "Non existing date!";
       }
       
       if ((month == 4) && (mday>30))
       {
        return "Non existing date!";
       }
       
       if ((month == 5) && (mday>31))
       {
        return "Non existing date!";
       }
       
       if ((month == 6) && (mday>30))
       {
        return "Non existing date!";
       }
       
       if ((month == 7) && (mday>31))
       {
        return "Non existing date!";
       }
       
       if ((month == 8) && (mday>31))
       {
        return "Non existing date!";
       }
       
       if ((month == 9) && (mday>30))
       {
        return "Non existing date!";
       }
       
       if ((month == 10) && (mday>31))
       {
        return "Non existing date!";
       }
       
       if ((month == 11) && (mday>30))
       {
        return "Non existing date!";
       }
       
       if ((month == 12) && (mday>31))
       {
        return "Non existing date!";
       }
       
       if (month == 1)
       {
        sb.append ("Ocak");
       }
       else if (month == 2)
       {
        sb.append ("\u015Eubat");
       }
       else if (month == 3)
       {
        sb.append ("Mart");
       }
       else if (month == 4)
       {
        sb.append ("Nisan");
       }
       else if (month == 5)
       {
        sb.append ("May\u0131s");
       }
       else if (month == 6)
       {
        sb.append ("Haziran");
       }
       else if (month == 7)
       {
        sb.append ("Temmuz");
       }
       else if (month == 8)
       {
        sb.append ("A\u011Fustos");
       }
       else if (month == 9)
       {
        sb.append ("Eyl\u00FCl");
       }
       else if (month == 10)
       {
        sb.append ("Ekim");
       }
       else if (month == 11)
       {
        sb.append ("Kas\u0131m");
       }
       else if (month == 12)
       {
        sb.append ("Aral\u0131k");
       }
       else
       {}
       
       sb.append (" ");
             
       String yearstr=Integer.toString (myear);
       sb.append (yearstr);
       
       sb.append ("; ");
       
       //tarih al, getdate veya gettime, hangisi ise artik
       //date'yi toString et
       //icindeki gun ifadesi MON ise Pazartesi, TUE ise Sali gibi sb'ye ekle
       
       int r=cal.get (Calendar.DAY_OF_WEEK);
       
       if (r == 1)
       {
    	    sb.append ("Pazar");
       }
       else if (r == 2)
       {
    	    sb.append ("Pazartesi");
       }
       else if (r == 3)
       {
    	    sb.append ("Sal\u0131");
       }
       else if (r == 4)
       {
    	    sb.append ("\u00C7ar\u015Famba");
       }
       else if (r == 5)
       {
    	    sb.append ("Per\u015Fembe");
       }
       else if (r == 6)
       {
    	    sb.append ("Cuma");
       }
       else if (r == 7)
       {
    	    sb.append ("Cumartesi");
       }
       else
       {}
       
       //sb.append (Integer.toString (r));
       
       String s=sb.toString ();
       
       return s;
    }//end method getDayNameTurkish
    
    final
    public static void main (final String [] args)
    {
	if (args.length < 1)
	{
	    System.out.println ("Example:\n\tjava FindTarih 07.02.1976");
	    System.exit (-1);
	}
	
	String s=args [0];
	System.out.println (s);
	System.out.println ("");
	
	String [] strs=s.split ("\\.");
	if (strs == null) System.exit (-1);
	if (strs.length < 3) System.exit (-1);
	
	String sday=strs [0];
	sday=sday.trim ();
	if (sday.startsWith ("0")) sday=sday.substring (1);
	int day=-1;
	
	String smonth=strs [1];
	smonth=smonth.trim ();
	if (smonth.startsWith ("0")) smonth=smonth.substring (1);
	int month=-1;
	
	String syear=strs [2];
	syear=syear.trim ();
	int year=-1;
	
	try
	{
	    day=Integer.parseInt (sday);
	    month=Integer.parseInt (smonth);
	    year=Integer.parseInt (syear);
	}
	catch (NumberFormatException nfe)
	{
	    nfe.printStackTrace ();
	    System.exit (-1);
	}
	
	--month;
	
	s=getDayNameTurkish (day, month, year);
	System.out.println (s);
	
	System.exit (0);
    }
    
}//end class
