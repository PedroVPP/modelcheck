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
import br.algorithms.CounterExample;
import br.algorithms.Transicao;
import br.mef.Expression;
import br.mef.ExpressionType;
import br.mef.MEF;
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
		expression.setType(ExpressionType.OR);
		
		assertTrue(Algorithms.executeOperation(this.states.get(0), expression));
		assertTrue(Algorithms.executeOperation(this.states.get(1), expression));
		assertFalse(Algorithms.executeOperation(this.states.get(2), expression));
		assertTrue(Algorithms.executeOperation(this.states.get(3), expression));
		
		expression = new Expression("q OR r");
		expression.setExp1(new Expression("q"));
		expression.setExp2(new Expression("r"));
		expression.setType(ExpressionType.OR);
		
		assertTrue(Algorithms.executeOperation(this.states.get(0), expression));
		assertTrue(Algorithms.executeOperation(this.states.get(1), expression));
		assertTrue(Algorithms.executeOperation(this.states.get(2), expression));
		assertTrue(Algorithms.executeOperation(this.states.get(3), expression));
		
		expression = new Expression("p OR r");
		expression.setExp1(new Expression("p"));
		expression.setExp2(new Expression("r"));
		expression.setType(ExpressionType.OR);
		
		assertTrue(Algorithms.executeOperation(this.states.get(0), expression));
		assertTrue(Algorithms.executeOperation(this.states.get(1), expression));
		assertTrue(Algorithms.executeOperation(this.states.get(2), expression));
		assertTrue(Algorithms.executeOperation(this.states.get(3), expression));
		printLabelsInformation();


	}
	
	@Test
	public void testAND() throws Exception {
		// criacao da arvore de derivacao da expressao
		expression = new Expression("p AND q");
		expression.setExp1(new Expression("p"));
		expression.setExp2(new Expression("q"));
		expression.setType(ExpressionType.AND);;
		
		assertTrue(Algorithms.AND(this.states.get(0), expression));
		assertFalse(Algorithms.AND(this.states.get(1), expression));
		assertFalse(Algorithms.AND(this.states.get(2), expression));
		assertFalse(Algorithms.AND(this.states.get(3), expression));
		
		expression = new Expression("q AND r");
		expression.setExp1(new Expression("q"));
		expression.setExp2(new Expression("r"));
		expression.setType(ExpressionType.AND);;
		
		assertFalse(Algorithms.AND(this.states.get(0), expression));
		assertTrue(Algorithms.AND(this.states.get(1), expression));
		assertFalse(Algorithms.AND(this.states.get(2), expression));
		assertTrue(Algorithms.AND(this.states.get(3), expression));
		
		expression = new Expression("p AND r");
		expression.setExp1(new Expression("p"));
		expression.setExp2(new Expression("r"));
		expression.setType(ExpressionType.AND);;
		
		assertFalse(Algorithms.AND(this.states.get(0), expression));
		assertFalse(Algorithms.AND(this.states.get(1), expression));
		assertFalse(Algorithms.AND(this.states.get(2), expression));
		assertFalse(Algorithms.AND(this.states.get(3), expression));
	}

	@Test
	public void testNOT() {
		// criacao da arvore de derivacao da expressao
		expression = new Expression("NOT p");
		expression.setExp1(new Expression("p"));
		expression.setType(ExpressionType.NOT);

		assertFalse(Algorithms.NOT(this.states.get(0), expression));
		assertTrue(Algorithms.NOT(this.states.get(1), expression));
		assertTrue(Algorithms.NOT(this.states.get(2), expression));
		assertTrue(Algorithms.NOT(this.states.get(3), expression));

		// criacao da arvore de derivacao da expressao
		expression = new Expression("NOT q");
		expression.setExp1(new Expression("q"));
		expression.setType(ExpressionType.NOT);

		assertFalse(Algorithms.NOT(this.states.get(0), expression));
		assertFalse(Algorithms.NOT(this.states.get(1), expression));
		assertTrue(Algorithms.NOT(this.states.get(2), expression));
		assertFalse(Algorithms.NOT(this.states.get(3), expression));
		
		expression = new Expression("NOT r");
		expression.setExp1(new Expression("r"));
		expression.setType(ExpressionType.NOT);

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
		expression.setType(ExpressionType.IMP);

		assertTrue(Algorithms.IMP(this.states.get(0), expression));
		assertTrue(Algorithms.IMP(this.states.get(1), expression));
		assertTrue(Algorithms.IMP(this.states.get(2), expression));
		assertTrue(Algorithms.IMP(this.states.get(3), expression));
		
		expression = new Expression("r -> q");
		expression.setExp1(new Expression("r"));
		expression.setExp2(new Expression("q"));
		expression.setType(ExpressionType.IMP);

		assertTrue(Algorithms.IMP(this.states.get(0), expression));
		assertTrue(Algorithms.IMP(this.states.get(1), expression));
		assertFalse(Algorithms.IMP(this.states.get(2), expression));
		assertTrue(Algorithms.IMP(this.states.get(3), expression));
		
		expression = new Expression("r -> p");
		expression.setExp1(new Expression("r"));
		expression.setExp2(new Expression("p"));
		expression.setType(ExpressionType.IMP);

		assertTrue(Algorithms.IMP(this.states.get(0), expression));
		assertFalse(Algorithms.IMP(this.states.get(1), expression));
		assertFalse(Algorithms.IMP(this.states.get(2), expression));
		assertFalse(Algorithms.IMP(this.states.get(3), expression));
		
		expression = new Expression("p -> r");
		expression.setExp1(new Expression("p"));
		expression.setExp2(new Expression("r"));
		expression.setType(ExpressionType.IMP);

		assertFalse(Algorithms.IMP(this.states.get(0), expression));
		assertTrue(Algorithms.IMP(this.states.get(1), expression));
		assertTrue(Algorithms.IMP(this.states.get(2), expression));
		assertTrue(Algorithms.IMP(this.states.get(3), expression));
		
		expression = new Expression("q -> p");
		expression.setExp1(new Expression("q"));
		expression.setExp2(new Expression("p"));
		expression.setType(ExpressionType.IMP);

		assertTrue(Algorithms.IMP(this.states.get(0), expression));
		assertFalse(Algorithms.IMP(this.states.get(1), expression));
		assertTrue(Algorithms.IMP(this.states.get(2), expression));
		assertFalse(Algorithms.IMP(this.states.get(3), expression));
		
		expression = new Expression("q -> r");
		expression.setExp1(new Expression("q"));
		expression.setExp2(new Expression("r"));
		expression.setType(ExpressionType.IMP);

		assertFalse(Algorithms.IMP(this.states.get(0), expression));
		assertTrue(Algorithms.IMP(this.states.get(1), expression));
		assertTrue(Algorithms.IMP(this.states.get(2), expression));
		assertTrue(Algorithms.IMP(this.states.get(3), expression));
	}
	
	@Test
	public void testBIC() throws Exception {
//		tabela verdade
//		p   q  p<->q
//		V 	V 	V
//		V 	F 	F
//		F 	V 	F
//		F 	F 	V
		expression = new Expression("p <-> q");
		expression.setExp1(new Expression("p"));
		expression.setExp2(new Expression("q"));
		expression.setType(ExpressionType.BIC);

		assertTrue(Algorithms.BIC(this.states.get(0), expression));
		assertFalse(Algorithms.BIC(this.states.get(1), expression));
		assertTrue(Algorithms.BIC(this.states.get(2), expression));
		assertFalse(Algorithms.BIC(this.states.get(3), expression));
		
		expression = new Expression("r <-> q");
		expression.setExp1(new Expression("r"));
		expression.setExp2(new Expression("q"));
		expression.setType(ExpressionType.BIC);

		assertFalse(Algorithms.BIC(this.states.get(0), expression));
		assertTrue(Algorithms.BIC(this.states.get(1), expression));
		assertFalse(Algorithms.BIC(this.states.get(2), expression));
		assertTrue(Algorithms.BIC(this.states.get(3), expression));
		
		expression = new Expression("r <-> p");
		expression.setExp1(new Expression("r"));
		expression.setExp2(new Expression("p"));
		expression.setType(ExpressionType.BIC);

		assertFalse(Algorithms.BIC(this.states.get(0), expression));
		assertFalse(Algorithms.BIC(this.states.get(1), expression));
		assertFalse(Algorithms.BIC(this.states.get(2), expression));
		assertFalse(Algorithms.BIC(this.states.get(3), expression));
		
		expression = new Expression("p <-> r");
		expression.setExp1(new Expression("p"));
		expression.setExp2(new Expression("r"));
		expression.setType(ExpressionType.BIC);

		assertFalse(Algorithms.BIC(this.states.get(0), expression));
		assertFalse(Algorithms.BIC(this.states.get(1), expression));
		assertFalse(Algorithms.BIC(this.states.get(2), expression));
		assertFalse(Algorithms.BIC(this.states.get(3), expression));
		
		expression = new Expression("q <-> p");
		expression.setExp1(new Expression("q"));
		expression.setExp2(new Expression("p"));
		expression.setType(ExpressionType.BIC);

		assertTrue(Algorithms.BIC(this.states.get(0), expression));
		assertFalse(Algorithms.BIC(this.states.get(1), expression));
		assertTrue(Algorithms.BIC(this.states.get(2), expression));
		assertFalse(Algorithms.BIC(this.states.get(3), expression));
		
		expression = new Expression("q <-> r");
		expression.setExp1(new Expression("q"));
		expression.setExp2(new Expression("r"));
		expression.setType(ExpressionType.BIC);

		assertFalse(Algorithms.BIC(this.states.get(0), expression));
		assertTrue(Algorithms.BIC(this.states.get(1), expression));
		assertFalse(Algorithms.BIC(this.states.get(2), expression));
		assertTrue(Algorithms.BIC(this.states.get(3), expression));
	}

	@Test
	public void testEG() {
		expression = new Expression("EG p");
		expression.setExp1(new Expression("p"));
		expression.setType(ExpressionType.EG);
		assertFalse(Algorithms.EG(this.states.get(0), expression));
		assertFalse(Algorithms.EG(this.states.get(1), expression));
		assertFalse(Algorithms.EG(this.states.get(2), expression));
		assertFalse(Algorithms.EG(this.states.get(3), expression));

		expression = new Expression("EG q");
		expression.setExp1(new Expression("q"));
		expression.setType(ExpressionType.EG);
		assertTrue(Algorithms.EG(this.states.get(0), expression));
		assertTrue(Algorithms.EG(this.states.get(1), expression));
		assertFalse(Algorithms.EG(this.states.get(2), expression));
		assertTrue(Algorithms.EG(this.states.get(3), expression));

		expression = new Expression("EG r");
		expression.setExp1(new Expression("r"));
		expression.setType(ExpressionType.EG);
		assertFalse(Algorithms.EG(this.states.get(0), expression));
		assertFalse(Algorithms.EG(this.states.get(1), expression));
		assertTrue(Algorithms.EG(this.states.get(2), expression));
		assertTrue(Algorithms.EG(this.states.get(3), expression));
	}

	@Test
	public void testEF() {
		expression = new Expression("EF p");
		expression.setExp1(new Expression("p"));
		expression.setType(ExpressionType.EF);
		assertTrue(Algorithms.EF(this.states.get(0), expression));
		assertTrue(Algorithms.EF(this.states.get(1), expression));
		assertFalse(Algorithms.EF(this.states.get(2), expression));
		assertFalse(Algorithms.EF(this.states.get(3), expression));

		expression = new Expression("EF q");
		expression.setExp1(new Expression("q"));
		expression.setType(ExpressionType.EF);
		assertTrue(Algorithms.EF(this.states.get(0), expression));
		assertTrue(Algorithms.EF(this.states.get(1), expression));
		assertTrue(Algorithms.EF(this.states.get(2), expression));
		assertTrue(Algorithms.EF(this.states.get(3), expression));

		expression = new Expression("EF r");
		expression.setExp1(new Expression("r"));
		expression.setType(ExpressionType.EF);
		assertTrue(Algorithms.EF(this.states.get(0), expression));
		assertTrue(Algorithms.EF(this.states.get(1), expression));
		assertTrue(Algorithms.EF(this.states.get(2), expression));
		assertTrue(Algorithms.EF(this.states.get(3), expression));
	}

	@Test
	public void testAG() {
		expression = new Expression("AG p");
		expression.setExp1(new Expression("p"));
		expression.setType(ExpressionType.AG);
		assertFalse(Algorithms.AG(this.states.get(0), expression));
		assertFalse(Algorithms.AG(this.states.get(1), expression));
		assertFalse(Algorithms.AG(this.states.get(2), expression));
		assertFalse(Algorithms.AG(this.states.get(3), expression));
//
		expression = new Expression("AG q");
		expression.setExp1(new Expression("q"));
		expression.setType(ExpressionType.AG);
		assertFalse(Algorithms.AG(this.states.get(0), expression));
		assertFalse(Algorithms.AG(this.states.get(1), expression));
		
		ArrayList<CounterExample> cps = MEF.getInstance().getCounterExample();
		for (Iterator iterator = cps.iterator(); iterator.hasNext();) {
			CounterExample counterExample = (CounterExample) iterator.next();
			System.out.println("Estado: " + counterExample.getState());
			System.out.println("Expressão: " + counterExample.getExp());
			System.out.println("Validos: ");
			ArrayList<State> validos = counterExample.getValidos();
			for (Iterator iterator2 = validos.iterator(); iterator2.hasNext();) {
				State state = (State) iterator2.next();
				System.out.println(state.getName());
			}
			System.out.println("Transições: ");
			ArrayList<Transicao> conexoes = counterExample.getTransicoes();
			for (Iterator iterator2 = conexoes.iterator(); iterator2.hasNext();) {
				Transicao transicao = (Transicao) iterator2.next();
				System.out.println(transicao.toString());
			}
			
		}
		
		
		assertFalse(Algorithms.AG(this.states.get(2), expression));
		assertTrue(Algorithms.AG(this.states.get(3), expression));

		expression = new Expression("AG r");
		expression.setExp1(new Expression("r"));
		expression.setType(ExpressionType.AG);
		assertFalse(Algorithms.AG(this.states.get(0), expression));
		assertFalse(Algorithms.AG(this.states.get(1), expression));
		assertTrue(Algorithms.AG(this.states.get(2), expression));
		assertTrue(Algorithms.AG(this.states.get(3), expression));
	}

	@Test
	public void testAF() {
		expression = new Expression("AF p");
		expression.setExp1(new Expression("p"));
		expression.setType(ExpressionType.AF);
		assertFalse(Algorithms.AF(this.states.get(0), expression));
		assertFalse(Algorithms.AF(this.states.get(1), expression));
		assertFalse(Algorithms.AF(this.states.get(2), expression));
		assertFalse(Algorithms.AF(this.states.get(3), expression));

		expression = new Expression("AF q");
		expression.setExp1(new Expression("q"));
		expression.setType(ExpressionType.AF);
		assertFalse(Algorithms.AF(this.states.get(0), expression));
		assertFalse(Algorithms.AF(this.states.get(1), expression));
		assertFalse(Algorithms.AF(this.states.get(2), expression));
		assertTrue(Algorithms.AF(this.states.get(3), expression));

		expression = new Expression("AF r");
		expression.setExp1(new Expression("r"));
		expression.setType(ExpressionType.AF);
		assertFalse(Algorithms.AF(this.states.get(0), expression));
		assertFalse(Algorithms.AF(this.states.get(1), expression));
		assertTrue(Algorithms.AF(this.states.get(2), expression));
		assertTrue(Algorithms.AF(this.states.get(3), expression));
	}
	
	@Test
	public void testEX() {
		expression = new Expression("EX p");
		expression.setExp1(new Expression("p"));
		expression.setType(ExpressionType.EX);
		assertFalse(Algorithms.EX(this.states.get(0), expression));
		assertTrue(Algorithms.EX(this.states.get(1), expression));
		assertFalse(Algorithms.EX(this.states.get(2), expression));
		assertFalse(Algorithms.EX(this.states.get(3), expression));

		expression = new Expression("EX q");
		expression.setExp1(new Expression("q"));
		expression.setType(ExpressionType.EX);
		assertTrue(Algorithms.EX(this.states.get(0), expression));
		assertTrue(Algorithms.EX(this.states.get(1), expression));
		assertTrue(Algorithms.EX(this.states.get(2), expression));
		assertTrue(Algorithms.EX(this.states.get(3), expression));

		expression = new Expression("EX r");
		expression.setExp1(new Expression("r"));
		expression.setType(ExpressionType.EX);
		assertTrue(Algorithms.EX(this.states.get(0), expression));
		assertFalse(Algorithms.EX(this.states.get(1), expression));
		assertTrue(Algorithms.EX(this.states.get(2), expression));
		assertTrue(Algorithms.EX(this.states.get(3), expression));
	}
	
	@Test
	public void testAX() {
		expression = new Expression("AX p");
		expression.setExp1(new Expression("p"));
		expression.setType(ExpressionType.AX);
		assertFalse(Algorithms.AX(this.states.get(0), expression));
		assertTrue(Algorithms.AX(this.states.get(1), expression));
		assertFalse(Algorithms.AX(this.states.get(2), expression));
		assertFalse(Algorithms.AX(this.states.get(3), expression));

		expression = new Expression("AX q");
		expression.setExp1(new Expression("q"));
		expression.setType(ExpressionType.AX);
		assertFalse(Algorithms.AX(this.states.get(0), expression));
		assertTrue(Algorithms.AX(this.states.get(1), expression));
		assertTrue(Algorithms.AX(this.states.get(2), expression));
		assertTrue(Algorithms.AX(this.states.get(3), expression));

		expression = new Expression("AX r");
		expression.setExp1(new Expression("r"));
		expression.setType(ExpressionType.AX);
		assertTrue(Algorithms.AX(this.states.get(0), expression));
		assertFalse(Algorithms.AX(this.states.get(1), expression));
		assertTrue(Algorithms.AX(this.states.get(2), expression));
		assertTrue(Algorithms.AX(this.states.get(3), expression));
	}
	
	@Test
	public void testEU() {
//		This formula is TRUE in a state s0 if for some path starting
//		with s0, there exists an initial prefix of that path such that f2
//		holds at the last state of the prefix and f1 holds at all other
//		states along the prefix
		expression = new Expression("E (p U q)");
		expression.setExp1(new Expression("p"));
		expression.setExp2(new Expression("q"));
		expression.setType(ExpressionType.EU);
		assertTrue(Algorithms.EU(this.states.get(0), expression));
		assertTrue(Algorithms.EU(this.states.get(1), expression));
		assertFalse(Algorithms.EU(this.states.get(2), expression));
		assertTrue(Algorithms.EU(this.states.get(3), expression));

		expression = new Expression("E (q U r)");
		expression.setExp1(new Expression("q"));
		expression.setExp2(new Expression("r"));
		expression.setType(ExpressionType.EU);
		assertTrue(Algorithms.EU(this.states.get(0), expression));
		assertTrue(Algorithms.EU(this.states.get(1), expression));
		assertTrue(Algorithms.EU(this.states.get(2), expression));
		assertTrue(Algorithms.EU(this.states.get(3), expression));
		
		expression = new Expression("E (r U q)");
		expression.setExp1(new Expression("r"));
		expression.setExp2(new Expression("q"));
		expression.setType(ExpressionType.EU);
		assertTrue(Algorithms.EU(this.states.get(0), expression));
		assertTrue(Algorithms.EU(this.states.get(1), expression));
		assertTrue(Algorithms.EU(this.states.get(2), expression));
		assertTrue(Algorithms.EU(this.states.get(3), expression));
		
		expression = new Expression("E (p U r)");
		expression.setExp1(new Expression("p"));
		expression.setExp2(new Expression("r"));
		expression.setType(ExpressionType.EU);
		assertTrue(Algorithms.EU(this.states.get(0), expression));
		assertTrue(Algorithms.EU(this.states.get(1), expression));
		assertTrue(Algorithms.EU(this.states.get(2), expression));
		assertTrue(Algorithms.EU(this.states.get(3), expression));
		
		expression = new Expression("E (r U p)");
		expression.setExp1(new Expression("r"));
		expression.setExp2(new Expression("p"));
		expression.setType(ExpressionType.EU);
		assertTrue(Algorithms.EU(this.states.get(0), expression));
		assertTrue(Algorithms.EU(this.states.get(1), expression));
		assertFalse(Algorithms.EU(this.states.get(2), expression));
		assertFalse(Algorithms.EU(this.states.get(3), expression));
		
		expression = new Expression("E (q U p)");
		expression.setExp1(new Expression("q"));
		expression.setExp2(new Expression("p"));
		expression.setType(ExpressionType.EU);
		assertTrue(Algorithms.EU(this.states.get(0), expression));
		assertTrue(Algorithms.EU(this.states.get(1), expression));
		assertFalse(Algorithms.EU(this.states.get(2), expression));
		assertFalse(Algorithms.EU(this.states.get(3), expression));
	}
	
	@Test
	public void testAU() {
//		This formula is TRUE in a state s0 if for every path starting
//		with s0, there exists an initial prefix of that path such that f2
//		holds at the last state of the prefix and f1 holds at all other
//		states along the prefix.
		expression = new Expression("A (p U q)");
		expression.setExp1(new Expression("p"));
		expression.setExp2(new Expression("q"));
		expression.setType(ExpressionType.AU);
		assertTrue(Algorithms.AU(this.states.get(0), expression));
		assertTrue(Algorithms.AU(this.states.get(1), expression));
		assertFalse(Algorithms.AU(this.states.get(2), expression));
		assertTrue(Algorithms.AU(this.states.get(3), expression));

		expression = new Expression("A (q U r)");
		expression.setExp1(new Expression("q"));
		expression.setExp2(new Expression("r"));
		expression.setType(ExpressionType.AU);
		assertTrue(Algorithms.AU(this.states.get(0), expression));
		assertTrue(Algorithms.AU(this.states.get(1), expression));
		assertTrue(Algorithms.AU(this.states.get(2), expression));
		assertTrue(Algorithms.AU(this.states.get(3), expression));
//		
		expression = new Expression("A (r U q)");
		expression.setExp1(new Expression("r"));
		expression.setExp2(new Expression("q"));
		expression.setType(ExpressionType.AU);
		assertTrue(Algorithms.AU(this.states.get(0), expression));
		assertTrue(Algorithms.AU(this.states.get(1), expression));
		assertTrue(Algorithms.AU(this.states.get(2), expression));
		assertTrue(Algorithms.AU(this.states.get(3), expression));
//		
		expression = new Expression("A (p U r)");
		expression.setExp1(new Expression("p"));
		expression.setExp2(new Expression("r"));
		expression.setType(ExpressionType.AU);
		assertTrue(Algorithms.AU(this.states.get(0), expression));
		assertTrue(Algorithms.AU(this.states.get(1), expression));
		assertTrue(Algorithms.AU(this.states.get(2), expression));
		assertTrue(Algorithms.AU(this.states.get(3), expression));
		
		expression = new Expression("A (r U p)");
		expression.setExp1(new Expression("r"));
		expression.setExp2(new Expression("p"));
		expression.setType(ExpressionType.AU);
		assertTrue(Algorithms.AU(this.states.get(0), expression));
		assertTrue(Algorithms.AU(this.states.get(1), expression));
		assertFalse(Algorithms.AU(this.states.get(2), expression));
		assertFalse(Algorithms.AU(this.states.get(3), expression));
		
		expression = new Expression("A (q U p)");
		expression.setExp1(new Expression("q"));
		expression.setExp2(new Expression("p"));
		expression.setType(ExpressionType.AU);
		assertTrue(Algorithms.AU(this.states.get(0), expression));
		assertTrue(Algorithms.AU(this.states.get(1), expression));
		assertFalse(Algorithms.AU(this.states.get(2), expression));
		assertFalse(Algorithms.AU(this.states.get(3), expression));
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
	
	@Test
	public void testGetConnectedStates() {
		ArrayList<State> states = Algorithms.getConnectingStates(this.states.get(2));
		for (Iterator<State> iterator = states.iterator(); iterator.hasNext();) {
			State state = (State) iterator.next();
			System.out.println("\nState: " + "\"" + state.getName() + "\"");
		}
	}
}
