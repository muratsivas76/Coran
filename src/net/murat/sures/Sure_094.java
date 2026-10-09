package net.murat.sures;

final
public class Sure_094
extends Object
implements java.io.Serializable, KSure
{

	private String allSure="";

	private final String [] ayts =
	{
		"\u0628\u0633\u0645 \u0627\u0644\u0644\u0647 \u0627\u0644\u0631\u062D\u0645\u0646 \u0627\u0644\u0631\u062D\u064A\u0645",
		"\u0627\u0644\u0645 \u0646\u0634\u0631\u062D \u0644\u0643 \u0635\u062F\u0631\u0643 (\u0661)",
		"\u0648\u0648\u0636\u0639\u0646\u0627 \u0639\u0646\u0643 \u0648\u0632\u0631\u0643 (\u0662)",
		"\u0627\u0644\u0651\u0630\u0653\u064A \u0627\u0646\u0642\u0636 \u0638\u0647\u0631\u0643 (\u0663)",
		"\u0648\u0631\u0641\u0639\u0646\u0627 \u0644\u0643 \u0630\u0643\u0631\u0643 (\u0664)",
		"\u0641\u0627\u0646\u0651 \u0645\u0639 \u0627\u0644\u0639\u0633\u0631 \u064A\u0633\u0631\u0627 (\u0665)",
		"\u0627\u0646\u0651 \u0645\u0639 \u0627\u0644\u0639\u0633\u0631 \u064A\u0633\u0631\u0627 (\u0666)",
		"\u0641\u0627\u0630\u0627 \u0641\u0631\u063A\u062A \u0641\u0627\u0646\u0635\u0628 (\u0667)",
		"\u0648\u0627\u0644\u0670\u0649 \u0631\u0628\u0651\u0643 \u0641\u0627\u0631\u063A\u0628 (\u0668)",
	};

	public Sure_094 () {}

	public String getName ()
	{
		return "Insirah";
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
