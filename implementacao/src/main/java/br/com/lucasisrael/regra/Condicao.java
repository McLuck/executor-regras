
package br.com.lucasisrael.regra;

import java.util.ArrayList;
import java.util.List;

import br.com.lucasisrael.regra.CondicaoBuilder.AndOr.DeveSer;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Representa a condicao de uma regra
 * <br>
 * HIST�RICO DE DESENVOLVIMENTO: <br>
 * 14/11/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

@SuppressWarnings({"rawtypes", "unchecked"})
public class Condicao {
	private String atributo;
	private OperadorComparacao operacaoComparacao = OperadorComparacao.IGUAL;
	private List<Comparable> valores;
	private Condicao and;
	private Condicao or;
	
	/** 
	 * Construtor padrão da classe
	 * @param atributo nome do atributo que devera ser analisado na condicao
	 */
	public Condicao(String atributo) {
		this.atributo = atributo;
	}



	/**
	 * Construtor padrão da classe
	 * @param atributo nome do atributo que devera ser analisado na condicao
	 * @param operacaoLogica {@link OperadorComparacao} que sera aplicado na condicao
	 */
	public Condicao(String atributo, OperadorComparacao operacaoLogica) {
		this.atributo = atributo;
		this.operacaoComparacao = operacaoLogica;
	}
	
	/**
	 * adiciona valor na condicao
	 * @param valor
	 * @return
	 */
	public synchronized Condicao addValor(Comparable valor) {
		if(valores == null) {
			valores = new ArrayList();
		}
		valores.add(valor);
		return this;
	}
	
	/**
	 * muda a {@link OperadorComparacao} que sera utilizada na condicao
	 * @param operacaoLogica {@link OperadorComparacao} que sera utilizada na condicao
	 * @return
	 */
	public Condicao mudarOperacaoComparacao(OperadorComparacao operacaoLogica) {
		this.operacaoComparacao = operacaoLogica;
		return this;
	}

	/**
	 * Adiciona nova condicao com operacao AND
	 * @param and
	 * @return
	 */
	public Condicao and(Condicao and) {
		this.and = and;
		return this.and;
	}
	
	/**
	 * Adiciona nova condicao com operacao OR
	 * @param and
	 * @return
	 */
	public Condicao or(Condicao or) {
		this.or = or;
		return this.or;
	}

	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 100;
		StringBuilder builder = new StringBuilder();
		builder.append("atributo ");
		builder.append(atributo);
		builder.append(" deve ser ");
		builder.append(operacaoComparacao.toString());
		builder.append(" a ");
		builder.append(valores != null ? valores.subList(0,
				Math.min(valores.size(), maxLen)) : null);
		
		if(and!=null) {
			builder.append(" AND ( ").append(and.toString()).append(" )");
		}
		
		if(or!=null) {
			builder.append(" OR ( ").append(or.toString()).append(" )");
		}
		
		return builder.toString();
	}
	
	/**
	 * Inicia criacao de uma condicao
	 * @return
	 */
	public static DeveSer iniciarCriacaoParaAtributo(final String atributo) {
		return new CondicaoBuilder(atributo).iniciarBuilder();
	}



	/** 
	 * Altera o valor para nulo
	 */
	public void valorNulo() {
		this.valores = null;
	}
	
	/**
	 * Método de recuperação do campo and
	 *
	 * @return valor do campo and
	 */
	public Condicao getAnd() {
		return and;
	}
	
	
	/**
	 * Método de recuperação do campo or
	 *
	 * @return valor do campo or
	 */
	public Condicao getOr() {
		return or;
	}
	
	/**
	 * Verifica da condição é nulo
	 * @return
	 */
	public boolean isNull() {
		return valores == null;
	}
	
	/**
	 * Método de recuperação do campo atributo
	 *
	 * @return valor do campo atributo
	 */
	public String getAtributo() {
		return atributo;
	}
	
	/**
	 * Método de recuperação do campo valores
	 *
	 * @return valor do campo valores
	 */
	public List<Comparable> getValores() {
		return valores;
	}
	
	/**
	 * Método de recuperação do campo operacaoComparacao
	 *
	 * @return valor do campo operacaoComparacao
	 */
	public OperadorComparacao getOperacaoComparacao() {
		return operacaoComparacao;
	}
}
