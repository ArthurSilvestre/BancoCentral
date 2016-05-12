package br.unipe.cc.mlpIII.modelo;

public class Pessoa {
	private int codigo;
	private String nome;
	
	//Constructors
	public Pessoa(){
		
	}
	
	public Pessoa(int codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
	
	//Methods
	
	//Get's and set's methods
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	//Override methods
	@Override
	public String toString() {
		return "Pessoa [codigo=" + codigo + ", nome=" + nome + "]";
	}

}