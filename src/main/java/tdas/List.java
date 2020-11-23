/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author margo
 */
public interface List<E> {
    boolean addFirst(E element);
    boolean addLast(E element);
    boolean add(E element, int index);
    boolean remove(int index);
    E get(int index);
    boolean contains (E element);
    boolean removeFirst();
    boolean removeLast();
    E getFirst();
    E getLast();
    int size();
    boolean isEmpty();
}

