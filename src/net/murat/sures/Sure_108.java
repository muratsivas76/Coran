package net.murat.sures;

final
public class Sure_108
extends Object
implements java.io.Serializable, KSure
{

	private String allSure="";

	private final String [] ayts =
	{
		"\u0628\u0633\u0645 \u0627\u0644\u0644\u0647 \u0627\u0644\u0631\u062D\u0645\u0646 \u0627\u0644\u0631\u062D\u064A\u0645",
		"\u0627\u0646\u0651\u0653\u0627 \u0627\u0639\u0637\u064A\u0646\u0627\u0643 \u0627\u0644\u0643\u0648\u062B\u0631 (\u0661)",
		"\u0641\u0635\u0644\u0651 \u0644\u0631\u0628\u0651\u0643 \u0648\u0627\u0646\u062D\u0631 (\u0662)",
		"\u0627\u0646\u0651 \u0634\u0627\u0646\u0626\u0643 \u0647\u0648 \u0627\u0644\u0627\u0628\u062A\u0631 (\u0663)",
	};

	public Sure_108 () {}

	public String getName ()
	{
		return "Kevser";
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
