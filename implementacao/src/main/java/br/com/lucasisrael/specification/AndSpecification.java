
package br.com.lucasisrael.specification;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Implementação da especificação com operação OR
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 14/11/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class AndSpecification<T> extends AbstractSpecification<T> {
	private Specification<T> spec1;
	private Specification<T> spec2;
	
	/** 
	 * Construtor padrão da classe
	 * @param spec1 
	 * @param spec2
	 */
	public AndSpecification(Specification<T> spec1, Specification<T> spec2) {
		this.spec1 = spec1;
		this.spec2 = spec2;
	}



	/** 
	 * @see br.com.lucasisrael.specification.AbstractSpecification#isSatisfiedBy(java.lang.Object)
	 */
	@Override
	public boolean isSatisfiedBy(T object) {
		return spec1.isSatisfiedBy(object) && spec2.isSatisfiedBy(object);
	}

}
