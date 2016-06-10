package br.unipe.cc.mlpIII.ui.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.unipe.cc.mlpIII.modelo.Usuario;
import br.unipe.cc.mlpIII.repositorio.DataBase;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SuppressWarnings("deprecation")
public class TelaIncluirUsuario extends JFrame {
	private static final long serialVersionUID = -2826558573523795502L;
	private boolean update = false;
	private int codigo;
	
	private DataBase dataBase = new DataBase();

	private JTextField textFieldUsuario;
	private JPasswordField passwordFieldSenha;
	private JComboBox<String> comboBoxNivel; 
	
	public TelaIncluirUsuario() {
		initialize();
	}

	public TelaIncluirUsuario(Usuario usuario) {
		initialize();
		textFieldUsuario.setText(usuario.getId());
		passwordFieldSenha.setText(usuario.getSenha());
		comboBoxNivel.setSelectedIndex(usuario.getNivel()-1);
		update = true;
		codigo = usuario.getCodigo();
	}
	
	private void initialize() {
		dataBase.openConnection();

		new JFrame();
		setTitle("Usu\u00E1rio");
		setBounds(100, 100, 245, 170);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo( null );
		getContentPane().setLayout(null);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(66, 8, 150, 20);
		getContentPane().add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.setBounds(66, 33, 150, 20);
		getContentPane().add(passwordFieldSenha);
		
		comboBoxNivel = new JComboBox<String>();
		comboBoxNivel.setModel(new DefaultComboBoxModel<String>(new String[] {"1.Administrador", "2.Consulta"}));
		comboBoxNivel.setBounds(66, 58, 150, 20);
		getContentPane().add(comboBoxNivel);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (confirmar()){
					fechar();
				}
			}
		});
		btnSalvar.setBounds(28, 99, 89, 23);
		getContentPane().add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechar();
			}
		});
		btnCancelar.setBounds(127, 99, 89, 23);
		getContentPane().add(btnCancelar);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio");
		lblUsurio.setBounds(10, 11, 46, 14);
		getContentPane().add(lblUsurio);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(10, 36, 46, 14);
		getContentPane().add(lblSenha);
		
		JLabel lblNvel = new JLabel("N\u00EDvel");
		lblNvel.setBounds(10, 61, 46, 14);
		getContentPane().add(lblNvel);

		this.setVisible(true);
	}
	
	private boolean confirmar(){
		try {
			if (update){
				dataBase.execute("update usuario set id = '" + textFieldUsuario.getText() + "', senha = '" + passwordFieldSenha.getText() + "', nivel = " + (comboBoxNivel.getSelectedIndex() + 1) + " where codigo = " + codigo);
			} else {
				dataBase.execute("insert into usuario (id, senha, nivel) values ('" + textFieldUsuario.getText() + "', '" + passwordFieldSenha.getText() + "', " + (comboBoxNivel.getSelectedIndex() + 1) + ")");
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
