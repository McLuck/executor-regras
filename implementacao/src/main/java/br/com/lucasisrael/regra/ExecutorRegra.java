
package br.com.lucasisrael.regra;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Executor de regra
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 14/11/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public interface ExecutorRegra<T> {

	/**
	 * Executa regra para o objeto
	 * @param o objeto de dominio que precisa ter a regra executada
	 */
	void executar(T o);
}
