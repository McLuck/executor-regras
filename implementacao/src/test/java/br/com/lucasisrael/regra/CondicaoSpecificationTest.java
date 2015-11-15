
package br.com.lucasisrael.regra;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * TODO Definir documentação da classe. <br>
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 15/11/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */
@SuppressWarnings("unused")
public class CondicaoSpecificationTest {
	
	@Test
	public void testaIntegracaoCompletaEsperandoNaoAtenderSpecification() {
		Condicao condicao = Condicao.iniciarCriacaoParaAtributo("nome")
				.igual("Lucas Israel").e("idade").maiorIgual(18)
				.e("qtdFilhos").igual(1).e("mulher.idade").igual(25)
				.e("endereco.logradouro").deveSer(OperadorComparacao.IGUAL).nulo()
				.e("mulher.nome").igual("Karen").concluir();
		
		CondicaoSpecification specification = new CondicaoSpecification(condicao);
	
		PessoaFisica pessoaFisica = new PessoaFisica();
		pessoaFisica.mulher = new PessoaFisica();
		pessoaFisica.nome = "Lucas Israel";
		pessoaFisica.mulher.nome = "Karen";
		pessoaFisica.mulher.setIdade(25);
		pessoaFisica.qtdFilhos = 1;
		pessoaFisica.setIdade(27);
		pessoaFisica.endereco = new Endereco();
		pessoaFisica.endereco.bairro = "Jd Colonial";
		pessoaFisica.endereco.logradouro = "Rua Jose Maria";
		pessoaFisica.endereco.numero = "416";
		
		boolean satisfied = specification.isSatisfiedBy(pessoaFisica);
		assertFalse(satisfied);
	}
	
	@Test
	public void testaIntegracaoCompletaEsperandoAtenderSpecification() {
		Condicao condicao = Condicao.iniciarCriacaoParaAtributo("nome")
				.igual("Lucas Israel").e("idade").maiorIgual(18)
				.e("qtdFilhos").igual(1).e("mulher.idade").igual(25)
				.e("endereco.logradouro").deveSer(OperadorComparacao.DIFERENTE).nulo()
				.e("mulher.nome").igual("Karen").concluir();
		
		CondicaoSpecification specification = new CondicaoSpecification(condicao);
		
		PessoaFisica pessoaFisica = new PessoaFisica();
		pessoaFisica.mulher = new PessoaFisica();
		pessoaFisica.nome = "Lucas Israel";
		pessoaFisica.mulher.nome = "Karen";
		pessoaFisica.mulher.setIdade(25);
		pessoaFisica.qtdFilhos = 1;
		pessoaFisica.setIdade(27);
		pessoaFisica.endereco = new Endereco();
		pessoaFisica.endereco.bairro = "Jd Colonial";
		pessoaFisica.endereco.logradouro = "Rua Jose Maria";
		pessoaFisica.endereco.numero = "416";
		
		boolean satisfied = specification.isSatisfiedBy(pessoaFisica);
		assertTrue(satisfied);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public abstract class Pessoa {
		String nome;
		protected Endereco endereco;
		private Integer idade;
		
		/**
		 * Valor de idade atribuído a idade
		 *
		 * @param idade Atributo da Classe
		 */
		public void setIdade(Integer idade) {
			this.idade = idade;
		}
		
		/**
		 * Método de recuperação do campo idade
		 *
		 * @return valor do campo idade
		 */
		public Integer getIdade() {
			return idade;
		}
	}
	
	public class Endereco {
		private String numero;
		
		private String logradouro;
		private String bairro;
	}
	
	private class PessoaFisica extends Pessoa {
		private Pessoa mulher;
		private int qtdFilhos;
	}
}
