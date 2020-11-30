/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdas;

import java.util.Collection;
import modelo.exceptions.ValorNuloException;
import java.util.Iterator;
import java.util.ListIterator;

/**
 *
 * @author JOVEN EJEMPLAR
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E>{
    private Node<E> first;
    private Node<E> last;
    private int current;
    
    public DoublyLinkedList(){ //innecesario
        first=null;
        last=null;
        current = 0;
    }

    @Override
    public Iterator<E> iterator() {
        //creacion de clase anónima
        Iterator<E> it = new Iterator<E>(){
            private Node<E> i = first;
            
            @Override
            public boolean hasNext(){
                return i!=null; 
            }
            
            @Override
            public E next(){
                E tmp = i.data;
                i = i.next;
                return tmp;
            }
        };
        return it;
    }
    
    private Node<E> getNode(int index) throws ValorNuloException{
        if(last==null && first==null) throw new ValorNuloException("Lista vacía");
        if(index<0 || index>current) throw new ValorNuloException("Valor fuera de rango");
        else if(index==current-1) return last;
        Node<E> nodeFound = first;
        while(index-->0){
            nodeFound = nodeFound.next;
        }
        return nodeFound;
    }
    
    public ListIterator<E> listIterator(int index)throws ValorNuloException{
        ListIterator<E> lIt = new ListIterator(){
            private Node<E> i = getNode(index);
            private int c = index; //variable de control que indica en qué indice se encuentra ubicado el elemento
            
            @Override
            public boolean hasNext() {
                return i!=null;
            }

            @Override
            public Object next() {
                E tmp = i.data;
                i = i.next;
                c++;
                return tmp;
            }

            @Override
            public boolean hasPrevious() {
                return i!=null;
            }

            @Override
            public Object previous() {
                E tmp = i.data;
                i = i.previous;
                c--;
                return tmp;
            }

            @Override
            public int nextIndex() {
                return c+1;
            }

            @Override
            public int previousIndex() {
                return c-1;
            }

            @Override
            public void remove() {
                /*i.data = null;
                i.previous.next = i.next;
                i.next.previous = i.previous;
                
                i.previous=null;
                i.next=null;*/
//                Node<E> nodeEliminate = i;
//                Node<E> nodeNext = i.next;
//                Node<E> nodePrev = i.previous;
                if(i!=null){
                    Node<E> nodeEliminate = i.previous;
                    System.out.println("VALOR A ELIMINAR: "+nodeEliminate.data);
                    if (nodeEliminate == first) {
                        removeFirst();
                        System.out.println("Se remueve first");
                    }
                    else{
                        /*Node<E> nodeNext = nodeEliminate.next;
                        Node<E> nodePrev = nodeEliminate.previous;

                        nodeEliminate.data=null;
                        nodeEliminate.next=null;
                        nodeEliminate.previous=null;

                        nodeNext.previous = null; nodeNext.previous=nodePrev;
                        nodePrev.next = null; nodePrev.next=nodeNext;*/
                        

                        nodeEliminate.data=null; //HELP GC;
                        nodeEliminate.previous.next = nodeEliminate.next;
                        nodeEliminate.next.previous = nodeEliminate.previous;
                        
                        nodeEliminate.previous = null;
                        nodeEliminate.next = null;
                        
                        current--;
                    }
                }else {
                    System.out.println("Se eliminará last");
                    removeLast();
                }
            }

            @Override
            public void set(Object e) {
                E obj = (E)e;
                i.previous.data = obj;
            }

            @Override
            public void add(Object e) {
                E data = (E)e;
                if(i!=null){
                    Node<E> nodeAct = i.previous;    
                    if(nodeAct == first){
                        System.out.println("Se agrega al inicio");
                        addFirst(data);
                    }else{
                        Node<E> node = new Node(data);

                        nodeAct.next=node;
                        i.previous = node;

                        node.previous = nodeAct;
                        node.next = i;

                        current++;
                    }
                }else{
                    System.out.println("Se agregará elemento al final");
                    addLast(data);
                }
            }
        };
        return lIt;
    }
    
    private class Node<E>{
        private E data;
        private Node<E> next;
        private Node<E> previous;
        
        public Node (E data){
            this.data= data;
        }
    }

    @Override
    public boolean addFirst(E element) {
        if(element == null) return false;
        Node<E> n = new Node<>(element);
        if(isEmpty()){
            first = last= n;
        }else{
            n.next = first;
            first.previous = n;
            first = n;
        }
        current++;
        return true;
    }

    @Override
    public boolean addLast(E element) {
        if(element==null) return false;
        Node<E> carro = new Node<>(element);
        if(isEmpty()){
            first = last = carro;            
        }else{
            carro.previous=last;
            last.next = carro;
            last = carro;
        }
        current++;
        return true;
    }

    @Override
    public boolean add(E element, int index) {
        if(element == null) return false;
        if(index>=current || index<0) return false;
        if(index==0) return addFirst(element);
        if(index==current-1) return addLast(element);
        int i = 0;
        Node<E> nodo = first;
        while(i++<index-1){
            nodo = nodo.next;
        }
        Node<E> carro = new Node<>(element);
        Node<E> tmp = nodo.next;
        
        //nodo.next = null; CREO QUE NO ES NECESARIO PORQUE EN LA SIGUIENTE LINEA DIRECTAMENTE APUNTA AL CARRO
        nodo.next = null; nodo.next = carro;
        tmp.previous = null; tmp.previous = carro;
        
        carro.next=tmp;
        carro.previous=nodo; //AQUI CONECTO EL CARRO DE LA PARTE TRASERA AL NODO CON TODOS LO CARROS ANTERIORES
        
        current++;
        return true;
    }

    @Override
    public E get(int index) throws ValorNuloException {
        if(isEmpty()) throw new ValorNuloException("Lista Vacía");
        if(index>=current || index <0) throw new ValorNuloException("Valor fuera de rango");
        if(index==0) return getFirst();
        if(index==current-1) return getLAst();
        int i = 0;
        Node<E> nodo = first;
        while(i++<index){
            nodo = nodo.next;
        }
        return nodo.data;
    }

    @Override
    public int size() {
        return current;
    }

    @Override
    public boolean isEmpty() {
        return (first == null && last == null);
    }

    @Override
    public boolean remove(int index) {
        if(isEmpty()) return false;
        if(index>=current || index <0) return false;
        if(index==0) return removeFirst();
        if(index==current-1) return removeLast();
        int i = 0;
        Node<E> newPrimeraPart = first;
        while(i++<index-1){
            newPrimeraPart = newPrimeraPart.next;
        }
        Node<E> carroEliminar = newPrimeraPart.next;
        Node<E> newSegundaPart = carroEliminar.next;
        
        //DESVINCULANDO EL CARRO Y BORRANDO SU PASAJERO
        carroEliminar.data =null; //Help GC
        carroEliminar.previous = null;
        carroEliminar.next = null; 
        
        //DESVINCULANDO LO QUE SIGUE DE LA NUEVA PRIMERA PARTE Y CONECTANDO CON LA NUEVA SEGUNDA PARTE
        newPrimeraPart.next = null; newPrimeraPart.next = newSegundaPart;
        
        //DESVINCULANDO LO QUE ESTÁ ANTES DE LA NUEVA SEGUNDA PARTE Y CONECTANDO CON LA NUEVA PRIMERA PARTE
        newSegundaPart.previous = null; newSegundaPart.previous = newPrimeraPart;
        
        current--;
        return true;
    }

    @Override
    public boolean contains(E element) {
        if(element==null) return false;
        for(Node<E> i = first; i!=null; i = i.next){
            if(i.data.equals(element)) return true;
        }
        return false;
    }

    @Override
    public boolean removeFirst() {
        if(isEmpty()) return false;
        if(first == last){
            first.data=null; //HELP GC
            first = last = null;
        }else{
            first.data=null;
            Node<E> newFirst = first.next;
            first.next = null;
            newFirst.previous = null;
            first = newFirst;
        }
        current--;
        return true;
    }

    @Override
    public boolean removeLast() {
        if(isEmpty()) return false;
        if(first == last){
            first.data=null; //Ayuda a desalojar el carro y que garbage collector se lo lleve más rápido
            first = last = null;
        }else{
//            last.previous.next = null; //se quita el sig de previo y queda null
//            last.data=null; //help GC
//            last = last.previous;
            last.data=null;
            Node<E> prev = last.previous;
            prev.next = null;
            last.previous=null; 
            last=prev;
        }
        current--;
        return true;
    }
    
    public void reverse(){
        reverse(first,last);
    }
    private void reverse(Node<E> low, Node<E> high){ //validamos el caso donde si haremos algo
        if(!(low==high || low.previous==high)){
            E tmp = low.data;
            low.data=high.data;
            high.data=tmp;
            reverse(low.next,high.previous);
        }
    }
    
    @Override
    public E getFirst() throws ValorNuloException {
        if(isEmpty()) throw new ValorNuloException("Lista vacía");
        else return first.data;
    }

    @Override
    public E getLAst() throws ValorNuloException {
        if(isEmpty()) throw new ValorNuloException("Lista vacía");
        else return last.data;
    }
    
    @Override
    public String toString(){
        if(isEmpty()) return("[]");
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(Node<E> i = first; i!=last; i=i.next){
            sb.append(i.data);
            sb.append(", ");
        }
        sb.append(last.data);
        sb.append("]");
        return sb.toString();
    }
}
