package br.mef;

import java.util.ArrayList;
import java.util.List;

public class CTLFormula {

	private List<String> ctlExpression;
	private List<State> states;

	public CTLFormula(List<State> states) {
		ctlExpression = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < states.size(); i++) {
			for (int j = 0; j < states.get(i).getValidProperties().size(); j++) {
				if (states.get(i).getValidProperties().size() < j) {
					sb.append(states.get(i).getValidProperties().get(i)
							.getName()
							+ ",");
				} else {
					sb.append(states.get(i).getValidProperties().get(i)
							.getName());
				}
			}
			ctlExpression.add(states.get(i).getName() + " " + sb.toString());
		}
	}

	public CTLFormula() {
		ctlExpression = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < states.size(); i++) {
			for (int j = 0; j < states.get(i).getValidProperties().size(); j++) {
				if (states.get(i).getValidProperties().size() < j) {
					sb.append(states.get(i).getValidProperties().get(i)
							.getName()
							+ ",");
				} else {
					sb.append(states.get(i).getValidProperties().get(i)
							.getName());
				}
			}
			ctlExpression.add(states.get(i).getName() + " " + sb.toString());
		}
	}

	public List<String> getCTLExpression() {
		return this.ctlExpression;
	}

	public void setCTLExpression(List<String> ctlExpression) {
		this.ctlExpression = ctlExpression;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

}