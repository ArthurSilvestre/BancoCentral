package br.unipe.cc.mlpIII.util;

import junit.framework.TestCase;

public class JFunctionsTest extends TestCase{

	@Override
	protected void setUp() throws Exception {
		new JFunctionsTest();
	}

	public final static String testReplicate(String string, int quantidade){
		String result = new String();
		
		for (int i = 0; i < quantidade; i++)
			result += string;
		
		return result;
	}
	
	public final static String testStrZero(int n, int quantZeros){
		String result = new String(n+"");
		
		result = testReplicate("0",quantZeros - result.length()) + n;
		
		return result;
	}
	
	public void testMethods(){
		assertNotNull( testStrZero(52, 16) );
		assertNotNull( testReplicate("A", 25) );
	}

	@Override
	protected void tearDown() throws Exception {

	}

}
