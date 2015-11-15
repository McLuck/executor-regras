
package br.com.lucasisrael.specification;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Implementação abstrata de specification
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 14/11/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public abstract class AbstractSpecification<T> implements Specification<T> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public abstract boolean isSatisfiedBy(T object);

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Specification<T> and(Specification<T> specification) {
		return new AndSpecification<T>(this, specification);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Specification<T> or(Specification<T> specification) {
		return new OrSpecification<T>(this, specification);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Specification<T> not(Specification<T> specification) {
		return new NotSpecification<T>(this, specification);
	}

}
