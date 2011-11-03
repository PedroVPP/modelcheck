package br.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.algorithms.Algorithms;
import br.mef.Expression;
import br.mef.Property;
import br.mef.State;

public class PedroTestCases extends Assert {

	@Test
	public void testOR() {
		ArrayList<State> states = new ArrayList<State>();
		// Essa MEF e suas propriedas é a mesma do exemplo do Adenilso nas suas aulas
		State s0 = new State("S0");
		State s1 = new State("S1");
		State s2 = new State("S2");
		State s3 = new State("S3");
		
		s0.addChild(s2);
		s0.addChild(s1);
		s0.addLabelsString("p");
		s0.addLabelsString("q");
		
		s1.addChild(s0);
		s1.addLabelsString("q");
		s1.addLabelsString("r");
		
		s2.addChild(s3);
		s2.addLabelsString("r");
		
		s3.addChild(s3);
		s3.addLabelsString("q");
		s3.addLabelsString("r");
		
		states.add(s0);
		states.add(s1);
		states.add(s2);
		states.add(s3);

		// criacao da arvore de derivacao da expressao
		Expression expression = new Expression("p OR q");
		expression.setExp1(new Expression("p"));
		expression.setExp2(new Expression("q"));
		expression.setType("OR");
		
		// Ainda não contém Assert
		ArrayList<State> validStates = Algorithms.OR(states, expression);

		// imprime todos os estados com seus respectivos labels e propriedades
		// A partir daqui é só System.out.print
		System.out.println("Expression: " + expression.getName());
		
		for (Iterator iterator = states.iterator(); iterator.hasNext();) {
			State state = (State) iterator.next();
			System.out.println("\nState: " + "\"" + state.getName() + "\"");
			ArrayList<String> labels = state.getLabelsString();
			System.out.println("Labels: ");
			for (Iterator iterator2 = labels.iterator(); iterator2.hasNext();) {
				String string = (String) iterator2.next();
				System.out.print("\"" + string + "\"" + " ");
			}
		}
		
		System.out.println("\nValid States:");
		for (Iterator iterator = validStates.iterator(); iterator.hasNext();) {
			State state = (State) iterator.next();
			System.out.print(state.getName() + " ");
		}
	}
	
	@Test
	public void testNOT() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testEG() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testEF() {
		fail("Not yet implemented"); // TODO
	}

}
