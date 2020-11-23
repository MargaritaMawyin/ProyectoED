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
public class CircularLinkedList<E> implements List<E>{
    private Node<E> last;
    private int current;
    
    private class Node<E>{
        private E data;
        private Node<E> next;
        private Node<E> previous;
    public Node(E data){
        this.data=data;
    }
    }
    
    @Override
    public boolean addFirst(E element) {
    if(element ==null) return false;
        Node<E> n=new Node<>(element);
        if(isEmpty())
            last=n;
        else{
            n.next=last.next;
            last.next=n;
            last=n;
            
        }
        current++;
        return true;    }

    @Override
    public boolean addLast(E element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(E element, int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E get(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(E element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeFirst() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeLast() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E getFirst() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E getLast() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        return current==0;  }
   
    
        /**
     * OJO
     * @return 
     */
     @Override
    public String toString(){
        if(isEmpty())return "[]"; //si la lista esta vacia, bota []
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> i=last;
        do{
            sb.append(i.data); 
            System.out.println(i.data);
            sb.append(","); 
            i=i.next;
        }
        while(i!=last.next );
        sb.append(last.data);
        System.out.println(last.data);
        sb.append("]");
        return sb.toString();
        
    }
    
}
