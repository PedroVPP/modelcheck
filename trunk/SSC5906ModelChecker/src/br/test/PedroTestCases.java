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

	ArrayList<State> states;
	Expression expression;

	@Before
	public void initialize() {
		states = new ArrayList<State>();
		// Essa MEF e suas propriedas é a mesma do exemplo do Adenilso nas suas
		// aulas
		State s0 = new State("S0");
		State s1 = new State("S1");
		State s2 = new State("S2");
		State s3 = new State("S3");

		s0.addLabelsString("p");
		s0.addLabelsString("q");

		s1.addLabelsString("q");
		s1.addLabelsString("r");

		s2.addLabelsString("r");

		s3.addLabelsString("q");
		s3.addLabelsString("r");

		s0.addChild(s2);
		s0.addChild(s1);

		s1.addChild(s0);

		s2.addChild(s3);

		s3.addChild(s3);

		states.add(s0);
		states.add(s1);
		states.add(s2);
		states.add(s3);
	}

	@Test
	public void testOR() throws Exception {
		// criacao da arvore de derivacao da expressao
		expression = new Expression("p OR q");
		expression.setExp1(new Expression("p"));
		expression.setExp2(new Expression("q"));
		expression.setType("OR");
		
		assertTrue(Algorithms.OR(this.states.get(0), expression));
		assertTrue(Algorithms.OR(this.states.get(1), expression));
		assertFalse(Algorithms.OR(this.states.get(2), expression));
		assertTrue(Algorithms.OR(this.states.get(3), expression));
		
		expression = new Expression("q OR r");
		expression.setExp1(new Expression("q"));
		expression.setExp2(new Expression("r"));
		expression.setType("OR");
		
		assertTrue(Algorithms.OR(this.states.get(0), expression));
		assertTrue(Algorithms.OR(this.states.get(1), expression));
		assertTrue(Algorithms.OR(this.states.get(2), expression));
		assertTrue(Algorithms.OR(this.states.get(3), expression));

	}

	@Test
	public void testNOT() {
		// criacao da arvore de derivacao da expressao
		expression = new Expression("NOT p");
		expression.setExp1(new Expression("p"));
		expression.setType("NOT");

		assertFalse(Algorithms.NOT(this.states.get(0), expression));
		assertTrue(Algorithms.NOT(this.states.get(1), expression));
		assertTrue(Algorithms.NOT(this.states.get(2), expression));
		assertTrue(Algorithms.NOT(this.states.get(3), expression));

		// criacao da arvore de derivacao da expressao
		expression = new Expression("NOT q");
		expression.setExp1(new Expression("q"));
		expression.setType("NOT");

		assertFalse(Algorithms.NOT(this.states.get(0), expression));
		assertFalse(Algorithms.NOT(this.states.get(1), expression));
		assertTrue(Algorithms.NOT(this.states.get(2), expression));
		assertFalse(Algorithms.NOT(this.states.get(3), expression));
		
		expression = new Expression("NOT r");
		expression.setExp1(new Expression("r"));
		expression.setType("NOT");

		assertTrue(Algorithms.NOT(this.states.get(0), expression));
		assertFalse(Algorithms.NOT(this.states.get(1), expression));
		assertFalse(Algorithms.NOT(this.states.get(2), expression));
		assertFalse(Algorithms.NOT(this.states.get(3), expression));
	}

	@Test
	public void testIMP() throws Exception {
//		tabela verdade
//		p   q  p->q
//		V 	V 	V
//		V 	F 	F
//		F 	V 	V
//		F 	F 	V
		expression = new Expression("p -> q");
		expression.setExp1(new Expression("p"));
		expression.setExp2(new Expression("q"));
		expression.setType("IMP");

		assertTrue(Algorithms.IMP(this.states.get(0), expression));
		assertTrue(Algorithms.IMP(this.states.get(1), expression));
		assertTrue(Algorithms.IMP(this.states.get(2), expression));
		assertTrue(Algorithms.IMP(this.states.get(3), expression));

		expression = new Expression("r -> q");
		expression.setExp1(new Expression("r"));
		expression.setExp2(new Expression("q"));
		expression.setType("IMP");

		assertTrue(Algorithms.IMP(this.states.get(0), expression));
		assertTrue(Algorithms.IMP(this.states.get(1), expression));
		assertFalse(Algorithms.IMP(this.states.get(2), expression));
		assertTrue(Algorithms.IMP(this.states.get(3), expression));
		
		expression = new Expression("q -> p");
		expression.setExp1(new Expression("q"));
		expression.setExp2(new Expression("p"));
		expression.setType("IMP");

		assertTrue(Algorithms.IMP(this.states.get(0), expression));
		assertFalse(Algorithms.IMP(this.states.get(1), expression));
		assertTrue(Algorithms.IMP(this.states.get(2), expression));
		assertFalse(Algorithms.IMP(this.states.get(3), expression));
		
		expression = new Expression("q -> r");
		expression.setExp1(new Expression("q"));
		expression.setExp2(new Expression("r"));
		expression.setType("IMP");

		assertFalse(Algorithms.IMP(this.states.get(0), expression));
		assertTrue(Algorithms.IMP(this.states.get(1), expression));
		assertTrue(Algorithms.IMP(this.states.get(2), expression));
		assertTrue(Algorithms.IMP(this.states.get(3), expression));
	}

	@Test
	public void testEG() {
		expression = new Expression("EG p");
		expression.setExp1(new Expression("p"));
		expression.setType("EG");
		assertFalse(Algorithms.EG(this.states.get(0), expression));
		State.resetVisited(states);
		assertFalse(Algorithms.EG(this.states.get(1), expression));
		State.resetVisited(states);
		assertFalse(Algorithms.EG(this.states.get(2), expression));
		State.resetVisited(states);
		assertFalse(Algorithms.EG(this.states.get(3), expression));
		State.resetVisited(states);

		expression = new Expression("EG q");
		expression.setExp1(new Expression("q"));
		expression.setType("EG");
		assertTrue(Algorithms.EG(this.states.get(0), expression));
		State.resetVisited(states);
		assertTrue(Algorithms.EG(this.states.get(1), expression));
		State.resetVisited(states);
		assertFalse(Algorithms.EG(this.states.get(2), expression));
		State.resetVisited(states);
		assertTrue(Algorithms.EG(this.states.get(3), expression));
		State.resetVisited(states);

		expression = new Expression("EG r");
		expression.setExp1(new Expression("r"));
		expression.setType("EG");
		assertFalse(Algorithms.EG(this.states.get(0), expression));
		State.resetVisited(states);
		assertFalse(Algorithms.EG(this.states.get(1), expression));
		State.resetVisited(states);
		assertTrue(Algorithms.EG(this.states.get(2), expression));
		State.resetVisited(states);
		assertTrue(Algorithms.EG(this.states.get(3), expression));
		State.resetVisited(states);
	}

	@Test
	public void testEF() {
		expression = new Expression("EF p");
		expression.setExp1(new Expression("p"));
		expression.setType("EF");
		assertTrue(Algorithms.EF(this.states.get(0), expression));
		State.resetVisited(states);
		assertTrue(Algorithms.EF(this.states.get(1), expression));
		State.resetVisited(states);
		assertFalse(Algorithms.EF(this.states.get(2), expression));
		State.resetVisited(states);
		assertFalse(Algorithms.EF(this.states.get(3), expression));
		State.resetVisited(states);

		expression = new Expression("EF q");
		expression.setExp1(new Expression("q"));
		expression.setType("EF");
		assertTrue(Algorithms.EF(this.states.get(0), expression));
		State.resetVisited(states);
		assertTrue(Algorithms.EF(this.states.get(1), expression));
		State.resetVisited(states);
		assertTrue(Algorithms.EF(this.states.get(2), expression));
		State.resetVisited(states);
		assertTrue(Algorithms.EF(this.states.get(3), expression));
		State.resetVisited(states);

		expression = new Expression("EF r");
		expression.setExp1(new Expression("r"));
		expression.setType("EF");
		assertTrue(Algorithms.EF(this.states.get(0), expression));
		State.resetVisited(states);
		assertTrue(Algorithms.EF(this.states.get(1), expression));
		State.resetVisited(states);
		assertTrue(Algorithms.EF(this.states.get(2), expression));
		State.resetVisited(states);
		assertTrue(Algorithms.EF(this.states.get(3), expression));
		State.resetVisited(states);

	}

	@Test
	public void testAG() {
		expression = new Expression("AG p");
		expression.setExp1(new Expression("p"));
		expression.setType("AG");
		assertFalse(Algorithms.AG(this.states.get(0), expression));
		State.resetVisited(states);
		assertFalse(Algorithms.AG(this.states.get(1), expression));
		State.resetVisited(states);
		assertFalse(Algorithms.AG(this.states.get(2), expression));
		State.resetVisited(states);
		assertFalse(Algorithms.AG(this.states.get(3), expression));
		State.resetVisited(states);

		expression = new Expression("AG q");
		expression.setExp1(new Expression("q"));
		expression.setType("AG");
		assertFalse(Algorithms.AG(this.states.get(0), expression));
		State.resetVisited(states);
		assertFalse(Algorithms.AG(this.states.get(1), expression));
		State.resetVisited(states);
		assertFalse(Algorithms.AG(this.states.get(2), expression));
		State.resetVisited(states);
		assertTrue(Algorithms.AG(this.states.get(3), expression));
		State.resetVisited(states);

		expression = new Expression("AG r");
		expression.setExp1(new Expression("r"));
		expression.setType("AG");
		assertFalse(Algorithms.AG(this.states.get(0), expression));
		State.resetVisited(states);
		assertFalse(Algorithms.AG(this.states.get(1), expression));
		State.resetVisited(states);
		assertTrue(Algorithms.AG(this.states.get(2), expression));
		State.resetVisited(states);
		assertTrue(Algorithms.AG(this.states.get(3), expression));
		State.resetVisited(states);
	}

	@Test
	public void testAF() {
		// This formula is TRUE in a state s0 if formula f holds in the future along every path from s0
		expression = new Expression("AF p");
		expression.setExp1(new Expression("p"));
		expression.setType("AF");
		assertFalse(Algorithms.AF(this.states.get(0), expression));
		State.resetVisited(states);
		assertFalse(Algorithms.AF(this.states.get(1), expression));
		State.resetVisited(states);
		assertFalse(Algorithms.AF(this.states.get(2), expression));
		State.resetVisited(states);
		assertFalse(Algorithms.AF(this.states.get(3), expression));
		State.resetVisited(states);

		expression = new Expression("AF q");
		expression.setExp1(new Expression("q"));
		expression.setType("AF");
		assertFalse(Algorithms.AF(this.states.get(0), expression));
		State.resetVisited(states);
		assertFalse(Algorithms.AF(this.states.get(1), expression));
		State.resetVisited(states);
		assertFalse(Algorithms.AF(this.states.get(2), expression));
		State.resetVisited(states);
		assertTrue(Algorithms.AF(this.states.get(3), expression));
		State.resetVisited(states);

		expression = new Expression("AF r");
		expression.setExp1(new Expression("r"));
		expression.setType("AF");
		assertFalse(Algorithms.AF(this.states.get(0), expression));
		State.resetVisited(states);
		assertFalse(Algorithms.AF(this.states.get(1), expression));
		State.resetVisited(states);
		assertTrue(Algorithms.AF(this.states.get(2), expression));
		State.resetVisited(states);
		assertTrue(Algorithms.AF(this.states.get(3), expression));
		State.resetVisited(states);
	}
	
	@Test
	public void testEX() {
		// This formula is TRUE in a state s0 if formula f holds in the future along every path from s0
		expression = new Expression("EX p");
		expression.setExp1(new Expression("p"));
		expression.setType("EX");
		assertFalse(Algorithms.EX(this.states.get(0), expression));
		State.resetVisited(states);
		assertTrue(Algorithms.EX(this.states.get(1), expression));
		State.resetVisited(states);
		assertFalse(Algorithms.EX(this.states.get(2), expression));
		State.resetVisited(states);
		assertFalse(Algorithms.EX(this.states.get(3), expression));
		State.resetVisited(states);

		expression = new Expression("EX q");
		expression.setExp1(new Expression("q"));
		expression.setType("EX");
		assertTrue(Algorithms.EX(this.states.get(0), expression));
		State.resetVisited(states);
		assertTrue(Algorithms.EX(this.states.get(1), expression));
		State.resetVisited(states);
		assertTrue(Algorithms.EX(this.states.get(2), expression));
		State.resetVisited(states);
		assertTrue(Algorithms.EX(this.states.get(3), expression));
		State.resetVisited(states);

		expression = new Expression("EX r");
		expression.setExp1(new Expression("r"));
		expression.setType("EX");
		assertTrue(Algorithms.EX(this.states.get(0), expression));
		State.resetVisited(states);
		assertFalse(Algorithms.EX(this.states.get(1), expression));
		State.resetVisited(states);
		assertTrue(Algorithms.EX(this.states.get(2), expression));
		State.resetVisited(states);
		assertTrue(Algorithms.EX(this.states.get(3), expression));
		State.resetVisited(states);
	}
	
	public void printLabelsInformation() {
		// Ainda não contém Assert
//		ArrayList<State> validStates = Algorithms.OR(states, expression);

		// imprime todos os estados com seus respectivos labels e propriedades
		// A partir daqui é só System.out.print
		System.out.println("Expression: " + expression.getName());

		for (Iterator<State> iterator = states.iterator(); iterator.hasNext();) {
			State state = (State) iterator.next();
			System.out.println("\nState: " + "\"" + state.getName() + "\"");
			ArrayList<String> labels = state.getLabelsString();
			System.out.println("Labels: ");
			for (Iterator<String> iterator2 = labels.iterator(); iterator2
					.hasNext();) {
				String string = (String) iterator2.next();
				System.out.print("\"" + string + "\"" + " ");
			}
		}

		// System.out.println("\nValid States:");
		// for (Iterator<State> iterator = validStates.iterator();
		// iterator.hasNext();) {
		// State state = (State) iterator.next();
		// System.out.print(state.getName() + " ");
		// }
	}
}
