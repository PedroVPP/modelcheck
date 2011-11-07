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
import br.mef.ExpressionType;
import br.mef.Property;
import br.mef.State;

public class ComplexCTLTest extends Assert{

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
		s3.addLabelsString("r");

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
	public void test() {
		
//		(1) M, s0 |= EX(¬p);
		Expression NOT = new Expression("NOT p");
		NOT.setExp1(new Expression("p"));
		NOT.setType(ExpressionType.NOT);
		expression = new Expression("EX (NOT p)");
		expression.setExp1(NOT);
		expression.setType(ExpressionType.EX);
//		(1) YES;
		assertTrue(Algorithms.executeOperation(this.states.get(0), expression));
		
		
//		(2) M, s0 |= EX (EG(r));
		Expression EG = new Expression("EG r");
		EG.setExp1(new Expression("r"));
		EG.setType(ExpressionType.EG);
		expression = new Expression("EX (EG r)");
		expression.setExp1(EG);
		expression.setType(ExpressionType.EX);
//		(2) YES;
		assertTrue(Algorithms.executeOperation(this.states.get(0), expression));
		
//		(3) M, s1 |= AG (q ∨ r);
		Expression OR = new Expression("q OR r");
		OR.setExp1(new Expression("q"));
		OR.setExp2(new Expression("r"));
		OR.setType(ExpressionType.OR);
		expression = new Expression("AG (q OR r)");
		expression.setExp1(OR);
		expression.setType(ExpressionType.AG);
//		(3) YES ;
		assertTrue(Algorithms.executeOperation(this.states.get(1), expression));
		
//		(4) M, s2 |= A [r U q];
		expression = new Expression("A [r U q]");
		expression.setExp1(new Expression("r"));
		expression.setExp2(new Expression("q"));
		expression.setType(ExpressionType.AU);
//		(4) YES;
		assertTrue(Algorithms.executeOperation(this.states.get(2), expression));
		
		
		
//		(5) M, s1 |= A [q U AG(r)];
//		(5) NO (because AG(r) is never true if you keep looping between s0 and s1);
		
//		(6) M, s1 |= E[q U EG(r)];
//		(6) YES;
		
//		(7) M, s0 |= ¬EG(q);
//		(7) YES;
		
//		(8) M, s1 |= EF (AG(q)).
//		(8) YES.
		
	}

}
