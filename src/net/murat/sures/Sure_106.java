package net.murat.sures;

final
public class Sure_106
extends Object
implements java.io.Serializable, KSure
{

	private String allSure="";

	private final String [] ayts =
	{
		"\u0628\u0633\u0645 \u0627\u0644\u0644\u0647 \u0627\u0644\u0631\u062D\u0645\u0646 \u0627\u0644\u0631\u062D\u064A\u0645",
		"\u0644\u0627\u064A\u0644\u0627\u0641 \u0642\u0631\u064A\u0634 (\u0661)",
		"\u0627\u064A\u0644\u0627\u0641\u0647\u0645 \u0631\u062D\u0644\u0629 \u0627\u0644\u0634\u0651\u062A\u0653\u0627\u0621 \u0648\u0627\u0644\u0635\u0651\u064A\u0641 (\u0662)",
		"\u0641\u0644\u064A\u0639\u0628\u062F\u0648\u0627 \u0631\u0628\u0651 \u0647\u0670\u0630\u0627 \u0627\u0644\u0628\u064A\u062A (\u0663)",
		"\u0627\u0644\u0651\u0630\u0653\u064A \u0627\u0637\u0639\u0645\u0647\u0645 \u0645\u0646 \u062C\u0648\u0639 \u0648\u0627\u0670\u0645\u0646\u0647\u0645 \u0645\u0646 \u062E\u0648\u0641 (\u0664)",
	};

	public Sure_106 () {}

	public String getName ()
	{
		return "Kureys";
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
