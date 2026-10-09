package net.murat.sures;

final
public class Sure_112
extends Object
implements java.io.Serializable, KSure
{

	private String allSure="";

	private final String [] ayts =
	{
		"\u0628\u0633\u0645 \u0627\u0644\u0644\u0647 \u0627\u0644\u0631\u062D\u0645\u0646 \u0627\u0644\u0631\u062D\u064A\u0645",
		"\u0642\u0644 \u0647\u0648 \u0627\u0644\u0644\u0651\u0670\u0647 \u0627\u062D\u062F (\u0661)",
		"\u0627\u0644\u0644\u0651\u0670\u0647 \u0627\u0644\u0635\u0651\u0645\u062F (\u0662)",
		"\u0644\u0645 \u064A\u0644\u062F \u0648\u0644\u0645 \u064A\u0648\u0644\u062F (\u0663)",
		"\u0648\u0644\u0645 \u064A\u0643\u0646 \u0644\u0647 \u0643\u0641\u0648\u0627 \u0627\u062D\u062F (\u0664)",
	};

	public Sure_112 () {}

	public String getName ()
	{
		return "Ihlas";
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
