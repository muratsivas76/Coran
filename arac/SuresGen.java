import java.io.*;

public class SuresGen
extends Object
implements Serializable
{

    private SuresGen ()
    {
	System.exit (-1);
    }
    
    private static final void gen ()
    throws IOException
    {
	File fd=new File ("kuran_harekesiz.txt");
	InputStream fis=new FileInputStream (fd);
	Reader isr=new InputStreamReader (fis, "UTF-8");
	BufferedReader br=new BufferedReader (isr);
	
	final String input="\u0628\u0633\u0645 \u0627\u0644\u0644\u0647";
	
	String line=null;
	String xline="";
	
//	while ( (line=br.readLine ()) != null)
//	{
//	    xline=line.trim ();
//	    
//	    if (xline.startsWith (input) == false) continue;
//	}
	
	int syc=-1;
	
	String fnum=String.format ("%03d", (++syc));
	
	File fs=new File ("orijsures/sr_"+(fnum)+".txt");
	OutputStream fos=new FileOutputStream (fs);
	PrintStream ps=new PrintStream (fos, true, "UTF-8");
	
	System.out.println (fs.getName ());
	
//	ps.println (xline);
	
	while ( (line=br.readLine ()) != null)
	{
	    xline=line.trim ();
	    
	    int xlen=xline.length ();
	    
	    if (xlen < 1) continue;
	    
	    if ( (xline.length () < 6) &&
		 (xline.indexOf ("(") >= 0x0000) &&
		 (xline.indexOf (")") >= 0x0000)
	       )
	    {
		ps.flush ();  ps.close ();
		fos.flush (); fos.close ();
		
		fnum=String.format ("%03d", (++syc));
	
		fs=new File ("orijsures/sr_"+(fnum)+".txt");
		fos=new FileOutputStream (fs);
		ps=new PrintStream (fos, true, "UTF-8");
		
		System.out.println (fs.getName ());
		br.readLine (); //skip sure name
		continue;
	    }
	    
	    ps.println (line);
	}
	
	br.close ();
	isr.close ();
	fis.close ();
	
	ps.flush ();  ps.close ();
	fos.flush (); fos.close ();
	
	return;
    }
    
    public static final void main (final String [] args)
    {
	try
	{
	    gen ();
	}
	catch (IOException ioe)
	{
	    ioe.printStackTrace ();
	    System.exit (-1);
	}
	
	System.exit (0);
    }
}
