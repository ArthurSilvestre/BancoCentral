package br.unipe.cc.mlpIII.modelo;

public class Usuario {
	private int codigo;
	private String id;
	private String senha;
	private int nivel;
	
	//Constructors
	public Usuario(){
		
	}
	
	public Usuario(int codigo, String id, String senha, int nivel) {
		super();
		this.codigo = codigo;
		this.id = id;
		this.senha = senha;
		this.nivel = nivel;
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

	//Override methods
	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", id=" + id + ", senha=" + senha + ", nivel=" + nivel + "]";
	}
	
}
