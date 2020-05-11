import java.util.Collection; 

/**
 * MyPriorityQueue class creates a priority queue.
 *
 * Name: Jamshed Ashurov
 * PID: A15475198
 * Email: jashurov@ucsd.edu
 * Login: cs12sp20aqk
 */

/**
 * This class creates a priority queue using the MyMinHeap class and
 * its methods.
 */
public class MyPriorityQueue<E extends Comparable<E>> {
    
    protected MyMinHeap<E> heap; //the heap that holds the elements of queue

    /**
     * Constructor that simply initializes the priorityqueue(heap)
     */
    public MyPriorityQueue() {
        heap = new MyMinHeap<>(); 
    }

    /**
     * Constructor that stores the elements of the given collection to the 
     * priority queue. 
     * Throws an exception for invalid input. 
     *
     * @param collection - the given array of elements 
     */
    public MyPriorityQueue(Collection<? extends E> collection) {
        if (collection == null || collection.contains(null) == true) {
            throw new NullPointerException();
        }
        heap = new MyMinHeap<>(collection);
    }
    
    /**
     * Inserts the elements to the back of the queue.
     * Throws an exception for invalid input.
     *
     * @param element - The element to be inserted
     * @return null
     */
    public void push(E element) {
        if (element == null) {
            throw new NullPointerException(); 
        }
        heap.insert(element); 
    }
    
    /**
     * Returns the highest priority element of the queue.
     *
     * @return the priority element
     */
    public E peek() {
        return (heap.getMin());
    }
    
    /**
     * Removes the highest priority element of the queue. 
     *
     * @return the highest priority element.
     */
    public E pop() {
        return (heap.remove()); 
    }
    
    /**
     * Retunrs the size of the priority queue.
     *
     * @return the size of the queue. 
     */
    public int getLength() {
        return (heap.size());
    }
    
    /**
     * Removes all the elements from the queue. 
     *
     * @return null
     */
    public void clear() {
        heap.clear();
    }
}
