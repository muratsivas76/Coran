package net.murat.sures;

final
public class Sure_109
extends Object
implements java.io.Serializable, KSure
{

	private String allSure="";

	private final String [] ayts =
	{
		"\u0628\u0633\u0645 \u0627\u0644\u0644\u0647 \u0627\u0644\u0631\u062D\u0645\u0646 \u0627\u0644\u0631\u062D\u064A\u0645",
		"\u0642\u0644 \u064A\u0653\u0627 \u0627\u064A\u0651\u0647\u0627 \u0627\u0644\u0643\u0627\u0641\u0631\u0648\u0646 (\u0661)",
		"\u0644\u0653\u0627 \u0627\u0639\u0628\u062F \u0645\u0627 \u062A\u0639\u0628\u062F\u0648\u0646 (\u0662)",
		"\u0648\u0644\u0653\u0627 \u0627\u0646\u062A\u0645 \u0639\u0627\u0628\u062F\u0648\u0646 \u0645\u0653\u0627 \u0627\u0639\u0628\u062F (\u0663)",
		"\u0648\u0644\u0653\u0627 \u0627\u0646\u0627 \u0639\u0627\u0628\u062F \u0645\u0627 \u0639\u0628\u062F\u062A\u0645 (\u0664)",
		"\u0648\u0644\u0653\u0627 \u0627\u0646\u062A\u0645 \u0639\u0627\u0628\u062F\u0648\u0646 \u0645\u0653\u0627 \u0627\u0639\u0628\u062F (\u0665)",
		"\u0644\u0643\u0645 \u062F\u064A\u0646\u0643\u0645 \u0648\u0644\u064A \u062F\u064A\u0646 (\u0666)",
	};

	public Sure_109 () {}

	public String getName ()
	{
		return "Kafirun";
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
