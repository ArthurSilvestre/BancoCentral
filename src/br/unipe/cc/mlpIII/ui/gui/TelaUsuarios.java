package br.unipe.cc.mlpIII.ui.gui;

import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import br.unipe.cc.mlpIII.respositorio.DataBase;
import javax.swing.ImageIcon;

public class TelaUsuarios {
	private JFrame frame;
	private JTable table;
	private DataBase dataBase;
	
	public TelaUsuarios() {
		this.dataBase = new DataBase();
		this.dataBase.query("SELECT * FROM usuario");
		initialize();
	}	

	private void initialize() {
		frame = new JFrame("Usuarios");
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(TelaUsuarios.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		frame.setBounds(100, 100, 600, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		table = new JTable();
		table.setBounds(10, 527, 574, -504);
		frame.getContentPane().add(table);
		
		JButton retornar = new JButton("");
		retornar.setIcon(new ImageIcon(TelaUsuarios.class.getResource("/com/sun/javafx/scene/web/skin/Redo_16x16_JFX.png")));
		retornar.setBounds(524, 562, 60, 49);
		frame.getContentPane().add(retornar);
		
		JButton excluir = new JButton("");
		excluir.setIcon(new ImageIcon(TelaUsuarios.class.getResource("/com/sun/deploy/uitoolkit/impl/fx/ui/resources/image/graybox_error.png")));
		excluir.setBounds(454, 562, 60, 49);
		frame.getContentPane().add(excluir);
		
		JButton alterar = new JButton("");
		alterar.setIcon(new ImageIcon(TelaUsuarios.class.getResource("/com/sun/javafx/scene/web/skin/Paste_16x16_JFX.png")));
		alterar.setBounds(384, 562, 60, 49);
		frame.getContentPane().add(alterar);
		
		JButton incluir = new JButton("");
		incluir.setIcon(new ImageIcon(TelaUsuarios.class.getResource("/com/sun/javafx/scene/web/skin/Copy_16x16_JFX.png")));
		incluir.setBounds(314, 562, 60, 49);
		frame.getContentPane().add(incluir);
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	
}
