import java.io.*;

public class Unicdchr
{

    private static String convert (String input) 
    {
        StringBuffer msb=new StringBuffer ();
        
        final int len=input.length ();
        char chr='v';
        
        for (int i=0; i<len; i++)
        {
    	    chr=input.charAt (i);
    	    if (chr == ((char)(32)))
    	    {
    		msb.append (" ");
    		continue;
    	    }
    	
    	    if (chr == ((char)(40)))
    	    {
    		msb.append ("(");
    		continue;
    	    }
    	
    	    if (chr == ((char)(41)))
    	    {
    		msb.append (")");
    		continue;
    	    }
    	    
    	    if (Character.isDigit (chr))
    	    {
    		StringBuffer sbt=new StringBuffer ("\\u");
        
    	        String strt=String.format ("%04x", (int) (arab (chr)));
    	        strt=strt.toUpperCase ();
        
    	        sbt.append (strt);
        
    		strt=sbt.toString ();
    		msb.append (strt);
    		continue;
    	    }
    	    
    	    StringBuffer sb=new StringBuffer ("\\u");
        
    	    String str=String.format("%04x", (int) chr);
    	    str=str.toUpperCase ();
        
    	    sb.append (str);
        
    	    str=sb.toString ();
    	    msb.append (str);
        }
        
        return (msb.toString ());
    }
    
    private static char arab (char orig)
    {
	char ch=orig;
	
	if (orig == '0')
	{
	    ch='\u0660';
	}
	else if (orig == '1')
	{
	    ch='\u0661';
	}
	else if (orig == '2')
	{
	    ch='\u0662';
	}
	else if (orig == '3')
	{
	    ch='\u0663';
	}
	else if (orig == '4')
	{
	    ch='\u0664';
	}
	else if (orig == '5')
	{
	    ch='\u0665';
	}
	else if (orig == '6')
	{
	    ch='\u0666';
	}
	else if (orig == '7')
	{
	    ch='\u0667';
	}
	else if (orig == '8')
	{
	    ch='\u0668';
	}
	else if (orig == '9')
	{
	    ch='\u0669';
	}
	else
	{
	    ch=orig;
	}
	
	return ch;
    }
    
    private static void generate ()
    throws IOException
    {
	File f=new File ("orijsures");
	File [] files=f.listFiles ();
	final int len=files.length;
	
	File fd=null;
	
	for (int i=0; i<len; i++)
	{
	    fd=files [i];
	    hesap (fd);
	    System.out.println ("Wrote: "+(i+1)+"/"+len+": "+fd.getName ());
	}
    }
    
    private static void hesap (File fs)
    throws IOException
    {
	InputStream fis=new FileInputStream (fs);
	Reader isr=new InputStreamReader (fis, "UTF-8");
	BufferedReader br=new BufferedReader (isr);
	
	File rf=new File ("unisures/"+fs.getName ()+"");
	OutputStream fos=new FileOutputStream (rf);
	PrintStream ps=new PrintStream (fos, true, "UTF-8");
	
	String line=null;
	String uni="";
	
	while ( (line=br.readLine ()) != null )
	{
	    uni=convert (line.trim ());
	    ps.println (uni);
	}
	
	br.close ();
	isr.close ();
	fis.close ();
	
	ps.flush ();  ps.close ();
	fos.flush (); fos.close ();
	
	return;
    }
    
    public static void main(String[] args) 
    {
        //String input="\u0628\u0633\u0645 \u0627\u0644\u0644\u0647 \u0627\u0644\u0631";
        //String unicodeOutput = convertToUnicode (input);
        //System.out.println("Unicode Output: " + unicodeOutput);
        
        try
        {
    	    generate ();
        }
        catch (IOException ioe)
        {
    	    ioe.printStackTrace ();
    	    System.exit (-1);
        }
        
        System.exit (0);
    }
}
