/** An ArrayDeque is a sequence of items in array form which can both
 * be added from the front and the end
 * @param <T>
 */
public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private int capacity;
    private int RFACTOR = 4;
    private int RdcFACTOR = 2;
    private T[] items;

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
        float rate = (float)size / capacity;
        if (rate <= 0.25) {
            rdcSize();
        }
        size -= 1;
        nextFirst = adjustPlusIndex(nextFirst + 1);
        T removeItem = items[nextFirst];
        items[nextFirst] = null;
        return removeItem;
    }

    public T removeLast() {
        float rate = (float)size / capacity;
        if (rate <= 0.25) {
            rdcSize();
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
        return items[adjustPlusIndex(nextFirst + index + 1)];
    }

    private int adjustMinusIndex(int index) {
        if (index < 0) {
            return capacity + index;
        }
        return index;
    }

    private int adjustPlusIndex(int index) {
        if (index >= capacity) {
            return index - capacity;
        }
        return index;
    }

    private void resize() {
        capacity = capacity * RFACTOR;
        T[] newItemArray = (T[]) new Object[capacity];

        if (nextLast == 0) {
            System.arraycopy(items, 1, newItemArray, capacity - size, size);
            nextFirst = capacity -1 - size;
            items = newItemArray;
            return;
        }
        System.arraycopy(items, 0, newItemArray, 0, nextLast);
        System.arraycopy(items, adjustPlusIndex(nextFirst + 1), newItemArray,
                capacity - size + nextLast, size - nextLast);
        nextFirst = nextLast + capacity / RFACTOR * (RFACTOR - 1);
        items = newItemArray;
    }

    private void rdcSize() {
        capacity = capacity / RdcFACTOR;
        T[] newItemArray = (T[]) new Object[capacity];

        if (nextLast == 0) {
            System.arraycopy(items, nextFirst + 1, newItemArray, capacity - size, size );
            items = newItemArray;
            nextFirst = capacity - size -1;
            return;
        }

        if (nextFirst == 0) {
            System.arraycopy(items, 0, newItemArray, 0, nextLast);
            items = newItemArray;
            nextFirst = capacity - 1;
            return;
        }
        System.arraycopy(items, 0, newItemArray, 0, nextLast);
        System.arraycopy(items, nextFirst + 1, newItemArray, capacity - size + nextLast,size - nextLast);
        nextFirst = nextFirst - capacity * (RdcFACTOR - 1);
        items = newItemArray;
    }
}
