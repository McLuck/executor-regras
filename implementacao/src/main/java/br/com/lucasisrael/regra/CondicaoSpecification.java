
package br.com.lucasisrael.regra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.lucasisrael.regra.reflections.TratamentoReflections;
import br.com.lucasisrael.specification.AbstractSpecification;
import br.com.lucasisrael.specification.AndSpecification;
import br.com.lucasisrael.specification.OrSpecification;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * 
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 15/11/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CondicaoSpecification extends AbstractSpecification<Object>{
	private Logger logger = LoggerFactory.getLogger(CondicaoSpecification.class);
	private Condicao condicao;
	private AndSpecification<Object> and;
	private OrSpecification<Object> or;
	
	/** 
	 * Construtor padrão da classe
	 */
	public CondicaoSpecification(final Condicao condicao) {
		this.condicao = condicao;
	}
	
	/** 
	 * {@inheritDoc} 
	 */
	@Override
	public boolean isSatisfiedBy(Object object) {
		if(condicao.getAnd()!=null && and == null ) {
			and = new AndSpecification<Object>(this, new CondicaoSpecification(condicao.getAnd()));
			return and.isSatisfiedBy(object);
		}
		if(condicao.getOr() !=null && or == null ) {
			or = new OrSpecification<Object>(this, new CondicaoSpecification(condicao.getOr()));
			return or.isSatisfiedBy(object);
		}
		
		Comparable valor = (Comparable)TratamentoReflections.getField(object, condicao.getAtributo());
		if(!condicao.isNull() && valor!=null) {
			for (Comparable valorCondicao : condicao.getValores()) {
				int comparacao = valor.compareTo(valorCondicao);
				if(OperadorComparacao.MAIORIGUAL.equals(condicao.getOperacaoComparacao()) && comparacao < 0) {
					if(logger.isDebugEnabled()) {
						logger.debug(montarRelatorioFalha(valor, condicao));
					}
					return false;
				}
				if(OperadorComparacao.MAIORQUE.equals(condicao.getOperacaoComparacao()) && comparacao <= 0) {
					if(logger.isDebugEnabled()) {
						logger.debug(montarRelatorioFalha(valor, condicao));
					}
					return false;
				}
				if(OperadorComparacao.DIFERENTE.equals(condicao.getOperacaoComparacao()) && comparacao == 0) {
					if(logger.isDebugEnabled()) {
						logger.debug(montarRelatorioFalha(valor, condicao));
					}
					return false;
				}
				if(OperadorComparacao.IGUAL.equals(condicao.getOperacaoComparacao()) && comparacao != 0) {
					if(logger.isDebugEnabled()) {
						logger.debug(montarRelatorioFalha(valor, condicao));
					}
					return false;
				}
				if(OperadorComparacao.MENORIGUAL.equals(condicao.getOperacaoComparacao()) && comparacao >= 0) {
					if(logger.isDebugEnabled()) {
						logger.debug(montarRelatorioFalha(valor, condicao));
					}
					return false;
				}
				if(OperadorComparacao.MENORQUE.equals(condicao.getOperacaoComparacao()) && comparacao > 0) {
					if(logger.isDebugEnabled()) {
						logger.debug(montarRelatorioFalha(valor, condicao));
					}
					return false;
				}
			}
			return true;
		} else if(!OperadorComparacao.IGUAL.equals(condicao.getOperacaoComparacao()) && !OperadorComparacao.DIFERENTE.equals(condicao.getOperacaoComparacao())) {
			throw new RuntimeException("Valor da condição é nulo, mas o as operações de comparação não são de 'IGUAL' ou 'DIFERENTE'. Quando valor for nulo na condição, somente é permitido utilizar operação 'IGUAL' e 'DIFERENTE'");
		} else if(condicao.isNull() && OperadorComparacao.IGUAL.equals(condicao.getOperacaoComparacao())){
			if(valor != null && logger.isDebugEnabled()) {
				logger.debug(montarRelatorioFalha(valor, condicao));
			}
			return valor == null;
		} else if(condicao.isNull() && OperadorComparacao.DIFERENTE.equals(condicao.getOperacaoComparacao())){
			if(valor == null && logger.isDebugEnabled()) {
				logger.debug(montarRelatorioFalha(valor, condicao));
			}
			return valor != null;			
		}
		if(logger.isDebugEnabled()) {
			logger.debug(montarRelatorioFalha(valor, condicao));
		}
		return false;
	}

	private String montarRelatorioFalha(Comparable valor, Condicao condicao) {
		StringBuilder builder = new StringBuilder();
		
		builder.append("![COMPARACAO] Atributo '").append(condicao.getAtributo())
		.append("' esperado ser ").append(condicao.getOperacaoComparacao().toString()) ;
		
		if(condicao.isNull()) {
			builder.append(" nulo");
		} else {
			builder.append(" a valores ").append(condicao.getValores());			
		}
		
		builder.append(" mas o objeto comparado estava com valor ").append(valor);
		
		return builder.toString();
	}
}
