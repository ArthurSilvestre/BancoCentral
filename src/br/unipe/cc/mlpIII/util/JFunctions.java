package br.unipe.cc.mlpIII.util;

public final class JFunctions {
	
	public final static String replicate(String string, int quantidade){
		String result = new String();
		
		for (int i = 0; i < quantidade; i++)
			result += string;
		
		return result;
	}
	
	public final static String strZero(int n, int quantZeros){
		String result = new String(n+"");
		
		result = replicate("0",quantZeros - result.length()) + n;
		
		return result;
	}
	
}
