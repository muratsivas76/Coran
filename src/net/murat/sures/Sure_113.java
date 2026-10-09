package net.murat.sures;

final
public class Sure_113
extends Object
implements java.io.Serializable, KSure
{

	private String allSure="";

	private final String [] ayts =
	{
		"\u0628\u0633\u0645 \u0627\u0644\u0644\u0647 \u0627\u0644\u0631\u062D\u0645\u0646 \u0627\u0644\u0631\u062D\u064A\u0645",
		"\u0642\u0644 \u0627\u0639\u0648\u0630 \u0628\u0631\u0628\u0651 \u0627\u0644\u0641\u0644\u0642 (\u0661)",
		"\u0645\u0646 \u0634\u0631\u0651 \u0645\u0627 \u062E\u0644\u0642 (\u0662)",
		"\u0648\u0645\u0646 \u0634\u0631\u0651 \u063A\u0627\u0633\u0642 \u0627\u0630\u0627 \u0648\u0642\u0628 (\u0663)",
		"\u0648\u0645\u0646 \u0634\u0631\u0651 \u0627\u0644\u0646\u0651\u0641\u0651\u0627\u062B\u0627\u062A \u0641\u064A \u0627\u0644\u0639\u0642\u062F (\u0664)",
		"\u0648\u0645\u0646 \u0634\u0631\u0651 \u062D\u0627\u0633\u062F \u0627\u0630\u0627 \u062D\u0633\u062F (\u0665)",
	};

	public Sure_113 () {}

	public String getName ()
	{
		return "Felak";
	}

	public void generate ()
	{
		StringBuffer sb=new StringBuffer ();

		final int len=ayts.length;

		for (int i=KSure.CERO; i<len; i++)
		{
			sb.append (ayts [i]);
			sb.append (KSure.NL);
		}

		String rtt=sb.toString ();

		allSure=rtt;
	}

	public String getAllSure ()
	{
		return allSure;
	}

	public String toString ()
	{
		return (getName ());
	}

}//class end
