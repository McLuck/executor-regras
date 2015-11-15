
package br.com.lucasisrael.specification;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Implementação da especificação com operação NOT
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 14/11/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class NotSpecification<T> extends AbstractSpecification<T> {
	private Specification<T> spec1;
	
	/** 
	 * Construtor padrão da classe
	 * @param spec1
	 * @param spec2
	 */
	public NotSpecification(Specification<T> spec1, Specification<T> spec2) {
		this.spec1 = spec1;
	}

	/** 
	 * @see br.com.lucasisrael.specification.AbstractSpecification#isSatisfiedBy(java.lang.Object)
	 */
	@Override
	public boolean isSatisfiedBy(T object) {
		return !spec1.isSatisfiedBy(object);
	}

}
