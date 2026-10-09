import java.io.*;

public class Ren
{

    public static void main (String [] args)
    throws IOException
    {
	//mv -fv src/net/murat/sures/Sure20.java src/net/murat/sures/Sure_020.java
	PrintStream ps=new PrintStream (new FileOutputStream (new File ("ren.sh")), true, "UTF-8");
	
	for (int i=22; i<115; i++)
	{
	    ps.println ("mv -fv src/net/murat/sures/Sure"+i+".java src/net/murat/sures/Sure_"+(String.format ("%03d", i))+".java");
	}
	
	ps.flush ();
	ps.close ();
	
	System.out.println ("ren.sh");
	
	System.exit (0x0000);
    }
    
}
