package br.unipe.cc.mlpIII.ui.gui;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JTextField;

import br.unipe.cc.mlpIII.modelo.Conta;
import br.unipe.cc.mlpIII.repositorio.DataBase;
import br.unipe.cc.mlpIII.util.ErroLog;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class TelaLancamentos extends JFrame{
	private static final long serialVersionUID = -6964346871283037677L;

	private DataBase dataBase = new DataBase();
	
	private JLabel label = new JLabel();
	private JLabel label_1 = new JLabel();	
	private JTextField textField;
	private JComboBox<String> comboBox = new JComboBox<String>();
	private JRadioButton rdbtnDebitar = new JRadioButton("Debitar", true);
	private JRadioButton rdbtnCreditar = new JRadioButton("Creditar", false);
	
	private Conta contaAtual = new Conta();
	
	public TelaLancamentos() {
		dataBase.openConnection();
		fillComboBox();
		initialize();
	}

	private void initialize() {
		new JFrame();
		setBounds(100, 100, 450, 327);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo( null );
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaLancamentos.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		
		rdbtnDebitar.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (rdbtnDebitar.isSelected()){
					rdbtnCreditar.setSelected(false);			
				}
			}
		});
		rdbtnDebitar.setBounds(10, 164, 109, 23);
		getContentPane().add(rdbtnDebitar);
		rdbtnCreditar.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (rdbtnCreditar.isSelected()){
					rdbtnDebitar.setSelected(false);			
				}
			}
		});
		
		rdbtnCreditar.setBounds(10, 190, 109, 23);
		getContentPane().add(rdbtnCreditar);
		
		JLabel lblBemVindoAo = new JLabel("Bem vindo ao seu auto atendimento CAIXA");
		lblBemVindoAo.setFont(new Font("Bodoni MT", Font.BOLD | Font.ITALIC, 19));
		lblBemVindoAo.setBounds(28, 11, 396, 14);
		getContentPane().add(lblBemVindoAo);
		
		JLabel lblResponsavel = new JLabel("RESPONS\u00C1VEL");
		lblResponsavel.setBounds(10, 69, 89, 14);
		getContentPane().add(lblResponsavel);
		
		JLabel lblNmero = new JLabel("CONTA");
		lblNmero.setBounds(10, 50, 46, 14);
		getContentPane().add(lblNmero);
		
		JLabel lblSaldo = new JLabel("SALDO");
		lblSaldo.setBounds(10, 89, 46, 14);
		getContentPane().add(lblSaldo);
		
		JLabel lblQualOperaoDeseja = new JLabel("Qual opera\u00E7\u00E3o deseja realizar?");
		lblQualOperaoDeseja.setBounds(10, 143, 185, 14);
		getContentPane().add(lblQualOperaoDeseja);
		
		JLabel lblValor = new JLabel("Valor: R$");
		lblValor.setBounds(10, 224, 51, 14);
		getContentPane().add(lblValor);
		
		textField = new JTextField();
		textField.setBounds(76, 221, 98, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharTelaLancamentos();
			}
		});
		btnCancelar.setBounds(335, 254, 89, 23);
		getContentPane().add(btnCancelar);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (confirmar()){
					fecharTelaLancamentos();	
				}
			}
		});
		btnConfirmar.setBounds(227, 254, 98, 23);
		getContentPane().add(btnConfirmar);

		label.setBounds(99, 69, 298, 14);
		getContentPane().add(label);
		
		label_1.setBounds(99, 89, 298, 14);
		getContentPane().add(label_1);

		comboBox.setBounds(60, 47, 135, 20);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				changComponentes();
			}
		});
		getContentPane().add(comboBox);
		
		changComponentes();

		this.setVisible(true);
	}
	
	public void fecharTelaLancamentos(){
		dataBase.closeConnection();
		dispose();
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
	
	public void changComponentes(){
		StringTokenizer stringTokenizerDataBaseSettings = new StringTokenizer(comboBox.getSelectedItem().toString());
		
		String conta = stringTokenizerDataBaseSettings.nextToken(" - ");
		
		dataBase.query("Select P.nome, C.codigo, C.numero, C.saldo from contas As C Inner Join Pessoa As P On C.codpessoa = P.codigo where C.codigo = " + conta);

		try {
			if (dataBase.getDbResultSet().next()){
				label.setText(dataBase.getDbResultSet().getString("P.nome"));
				label_1.setText("R$ " + dataBase.getDbResultSet().getString("C.saldo"));
			
				contaAtual = new Conta(dataBase.getDbResultSet().getInt("C.codigo"), dataBase.getDbResultSet().getString("P.nome"), dataBase.getDbResultSet().getString("C.numero"), dataBase.getDbResultSet().getInt("C.saldo"));
			}
		} catch (SQLException e) {
			ErroLog.gravarErroLog(e.toString(), e.getStackTrace());
		}
	}
	
	public boolean confirmar(){
		try{

			if (rdbtnDebitar.isSelected()) {
				this.contaAtual.debitar( Integer.parseInt(textField.getText()) );
				dataBase.execute("insert into extrato (conta, tipooperacao, valor, data) values (" + contaAtual.getCodigo() + ", 'D', " + textField.getText() + ", now())");
			} else if (rdbtnCreditar.isSelected()) {
				this.contaAtual.creditar( Integer.parseInt(textField.getText()) );
				dataBase.execute("insert into extrato (conta, tipooperacao, valor, data) values (" + contaAtual.getCodigo() + ", 'C', " + textField.getText() + ", now())");
			}
		
			dataBase.execute("update contas set saldo = " + contaAtual.getSaldo() + " where codigo = " + contaAtual.getCodigo());
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
}
