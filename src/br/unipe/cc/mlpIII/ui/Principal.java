package br.unipe.cc.mlpIII.ui;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import br.unipe.cc.mlpIII.ui.gui.TelaPrincipal;
import br.unipe.cc.mlpIII.util.ErroLog;

public class Principal {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
	
		if (ErroLog.verificaErroLog()){
			try {
				if (ErroLog.enviarErroLog()){
					ErroLog.apagarErroLog();
				} else {
					//TODO: Mensagem de que não foi possível enviar o erro.log.
				}
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//TelaLogin Login = new TelaLogin();
		
		//if (Login.getAutorized)
			TelaPrincipal telaPrincipal = new TelaPrincipal();
	}
	
}
