
package br.com.lucasisrael.regra;

import java.util.List;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Representa uma regra
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 14/11/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

@SuppressWarnings({ "unused"})
public class Regra<T> {
	private String nome;
	private List<Condicao> condicoes;
	private ExecutorRegra<T> executorRegra;
	
}
