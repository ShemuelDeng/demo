package link;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * 单链表
 */
public class SingleDirectLink<E> {


    private Node<E> head  = new Node(); // head node with no data

    private int size; // the size of the link

    private int iteratorCount; // the count for iterator

    private Node iteratorCurrent = head; // current element for iterator;
    // add a node to link at the end
    public E add(E element){
        Node newNode = new Node(element);
        Node temp = head;
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = newNode; // add a new node to the link's end
        size++;
        return element;
    }

    // and a node to the specific index of the link
    public E add(int index,E element){
        checkForAdd(index);
        Node newNode = new Node(element);
        Node temp = head;
        for (int i = 0; i<= index; i++ ){
            if (i == index){
                Node oldNode = temp.next;
                temp.next = newNode;
                newNode.next = oldNode;
                size++;
            }else{
                temp = temp.next;
            }
        }
        return element;
    }

    // if the link is empty
    public boolean isEmpty(){
        return size == 0;
    }

    // return the size of link
    public int size(){
        return this.size;
    }


    // to display the element in the link
    @Override
    public String toString(){
        Node temp = head;
        if (size() == 0) return "[]";

        StringBuilder result = new StringBuilder("[");
        while (temp.next != null){
            temp = temp.next;
            result.append(temp.data);
           if (temp.next != null){
               result.append(", ");
           }
        }
        result.append("]");
        return  result.toString();
    }


    // remove the end of link
    public boolean remove(){
        if (size == 0){
            return false;
        }
        Node temp = head;
        for (int i = 0; i < size; i++){
            if (i == size-1){
                temp.next = null;
                size--;
                return true;
            }else {
                temp = temp.next;
            }
        }
        return false;
    }

    // remove the node on specific index
    public boolean remove(int index){
        check(index);
        index++ ;

        if (index == size){
           return this.remove();
        }

        Node temp = head;
        for (int i = 0; i < index; i++){
            if (i == index-1){
                temp.next = temp.next.next;
                size--;
                return true;
            }else {
                temp = temp.next;
            }
        }
        return false;
    }

    public Iterator iterator(){
        iteratorCount = size;
        return new Itr();
    };

    // validate the index
    private void check(int index) {
        if (index   >= size || index < 0){
            throw new IndexOutOfBoundsException("size:"+size+", index:"+index);
        }
    }

    private void checkForAdd(int index) {
        if (index   > size || index < 0){
            throw new IndexOutOfBoundsException("size:"+size+", index:"+index);
        }
    }
    // get the element on the specific index
    public E get(int index){
        check(index); // validate the index
        index ++;
        Node<E> temp = head;
        for (int i = 0; i < index; i++){
            if (i == index - 1){
                return (E)temp.next.data;
            }else {
                temp = temp.next;
            }
        }
        return null;
    }

    // iterator
    public void forEach(Consumer<? super E> action){
        Node temp = head;
        for (int i = 0; i < size; i++){
            action.accept((E)temp.next.data);
            temp = temp.next;
        }
    }

    // return the index of the specific element
    public int indexOf(E element){
        Node temp = head;
        for (int i = 0; i < size; i++){
            if (temp.next.data.equals(element)){
                return i;
            }
            temp = temp.next;
        }
        return -1;
    }

    // reverse the link
    public void reverse(){
        Node temp = head;
        Node newHead = new Node();
        while (temp.next != null){
            Node current = new Node(temp.next.data);
            if (newHead.next != null){
                Node old = newHead.next;
                newHead.next = current;
                current.next = old;
            }else{
                newHead.next = current;
            }
            temp = temp.next; // point the next
        }
        head.next =  newHead.next;
        newHead.next = null; // let gc to clear the temp link

    }

    // anonymous inner class
    private class Node<E>{
        public E data; // data
        public Node next; //  next node

        public Node(){
        }
        public Node(E data){
            this.data = data;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private class Itr implements Iterator{
        @Override
        public boolean hasNext() {
            if (iteratorCount>0) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            Node result = null;
            if (hasNext()){
                result = iteratorCurrent.next;
                iteratorCurrent = iteratorCurrent.next;
                iteratorCount--;
                return result.data;
            }else {
                throw new NoSuchElementException();
            }
        }


        @Override
        public void remove() {
            SingleDirectLink.this.remove(size-iteratorCount-1);
        }
    }
}
