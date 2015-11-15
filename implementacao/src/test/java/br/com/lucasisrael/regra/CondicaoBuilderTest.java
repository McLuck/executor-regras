
package br.com.lucasisrael.regra;

import org.junit.Test;

import br.com.lucasisrael.regra.Condicao;
import static org.junit.Assert.*;
/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Testador do builder de condição
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 15/11/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class CondicaoBuilderTest {
	
	@Test
	public void testarIntegracaoCompleta() {
		
		
		Condicao condicao = Condicao.iniciarCriacaoParaAtributo("nome")
				.igual("Lucas Israel").ou("idade").maiorIgual(18).voltar()
				.e("qtdFilhos").igual(1).e("mulher").diferente("Maria")
				.e("mulher").igual("Karen").concluir();
		
		String narracaoCondicao = condicao.toString();
		
		String expectativaNarrativa = "atributo nome deve ser IGUAL a [Lucas Israel] "
				+ "AND ( atributo qtdFilhos deve ser IGUAL a [1] "
				+ "AND ( atributo mulher deve ser DIFERENTE a [Maria] "
				+ "AND ( atributo mulher deve ser IGUAL a [Karen] ) ) ) "
				+ "OR ( atributo idade deve ser MAIORIGUAL a [18] )";
		assertEquals(expectativaNarrativa, narracaoCondicao);
	}
}
