package br.mef;

import java.util.List;

public class CTLFormula {

	private List<String> ctlExpression;

	public CTLFormula(List<String> ctlExpression) {
		this.ctlExpression = ctlExpression;
	}

	public List<String> getCTLExpression() {
		return this.ctlExpression;
	}

	public void setCTLExpression(List<String> ctlExpression) {
		this.ctlExpression = ctlExpression;
	}

}
