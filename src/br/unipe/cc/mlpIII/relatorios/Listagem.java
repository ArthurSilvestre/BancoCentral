package br.unipe.cc.mlpIII.relatorios;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import br.unipe.cc.mlpIII.util.ErroLog;

public class Listagem {
	private String fileLocation;
	private FileWriter fileWriter;
	private PrintWriter printWriter;
	private List<Object> objects;
	
	//Constructor
	public Listagem(){
		try {
			this.inicializarRelatorio();
		} catch (IOException e) {
			ErroLog.gravarErroLog(e.toString(), e.getStackTrace());
		}
	}

	//Methods	
	public void inicializarRelatorio() throws IOException {
		this.fileLocation = "relatorio.txt";
		this.fileWriter = new FileWriter("relatorios\\" + this.fileLocation);
		this.printWriter = new PrintWriter(this.fileWriter);
	}
	
	public void finalizarRelatorio() throws IOException {
		this.fileWriter.close();
		this.printWriter.close();
	}

	public void gerarRelatorio(){
		this.printWriter.println("Centro Universitário de joão Pessoa - UNIPE");
		this.printWriter.println("Metodologia e Linguagem de programação avançada");
		this.printWriter.println();
		this.printWriter.println("Listagem de " + objects.getClass());
		this.printWriter.println("--------------------------------------------------------------------------------------------------------------------------");
		this.printWriter.println();
		for	(Object	object : objects){ 
			this.printWriter.println(object);
		}
		this.printWriter.println();
		this.printWriter.println("--------------------------------------------------------------------------------------------------------------------------");
	}
	
	public void gerarRelatorio(Object[] dados){
		this.printWriter.println("Centro Universitário de joão Pessoa - UNIPE");
		this.printWriter.println("Metodologia e Linguagem de programação avançada");
		this.printWriter.println();
		this.printWriter.println("--------------------------------------------------------------------------------------------------------------------------");
		this.printWriter.println();
		for (int i = 0; i < dados.length; i++) {
			this.printWriter.println((String) dados[i]);
		}
		this.printWriter.println();
		this.printWriter.println("--------------------------------------------------------------------------------------------------------------------------");
	}

	public void abrirRelatorio(){
		try {
			Desktop.getDesktop().open( new File( "relatorios\\" + this.fileLocation ) );
		} catch (IOException e) {
			ErroLog.gravarErroLog(e.toString(), e.getStackTrace());
		}
	}
	
	//Get's and set's methods
	public List<Object> getObjects() {
		return objects;
	}

	public void setObjects(List<Object> objects) {
		this.objects = objects;
	}

	//Override methods
}
