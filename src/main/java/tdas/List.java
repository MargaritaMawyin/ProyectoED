/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdas;

import modelo.exceptions.ValorNuloException;

/**
 *
 * @author JOVEN EJEMPLAR
 * @param <E>
 */
public interface List<E> {
    boolean addFirst(E element);
    boolean addLast(E element);
    boolean add(E element, int index);
    E get(int index)throws ValorNuloException;
    int size();
    boolean isEmpty();
    boolean remove(int index);
    boolean contains(E element);
    boolean removeFirst();
    boolean removeLast();
    E getFirst()throws ValorNuloException;
    E getLAst()throws ValorNuloException;
}
