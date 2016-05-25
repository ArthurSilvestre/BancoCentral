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
import br.unipe.cc.mlpIII.relatorios.Listagem;
import br.unipe.cc.mlpIII.repositorio.DataBase;
import br.unipe.cc.mlpIII.util.JFunctions;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TelaCadastroClassesModelo extends JFrame{
	private DataBase dataBase = new DataBase();
	
	private String tableQuery;
	private String[] fieldsQuery;
	private String[] titlesTable;
	private String conditionQuery;

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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			e.printStackTrace(); //TODO: Mensagem do catch
		}
	}
	
	public void incluirRegistro(){
		
	}
	
	public void atualizarRegistro(){
		
	}
	
	public void deletarRegistro(){
		int row = table.getSelectedRow();
		String codigo = modelTable.getValueAt(row, 0).toString();
		String message = "Deseja realmente excluir este registro?";
		String title = "Confirmação";
		StringTokenizer stringTokenizerDataBaseSettings = new StringTokenizer(this.tableQuery);

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
				e.printStackTrace(); //TODO: Mensagem do catch
			}
			relatorioListagem.abrirRelatorio();
		} else {
			//TODO: Mensagem do else
		}
	}

	//Get's and set's methods
	//Override methods
}
