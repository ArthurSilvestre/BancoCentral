package br.unipe.cc.mlpIII.exception;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EntidadeJaCadastradaException extends Exception implements Serializable  {

	public EntidadeJaCadastradaException(){

		super("Exceção: Entidade já está cadastrada");

	}

}
