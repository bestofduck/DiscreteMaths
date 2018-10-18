package discretemaths.rules;

import discretemaths.Proof;
import discretemaths.forms.Form;
import discretemaths.forms.Not;

public class NotE extends Rule{

	private int line;
	
	public NotE(int line)
	{
		this.line = line;
	}
	
	public Form evaluate(Proof proof) throws Exception
	{
		int[] lines = {line};
		if (checkSubHyp(proof, lines)){
			throw new InvalidRuleException("Trying to use line(s) from sub proof to outer proof");
		}

		Form p = proof.refer(line);
		if (p.getClass() != Not.class)
			throw new InvalidRuleException("NotE - Did not find a negation at line " + line);
		else if (((Not)p).getSub().getClass() != Not.class)
			throw new InvalidRuleException("NotE - Did not find double negation at line " + line);
		else if (p.hasSubt())
			throw new InvalidRuleException("NotE - Don't know how to handle substitution "+ line);
		else
			return ((Not)((Not)p).getSub()).getSub().clone();	 
	}
	
	public String toString()
	{
		return line + ", Not Elimination";
	}
	
}










