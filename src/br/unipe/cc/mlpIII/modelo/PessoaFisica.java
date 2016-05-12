package br.unipe.cc.mlpIII.modelo;

import java.util.Date;

public class PessoaFisica extends Pessoa {
	private String rg;
	private String cpf;
	private char sexo;
	private Date datanascimento;
	
	//Constructors
	public PessoaFisica(){
		
	}

	public PessoaFisica(int codigo, String nome, Date datanascimento, char sexo, String rg, String cpf) {
		super(codigo, nome);
		this.datanascimento = datanascimento;
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

	public Date getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}

	//Override methods
	@Override
	public String toString() {
		return "PessoaFisica [Pessoa="+super.toString()+", sexo=" + sexo + ", rg=" + rg + ", cpf=" + cpf + ", datanascimento=" + datanascimento+ "]";
	}

}
