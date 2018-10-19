package discretemaths.rules;

import discretemaths.Proof;
import discretemaths.forms.Form;
import discretemaths.forms.Implies;
import discretemaths.forms.Not;

public class NotI extends Rule{

	private int impLine1;
	private int impLine2;
	
	public NotI(int impLine1, int impLine2)
	{
		this.impLine1 = impLine1;
		this.impLine2 = impLine2;
	}
	
	public Form evaluate(Proof proof) throws Exception
	{
		int[] lines = {impLine1, impLine2};
		if (checkSubHyp(proof, lines)){
			throw new InvalidRuleException("Trying to use line(s) from sub proof to outer proof");
		}

		Form imp1 = proof.refer(impLine1);
		Form imp2 = proof.refer(impLine2);
		
		if (imp1.getClass() != Implies.class)
			throw new InvalidRuleException("NotI - Did not find an implication at line "+ impLine1);
		else if (imp2.getClass() != Implies.class)
			throw new InvalidRuleException("NotI - Did not find an implication at line "+ impLine2);
		else if (!((Implies)imp1).getLeft().equals(((Implies)imp2).getLeft()))
			throw new InvalidRuleException("NotI - Mismatch in implications at lines "+ impLine1 + "," + impLine2);
		else if (((Implies)imp2).getRight().getClass() != Not.class)
			throw new InvalidRuleException("NotI - Not expected in right side of implication at line "+ impLine2);
		else if (!((Implies)imp1).getRight().equals(((Not)((Implies)imp2).getRight()).getSub()))
			throw new InvalidRuleException("NotI - Mismatch in right side of implications at lines "+ impLine1 + "," + impLine2);
		else if (imp1.hasSubt())
			throw new InvalidRuleException("NotI - Don't know how to handle substitution "+ impLine1);
		else if (imp2.hasSubt())
			throw new InvalidRuleException("NotI - Don't know how to handle substitution "+ impLine2);
		else
			return new Not(((Implies)imp1).getLeft().clone());
				 
	}
	
	public String toString()
	{
		return impLine1 + "," + impLine2 + ", Not Introduction";
	}
	
}










