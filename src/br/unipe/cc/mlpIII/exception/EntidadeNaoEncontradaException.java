package br.unipe.cc.mlpIII.exception;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EntidadeNaoEncontradaException extends Exception implements Serializable {
	
	public EntidadeNaoEncontradaException(){
	
		super("Exce��o: Entidade N�o Encontrada");
	
	}

}
