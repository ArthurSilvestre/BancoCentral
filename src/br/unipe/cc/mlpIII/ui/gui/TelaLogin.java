package br.unipe.cc.mlpIII.ui.gui;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import br.unipe.cc.mlpIII.repositorio.DataBase;
import br.unipe.cc.mlpIII.util.ErroLog;
import br.unipe.cc.mlpIII.modelo.Usuario;

import javax.swing.JLabel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

@SuppressWarnings({"serial","deprecation"})
public class TelaLogin extends JFrame{
	private DataBase dataBase = new DataBase();
	
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	private JTextField textFieldID;
	private JPasswordField passwordField;
	
	private boolean autorizado = false;

	public TelaLogin() {
		dataBase.openConnection();
		fillListUsuarios();
		initialize();
	}

	private void initialize() {
		new JFrame();
		setTitle("Banco Central");
		setBounds(100, 100, 220, 145);
		setIconImage(Toolkit.getDefaultToolkit().getImage("imagens\\bancocentral.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo( null );
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				autorizado = validaLoguin();
				
				if (autorizado){
					setVisible(false);
					dispose();
				}
			}
		});
		btnNewButton.setBounds(66, 73, 128, 23);
		getContentPane().add(btnNewButton);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(66, 11, 128, 20);
		getContentPane().add(textFieldID);
		textFieldID.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(66, 42, 128, 20);
		getContentPane().add(passwordField);
		
		JLabel lblNewLabel = new JLabel("Usu\u00E1rio");
		lblNewLabel.setBounds(10, 14, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setBounds(10, 45, 46, 14);
		getContentPane().add(lblNewLabel_1);

		setVisible(true);
	}
	
	private void fillListUsuarios(){
		dataBase.query("select * from usuario");
		
		try {
			while (dataBase.getDbResultSet().next()){
				usuarios.add(new Usuario(dataBase.getDbResultSet().getInt("codigo"), dataBase.getDbResultSet().getString("id"), dataBase.getDbResultSet().getString("senha"), dataBase.getDbResultSet().getInt("nivel")));
			}
		} catch (SQLException e) {
			ErroLog.gravarErroLog(e.toString(), e.getStackTrace());
		}
	}
	
	public boolean validaLoguin(){
		boolean result = false;
		
		for	(Usuario usuario : usuarios){ 
			if (usuario.getId().compareToIgnoreCase(textFieldID.getText()) == 0){
				if (usuario.getSenha().compareToIgnoreCase(passwordField.getText()) == 0){
					result = true;
				}
			}
		}
		
		return result;
	}

	public boolean isAutorizado() {
		return autorizado;
	}
	
	public DataBase getDataBase(){
		return dataBase;
	}
}
