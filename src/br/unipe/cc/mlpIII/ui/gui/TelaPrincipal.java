package br.unipe.cc.mlpIII.ui.gui;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings({"serial","unused"})
public class TelaPrincipal extends JFrame{
	private JLabel backgroundLabel;
	
	private JMenuBar menuBar;
	
	private JButton btnEfetuarLancamentos;
	private JButton btnGerarExtrato;
	private JButton btnContas;
	private JButton btnPessoaFsica;
	private JButton btnPessoaJurdica;
	private JButton btnUsuarios;
	
	public TelaPrincipal() {
		initialize();
	}

	private void initialize() {
		new JFrame();
		this.setTitle("Banco Central");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipal.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		getContentPane().setLayout(null);
		
		this.menuBar = new JMenuBar();
		this.menuBar.setBounds(0, 0, 1920, 80);
		getContentPane().add(this.menuBar);
		
		this.btnEfetuarLancamentos = new JButton("Efetuar Lan\u00E7amentos");
		this.btnEfetuarLancamentos.setIcon(null);
		this.btnEfetuarLancamentos.setMargin(new Insets(2, 10, 2, 10));
		this.btnEfetuarLancamentos.setLocation(new Point(10, 10));
		this.btnEfetuarLancamentos.setPreferredSize(new Dimension(64, 64));
		this.btnEfetuarLancamentos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.btnEfetuarLancamentos.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.btnEfetuarLancamentos.setSize(new Dimension(64, 64));
		this.btnEfetuarLancamentos.setMinimumSize(new Dimension(64, 64));
		this.btnEfetuarLancamentos.setMaximumSize(new Dimension(150, 64));
		menuBar.add(this.btnEfetuarLancamentos);
		
		this.btnGerarExtrato = new JButton("Gerar Extrato");
		this.btnGerarExtrato.setMargin(new Insets(2, 10, 2, 10));
		this.btnGerarExtrato.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.btnGerarExtrato.setSize(new Dimension(64, 64));
		this.btnGerarExtrato.setPreferredSize(new Dimension(64, 64));
		this.btnGerarExtrato.setMinimumSize(new Dimension(64, 64));
		this.btnGerarExtrato.setMaximumSize(new Dimension(150, 64));
		this.btnGerarExtrato.setLocation(new Point(10, 10));
		this.btnGerarExtrato.setAlignmentX(0.5f);
		menuBar.add(this.btnGerarExtrato);
		
		this.btnContas = new JButton("Contas");
		this.btnContas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroClassesModelo cadastroUsuarios = new TelaCadastroClassesModelo("contas As Con,pessoa As P",new String[]{"Con.codigo","P.nome","Con.numero","Con.saldo"}, new String[]{"Código","Responsável","Número","Saldo"},"P.codigo = Con.codpessoa");
			}
		});
		this.btnContas.setMargin(new Insets(2, 10, 2, 10));
		this.btnContas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.btnContas.setSize(new Dimension(64, 64));
		this.btnContas.setPreferredSize(new Dimension(64, 64));
		this.btnContas.setMinimumSize(new Dimension(64, 64));
		this.btnContas.setMaximumSize(new Dimension(150, 64));
		this.btnContas.setLocation(new Point(10, 10));
		this.btnContas.setAlignmentX(Component.CENTER_ALIGNMENT);
		menuBar.add(this.btnContas);
		
		this.btnPessoaFsica = new JButton("Pessoa F\u00EDsica");
		this.btnPessoaFsica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroClassesModelo cadastroUsuarios = new TelaCadastroClassesModelo("pessoa As P, pessoafisica As PF",new String[]{"P.codigo","P.nome","PF.rg","PF.cpf","PF.datanascimento","PF.sexo"}, new String[]{"Código","Nome","RG","CPF","Data de Nascimento","Sexo"},"P.codigo = PF.codpessoa");
			}
		});
		this.btnPessoaFsica.setSize(new Dimension(64, 64));
		this.btnPessoaFsica.setPreferredSize(new Dimension(64, 64));
		this.btnPessoaFsica.setMinimumSize(new Dimension(64, 64));
		this.btnPessoaFsica.setMaximumSize(new Dimension(150, 64));
		this.btnPessoaFsica.setMargin(new Insets(2, 10, 2, 10));
		this.btnPessoaFsica.setLocation(new Point(10, 10));
		this.btnPessoaFsica.setAlignmentX(0.5f);
		menuBar.add(this.btnPessoaFsica);
		
		this.btnPessoaJurdica = new JButton("Pessoa Jur\u00EDdica");
		this.btnPessoaJurdica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroClassesModelo cadastroUsuarios = new TelaCadastroClassesModelo("pessoa As P, pessoajuridica As PJ",new String[]{"P.codigo","P.nome","PJ.inscricaoestadual","PJ.cnpj"}, new String[]{"Código","Nome","Inscrição Estadual","CNPJ"},"P.codigo = PJ.codpessoa");
			}
		});
		this.btnPessoaJurdica.setSize(new Dimension(64, 64));
		this.btnPessoaJurdica.setPreferredSize(new Dimension(64, 64));
		this.btnPessoaJurdica.setMinimumSize(new Dimension(64, 64));
		this.btnPessoaJurdica.setMaximumSize(new Dimension(150, 64));
		this.btnPessoaJurdica.setMargin(new Insets(2, 10, 2, 10));
		this.btnPessoaJurdica.setLocation(new Point(10, 10));
		this.btnPessoaJurdica.setAlignmentX(0.5f);
		menuBar.add(this.btnPessoaJurdica);
		
		this.btnUsuarios = new JButton("Usuarios");
		this.btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroClassesModelo cadastroUsuarios = new TelaCadastroClassesModelo("usuario",new String[]{"codigo","id","nivel"}, new String[]{"Código","ID","Nível"},""); 
			}
		});
		this.btnUsuarios.setSize(new Dimension(64, 64));
		this.btnUsuarios.setPreferredSize(new Dimension(64, 64));
		this.btnUsuarios.setMinimumSize(new Dimension(64, 64));
		this.btnUsuarios.setMaximumSize(new Dimension(150, 64));
		this.btnUsuarios.setMargin(new Insets(2, 10, 2, 10));
		this.btnUsuarios.setLocation(new Point(10, 10));
		this.btnUsuarios.setAlignmentX(0.5f);
		menuBar.add(this.btnUsuarios);
		
		this.backgroundLabel = new JLabel("");
		this.backgroundLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.backgroundLabel.setIcon(new ImageIcon("imagens\\background.png"));
		this.backgroundLabel.setBounds(0, 0, 1920, 1080);
		this.getContentPane().add(this.backgroundLabel);

		this.setVisible(true);
	}
}
