/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdas;

import java.util.Iterator;
import modelo.exceptions.ValorNuloException;

/**
 *
 * @author JOVEN EJEMPLAR
 */
public class circularDoublyLinkedList<E> implements List<E>, Iterable<E>{
    private Node<E> last;
    private int current;
    private boolean stop = false;
    
    public circularDoublyLinkedList(){
        last=null;
        current=0;
    }
    
    private class Node<E>{
        private Node<E> previous;
        private Node<E> next;
        private E data;
        
        public Node(E data){
            this.data=data;
            previous=null;
            next=null;
        }
    }

    @Override
    public boolean addFirst(E element) {
        Node<E> newNode = new Node<>(element);
        if(element == null) return false;
        else if(isEmpty()){
            /*newNode.next = newNode; //su siguiente se apunta a si mismo
            newNode.previous = newNode;//su anterior se apunta a si mismo
            
            last=newNode;*/
            last=newNode;
            last.next = last.previous =newNode;
            
            newNode.previous = last;
            newNode.next =  last.next;
            //last.next = last = newNode;
        }else{
            Node<E> tmp = last.next;
            last.next=newNode;
            newNode.next = tmp;
            newNode.previous = last;
        }
        current++;
        return true;
    }

    @Override
    public boolean addLast(E element) {
        Node<E> newNode = new Node<>(element);
        if(element==null) return false;
        else if(isEmpty()){
            last = newNode;
            last.next = last.previous = newNode;
            
            newNode.previous = last;
            newNode.next =  last.next;
        }else{
            newNode.previous = last;
            newNode.next = last.next;
            
            last.next.previous = newNode;
            last.next = newNode;
            last=newNode;
        }
        current++;
        return true;
    }

    @Override
    public boolean add(E element, int index) {
        Node<E> newNode = new Node<>(element);
        if(element == null || index<0 || index>=current) return false;
        //else if(index==0) return addFirst(element);
        else if(index==current-1) return addLast(element);
        else{
            Node<E> first = last.next;
            while(index-->1){
                first = first.next;
            }
            Node<E> tmp = first.next;
            
            first.next = newNode;
            tmp.previous = newNode;
            
            newNode.previous = first;
            newNode.next = tmp;
            /*newNode.previous = first.previous;
            newNode.next = first.next;
            
            first.previous.next = newNode;
            first.next.previous = newNode;*/
            
        }
        current++;
        return true;
    }

    @Override
    public E get(int index) throws ValorNuloException {
        if(isEmpty()) throw new ValorNuloException("Lista Vacía");
        else if(index<0 || index>=current) throw new ValorNuloException("Indice fuera de rango");
        else if(index == 0) return getFirst();
        else if(index == current-1) return getLAst();
        Node<E> nodeResp = last.next;
        while(index-->0){
            nodeResp =  nodeResp.next;
        }
        return nodeResp.data;
    }

    @Override
    public int size() {
        return current;
    }

    @Override
    public boolean isEmpty() {
        return(last==null);
    }

    @Override
    public boolean remove(int index) {
        if(isEmpty() || index<0 || index>=current) return false;
        Node<E> nodeItr = last.next;
        //if(index==0) return removeFirst();
        if(index==current-1) return removeLast();
        while(index-->0){
            nodeItr = nodeItr.next;
        }
        nodeItr.previous.next = nodeItr.next;
        nodeItr.next.previous = nodeItr.previous;
        
        nodeItr.data = null;
        nodeItr.previous = null;
        nodeItr.next = null;
        
        current--;
        return true;
    }

    @Override
    public boolean contains(E element) {
        if(isEmpty() || element == null) return false;
        if(last.data.equals(element)) return true;
        Node<E> nodeItr = last.next;
        do{
            if(nodeItr.data.equals(element))return true;
            nodeItr = nodeItr.next;
        }while(nodeItr != last.next);
        return false;
    }

    @Override
    public boolean removeFirst() {
        if(isEmpty()) return false;
        if(last == last.next){
            last.data = null;
            last = last.next = null;
        }else{
            Node<E> first = last.next;
            first.data=null;
            first.next.previous = last;
            last.next = first.next;
            
            first.previous = null;
            first.next = null;
        }
        current--;
        return true;
    }

    @Override
    public boolean removeLast() {
        if(isEmpty()) return false;
        if(last == last.next){
            System.out.println("Un solo elemento");
            last.data = null;
            last = last.next = null;
        }else{
            
            last.data=null;
            last.previous.next = last.next;
            last.next.previous = last.previous;
            
            last = last.previous;
            //last.previous = null; NO MESSIRVE
            //last.next = null;     NO MESSIRVE
            
        }
        current--;
        return true;
    }

    @Override
    public E getFirst() throws ValorNuloException {
        if(isEmpty()) throw new ValorNuloException("Lista vacia");
        return last.next.data;
    }

    @Override
    public E getLAst() throws ValorNuloException {
        if(isEmpty()) throw new ValorNuloException("Lista vacia");
        return last.data;
    }

    @Override
    public String toString(){
        if(isEmpty()) return("[]");
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> i = last.next;
        while(i!=last&&i!=null){
            sb.append(i.data);
            sb.append(", ");
            i=i.next;
        }
        sb.append(last.data);
        sb.append("]");
        return sb.toString();
    }
    
        @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            private Node<E> i = last;
            
            @Override
            public boolean hasNext() {
                return !stop;
            }

            @Override
            public E next() {
                E tmp = i.data;
                i = i.next;
                return tmp;
            }
        };        
        return it;
    }
    
    public void finalizarIterator(){
        stop = true;
    }
    
    public E get(E element) throws ValorNuloException{
        if(isEmpty() || element == null) throw new ValorNuloException("Lista vacia");
        if(last.data.equals(element)) return last.data;        
        Node<E> nodeItr = last.next;
        do{
            if(nodeItr.data.equals(element))return nodeItr.data;
            nodeItr = nodeItr.next;
        }while(nodeItr != last.next);
        throw new ValorNuloException("No encontrado");
    }
}