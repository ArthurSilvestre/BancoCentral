package br.unipe.cc.mlpIII.modelo;

public class Conta {
	private int codigo;
	private Pessoa responsavel;
	private String numero;
	private double saldo;
	
	//Constructors
	public Conta(){
		
	}

	public Conta(int codigo, Pessoa responsavel, String numero, double saldo) {
		this.codigo = codigo;
		this.responsavel = responsavel;
		this.numero = numero;
		this.saldo = saldo;
	}

	//Methods
	public void creditar(double valor){
		if (valor > 0){
			this.saldo += valor;
		} else {
			//TODO: Mensagem para valor menor que 0.
		}
	}
	
	public void debitar(double valor){
		if (valor > 0){
			this.saldo -= valor;
		} else {
			//TODO: Mensagem para valor menor que 0.
		}
	}
	
	//Get's and set's methods
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Pessoa getCodpessoa() {
		return responsavel;
	}

	public void setCodpessoa(Pessoa responsavel) {
		this.responsavel = responsavel;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	//Override methods
	@Override
	public String toString() {
		return "Conta [codigo=" + codigo + ", codpessoa=" + responsavel + ", numero=" + numero + ", saldo=" + saldo + "]";
	}
	
}
