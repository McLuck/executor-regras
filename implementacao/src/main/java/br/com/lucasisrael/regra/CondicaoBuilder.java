
package br.com.lucasisrael.regra;

import br.com.lucasisrael.regra.CondicaoBuilder.AndOr.DeveSer;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Builder para {@link Condicao}
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 14/11/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */
@SuppressWarnings("rawtypes")
public class CondicaoBuilder  {
	private Condicao condicao;
	CondicaoBuilder builderPai;
	
	CondicaoBuilder(final String atributo){
		condicao = new Condicao(atributo);
	}
	
	/**
	 * Inicia a construção da condição
	 * @return
	 */
	DeveSer iniciarBuilder() {
		AndOrBuilder andOrBuilder = new AndOrBuilder();
		andOrBuilder.condicaoBuilder = this;
		return andOrBuilder.new DeveSerBuilder();
	}

	public class AndOrBuilder implements AndOr {
		private AndOrBuilder() {}
		private CondicaoBuilder condicaoBuilder;
		
		/** 
		 * {@inheritDoc}
		 */
		@Override
		public DeveSer e(String atributo) {
			CondicaoBuilder builder = new CondicaoBuilder(atributo);
			if(condicaoBuilder!=null) {
				condicaoBuilder.condicao.and(builder.condicao);
				builder.builderPai = CondicaoBuilder.this;
			}
			condicaoBuilder = builder;
			return new DeveSerBuilder();
		}

		/** 
		 * {@inheritDoc}
		 */
		@Override
		public DeveSer ou(String atributo) {
			CondicaoBuilder builder = new CondicaoBuilder(atributo);
			if(condicaoBuilder!=null) {
				condicaoBuilder.condicao.or(builder.condicao);
				builder.builderPai = CondicaoBuilder.this;
			}
			condicaoBuilder = builder;
			return new DeveSerBuilder();
		}
		
		/** 
		 * {@inheritDoc}
		 */
		@Override
		public AndOr voltar() {
			while(this.condicaoBuilder.builderPai!=null) {
				this.condicaoBuilder = condicaoBuilder.builderPai; 
			}
			return this;
		}
		
		/** 
		 * {@inheritDoc}
		 */
		@Override
		public Condicao concluir() {
			CondicaoBuilder condicaoBuilder = this.condicaoBuilder;
			while(condicaoBuilder.builderPai!=null) {
				condicaoBuilder = condicaoBuilder.builderPai; 
			}
			return condicaoBuilder.condicao;
		}
		
		public class DeveSerBuilder implements DeveSer {

			/** 
			 * {@inheritDoc}
			 */
			@Override
			public Valores deveSer(OperadorComparacao operadorComparacao) {
				AndOrBuilder.this.condicaoBuilder.condicao.mudarOperacaoComparacao(operadorComparacao);
				return new ValoresBuilder();
			}
			

			/** 
			 * {@inheritDoc}
			 */
			@Override
			public AndOr igual(Comparable... valores) {
				return deveSer(OperadorComparacao.IGUAL).valores(valores);
			}


			/** 
			 * {@inheritDoc}
			 */
			@Override
			public AndOr maiorQue(Comparable... valores) {
				return deveSer(OperadorComparacao.MAIORQUE).valores(valores);
			}


			/** 
			 * {@inheritDoc}
			 */
			@Override
			public AndOr menorQue(Comparable... valores) {
				return deveSer(OperadorComparacao.MENORQUE).valores(valores);
			}


			/** 
			 * {@inheritDoc}
			 */
			@Override
			public AndOr diferente(Comparable... valores) {
				return deveSer(OperadorComparacao.DIFERENTE).valores(valores);
			}


			/** 
			 * {@inheritDoc}
			 */
			@Override
			public AndOr maiorIgual(Comparable... valores) {
				return deveSer(OperadorComparacao.MAIORIGUAL).valores(valores);
			}


			/** 
			 * {@inheritDoc}
			 */
			@Override
			public AndOr menorIgual(Comparable... valores) {
				return deveSer(OperadorComparacao.MENORIGUAL).valores(valores);
			}
			
			
			public class ValoresBuilder implements Valores {

				/** 
				 * {@inheritDoc}
				 */
				@Override
				public AndOr valores(Comparable... valores) {
					for (Comparable valor : valores) {
						AndOrBuilder.this.condicaoBuilder.condicao.addValor(valor);
					}
					AndOrBuilder builder = new AndOrBuilder();
					builder.condicaoBuilder = AndOrBuilder.this.condicaoBuilder;
					return builder;
				}

				/** 
				 * {@inheritDoc}
				 */
				@Override
				public AndOr nulo() {
					AndOrBuilder.this.condicaoBuilder.condicao.valorNulo();
					AndOrBuilder builder = new AndOrBuilder();
					builder.condicaoBuilder = AndOrBuilder.this.condicaoBuilder;
					return builder;
				}

			}
		}

	}
	
	public interface AndOr {
		/**
		 * Nova condição encadeada com lógica AND
		 * @param atributo
		 * @return
		 */
		DeveSer e (final String atributo);
		
		/**
		 * Nova condição encadeada com lógica OR
		 * @param atributo
		 * @return
		 */
		DeveSer ou (final String atributo);
		
		/**
		 * Voltar um nivel na condicao
		 * @return
		 */
		AndOr voltar();

		/**
		 * Conclui a construção
		 * @return
		 */
		Condicao concluir();
		
		public interface DeveSer {
			/**
			 * Define a operacao de comparacao da condição
			 * @param operadorComparacao
			 * @return
			 */
			Valores deveSer(OperadorComparacao operadorComparacao);
			
			/**
			 * Define que a condição deve ser igual aos valores informados no parametro
			 * @param valores
			 * @return
			 */
			AndOr igual(Comparable ... valores);
			
			/**
			 * Define que a condição deve ser maior que os valores informados no parametro
			 * @param valores
			 * @return
			 */
			AndOr maiorQue(Comparable ... valores);

			/**
			 * Define que a condição deve ser menor que valores informados no parametro
			 * @param valores
			 * @return
			 */
			AndOr menorQue(Comparable ... valores);

			/**
			 * Define que a condição deve ser diferentes dos valores informados no parametro
			 * @param valores
			 * @return
			 */
			AndOr diferente(Comparable ... valores);

			/**
			 * Define que a condição deve ser maior ou igual aos valores informados no parametro
			 * @param valores
			 * @return
			 */
			AndOr maiorIgual(Comparable ... valores);

			/**
			 * Define que a condição deve ser menor ou igual aos valores informados no parametro
			 * @param valores
			 * @return
			 */
			AndOr menorIgual(Comparable ... valores);
			
			public interface Valores {
				/**
				 * Valores da condição para comparação
				 * @param object
				 * @return
				 */
				AndOr valores(Comparable ... object);
				
				/**
				 * Informa que condição deve comparar valor nulo
				 * @return
				 */
				AndOr nulo();
				
			}
		}
	}
}