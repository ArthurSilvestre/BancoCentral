package br.unipe.cc.mlpIII.exception;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EntidadeJaCadastradaException extends Exception implements Serializable  {

	public EntidadeJaCadastradaException(){

		super("Exce��o: Entidade j� est� cadastrada");

	}

}
