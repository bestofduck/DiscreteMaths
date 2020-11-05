package discretemaths.rules;

import discretemaths.Proof;
import discretemaths.forms.Form;
import discretemaths.forms.Implies;

public class ImpliesI extends Rule{

	private int startLine;
	private int endLine;
	
	
	public ImpliesI(int startLine, int endLine)
	{
		this.startLine = startLine;
		this.endLine = endLine;
	}
	
	public Form evaluate(Proof proof) throws Exception
	{
		Rule r = proof.getReason(startLine);
		if (r.getClass() != SubHyp.class)
			throw new InvalidRuleException("ImpliesI - Did not find an opening subhyp at "+ startLine);
		else if (!proof.getReason(endLine).isEnd())
			throw new InvalidRuleException("ImpliesI - Did not find end of subproof at "+ endLine + " (did you forget to call .end()?)");	
		else if (proof.getDepth(startLine) != proof.getCurrentDepth()+1)
			throw new InvalidRuleException("Trying to use line(s) from sub proof to outer proof"+ startLine);
		else
			return new Implies(proof.refer(startLine).clone(), proof.refer(endLine).clone());	 
	}
	
	public String toString()
	{
		return startLine + "-" + endLine + ", Implies Introduction";
	}
	
}










