package br.unipe.cc.mlpIII.relatorios;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class Listagem {
	private String fileLocation;
	private Scanner scanner;
	private FileWriter fileWriter;
	private PrintWriter printWriter;
	private List<Object> objects;
	
	public Listagem(){
		try {
			this.inicializarRelatorio();
		} catch (IOException e) {
			e.printStackTrace(); //TODO: Menssagem do catch
		}
	}
	
	public void gerarRelatorio(){
		this.printWriter.println("Relatorio");
		this.printWriter.println("--------------------------------------------------");
		for	(Object	object : objects){ 
			this.printWriter.println(object.toString());
		}
		this.printWriter.println("--------------------------------------------------");
	}	

	public void inicializarRelatorio() throws IOException {
		this.fileLocation = "relatorio.txt";
		this.scanner = new Scanner(System.in);
		this.fileWriter = new FileWriter("relatorios\\" + this.fileLocation);
		this.printWriter = new PrintWriter(this.fileWriter);
	}
	
	public void finalizarRelatorio() throws IOException {
		this.fileLocation = "";
		this.scanner.close();
		this.fileWriter.close();
		this.printWriter.close();
	}

	public List<Object> getObjects() {
		return objects;
	}

	public void setObjects(List<Object> objects) {
		this.objects = objects;
	}
}
