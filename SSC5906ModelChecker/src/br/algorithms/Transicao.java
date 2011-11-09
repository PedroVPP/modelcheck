package br.algorithms;

import br.mef.State;

public class Transicao {
	private State origem;
	private State destino;
	
	public State getOrigem() {
		return origem;
	}
	public void setOrigem(State origem) {
		this.origem = origem;
	}
	public State getDestino() {
		return destino;
	}
	public void setDestino(State destino) {
		this.destino = destino;
	}
	public String toString() {
		return origem + " -> " + destino;
	}
}
