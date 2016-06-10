package br.unipe.cc.mlpIII.ui.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import br.unipe.cc.mlpIII.modelo.PessoaJuridica;
import br.unipe.cc.mlpIII.repositorio.DataBase;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class TelaIncluirPessoaJuridica extends JFrame {
	private static final long serialVersionUID = 2927645713782300339L;
	private boolean update = false;
	private int codigo;

	private DataBase dataBase = new DataBase();

	private JTextField textFieldNome;
	private JTextField textFieldInscricaoEstadual;
	private JTextField textFieldCNPJ;
	
	public TelaIncluirPessoaJuridica() {
		initialize();
	}
	
	public TelaIncluirPessoaJuridica(PessoaJuridica pessoaJuridica) {
		initialize();
		textFieldCNPJ.setText(pessoaJuridica.getCnpj());
		textFieldInscricaoEstadual.setText(pessoaJuridica.getInscricaoestadual());
		textFieldNome.setText(pessoaJuridica.getNome());
		update = true;
		codigo = pessoaJuridica.getCodigo();
	}

	private void initialize() {
		dataBase.openConnection();

		new JFrame();
		setTitle("Pessoa Jur\u00EDdica");
		setBounds(100, 100, 385, 174);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo( null );
		getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 11, 46, 14);
		getContentPane().add(lblNome);
		
		JLabel lblInscrioEstadual = new JLabel("Inscri\u00E7\u00E3o Estadual");
		lblInscrioEstadual.setBounds(10, 36, 87, 14);
		getContentPane().add(lblInscrioEstadual);
		
		JLabel lblCnpj = new JLabel("CNPJ");
		lblCnpj.setBounds(10, 61, 46, 14);
		getContentPane().add(lblCnpj);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(66, 8, 295, 20);
		getContentPane().add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldInscricaoEstadual = new JTextField();
		textFieldInscricaoEstadual.setBounds(107, 33, 110, 20);
		getContentPane().add(textFieldInscricaoEstadual);
		textFieldInscricaoEstadual.setColumns(10);
		
		textFieldCNPJ = new JTextField();
		textFieldCNPJ.setColumns(10);
		textFieldCNPJ.setBounds(66, 58, 110, 20);
		getContentPane().add(textFieldCNPJ);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (confirmar()){
					fechar();
				}
			}
		});
		btnSalvar.setBounds(174, 103, 89, 23);
		getContentPane().add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechar();
			}
		});
		btnCancelar.setBounds(273, 103, 89, 23);
		getContentPane().add(btnCancelar);

		this.setVisible(true);
	}
	
	private boolean confirmar(){
		try {
			if (update){
				dataBase.execute("update pessoa set nome = '" + textFieldNome.getText() + "' where codigo = " + codigo);
				dataBase.execute("update pessoajuridica set inscricaoestadual = '" + textFieldInscricaoEstadual.getText() + "', cnpj = '" + textFieldCNPJ.getText() + "' where codpessoa = " + codigo);
			} else {
				int insert = 0;

				dataBase.execute("insert into pessoa (nome) values ('" + textFieldNome.getText() + "')");
				
				dataBase.query("select * from pessoa where nome = '" + textFieldNome.getText() + "'");
				if (dataBase.getDbResultSet().next()){
					insert = dataBase.getDbResultSet().getInt("codigo");
				}
				
				dataBase.execute("insert into pessoajuridica (codpessoa, inscricaoestadual, cnpj) values (" + insert + ", '" + textFieldInscricaoEstadual.getText() + "', '" + textFieldCNPJ.getText() + "')");
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
