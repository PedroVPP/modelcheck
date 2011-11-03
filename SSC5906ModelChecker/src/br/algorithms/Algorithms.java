package br.algorithms;

import java.util.ArrayList;
import java.util.Iterator;

import br.mef.Expression;
import br.mef.Property;
import br.mef.State;

public class Algorithms {

	public static boolean Alg_E(State st, Expression exp1, Expression exp2) {
		boolean resposta = false;
		// algoritmo para verificar se uma express�o e � v�lida
		return resposta;
	}

	public static boolean Alg_Id(State st, Expression exp1) {
		boolean resposta = false;
		// algoritmo para verificar se uma propriedade � v�lida em um estado
		// se o type dela for id, o name ser� uma propriedade...
		ArrayList<Property> properties = st.getValidProperties();
		for (int i = 0; i < properties.size(); ++i) {
			Property prop = (Property) properties.get(i);
			if (prop.getName().equals(exp1.getName())) {
				resposta = true;
				// st.addLabel(exp.getLabel());
				// ficou faltando a parte de associar um label a uma express�o
				// acho q daria certo se no icmcJCM.jj criasse um atributo
				// global
				// do tipo inteiro q fosse sendo incrementado sempre que uma
				// nova
				// express�o � criada e setando essa vari�vel no label
				// (expression.setLAbel());
				// ai, nesse trecho adicionaria o label da express�o
				// depois, para verificar se a express�o � v�lida em um
				// determinado
				// estado, bastaria verificar se o estado possui o label
				break;
			}
		}
		return resposta;
	}

	/**
	 * @author Pedro Pinheiro
	 * @param states
	 *            Consiste do array de estados da MEF inteira.
	 * @param expression
	 *            Consiste da expressao que se deseja verificar se e'
	 *            verdadeira.
	 * @return retorna os estados em que a expressao fornecida e' valida
	 * @throws Exception
	 */
	public static ArrayList<State> OR(ArrayList<State> states,
			Expression expression) {
		/*
		 * Ex: Expressao: p OR q
		 * 
		 * Expression 1 name = p OR q exp1 = p exp2 = q
		 * 
		 * Expression 2 name = p exp1 = null exp2 = null
		 * 
		 * Expression 3 name = q exp1 = null exp2 = null
		 */

		// Expression expression = "p OR q"
		Expression expression1 = expression.getExp1(); // = p
		Expression expression2 = expression.getExp2(); // = q
		ArrayList<State> validStates = new ArrayList<State>();

		for (Iterator iterator = states.iterator(); iterator.hasNext();) {
			State state = (State) iterator.next();

			if (state.getLabelsString().contains(expression1.getName())
					|| state.getLabelsString().contains(expression2.getName())) {
				// adiciona o label pois a expressao e verdadeira
				state.addLabelsString(expression.getName());
				validStates.add(state);
			}

		}
		return validStates;
	}

	/**
	 * @author Pedro Pinheiro
	 * @param state
	 *            Consiste do estado especifico da MEF em que se deseja
	 *            verificar se uma expressao e' verdadeira
	 * @param expression
	 *            Consiste da expressao que se deseja verificar se e'
	 *            verdadeira.
	 * @return retorna true se a expressao e' verdadeira para aquele estado, e
	 *         caso contrario retorna 'false'
	 * @throws Exception
	 */
	public static boolean OR(State state, Expression expression)
			throws Exception {
		// Expression expression = "p OR q"
		Expression expression1 = expression.getExp1(); // = p
		Expression expression2 = expression.getExp2(); // = q
		if (state.getLabelsString().contains(expression1)
				|| state.getLabelsString().contains(expression2)) {
			// adiciona o label pois a expressao e verdadeira
			state.addLabelsString(expression.getName());
			return true;
		} else {
			return false;
		}
	}

	// TODO: Fazer um método que de pra percorrer a MEF toda através de
	// getChild...

	public static boolean NOT(ArrayList<State> states, Expression expression) {
		return true;
	}

	public static boolean EG(ArrayList<State> states, Expression expression) {
		return true;
	}

	public static boolean EF(ArrayList<State> states, Expression expression) {
		return true;
	}
}
