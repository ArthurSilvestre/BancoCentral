package br.unipe.cc.mlpIII.modelo;

public class PessoaFisica extends Pessoa {
	private String rg;
	private String cpf;
	private char sexo;
	
	//Constructors
	public PessoaFisica(){
		
	}

	public PessoaFisica(int codigo, String nome, char sexo, String rg, String cpf) {
		super(codigo, nome);
		this.sexo = sexo;
		this.rg = rg;
		this.cpf = cpf;
	}

	//Methods
	
	//Get's and set's methods
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	//Override methods
	@Override
	public String toString() {
		return "PessoaFisica [Pessoa="+super.toString()+", sexo=" + sexo + ", rg=" + rg + ", cpf=" + cpf + "]";
	}

}
