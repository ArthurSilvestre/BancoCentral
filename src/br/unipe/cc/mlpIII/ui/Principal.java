package br.unipe.cc.mlpIII.ui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import br.unipe.cc.mlpIII.ui.gui.TelaLogin;
import br.unipe.cc.mlpIII.ui.gui.TelaPrincipal;
import br.unipe.cc.mlpIII.util.ErroLog;

@SuppressWarnings("unused")
public class Principal {

	private static TelaLogin Login;
	private static TelaPrincipal telaPrincipal;

	public static void main(String[] args) {
	
		if (ErroLog.verificaErroLog()){
			try {
				if (ErroLog.enviarErroLog()){
					ErroLog.apagarErroLog();
				} else {
					//TODO: Mensagem de que não foi possível enviar o erro.log.
				}
			} catch (AddressException e) {
				ErroLog.gravarErroLog(e.toString(), e.getStackTrace());
			} catch (MessagingException e) {
				ErroLog.gravarErroLog(e.toString(), e.getStackTrace());
			}
		}

		Login = new TelaLogin();
		Login.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				
				Login.getDataBase().closeConnection();
				
				if (Login.isAutorizado())
					telaPrincipal = new TelaPrincipal();
				
			}
		});

	}
	
}
