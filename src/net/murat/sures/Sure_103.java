package net.murat.sures;

final
public class Sure_103
extends Object
implements java.io.Serializable, KSure
{

	private String allSure="";

	private final String [] ayts =
	{
		"\u0628\u0633\u0645 \u0627\u0644\u0644\u0647 \u0627\u0644\u0631\u062D\u0645\u0646 \u0627\u0644\u0631\u062D\u064A\u0645",
		"\u0648\u0627\u0644\u0639\u0635\u0631 (\u0661)",
		"\u0627\u0646\u0651 \u0627\u0644\u0627\u0646\u0633\u0627\u0646 \u0644\u0641\u064A \u062E\u0633\u0631 (\u0662)",
		"\u0627\u0644\u0651\u0627 \u0627\u0644\u0651\u0630\u064A\u0646 \u0627\u0670\u0645\u0646\u0648\u0627 \u0648\u0639\u0645\u0644\u0648\u0627 \u0627\u0644\u0635\u0651\u0627\u0644\u062D\u0627\u062A \u0648\u062A\u0648\u0627\u0635\u0648\u0627 \u0628\u0627\u0644\u062D\u0642\u0651 \u0648\u062A\u0648\u0627\u0635\u0648\u0627 \u0628\u0627\u0644\u0635\u0651\u0628\u0631 (\u0663)",
	};

	public Sure_103 () {}

	public String getName ()
	{
		return "Asr";
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
