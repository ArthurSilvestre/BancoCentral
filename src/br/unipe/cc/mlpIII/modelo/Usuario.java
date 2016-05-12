package br.unipe.cc.mlpIII.modelo;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;

public class Usuario {
	private int codigo;
	private String id;
	private String senha;
	private int nivel;
	private Text digital;
	
	//Constructors
	public Usuario(){
		
	}
	
	public Usuario(int codigo, String id, String senha, int nivel, Text digital) {
		super();
		this.codigo = codigo;
		this.id = id;
		this.senha = senha;
		this.nivel = nivel;
		this.digital = digital;
	}

	//Methods
	
	//Get's and set's methods
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public Text getDigital() {
		return digital;
	}

	public void setDigital(Text digital) {
		this.digital = digital;
	}

	//Override methods
	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", id=" + id + ", senha=" + senha + ", nivel=" + nivel + ", digital=" + digital + "]";
	}
	
}
