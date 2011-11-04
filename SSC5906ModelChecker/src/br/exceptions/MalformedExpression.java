package br.exceptions;

public class MalformedExpression extends Exception {

	public MalformedExpression() {
		super("The expression contains some error");
	}
}
