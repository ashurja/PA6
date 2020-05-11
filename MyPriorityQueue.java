import java.util.Collection; 

/**
 * MyPriorityQueue class creates a priority queue.
 *
 * Name: Jamshed Ashurov
 * PID: A15475198
 * Email: jashurov@ucsd.edu
 * Login: cs12sp20aqk
 */

public class MyPriorityQueue<E extends Comparable<E>> {
    
    protected MyMinHeap<E> heap; 

    public MyPriorityQueue() {
        heap = new MyMinHeap<>(); 
    }

    public MyPriorityQueue(Collection<? extends E> collection) {
        if (collection == null || collection.contains(null) == true) {
            throw new NullPointerException();
        }
        heap = new MyMinHeap<>(collection);
    }

    public void push(E element) {
        if (element == null) {
            throw new NullPointerException(); 
        }
        heap.insert(element); 
    }

    public E peek() {
        return (heap.getMin());
    }

    public E pop() {
        return (heap.remove()); 
    }

    public int getLength() {
        return (heap.size());
    }

    public void clear() {
        heap.clear();
    }
}
