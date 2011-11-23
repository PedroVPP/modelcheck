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

public class ComplexMEFTest extends Assert{

	ArrayList<State> states;
	Expression expression;
	
	@Before
	public void initialize() {
		states = new ArrayList<State>();
		State s1 = new State("S1");
		State s2 = new State("S2");
		State s3 = new State("S3");
		State s4 = new State("S4");
		State s5 = new State("S5");
		State s6 = new State("S6");
		State s7 = new State("S7");
		State s8 = new State("S8");
		State s9 = new State("S9");
		State s10 = new State("S10");
		State s11 = new State("S11");
		State s12 = new State("S12");
		State s13 = new State("S13");

		s1.addLabelsString("p");
		s2.addLabelsString("p");
		s3.addLabelsString("p");
		s4.addLabelsString("p");
		s5.addLabelsString("p");
		s6.addLabelsString("p");
		s7.addLabelsString("p");
		s8.addLabelsString("p");
		s9.addLabelsString("p");
		s10.addLabelsString("p");
		s11.addLabelsString("p");
		s12.addLabelsString("p");
		s13.addLabelsString("q");
		
		s1.addChild(s3);
		s1.addChild(s8);
		s1.addChild(s2);
		
		s2.addChild(s2);
		s2.addChild(s6);
		
		s3.addChild(s4);
		s3.addChild(s1);
		
		s4.addChild(s5);
		s4.addChild(s3);
		
		s5.addChild(s4);
		
		s6.addChild(s7);
		
		s7.addChild(s7);
		
		s8.addChild(s9);
		
		s9.addChild(s11);
		s9.addChild(s10);
		
		s10.addChild(s7);
		s10.addChild(s12);
		
		s11.addChild(s6);
		
		s12.addChild(s13);
		
		s13.addChild(s13);

		states.add(s1);
		states.add(s2);
		states.add(s3);
		states.add(s4);
		states.add(s5);
		states.add(s6);
		states.add(s7);
		states.add(s8);
		states.add(s9);
		states.add(s10);
		states.add(s11);
		states.add(s12);
		states.add(s13);
	}
	
	@Test
	public void test1() {
		Expression AG = new Expression("AG p");
		AG.setType(ExpressionType.AG);
		AG.setExp1(new Expression("p"));
		assertTrue(Algorithms.executeOperation(this.states.get(10), AG));
		
		CounterExamplesTest.printCounterExamplesData(MEF.getInstance().findCounterExamplesByState(this.states.get(10)));
	}
}