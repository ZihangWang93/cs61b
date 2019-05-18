package synthesizer;

public interface BoundedQueue<T> {
    int capacity();
    int fillCount();
    void enquene(T x);
    T dequene();
    T peek();

    default boolean isEmpty() {
        return fillCount() == 0;
    }

    default boolean isFull() {
        return fillCount() == capacity();
    }
}
