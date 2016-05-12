package br.unipe.cc.mlpIII.modelo;

public class PessoaJuridica extends Pessoa{
	private String inscricaoestadual;
	private String cnpj;
	
	//Constructors
	public PessoaJuridica(){
		
	}
	
	public PessoaJuridica(int codigo, String nome, String inscricaoestadual, String cnpj) {
		super(codigo, nome);
		this.inscricaoestadual = inscricaoestadual;
		this.cnpj = cnpj;
	}

	//Methods
	
	//Get's and set's methods
	public String getInscricaoestadual() {
		return inscricaoestadual;
	}

	public void setInscricaoestadual(String inscricaoestadual) {
		this.inscricaoestadual = inscricaoestadual;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	//Override methods
	@Override
	public String toString() {
		return "PessoaJuridica [Pessoa="+super.toString()+", inscricaoestadual=" + inscricaoestadual + ", cnpj=" + cnpj + "]";
	}
	
}
