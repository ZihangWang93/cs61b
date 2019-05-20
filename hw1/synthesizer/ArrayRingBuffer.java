// TODO: Make sure to make this class a part of the synthesizer package
// package <package name>;
package synthesizer;
import synthesizer.AbstractBoundedQueue;

import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        rb = (T[]) new Object[capacity];
        this.capacity = capacity;
        this.fillCount = 0;
        this.first = 0;
        this.last = 0;

    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (isFull()) {
            throw new RuntimeException("Ring Buffer overflow");
        } else {
            rb[last] = x;
            fillCount += 1;
            last = (last + 1) % capacity;
        }

    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            T returnValue = rb[first];
            rb[first] = null;
            fillCount -= 1;
            first = (first + 1) % capacity;
            return returnValue;
        }

    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        if (isEmpty()) {
            throw new RuntimeException("This is an empty one");
        }
        return rb[first];
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
    @Override
    public Iterator<T> iterator() {
        return new BufferIterator();
    }

    private class BufferIterator implements Iterator<T> {
        private int wizPos;
        private int count;

        public BufferIterator() {
            wizPos = first;
            count = 0;
        }

        public boolean hasNext() {
            return (count < fillCount);
        }

        public T next() {
            T returnItem = rb[wizPos];
            wizPos = (wizPos + 1) % capacity;
            count += 1;
            return returnItem;
        }
    }
}
