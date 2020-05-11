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
 * 2) The key of a node is not greater the keys of its children. 
 */
public class MyMinHeap<E extends Comparable<E>>
{
    protected List<E> list; //The underlying data strcuture of the heap
    
    /**
     * Constructor of the class that simply intializes an empty arraylist.
     */    
    public MyMinHeap() {
        list = new ArrayList<>(); 
    }
    /**
     * Constructor of the class that moves the collection into the array.
     * Throws exceptions for invalid(null) input. 
     */
    public MyMinHeap(Collection<? extends E> collection) {

        if (collection == null || collection.contains(null) == true) {
            throw new NullPointerException(); 
        }
        list = new ArrayList<>(collection); 
        for (int i = this.list.size() - 1; i <= 0; i--) {
            percolateDown(i); 
        }
    }

    protected void swap(int from, int to) {
        E temp = this.list.get(from); 
        this.list.set(from, this.list.get(to));
        this.list.set(to, temp); 
    }

    protected int getParentIdx(int index) {
        return ((index - 1) / 2); 
    }

    protected int getLeftChildIdx(int index) {
        return (2 * (index) + 1); 
    }

    protected int getRightChildIdx(int index) {
        return (2 * (index) + 2);
    }

    protected int getMinChildIdx(int index) {
        int l_ind = getLeftChildIdx(index); //left child index
        int r_ind = getRightChildIdx(index); //right child index
        
        if (l_ind > this.list.size() - 1) {
            return -1;
        }
        else if (l_ind == this.list.size() - 1) {
            return l_ind; 
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
            percolateDown(min_index); 
        }
    }
    
    protected E deleteIndex(int index) {
        if (this.list.size() == 1) {
            return (this.list.remove(0));
        }
        else {
            swap(this.list.size() - 1, index); //check with println if true
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

    public void insert(E element) {
        if (element == null) {
            throw new NullPointerException(); 
        }
        this.list.add(element); 
        percolateUp(this.list.size() - 1); //Check with println if true
    }

    public E getMin() {
        if (this.list.size() == 0) {
            return null; 
        }
        else {
            return (this.list.get(0));
        }
    }

    public E remove() {
        if (this.list.size() == 0) {
            return null; 
        } 
        else {
            return (deleteIndex(0)); 
        }
    }

    public int size() {
        return (this.list.size()); 
    }

    public void clear() {
        this.list.clear(); 
        //check if the list is empty but not null
    }       
}
