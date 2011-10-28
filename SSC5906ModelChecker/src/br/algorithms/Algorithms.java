package br.algorithms;

import java.util.ArrayList;

import br.mef.Expression;
import br.mef.Property;
import br.mef.State;

public class Algorithms {
	
	public static boolean Alg_E(State st, Expression exp1, Expression exp2){
		boolean resposta = false;
		//algoritmo para verificar se uma express�o e � v�lida
		return resposta;
	}
	
	public static boolean Alg_Id(State st, Expression exp1){
		boolean resposta = false;
		//algoritmo para verificar se uma propriedade  � v�lida em um estado
		//se o type dela for id, o name ser� uma propriedade...
		ArrayList<Property> properties = st.getValidProperties();
		for (int i = 0; i < properties.size(); ++i) {
    		Property prop = (Property) properties.get(i);
    		if (prop.getName().equals(exp1.getName())){
    			resposta = true;
    			//st.addLabel(exp.getLabel());
    			//ficou faltando a parte de associar um label a uma express�o
    			//acho q daria certo se no icmcJCM.jj criasse um atributo global
    			// do tipo inteiro q fosse sendo incrementado sempre que uma nova 
    			//express�o � criada e setando essa vari�vel no label (expression.setLAbel());
    			//ai, nesse trecho adicionaria o label da express�o
    			//depois, para verificar se a express�o � v�lida em um determinado
    			//estado, bastaria verificar se o estado possui o label
    			break;
    		}
    	}		
		return resposta;
	}
	
}
