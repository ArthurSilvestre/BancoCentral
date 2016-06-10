package br.unipe.cc.mlpIII.ui.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.unipe.cc.mlpIII.modelo.PessoaFisica;
import br.unipe.cc.mlpIII.repositorio.DataBase;

import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class TelaIncluirPessoaFisica extends JFrame {
	private static final long serialVersionUID = -1658058625777371587L;
	private boolean update = false;
	private int codigo;

	private DataBase dataBase = new DataBase();

	private JTextField textFieldNome;
	private JTextField textFieldIdentidade;
	private JTextField textFieldCPF;
	private JComboBox<String> comboBoxSexo;
	
	public TelaIncluirPessoaFisica() {
		initialize();
	}
	public TelaIncluirPessoaFisica(PessoaFisica pessoaFisica) {
		initialize();
		textFieldCPF.setText(pessoaFisica.getCpf());
		textFieldIdentidade.setText(pessoaFisica.getRg());
		textFieldNome.setText(pessoaFisica.getNome());
		
		switch (pessoaFisica.getSexo()) {
			case 'M':{
				comboBoxSexo.setSelectedIndex(0);
				break;
			}
			case 'F':{
				comboBoxSexo.setSelectedIndex(1);
				break;
			}
			case 'I':{
				comboBoxSexo.setSelectedIndex(2);
				break;
			}
		}
		
		update = true;
		codigo = pessoaFisica.getCodigo();
	}

	private void initialize() {
		dataBase.openConnection();

		new JFrame();
		setTitle("Pessoa F\u00EDsica");
		setBounds(100, 100, 410, 199);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo( null );
		getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 11, 46, 14);
		getContentPane().add(lblNome);
		
		JLabel lblIdentidade = new JLabel("Identidade");
		lblIdentidade.setBounds(10, 36, 60, 14);
		getContentPane().add(lblIdentidade);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(10, 61, 46, 14);
		getContentPane().add(lblCpf);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(10, 89, 46, 14);
		getContentPane().add(lblSexo);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(90, 8, 289, 20);
		getContentPane().add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldIdentidade = new JTextField();
		textFieldIdentidade.setBounds(90, 33, 121, 20);
		getContentPane().add(textFieldIdentidade);
		textFieldIdentidade.setColumns(10);
		
		textFieldCPF = new JTextField();
		textFieldCPF.setColumns(10);
		textFieldCPF.setBounds(90, 58, 121, 20);
		getContentPane().add(textFieldCPF);
		
		comboBoxSexo = new JComboBox<String>();
		comboBoxSexo.setModel(new DefaultComboBoxModel<String>(new String[] {"Masculino", "Feminino", "Indefinido"}));
		comboBoxSexo.setBounds(90, 86, 121, 20);
		getContentPane().add(comboBoxSexo);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (confirmar()){
					fechar();
				}
			}
		});
		btnSalvar.setBounds(191, 131, 89, 23);
		getContentPane().add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechar();
			}
		});
		btnCancelar.setBounds(290, 131, 89, 23);
		getContentPane().add(btnCancelar);

		this.setVisible(true);
	}
	
	private boolean confirmar(){
		try {
			if (update){
				dataBase.execute("update pessoa set nome = '" + textFieldNome.getText() + "' where codigo = " + codigo);
				dataBase.execute("update pessoafisica set rg = '" + textFieldIdentidade.getText() + "', cpf = '" + textFieldCPF.getText() + "', sexo = '" + comboBoxSexo.getItemAt(comboBoxSexo.getSelectedIndex()).charAt(0) + "' where codpessoa = " + codigo);
			} else {
				int insert = 0;

				dataBase.execute("insert into pessoa (nome) values ('" + textFieldNome.getText() + "')");
				
				dataBase.query("select * from pessoa where nome = '" + textFieldNome.getText() + "'");
				if (dataBase.getDbResultSet().next()){
					insert = dataBase.getDbResultSet().getInt("codigo");
				}
				
				dataBase.execute("insert into pessoafisica (codpessoa, rg, cpf, sexo) values (" + insert + ", '" + textFieldIdentidade.getText() + "', '" + textFieldCPF.getText() + "', '" + comboBoxSexo.getItemAt(comboBoxSexo.getSelectedIndex()).charAt(0) + "')");
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
}
