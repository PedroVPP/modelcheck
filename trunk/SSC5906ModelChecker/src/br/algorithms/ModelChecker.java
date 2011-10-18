package br.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ModelChecker {

	private String[] states;
	private boolean st;

	private static final String atomic_proposition = null;
	private static int f;

	private boolean marked[][]; // l - num de estados do espaco de estados c -
								// tam da formula CTL a ser avaliada

	private boolean info[][]; // l- num de estados do espaco de estados e c -
								// tam da formula CTL a ser avalaida

	private ArrayList<int[]> pi = new ArrayList<int[]>(); // armazena o
															// resultado da
															// avaliacao da
															// formula indicada
	private boolean _continue[]; // serve de flag p/ controlar a aavaliacao da
									// formula indicada pelo indice do array

	public ModelChecker(){
		
		this.setMarked(new boolean[2][2]);
		this.setInfo(new boolean[1][1]);
		this.set_continue(new boolean[2]);
		this.setPi(new ArrayList<int[]>(){});
	}
	
	
	public boolean check(String f, int s, int i_f){
		
		if (marked[s][i_f]){
			return info[s][i_f];
		}
		return false;
		//...do something
	}

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

	public ArrayList<int[]> getPi() {
		return pi;
	}

	public void setPi(ArrayList<int[]> pi) {
		this.pi = pi;
	}

	public boolean[] get_continue() {
		return _continue;
	}

	public void set_continue(boolean[] _continue) {
		this._continue = _continue;
	}

}