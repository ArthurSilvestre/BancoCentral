package br.unipe.cc.mlpIII.modelo;

import junit.framework.TestCase;

public class ContaTest extends TestCase {
	private int codigo;
	private Pessoa responsavel;
	private String numero;
	private double saldo;

	//Constructors
	public ContaTest(){
		
	}

	public ContaTest(int codigo, Pessoa responsavel, String numero, double saldo) {
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
	
	public void testMethods(){
		creditar(500.00);
		debitar(10.00);
	}
	
	//Override methods
	@Override
	public String toString() {
		return "Conta [codigo=" + codigo + ", codpessoa=" + responsavel + ", numero=" + numero + ", saldo=" + saldo + "]";
	}
	
	@Override
	protected void setUp() throws Exception {
		Pessoa p = new Pessoa(1,"Larissa Targino");
		new ContaTest(1, p , "548245171", 25421.11); 
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
