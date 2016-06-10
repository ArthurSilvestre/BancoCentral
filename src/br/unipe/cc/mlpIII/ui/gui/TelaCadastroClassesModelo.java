package br.unipe.cc.mlpIII.ui.gui;

import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

import br.unipe.cc.mlpIII.modelo.Conta;
import br.unipe.cc.mlpIII.modelo.PessoaFisica;
import br.unipe.cc.mlpIII.modelo.PessoaJuridica;
import br.unipe.cc.mlpIII.modelo.Usuario;
import br.unipe.cc.mlpIII.relatorios.Listagem;
import br.unipe.cc.mlpIII.repositorio.DataBase;
import br.unipe.cc.mlpIII.util.ErroLog;
import br.unipe.cc.mlpIII.util.JFunctions;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TelaCadastroClassesModelo extends JFrame{
	private DataBase dataBase = new DataBase();
	
	private String tableQuery;
	private String[] fieldsQuery;
	private String[] titlesTable;
	private String conditionQuery;

	private JFrame telaIncluir = new JFrame();
	
	private DefaultTableModel modelTable = new DefaultTableModel();
	private JTable table = new JTable(modelTable);
	private JScrollPane jScrollPane = new JScrollPane(table);
	
	private JButton incluir = new JButton("");
	private JButton alterar = new JButton("");
	private JButton excluir = new JButton("");
	private JButton relatorio = new JButton("");
	private JButton retornar = new JButton("");
	
	//Constructor
	public TelaCadastroClassesModelo(String tableQuery, String[] fieldsQuery, String[] titlesTable, String conditionQuery) {
		new JFrame();
		
		this.tableQuery = tableQuery;
		this.fieldsQuery = fieldsQuery;
		this.titlesTable = titlesTable;
		this.conditionQuery = conditionQuery;
		
		for (int i = 0; i < this.titlesTable.length; i++) 
			this.modelTable.addColumn(this.titlesTable[i]);
		
		dataBase.openConnection();
		
		initialize();
		fillModelTable();
		
		this.setVisible(true);
	}	

	//Methods
	private void initialize() {
		this.setResizable(false);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCadastroClassesModelo.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		this.setBounds(100, 100, 600, 650);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo( null );

		this.jScrollPane.setBounds(10, 11, 574, 540);				

		this.incluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirRegistro();
			}
		});
		this.incluir.setIcon(new ImageIcon(TelaCadastroClassesModelo.class.getResource("/com/sun/javafx/scene/web/skin/Copy_16x16_JFX.png")));
		this.incluir.setBounds(244, 562, 60, 49);
		
		this.alterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizarRegistro();
			}
		});
		this.alterar.setIcon(new ImageIcon(TelaCadastroClassesModelo.class.getResource("/com/sun/javafx/scene/web/skin/Paste_16x16_JFX.png")));
		this.alterar.setBounds(314, 562, 60, 49);

		this.excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletarRegistro();
			}
		});
		this.excluir.setIcon(new ImageIcon(TelaCadastroClassesModelo.class.getResource("/com/sun/deploy/uitoolkit/impl/fx/ui/resources/image/graybox_error.png")));
		this.excluir.setBounds(384, 562, 60, 49);

		this.relatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gerarRelatorioRegistros();
			}
		});
		this.relatorio.setIcon(new ImageIcon(TelaCadastroClassesModelo.class.getResource("/sun/print/resources/orientPortrait.png")));
		this.relatorio.setBounds(454, 562, 60, 49);

		this.retornar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataBase.closeConnection();
				dispose();
			}
		});
		this.retornar.setIcon(new ImageIcon(TelaCadastroClassesModelo.class.getResource("/com/sun/javafx/scene/web/skin/Redo_16x16_JFX.png")));
		this.retornar.setBounds(524, 562, 60, 49);

		this.getContentPane().add(this.jScrollPane);
		this.getContentPane().add(this.incluir);
		this.getContentPane().add(this.alterar);		
		this.getContentPane().add(this.excluir);
		this.getContentPane().add(this.relatorio);
		this.getContentPane().add(this.retornar);		
	}
	
	public void fillModelTable(){
		String sQuery = "SELECT ";
		List<Object> fields;
		
		if (fieldsQuery.length > 0)
			for (int i = 0; i < fieldsQuery.length; i++) sQuery += (i == 0 ? "" : ",") + fieldsQuery[i];
		else 
			sQuery += "*";
		
		sQuery += " FROM " + this.tableQuery;
		
		if (!this.conditionQuery.isEmpty()) sQuery += " WHERE " + this.conditionQuery;
		
		dataBase.query(sQuery);

		try {
			while (dataBase.getDbResultSet().next()){
				fields = new ArrayList<Object>();
				
				for (int i = 0; i < this.fieldsQuery.length; i++)
					fields.add(dataBase.getDbResultSet().getObject(this.fieldsQuery[i]));

				modelTable.addRow(fields.toArray());
			
			}
		} catch (SQLException e) {
			ErroLog.gravarErroLog(e.toString(), e.getStackTrace());
		}
	}
	
	public void incluirRegistro(){
		switch (tableQuery) {
			case "pessoa As P, contas As Con":{
				telaIncluir = new TelaIncluirConta();
				break;
			}
			case "pessoa As P, pessoafisica As PF":{
				telaIncluir = new TelaIncluirPessoaFisica();
				break;
			}
			case "pessoa As P, pessoajuridica As PJ":{
				telaIncluir = new TelaIncluirPessoaJuridica(); 
				break;
			}
			case "usuario":{
				telaIncluir = new TelaIncluirUsuario();
				break;
			}
		}
		
		telaIncluir.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				refreshTable();
			}
		});
	}
	
	public void atualizarRegistro(){
		int row = table.getSelectedRow();

		if (row >= 0){
			switch (tableQuery) {
				case "pessoa As P, contas As Con":{
					telaIncluir = new TelaIncluirConta(new Conta((int) modelTable.getValueAt(row, 0), modelTable.getValueAt(row, 1).toString() , modelTable.getValueAt(row, 2).toString(), (double) modelTable.getValueAt(row, 3)));
					break;
				}
				case "pessoa As P, pessoafisica As PF":{
					telaIncluir = new TelaIncluirPessoaFisica(new PessoaFisica((int) modelTable.getValueAt(row, 0), modelTable.getValueAt(row, 1).toString(), modelTable.getValueAt(row, 4).toString().charAt(0), modelTable.getValueAt(row, 2).toString(), modelTable.getValueAt(row, 3).toString()));
					break;
				}
				case "pessoa As P, pessoajuridica As PJ":{
					telaIncluir = new TelaIncluirPessoaJuridica(new PessoaJuridica((int) modelTable.getValueAt(row, 0), modelTable.getValueAt(row, 1).toString(), modelTable.getValueAt(row, 2).toString(), modelTable.getValueAt(row, 3).toString())); 
					break;
				}
				case "usuario":{
					telaIncluir = new TelaIncluirUsuario(new Usuario((int) modelTable.getValueAt(row, 0), modelTable.getValueAt(row, 1).toString(), "", (int) modelTable.getValueAt(row, 2)));
					break;
				}
			}
		}

		telaIncluir.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				refreshTable();
			}
		});
	}
	
	public void deletarRegistro(){
		int row = table.getSelectedRow();
		String codigo = modelTable.getValueAt(row, 0).toString();
		String message = "Deseja realmente excluir este registro?";
		String title = "Confirmação";
		StringTokenizer stringTokenizerDataBaseSettings = new StringTokenizer( this.tableQuery == "pessoa As P, contas As Con" ? "contas" :	this.tableQuery	);
		
		if (JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
			dataBase.delete(stringTokenizerDataBaseSettings.nextToken(" "), "codigo = " + codigo);
			modelTable.removeRow(row);
		}
	}
	
	public void gerarRelatorioRegistros(){
		String row = new String(); 
		Vector<Integer> rows = new Vector<Integer>();
		List<String> dados = new ArrayList<String>(); 
		
		for (int i = 0; i < this.fieldsQuery.length; i++)
			row += ( i == 0 ? "" : ",") + titlesTable[i] + JFunctions.replicate(" ", 30 - titlesTable[i].length());
		
		dados.add(row);
		
		for (int i = 0; i < this.table.getSelectedRows().length; i++)
			rows.add( this.table.getSelectedRows()[i] );
		
		if (rows.toArray().length > 0){
			for (int i = 0 ; i < modelTable.getRowCount() ; i++) {
				if (rows.contains(i)){
					row = ""; 

					for (int j = 0; j < this.fieldsQuery.length; j++)
						row += ( j == 0 ? "" : ",") + modelTable.getValueAt(i, j).toString() + JFunctions.replicate(" ", 30 - modelTable.getValueAt(i, j).toString().length());
					
					dados.add(row);
				}
			}
			
			Listagem relatorioListagem = new Listagem();
			relatorioListagem.gerarRelatorio(dados.toArray());
			try {
				relatorioListagem.finalizarRelatorio();
			} catch (IOException e) {
				ErroLog.gravarErroLog(e.toString(), e.getStackTrace());
			}
			relatorioListagem.abrirRelatorio();
		} else {
			//TODO: Mensagem do else
		}
	}
	
	public void refreshTable(){
		while (modelTable.getRowCount() > 0) {  
			modelTable.removeRow(0);  
	    } 
		
		fillModelTable();
	}
	
	//Get's and set's methods
	//Override methods
}
 