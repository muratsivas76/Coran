import java.io.*;

public class BDizi
{

    public static void main (String [] args)
    throws IOException
    {
	//mv -fv src/net/murat/sures/Sure20.java src/net/murat/sures/Sure_020.java
	PrintStream ps=new PrintStream (new FileOutputStream (new File ("bdizi.txt")), true, "UTF-8");
	
	for (int i=1; i<115; i++)
	{
	    ps.print ("new Sure_"+(String.format ("%03d", i))+"(), ");
	    if ((i%4) == 0x000)
	    {
		ps.print ("\n");
	    }
	}
	
	ps.flush ();
	ps.close ();
	
	System.out.println ("bdizi.txt");
	
	System.exit (0x0000);
    }
    
}
