package net.murat.sures;

final
public class Sure_105
extends Object
implements java.io.Serializable, KSure
{

	private String allSure="";

	private final String [] ayts =
	{
		"\u0628\u0633\u0645 \u0627\u0644\u0644\u0647 \u0627\u0644\u0631\u062D\u0645\u0646 \u0627\u0644\u0631\u062D\u064A\u0645",
		"\u0627\u0644\u0645 \u062A\u0631 \u0643\u064A\u0641 \u0641\u0639\u0644 \u0631\u0628\u0651\u0643 \u0628\u0627\u0635\u062D\u0627\u0628 \u0627\u0644\u0641\u064A\u0644 (\u0661)",
		"\u0627\u0644\u0645 \u064A\u062C\u0639\u0644 \u0643\u064A\u062F\u0647\u0645 \u0641\u064A \u062A\u0636\u0644\u064A\u0644 (\u0662)",
		"\u0648\u0627\u0631\u0633\u0644 \u0639\u0644\u064A\u0647\u0645 \u0637\u064A\u0631\u0627 \u0627\u0628\u0627\u0628\u064A\u0644 (\u0663)",
		"\u062A\u0631\u0645\u064A\u0647\u0645 \u0628\u062D\u062C\u0627\u0631\u0629 \u0645\u0646 \u0633\u062C\u0651\u064A\u0644 (\u0664)",
		"\u0641\u062C\u0639\u0644\u0647\u0645 \u0643\u0639\u0635\u0641 \u0645\u0623\u0643\u0648\u0644 (\u0665)",
	};

	public Sure_105 () {}

	public String getName ()
	{
		return "Fil";
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
