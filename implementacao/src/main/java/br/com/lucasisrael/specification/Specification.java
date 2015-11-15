
package br.com.lucasisrael.specification;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Define contrato de specification
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 14/11/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public interface Specification<T> {
	/**
	 * Verifica se objeto é satisfeito pela especification
	 * @param object
	 * @return
	 */
	boolean isSatisfiedBy(T object);
	
	/**
	 * compoem specification dinamicamente utilizando operação AND. Sera criada uma nova especificação.
	 * @param specification
	 * @return
	 */
	Specification<T> and(Specification<T> specification);

	/**
	 * compoem specification dinamicamente utilizando operação OR. Sera criada uma nova especificação.
	 * @param specification
	 * @return
	 */
	Specification<T> or(Specification<T> specification);
	
	/**
	 * compoem specification dinamicamente utilizando operação NOT. Sera criada uma nova especificação.
	 * @param specification
	 * @return
	 */
	Specification<T> not(Specification<T> specification);
	
	
}
