package br.unipe.cc.mlpIII.ui.gui;

import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import br.unipe.cc.mlpIII.respositorio.DataBase;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TelaCadastroClassesModelo extends JFrame{
	private DataBase dataBase;
	private String tableQuery;
	private String[] fieldsQuery;
	private String[][] dataQuery;
	private String conditionQuery;

	private String[] titlesTable;
	private JTable table;
	private JScrollPane jScrollPane;
	private DefaultTableModel modelTable;
	
	public TelaCadastroClassesModelo(String selectTable, String[] fields, String[] titles, String conditionQuery) {
		new JFrame("Usuarios");
		
		this.tableQuery = selectTable;
		this.fieldsQuery = fields;
		this.titlesTable = titles;
		this.conditionQuery = conditionQuery;
		
		this.dataBase = new DataBase();
		this.modelTable = new DefaultTableModel(this.dataQuery, this.titlesTable);
		this.table = new JTable(modelTable);
		this.jScrollPane = new JScrollPane(table);
		
		dataBase.openConnection();
		
		initialize();
		fillModelTable();
		
		this.setVisible(true);
	}	

	private void initialize() {
		this.setResizable(false);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCadastroClassesModelo.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		this.setBounds(100, 100, 600, 650);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		this.jScrollPane.setBounds(10, 11, 574, 540);				
		this.getContentPane().add(this.jScrollPane);

		JButton retornar = new JButton("");
		retornar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataBase.closeConnection();
				dispose();
			}
		});
		retornar.setIcon(new ImageIcon(TelaCadastroClassesModelo.class.getResource("/com/sun/javafx/scene/web/skin/Redo_16x16_JFX.png")));
		retornar.setBounds(524, 562, 60, 49);
		this.getContentPane().add(retornar);
		
		JButton excluir = new JButton("");
		excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletarRegistro();
			}
		});
		excluir.setIcon(new ImageIcon(TelaCadastroClassesModelo.class.getResource("/com/sun/deploy/uitoolkit/impl/fx/ui/resources/image/graybox_error.png")));
		excluir.setBounds(454, 562, 60, 49);
		this.getContentPane().add(excluir);
		
		JButton alterar = new JButton("");
		alterar.setIcon(new ImageIcon(TelaCadastroClassesModelo.class.getResource("/com/sun/javafx/scene/web/skin/Paste_16x16_JFX.png")));
		alterar.setBounds(384, 562, 60, 49);
		this.getContentPane().add(alterar);
		
		JButton incluir = new JButton("");
		incluir.setIcon(new ImageIcon(TelaCadastroClassesModelo.class.getResource("/com/sun/javafx/scene/web/skin/Copy_16x16_JFX.png")));
		incluir.setBounds(314, 562, 60, 49);
		this.getContentPane().add(incluir);
	}
	
	public void fillModelTable(){
		String sQuery = "SELECT ";
		
		if (fieldsQuery.length > 0){
			for (int i = 0; i < fieldsQuery.length; i++) 
				sQuery += (i == 0 ? "" : ",") + fieldsQuery[i];
		} else {
			sQuery += "*";
		}
		
		sQuery += " FROM " + this.tableQuery;
		
		if (!this.conditionQuery.isEmpty()) 
			sQuery += " WHERE " + this.conditionQuery;
		
		dataBase.query(sQuery);

		try {
			while (dataBase.getDbResultSet().next()){
				List<Object> fields = new ArrayList<Object>();
				
				for (int i = 0; i < this.fieldsQuery.length; i++)
					fields.add(dataBase.getDbResultSet().getObject(this.fieldsQuery[i]));

				modelTable.addRow(fields.toArray());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deletarRegistro(){
		int row = table.getSelectedRow();
		String codigo = modelTable.getValueAt(row, 0).toString();
		String message = "Deseja realmente excluir este registro?";
		String title = "Confirmação";
		
		if (JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
			dataBase.delete(this.tableQuery, "codigo = " + codigo);
			modelTable.removeRow(row);
		}
	}
}
