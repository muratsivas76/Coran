package net.murat.sures;

final
public class Sure_114
extends Object
implements java.io.Serializable, KSure
{

	private String allSure="";

	private final String [] ayts =
	{
		"\u0628\u0633\u0645 \u0627\u0644\u0644\u0647 \u0627\u0644\u0631\u062D\u0645\u0646 \u0627\u0644\u0631\u062D\u064A\u0645",
		"\u0642\u0644 \u0627\u0639\u0648\u0630 \u0628\u0631\u0628\u0651 \u0627\u0644\u0646\u0651\u0627\u0633 (\u0661)",
		"\u0645\u0644\u0643 \u0627\u0644\u0646\u0651\u0627\u0633 (\u0662)",
		"\u0627\u0644\u0670\u0647 \u0627\u0644\u0646\u0651\u0627\u0633 (\u0663)",
		"\u0645\u0646 \u0634\u0631\u0651 \u0627\u0644\u0648\u0633\u0648\u0627\u0633 \u0627\u0644\u062E\u0646\u0651\u0627\u0633 (\u0664)",
		"\u0627\u0644\u0651\u0630\u064A \u064A\u0648\u0633\u0648\u0633 \u0641\u064A \u0635\u062F\u0648\u0631 \u0627\u0644\u0646\u0651\u0627\u0633 (\u0665)",
		"\u0645\u0646 \u0627\u0644\u062C\u0646\u0651\u0629 \u0648\u0627\u0644\u0646\u0651\u0627\u0633 (\u0666)",
	};

	public Sure_114 () {}

	public String getName ()
	{
		return "Nas";
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
