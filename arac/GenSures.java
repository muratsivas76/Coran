import java.io.*;

import java.util.Vector;

public class GenSures
{

    public static void main (String [] args)
    throws IOException
    {
	BufferedReader br=new BufferedReader (new FileReader (new File ("arac/salt_isimler.txt")));
	Vector <String> vec=new Vector <String> ();
	
	String line=null;
	
	while ( (line=br.readLine ()) != null)
	{
	    vec.add (line);
	}
	
	br.close ();
	
	final int vsize=vec.size ();
	
	System.out.println ("Size: "+vsize+"");
	
	if (vsize != 114)
	{
	    System.out.println ("Not 114 Error.");
	    System.exit (-1);
	}
	
	for (int i=1; i<115; i++)
	{
	    String fnum=(String.format ("%03d", i));
	    
	    String ss=(("src/net/murat/sures/Sure_")+(fnum)+(".java"));
	    
	    File ff=new File (ss);
	    
	    PrintStream ps=new PrintStream (new FileOutputStream (ff), true, "UTF-8");
	
		ps.println ("package net.murat.sures;");
		ps.println ("");
		ps.println ("final");
		ps.println ("public class Sure_"+fnum+"");
		ps.println ("extends Object");
		ps.println ("implements java.io.Serializable, KSure");
		ps.println ("{");
		ps.println ("");
		ps.println ("\tprivate String allSure=\"\";");
		ps.println ("");
		ps.println ("\tprivate final String [] ayts =");
		ps.println ("\t{");
		
		///////////
		InputStream ydfis=new FileInputStream ("arac/unisures/sr_"+fnum+".txt");
		Reader ydisr=new InputStreamReader (ydfis, "UTF-8");
		BufferedReader ydbr=new BufferedReader (ydisr);
		
		String ydline=null;
		String ydlinex="";
		
		while ( (ydline=ydbr.readLine ()) != null)
		{
		    ydlinex=ydline.trim ();
		    if (ydlinex.length () < 1) continue;
		    
		    ps.println ("\t\t"+"\""+ydlinex+"\","+"");
		}
		
		ydbr.close ();
		ydisr.close ();
		ydfis.close ();
		///////////
		
		ps.println ("\t};");
		ps.println ("");
		ps.println ("\tpublic Sure_"+fnum+" () {}");
		ps.println ("");
		ps.println ("\tpublic String getName ()");
		ps.println ("\t{");
		ps.println ("\t\treturn \""+vec.get (i-1)+"\""+";");
		ps.println ("\t}");
		ps.println ("");
		ps.println ("\tpublic void generate ()");
		ps.println ("\t{");
		ps.println ("\t\tStringBuffer sb=new StringBuffer ();");
		ps.println ("");
		ps.println ("\t\tfinal int len=ayts.length;");
		ps.println ("");
		ps.println ("\t\tfor (int i=KSure.CERO; i<len; i++)");
		ps.println ("\t\t{");
		ps.println ("\t\t\tsb.append (ayts [i]);");
		ps.println ("\t\t\tsb.append (KSure.NL);");
		ps.println ("\t\t}");
		ps.println ("");
		ps.println ("\t\tString rtt=sb.toString ();");
		ps.println ("");
		ps.println ("\t\tallSure=rtt;");
		ps.println ("\t}");
		ps.println ("");
		ps.println ("\tpublic String getAllSure ()");
		ps.println ("\t{");
		ps.println ("\t\treturn allSure;");
		ps.println ("\t}");
		ps.println ("");
		ps.println ("\tpublic String toString ()");
		ps.println ("\t{");
		ps.println ("\t\treturn (getName ());");
		ps.println ("\t}");
		ps.println ("");
		ps.println ("}//class end");

	    ps.flush ();
	    ps.close ();
	
	    System.out.println (ff.getName ());
	}
	
	System.exit (0x0000);
    }
    
}
