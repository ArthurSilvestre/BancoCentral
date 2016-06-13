package br.unipe.cc.mlpIII.ui.gui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.unipe.cc.mlpIII.repositorio.DataBase;
import br.unipe.cc.mlpIII.util.ErroLog;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TelaExtrato extends JFrame{
	private static final long serialVersionUID = -7116841538875284209L;

	private DataBase dataBase = new DataBase();
	
	private JComboBox<String> comboBox = new JComboBox<String>();
	
	private DefaultTableModel modelTable = new DefaultTableModel();
	private JTable table = new JTable(modelTable);
	private JScrollPane jScrollPane = new JScrollPane(table);
	
	private JLabel lblSaldoAtual;

	public TelaExtrato() {
		dataBase.openConnection();
		fillComboBox();
		initialize();
	}

	private void initialize() {
		new JFrame();
		setBounds(100, 100, 450, 635);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo( null );
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				fechar();
			}
		});
		
		jScrollPane.setBounds(10, 61, 414, 525);
		getContentPane().add(jScrollPane);
		
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				consultaExtratoConta();
			}
		});
		comboBox.setBounds(61, 8, 363, 20);
		getContentPane().add(comboBox);
		
		JLabel lblConta = new JLabel("Conta");
		lblConta.setBounds(10, 11, 41, 14);
		getContentPane().add(lblConta);
		
		lblSaldoAtual = new JLabel("Saldo Atual:");
		lblSaldoAtual.setBounds(10, 36, 414, 14);
		getContentPane().add(lblSaldoAtual);
		
		modelTable.addColumn("Tipo da Operação");
		modelTable.addColumn("Valor");
		modelTable.addColumn("Data");
		
		fillModelTable();
		
		setVisible(true);
	}
	
	private void fillComboBox(){
		dataBase.query("Select * from contas");

		try {
			while (dataBase.getDbResultSet().next()){
				comboBox.addItem(dataBase.getDbResultSet().getString("codigo") + " - " + dataBase.getDbResultSet().getString("numero"));
			}
		} catch (SQLException e) {
			ErroLog.gravarErroLog(e.toString(), e.getStackTrace());
		}
	}
	
	public void consultaExtratoConta(){
		fillModelTable();
	}
	
	private void fechar(){
		dataBase.closeConnection();
	}
	
	public void fillModelTable(){
		while (modelTable.getRowCount() > 0) {  
			modelTable.removeRow(0);  
	    } 
		
		StringTokenizer stringTokenizerDataBaseSettings = new StringTokenizer(comboBox.getSelectedItem().toString());
		
		String conta = stringTokenizerDataBaseSettings.nextToken(" - ");
		
		dataBase.query("Select * from extrato where conta = " + conta);

		try {
			while (dataBase.getDbResultSet().next()){
				modelTable.addRow( new Object[] {dataBase.getDbResultSet().getString("tipooperacao"),dataBase.getDbResultSet().getDouble("valor"),dataBase.getDbResultSet().getDate("data")} );
			}
		} catch (SQLException e) {
			ErroLog.gravarErroLog(e.toString(), e.getStackTrace());
		}
		
		dataBase.query("Select * from contas where codigo = " + conta);

		try {
			if (dataBase.getDbResultSet().next()){
				lblSaldoAtual.setText("Saldo Atual: R$ " + dataBase.getDbResultSet().getDouble("saldo"));
			}
		} catch (SQLException e) {
			ErroLog.gravarErroLog(e.toString(), e.getStackTrace());
		}
	}
}
