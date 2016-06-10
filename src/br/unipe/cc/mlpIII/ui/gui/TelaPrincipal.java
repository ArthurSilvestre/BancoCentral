package br.unipe.cc.mlpIII.ui.gui;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import br.unipe.cc.mlpIII.util.ErroLog;
import javafx.scene.image.Image;
import javax.swing.JMenuBar;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.ComponentOrientation;

@SuppressWarnings({"serial","unused"})
public class TelaPrincipal extends JFrame{
	private JLabel backgroundLabel;
	
	private JButton btnEfetuarLancamentos = new JButton("Efetuar Lan\u00E7amentos");
	private JButton btnGerarExtrato = new JButton("Gerar Extrato");
	private JButton btnContas = new JButton("Contas");
	private JButton btnPessoaFsica = new JButton("Pessoa F\u00EDsica");
	private JButton btnPessoaJurdica = new JButton("Pessoa Jur\u00EDdica");
	private JButton btnUsuarios = new JButton("Usuarios");
	private JButton btnErro = new JButton("Erro");
	
	public TelaPrincipal() {
		initialize();
	}

	private void initialize() {
		new JFrame();
		this.setTitle("BANKing");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("imagens\\bancocentral.png"));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		getContentPane().setLayout(null);
		
		this.btnEfetuarLancamentos.setLocation(new Point(10, 53));
		this.btnEfetuarLancamentos.setSize(new Dimension(300, 60));
		this.btnEfetuarLancamentos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.btnEfetuarLancamentos.setIcon(new ImageIcon("imagens\\lancamento.png"));
		
		this.btnGerarExtrato.setLocation(new Point(10, 124));
		this.btnGerarExtrato.setSize(new Dimension(300, 60));
		this.btnGerarExtrato.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.btnGerarExtrato.setIcon(new ImageIcon("imagens\\extrato.png"));

		this.btnContas.setLocation(new Point(317, 53));
		this.btnContas.setSize(new Dimension(300, 60));
		this.btnContas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.btnContas.setIcon(new ImageIcon("imagens\\conta.png"));
		this.btnContas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroClassesModelo cadastroUsuarios = new TelaCadastroClassesModelo("pessoa As P, contas As Con",new String[]{"Con.codigo","P.nome","Con.numero","Con.saldo"}, new String[]{"Código","Responsável","Número","Saldo"},"P.codigo = Con.codpessoa");
			}
		});
		
		this.btnPessoaFsica.setLocation(new Point(317, 124));
		this.btnPessoaFsica.setSize(new Dimension(300, 60));
		this.btnPessoaFsica.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.btnPessoaFsica.setIcon(new ImageIcon("imagens\\pessoafisica.png"));
		this.btnPessoaFsica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroClassesModelo cadastroUsuarios = new TelaCadastroClassesModelo("pessoa As P, pessoafisica As PF",new String[]{"P.codigo","P.nome","PF.rg","PF.cpf","PF.sexo"}, new String[]{"Código","Nome","RG","CPF","Sexo"},"P.codigo = PF.codpessoa");
			}
		});
		
		this.btnPessoaJurdica.setLocation(new Point(317, 195));
		this.btnPessoaJurdica.setSize(new Dimension(300, 60));
		this.btnPessoaJurdica.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.btnPessoaJurdica.setIcon(new ImageIcon("imagens\\pessoajuridica.png"));
		this.btnPessoaJurdica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroClassesModelo cadastroUsuarios = new TelaCadastroClassesModelo("pessoa As P, pessoajuridica As PJ",new String[]{"P.codigo","P.nome","PJ.inscricaoestadual","PJ.cnpj"}, new String[]{"Código","Nome","Inscrição Estadual","CNPJ"},"P.codigo = PJ.codpessoa");
			}
		});
		
		this.btnUsuarios.setLocation(new Point(317, 266));
		this.btnUsuarios.setSize(new Dimension(300, 60));
		this.btnUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.btnUsuarios.setIcon(new ImageIcon("imagens\\usuario.png"));
		this.btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroClassesModelo cadastroUsuarios = new TelaCadastroClassesModelo("usuario",new String[]{"codigo","id","nivel"}, new String[]{"Código","ID","Nível"},""); 
			}
		});
		btnErro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] vetorDeStrings = {"1"};
				String variavelDeString;
				
				try {
					variavelDeString = vetorDeStrings[1];
				} catch (Exception e) {
					ErroLog.gravarErroLog(e.toString(), e.getStackTrace());
				}
			}
		});
		
		this.btnErro.setLocation(new Point(10, 195));
		this.btnErro.setSize(new Dimension(300, 60));
		this.btnErro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.btnErro.setIcon(new ImageIcon("imagens\\erro.png"));

		this.backgroundLabel = new JLabel("");
		this.backgroundLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.backgroundLabel.setIcon(new ImageIcon("imagens\\background.png"));
		this.backgroundLabel.setBounds(0, 0, 1920, 1080);

		this.getContentPane().add(this.btnEfetuarLancamentos);
		this.getContentPane().add(this.btnGerarExtrato);
		this.getContentPane().add(this.btnErro);
		this.getContentPane().add(this.btnContas);
		this.getContentPane().add(this.btnPessoaFsica);
		this.getContentPane().add(this.btnPessoaJurdica);
		this.getContentPane().add(this.btnUsuarios);
		this.getContentPane().add(this.backgroundLabel);

		this.setVisible(true);
	}
}
