package br.algorithms;

import java.util.ArrayList;
import java.util.List;

public class ModelChecker {

	private String[] states;
	private boolean st;

	private static final String atomic_proposition = null;
	private static int f;

	private boolean marked[][]; // l - num de estados do espaco de estados c -
								// tam da formula CTL a ser avaliada

	private boolean info[][]; // l- num de estados do espaco de estados e c -
								// tam da formula CTL a ser avalaida

	private List<int[]> pi = new ArrayList<int[]>(); // armazena o
														// resultado da
														// avaliacao da
														// formula indicada
	private boolean _continue[]; // serve de flag p/ controlar a aavaliacao da
									// formula indicada pelo indice do array

	public ModelChecker() {

	}

	/**
	 * @param marked
	 *            : boolean multidimentional array, which "l" indicates the
	 *            current state and "c" indicates the size of CTL formula
	 * @param info
	 *            : boolean multidimensional arrey used to store information
	 *            related if the state was visited or not by a CTL subformula
	 * @param _continue
	 *            : is a boolean array with "p" positions where "p" is the size
	 *            of CTL formula.
	 * @param pi
	 *            int list array with "p" positions where "p" is the CTL formula
	 *            size to be evaluated. Each position stores the states which
	 *            are examples and counter-examples of the formula indicated by
	 *            each position
	 * */
	public ModelChecker(boolean[][] marked, boolean[][] info,
			boolean[] _continue, List<int[]> pi) {

		this.setMarked(marked);
		this.setInfo(info);
		this.set_continue(_continue);
		this.setPi(pi);

	}

	public boolean check(String f, int s, int i_f) {

		String f_type = null;

		if (marked[s][i_f])
			return info[s][i_f];

		f_type = this.formulaType(f);
		_continue[i_f] = true;

		if (f_type == "AP") {
			checkAP(f, s, i_f);
		} else if (f_type == "NOT") {
			checkNOT(f, s, i_f);
		}

		return info[s][i_f];
	}

	public String formulaType(String f) {

		if (f.startsWith("AP")) {
			return "AP";
		} else if (f.startsWith("¬")) {
			return "NOT";
		} else if (f.startsWith("^")) {
			return "AND";
		} else if (f.startsWith("v")) {
			return "OR";
		} else if (f.startsWith("AG")) {
			return "AG";
		} else if (f.startsWith("AX")) {
			return "AX";
		} else if (f.startsWith("EU")) {
			return "EU";
		} else if (f.startsWith("EG")) {
			return "EG";
		}
		return null;
	}

	public void checkAP(String f, int s, int i_f) {

	}

	public void checkNOT(String f, int s, int i_f) {

	}

	/*-------------------getters and setters------------------------------------*/
	public boolean[][] getMarked() {
		return marked;
	}

	public void setMarked(boolean[][] marked) {
		this.marked = marked;
	}

	public boolean[][] getInfo() {
		return info;
	}

	public void setInfo(boolean[][] info) {
		this.info = info;
	}

	public List<int[]> getPi() {
		return pi;
	}

	public void setPi(List<int[]> pi) {
		this.pi = pi;
	}

	public boolean[] get_continue() {
		return _continue;
	}

	public void set_continue(boolean[] _continue) {
		this._continue = _continue;
	}
	/*---------------------------------------------------------------------------*/

}