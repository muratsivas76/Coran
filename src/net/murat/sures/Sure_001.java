package net.murat.sures;

final
public class Sure_001
extends Object
implements java.io.Serializable, KSure
{

	private String allSure="";

	private final String [] ayts =
	{
		"\u0628\u0633\u0645 \u0627\u0644\u0644\u0647 \u0627\u0644\u0631\u062D\u0645\u0646 \u0627\u0644\u0631\u062D\u064A\u0645 (\u0661)",
		"\u0627\u0644\u062D\u0645\u062F \u0644\u0644\u0651\u0670\u0647 \u0631\u0628\u0651 \u0627\u0644\u0639\u0627\u0644\u0645\u064A\u0646 (\u0662)",
		"\u0627\u0644\u0631\u0651\u062D\u0645\u0670\u0646 \u0627\u0644\u0631\u0651\u062D\u064A\u0645 (\u0663)",
		"\u0645\u0627\u0644\u0643 \u064A\u0648\u0645 \u0627\u0644\u062F\u0651\u064A\u0646 (\u0664)",
		"\u0627\u064A\u0651\u0627\u0643 \u0646\u0639\u0628\u062F \u0648\u0627\u064A\u0651\u0627\u0643 \u0646\u0633\u062A\u0639\u064A\u0646 (\u0665)",
		"\u0627\u0647\u062F\u0646\u0627 \u0627\u0644\u0635\u0651\u0631\u0627\u0637 \u0627\u0644\u0645\u0633\u062A\u0642\u064A\u0645 (\u0666)",
		"\u0635\u0631\u0627\u0637 \u0627\u0644\u0651\u0630\u064A\u0646 \u0627\u0646\u0639\u0645\u062A \u0639\u0644\u064A\u0647\u0645 \u063A\u064A\u0631 \u0627\u0644\u0645\u063A\u0636\u0648\u0628 \u0639\u0644\u064A\u0647\u0645 \u0648\u0644\u0627 \u0627\u0644\u0636\u0651\u0653\u0627\u0644\u0651\u064A\u0646 (\u0667)",
	};

	public Sure_001 () {}

	public String getName ()
	{
		return "Fatiha";
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
