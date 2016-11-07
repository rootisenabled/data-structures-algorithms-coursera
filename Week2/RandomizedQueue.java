import java.util.Iterator;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue < Item > implements Iterable < Item > {

    private Item[] queue;
    private int N = 0;

    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = queue[i];
        }
        queue = copy;
    }

    private void checkItem(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
    }

    public void enqueue(Item item) {
        checkItem(item);
        if (N == queue.length) {
            resize(2 * queue.length);
        }
        queue[N++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int offset = StdRandom.uniform(N);
        Item item = queue[offset];
        if (offset != N - 1) queue[offset] = queue[N - 1];
        queue[N - 1] = null;
        N--;
        if (N > 0 && N == queue.length / 4) resize(queue.length / 2);
        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        return queue[StdRandom.uniform(N)];
    }

    public Iterator < Item > iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator < Item > {
        private int current = 0;
        private Item[] arrClone;

        public RandomizedQueueIterator() {
            arrClone = (Item[]) new Object[N];
            for (int i = 0; i < N; i++) {
                arrClone[i] = queue[i];
            }
        }

        public boolean hasNext() {
            return current < N;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Item item = arrClone[current++];
            return item;
        }
    }

    public static void main(String[] args) {
        RandomizedQueue < Integer > randomQueue = new RandomizedQueue < Integer > ();

        randomQueue.enqueue(1);
        randomQueue.enqueue(2);
        randomQueue.enqueue(3);
        randomQueue.enqueue(4);
        randomQueue.enqueue(5);
        randomQueue.enqueue(6);
        randomQueue.enqueue(7);
        randomQueue.enqueue(8);
        randomQueue.enqueue(9);
        randomQueue.dequeue();
        randomQueue.dequeue();

        StdOut.println("Output: ");
        for (Integer x: randomQueue) {
            StdOut.print(x + " ");
        }
    }
}