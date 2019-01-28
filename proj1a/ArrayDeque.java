/** An ArrayDeque is a sequence of items in array form which can both
 * be added from the front and the end
 * @param <T>
 */
public class ArrayDeque<T> {
    int size;
    int nextFirst;
    int nextLast;
    int capacity;
    private int RFACTOR = 4;
    private int RdcFACTOR = 2;
    T[] items;

    public ArrayDeque() {
        size = 0;
        nextFirst = 4;
        nextLast = 5;
        capacity = 8;
        items = (T[]) new Object[8];
    }

    public void addFirst(T i) {
        if (nextFirst == nextLast) {
            resize();
        }
        size += 1;
        items[nextFirst] = i;
        nextFirst = adjustMinusIndex(nextFirst - 1);
    }

    public void addLast(T i) {
        if (nextFirst == nextLast) {
            resize();
        }
        size += 1;
        items[nextLast] = i;
        nextLast = adjustPlusIndex(nextLast + 1);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size == 0) {
            System.out.print("There is nothing in the Deque");
            return;
        }
        int currentPosition = adjustPlusIndex(nextFirst + 1);
        int numberToPrint = size;
        while (numberToPrint != 0) {
            System.out.print(items[currentPosition]);
            currentPosition = adjustPlusIndex(currentPosition + 1);
            numberToPrint -= 1;
        }
    }

    public T removeFirst() {
        if (size / capacity <= 0.25) {
            rduceSize();
        }
        size -= 1;
        nextFirst = adjustPlusIndex(nextFirst + 1);
        T removeItem = items[nextFirst];
        items[nextFirst] = null;
        return removeItem;
    }

    public T removeLast() {
        if (size / capacity <= 0.25) {
            rduceSize();
        }
        size -= 1;
        nextLast = adjustMinusIndex(nextLast - 1);
        T removeItem = items[nextLast];
        items[nextLast] = null;
        return removeItem;
    }

    public T get(int index) {
        if (index > capacity - 1) {
            System.out.println("index is out of bound, please choose another index");
            return null;
        }
        return items[index];
    }

    private int adjustMinusIndex(int index) {
        if (index < 0) {
            return capacity - 1;
        }
        return index;
    }

    private int adjustPlusIndex(int index) {
        if (index == capacity) {
            return 0;
        }
        return index;
    }

    private void resize() {
        capacity = capacity * RFACTOR;
        T[] newItemArray = (T[]) new Object[capacity];
        System.arraycopy(items, 0, newItemArray, 0, nextLast);
        System.arraycopy(items, adjustPlusIndex(nextFirst + 1), newItemArray,
                capacity - size + nextLast + 1, size - nextLast);
        nextFirst = nextLast + capacity / RFACTOR * (RFACTOR - 1);
        items = newItemArray;
    }

    private void rduceSize() {
        capacity = capacity / RdcFACTOR;
        T[] newItemArray = (T[]) new Object[capacity];
        System.arraycopy(items, 0, newItemArray, 0, nextLast);
        System.arraycopy(items, adjustPlusIndex(nextFirst + 1), newItemArray,
                capacity - size + nextLast + 1, size - nextLast);
        nextFirst = nextLast - capacity * (RdcFACTOR - 1);
        items = newItemArray;
    }
}
