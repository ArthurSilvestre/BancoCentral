package br.unipe.cc.mlpIII.ui.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.unipe.cc.mlpIII.modelo.Conta;
import br.unipe.cc.mlpIII.repositorio.DataBase;
import br.unipe.cc.mlpIII.util.ErroLog;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;

public class TelaIncluirConta extends JFrame{
	private static final long serialVersionUID = 2516523575629056293L;
	private boolean update = false;
	private int codigo;

	private DataBase dataBase = new DataBase();
	
	private JTextField textFieldNumero;
	private JTextField textFieldSaldo;
	private JComboBox<String> comboBoxPessoa; 
	
	public TelaIncluirConta() {
		initialize();
		fillComboBox();
	}

	public TelaIncluirConta(Conta conta) {
		initialize();
		textFieldNumero.setText(conta.getNumero());
		textFieldSaldo.setText(Double.toString(conta.getSaldo()));
		comboBoxPessoa.removeAllItems();  
		fillComboBox();
		update = true;
		codigo = conta.getCodigo();
	}

	private void initialize() {
		dataBase.openConnection();

		new JFrame();
		setTitle("Conta");
		setBounds(100, 100, 450, 146);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo( null );
		getContentPane().setLayout(null);
		
		comboBoxPessoa = new JComboBox<String>();
		comboBoxPessoa.setBounds(103, 8, 321, 20);
		getContentPane().add(comboBoxPessoa);
		
		textFieldNumero = new JTextField();
		textFieldNumero.setColumns(10);
		textFieldNumero.setBounds(103, 39, 113, 20);
		getContentPane().add(textFieldNumero);
		
		textFieldSaldo = new JTextField();
		textFieldSaldo.setColumns(10);
		textFieldSaldo.setBounds(289, 40, 135, 20);
		getContentPane().add(textFieldSaldo);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (confirmar()){
					fechar();
				}
			}
		});
		btnSalvar.setBounds(236, 78, 89, 23);
		getContentPane().add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechar();
			}
		});
		btnCancelar.setBounds(335, 78, 89, 23);
		getContentPane().add(btnCancelar);
		
		JLabel lblPessoa = new JLabel("Respons\u00E1vel");
		lblPessoa.setBounds(10, 11, 67, 14);
		getContentPane().add(lblPessoa);
		
		JLabel lblNumero = new JLabel("N\u00FAmero da conta");
		lblNumero.setBounds(10, 43, 87, 14);
		getContentPane().add(lblNumero);
		
		JLabel lblSaldo = new JLabel("Saldo inicial");
		lblSaldo.setBounds(226, 43, 87, 14);
		getContentPane().add(lblSaldo);

		this.setVisible(true);
	}
	
	private boolean confirmar(){
		StringTokenizer stringTokenizerDataBaseSettings = new StringTokenizer(comboBoxPessoa.getSelectedItem().toString());
		
		try {
			if (update){
				dataBase.execute("update contas set codpessoa = '" + stringTokenizerDataBaseSettings.nextToken(" - ") + "', numero = '" + textFieldNumero.getText() + "', saldo = " + textFieldSaldo.getText() + " where codigo = " + codigo);
			} else {
				dataBase.execute("insert into contas (codpessoa, numero, saldo) values (" + stringTokenizerDataBaseSettings.nextToken(" - ") + ", '" + textFieldNumero.getText() + "', " + textFieldSaldo.getText() + ")");
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private void fechar(){
		dataBase.closeConnection();
		dispose();
	}
	
	private void fillComboBox(){
		dataBase.query("Select * from pessoa");

		try {
			while (dataBase.getDbResultSet().next()){
				comboBoxPessoa.addItem(dataBase.getDbResultSet().getString("codigo") + " - " + dataBase.getDbResultSet().getString("nome"));
			}
		} catch (SQLException e) {
			ErroLog.gravarErroLog(e.toString(), e.getStackTrace());
		}
	}
	
}
