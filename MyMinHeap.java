/**
 * MyMinHeap class creates a min heap tree using an Arraylist 
 * and helper methods.
 *
 * Name: Jamshed Ashurov
 * PID: A15475198
 * Email: jashurov@ucsd.edu
 * Login: cs12sp20aqk
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class creates a min heap, a binary tree with two properties:
 * 1) The tree is complete. 
 * 2)) The key of a node is not greater the keys of its children. 
 */
public class MyMinHeap<E extends Comparable<E>>
{
    protected List<E> list; //The underlying data strcuture of the heap

    private final static int TWO = 2; //used to find the parent/child indices
    
    /**
     * Constructor of the class that simply intializes an empty arraylist.
     */    
    public MyMinHeap() {
        list = new ArrayList<>(); 
    }
    /**
     * Constructor of the class that moves the collection into the array.
     *
     * @param collection - the provided array 
     * Throws exceptions for invalid(null) input. 
     */
    public MyMinHeap(Collection<? extends E> collection) {
        this();
        if (collection == null || collection.contains(null) == true) {
            throw new NullPointerException(); 
        }
        list = new ArrayList<>(collection); 
        for (int i = this.list.size() - 1; i >=0; i--) {
            System.out.println("check"); 
            percolateDown(i); 
        }
    }

    /**
     * A helper method that swaps the values of the two indicated indices.
     *
     * @param from - the index to swap
     * @param to - the index to swap
     * @return null
     */
    protected void swap(int from, int to) {
        E temp = this.list.get(from); 
        this.list.set(from, this.list.get(to));
        this.list.set(to, temp); 
    }
    
    /**
     * A helper method that returns the parent index of the specified key.
     *
     * @param index - the index of the key
     * @return The Parent index
     */
    protected int getParentIdx(int index) {
        return ((index - 1) / TWO); 
    }
    
    /**
     * A helper method that returns the left child index 
     * of the specified key.
     *
     * @param index - the index of the key
     * @return The left child index
     */
    protected int getLeftChildIdx(int index) {
        return (TWO * (index) + 1); 
    }
    
    /**
     * A helper method that returns the right child index 
     * of the specified key.
     *
     * @param index - the index of the key
     * @return The right child index
     */
    protected int getRightChildIdx(int index) {
        return (TWO * (index) + TWO);
    }
    
    /**
     * A helper method that returns the index of the smaller child 
     * of the specified key.
     *
     * @param index - the index of the key
     * @return The index of the smaller child
     */
    protected int getMinChildIdx(int index) {
        int l_ind = getLeftChildIdx(index); //left child index
        int r_ind = getRightChildIdx(index); //right child index
        
        if (l_ind > this.list.size() - 1) {
            return -1; //No children 
        }
        else if (l_ind == this.list.size() - 1) {
            return l_ind; //Only one child
        }
        else {
            E l_value = this.list.get(l_ind); //left child value
            E r_value = this.list.get(r_ind); //right child value 

            if (l_value.compareTo(r_value) == 0) {
                return l_ind; 
            }
            else if(l_value.compareTo(r_value) > 0) {
                return r_ind;
            }
            else {
                return l_ind; 
            }
        }
    }
    
    /**
     * A helper method that reorders the tree to match the property
     * of the min heap when going up the tree at the specified key. 
     * This method uses recursion. 
     *
     * @param index - the index of the key
     * @return null
     */
    protected void percolateUp(int index) {
        if (index == 0) {
            return; 
        }
        else {
            int p_ind = getParentIdx(index); //parent index
            E p_value = this.list.get(p_ind); //parent value
            E curr_value = this.list.get(index); //value at this index
            if (p_value.compareTo(curr_value) <= 0) {
                return; 
            }
            else {
                swap(index,p_ind); 
                percolateUp(p_ind); //recursion
            }
        }
    }
    
    /**
     * A helper method that reorders the tree to match the property
     * of the min heap when going down the tree at the specified key. 
     * This method uses recursion. 
     *
     * @param index - the index of the key
     * @return null
     */
    protected void percolateDown(int index) {
        int min_index = getMinChildIdx(index); //smaller child index
        if (min_index == -1) {
            return;
        }
        E min_value = this.list.get(min_index); //smaller child value
        E curr_value = this.list.get(index); //value at current index
        if (curr_value.compareTo(min_value) <= 0) {
            return;
        }
        else {
            swap(index, min_index); 
            percolateDown(min_index); //recursion
        }
    }
    
    /**
     * A helper method that deletes an element at the specified key.
     * Reorders the tree using percolate up and down helper methods
     * after the deletion. 
     *
     * @param index - the index of the key
     * @return The deleted element
     */
    protected E deleteIndex(int index) {
        if (this.list.size() == 1) {
            return (this.list.remove(0)); //if only one element in the heap
        }
        else {
            swap(this.list.size() - 1, index); //swaps with the last element
            E del_val = this.list.remove(this.list.size() - 1); 
            E replace_val = this.list.get(index); 
            if (replace_val.compareTo(del_val) < 0) {
                percolateUp(index); 
            }
            else if (replace_val.compareTo(del_val) > 0) {
                percolateDown(index); 
            }
            return del_val; 
        }
    }
    
    /**
     * A core method of the heap that inserts an element to the tree.
     * Inserts from the buttom of the tree and reorders the tree 
     * using percolate up helper method. 
     * Throws an exception for invalid input. 
     *
     * @param element - the element to be inserted 
     * @return null
     */
    public void insert(E element) {
        if (element == null) {
            throw new NullPointerException(); 
        }
        this.list.add(element); 
        percolateUp(this.list.size() - 1); //Check with println if true
    }
    
    /**
     * A core method that returns the minimum/higherst prirotity element 
     * of the tree/array.
     *
     * @return Null if the heap is empty or the element if not
     */
    public E getMin() {
        if (this.list.size() == 0) { 
            return null; 
        }
        else {
            return (this.list.get(0));//the priority item will always be first
        }
    }
    
    /**
     * The core method that removes the highest priority item of the heap.
     * Uses deleteIndex helper method to remove the item. 
     *
     * @return the removed priority item
     */
    public E remove() {
        if (this.list.size() == 0) {
            return null; 
        } 
        else {
            return (deleteIndex(0)); //priority item will always be first
        }
    }
    
    /**
     * The core method that returns the size of the heap.
     *
     * @return The size of the heap.
     */
    public int size() {
        return (this.list.size()); 
    }
    
    /**
     * The core method that removes all the elements from the heap.
     *
     * @return null
     */
    public void clear() {
        this.list.clear(); 
        //check if the list is empty but not null
    }       
}
