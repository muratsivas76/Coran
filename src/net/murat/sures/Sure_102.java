package net.murat.sures;

final
public class Sure_102
extends Object
implements java.io.Serializable, KSure
{

	private String allSure="";

	private final String [] ayts =
	{
		"\u0628\u0633\u0645 \u0627\u0644\u0644\u0647 \u0627\u0644\u0631\u062D\u0645\u0646 \u0627\u0644\u0631\u062D\u064A\u0645",
		"\u0627\u0644\u0647\u0670\u064A\u0643\u0645 \u0627\u0644\u062A\u0651\u0643\u0627\u062B\u0631 (\u0661)",
		"\u062D\u062A\u0651\u0670\u0649 \u0632\u0631\u062A\u0645 \u0627\u0644\u0645\u0642\u0627\u0628\u0631 (\u0662)",
		"\u0643\u0644\u0651\u0627 \u0633\u0648\u0641 \u062A\u0639\u0644\u0645\u0648\u0646 (\u0663)",
		"\u062B\u0645\u0651 \u0643\u0644\u0651\u0627 \u0633\u0648\u0641 \u062A\u0639\u0644\u0645\u0648\u0646 (\u0664)",
		"\u0643\u0644\u0651\u0627 \u0644\u0648 \u062A\u0639\u0644\u0645\u0648\u0646 \u0639\u0644\u0645 \u0627\u0644\u064A\u0642\u064A\u0646 (\u0665)",
		"\u0644\u062A\u0631\u0648\u0646\u0651 \u0627\u0644\u062C\u062D\u064A\u0645 (\u0666)",
		"\u062B\u0645\u0651 \u0644\u062A\u0631\u0648\u0646\u0651\u0647\u0627 \u0639\u064A\u0646 \u0627\u0644\u064A\u0642\u064A\u0646 (\u0667)",
		"\u062B\u0645\u0651 \u0644\u062A\u0633\u0640\u0654\u0644\u0646\u0651 \u064A\u0648\u0645\u0626\u0630 \u0639\u0646 \u0627\u0644\u0646\u0651\u0639\u064A\u0645 (\u0668)",
	};

	public Sure_102 () {}

	public String getName ()
	{
		return "Tekasur";
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
