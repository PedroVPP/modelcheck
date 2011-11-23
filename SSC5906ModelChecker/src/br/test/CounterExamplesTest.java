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

public class CounterExamplesTest extends Assert{

	ArrayList<State> states;
	Expression expression;
	
	@Before
	public void initialize() {
		states = new ArrayList<State>();
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
//		s3.addLabelsString("r");

		s0.addChild(s1);
		s0.addChild(s2);

		s1.addChild(s0);

		s2.addChild(s3);

		s3.addChild(s3);

		states.add(s0);
		states.add(s1);
		states.add(s2);
		states.add(s3);
	}
	
	@Test
	public void TestCounterExample() {
		Expression EG = new Expression("EG q");
		EG.setType(ExpressionType.EG);
		EG.setExp1(new Expression("q"));
		
		Expression AG = new Expression("AG r");
		AG.setType(ExpressionType.AG);
		AG.setExp1(new Expression("r"));
		
		expression = new Expression("A [EG q U AG r]");
		expression.setExp1(EG);
		expression.setExp2(AG);
		expression.setType(ExpressionType.AU);
		assertFalse(Algorithms.executeOperation(this.states.get(0), expression));
		
		printCounterExamplesData(MEF.getInstance().findCounterExamplesByState(this.states.get(1)));
	}

	@Test
	public void test2() {
//		(11) created by Pedro
//		E [EG q U AG r]
		Expression EG = new Expression("EG q");
		EG.setType(ExpressionType.EG);
		EG.setExp1(new Expression("q"));
		
		Expression AG = new Expression("AG r");
		AG.setType(ExpressionType.AG);
		AG.setExp1(new Expression("r"));
		
		expression = new Expression("E [EG q U AG r]");
		expression.setExp1(EG);
		expression.setExp2(AG);
		expression.setType(ExpressionType.EU);
		assertFalse(Algorithms.executeOperation(this.states.get(0), expression));
		
		printCounterExamplesData(MEF.getInstance().findCounterExamplesByState(this.states.get(0)));
		System.out.println("--------------------------------");
		printCounterExamplesData(MEF.getInstance().findCounterExamplesByState(this.states.get(1)));
		System.out.println("--------------------------------");
		printCounterExamplesData(MEF.getInstance().findCounterExamplesByState(this.states.get(2)));
		System.out.println("--------------------------------");
		printCounterExamplesData(MEF.getInstance().findCounterExamplesByState(this.states.get(3)));
	}
	
	public static void printCounterExamplesData(ArrayList<CounterExample> counterExamples) {
		for (Iterator iterator = counterExamples.iterator(); iterator.hasNext();) {
			CounterExample counterExample = (CounterExample) iterator.next();
			System.out.println("Expression: " + counterExample.getExp());
			for (Iterator iterator2 = counterExample.getInvalidos().iterator(); iterator2
					.hasNext();) {
				State state = (State) iterator2.next();
				System.out.println("Estado Inválido: "+state.getName());
			}
			
			for (Iterator iterator2 = counterExample.getValidos().iterator(); iterator2
					.hasNext();) {
				State state = (State) iterator2.next();
				System.out.println("Estado válido: "+state.getName());
			}
			
			for (Iterator iterator2 = counterExample.getTransicoes().iterator(); iterator2
					.hasNext();) {
				Transicao transicao = (Transicao) iterator2.next();
				System.out.println("Transicao: " + transicao.toString());
			}	
			
			System.out.println("");
		}
	}
	
	public static void printCounterExamplesData(CounterExample counterExample) {
			System.out.println("Expression: " + counterExample.getExp());
			for (Iterator iterator2 = counterExample.getInvalidos().iterator(); iterator2
					.hasNext();) {
				State state = (State) iterator2.next();
				System.out.println("Estado Inválido: "+state.getName());
			}
			
			for (Iterator iterator2 = counterExample.getValidos().iterator(); iterator2
					.hasNext();) {
				State state = (State) iterator2.next();
				System.out.println("Estado válido: "+state.getName());
			}
			
			for (Iterator iterator2 = counterExample.getTransicoes().iterator(); iterator2
					.hasNext();) {
				Transicao transicao = (Transicao) iterator2.next();
				System.out.println("Transicao: " + transicao.toString());
			}	
			
			System.out.println("");
	}
}
