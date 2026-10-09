package net.murat.sures;

final
public class Sure_104
extends Object
implements java.io.Serializable, KSure
{

	private String allSure="";

	private final String [] ayts =
	{
		"\u0628\u0633\u0645 \u0627\u0644\u0644\u0647 \u0627\u0644\u0631\u062D\u0645\u0646 \u0627\u0644\u0631\u062D\u064A\u0645",
		"\u0648\u064A\u0644 \u0644\u0643\u0644\u0651 \u0647\u0645\u0632\u0629 \u0644\u0645\u0632\u0629 (\u0661)",
		"\u0627\u0644\u0651\u0630\u064A \u062C\u0645\u0639 \u0645\u0627\u0644\u0627 \u0648\u0639\u062F\u0651\u062F\u0647 (\u0662)",
		"\u064A\u062D\u0633\u0628 \u0627\u0646\u0651 \u0645\u0627\u0644\u0647\u0653 \u0627\u062E\u0644\u062F\u0647 (\u0663)",
		"\u0643\u0644\u0651\u0627 \u0644\u064A\u0646\u0628\u0630\u0646\u0651 \u0641\u064A \u0627\u0644\u062D\u0637\u0645\u0629 (\u0664)",
		"\u0648\u0645\u0653\u0627 \u0627\u062F\u0631\u0670\u064A\u0643 \u0645\u0627 \u0627\u0644\u062D\u0637\u0645\u0629 (\u0665)",
		"\u0646\u0627\u0631 \u0627\u0644\u0644\u0651\u0670\u0647 \u0627\u0644\u0645\u0648\u0642\u062F\u0629 (\u0666)",
		"\u0627\u0644\u0651\u062A\u064A \u062A\u0637\u0651\u0644\u0639 \u0639\u0644\u0649 \u0627\u0644\u0627\u0641\u0640\u0654\u062F\u0629 (\u0667)",
		"\u0627\u0646\u0651\u0647\u0627 \u0639\u0644\u064A\u0647\u0645 \u0645\u0624\u0635\u062F\u0629 (\u0668)",
		"\u0641\u064A \u0639\u0645\u062F \u0645\u0645\u062F\u0651\u062F\u0629 (\u0669)",
	};

	public Sure_104 () {}

	public String getName ()
	{
		return "Humeze";
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
