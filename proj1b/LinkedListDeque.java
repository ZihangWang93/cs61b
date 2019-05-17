/** An LinkedListDeque is a structure that can be expanded
 * from either the front or the end
 * @param <T>
 */
public class LinkedListDeque<T> implements Deque<T> {
    private int size;
    private ItemNode frontSentinel;
    private ItemNode backSentinel;

    private class ItemNode {
        private T item;
        private ItemNode prePointer;
        private ItemNode nextPointer;

        private ItemNode(T i, ItemNode p, ItemNode n) {
            item = i;
            prePointer = p;
            nextPointer = n;
        }
    }

    public LinkedListDeque() {
        size = 0;
        frontSentinel = new ItemNode(null, null, null);
        backSentinel = new ItemNode(null, null, null);
        frontSentinel.nextPointer = backSentinel;
        backSentinel.prePointer = frontSentinel;
    }

    @Override
    public void addFirst(T i) {
        size += 1;
        ItemNode newItem = new ItemNode(i, frontSentinel, frontSentinel.nextPointer);
        frontSentinel.nextPointer.prePointer = newItem;
        frontSentinel.nextPointer = newItem;
    }

    @Override
    public void addLast(T i) {
        size += 1;
        ItemNode newItem = new ItemNode(i, backSentinel.prePointer, backSentinel);
        backSentinel.prePointer.nextPointer = newItem;
        backSentinel.prePointer = newItem;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        if (size == 0) {
            System.out.print("There is nothing in the deque");
            return;
        }

        ItemNode listToPrint = frontSentinel.nextPointer;
        while (listToPrint != backSentinel) {
            System.out.print(listToPrint.item + " ");
            listToPrint = listToPrint.nextPointer;
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        T itemRemoved = frontSentinel.nextPointer.item;
        frontSentinel.nextPointer.nextPointer.prePointer = frontSentinel;
        frontSentinel.nextPointer = frontSentinel.nextPointer.nextPointer;
        size -= 1;
        return itemRemoved;
    }

    @Override
    public  T removeLast() {
        if (size == 0) {
            return null;
        }

        T itemRemoved = backSentinel.prePointer.item;
        backSentinel.prePointer.prePointer.nextPointer = backSentinel;
        backSentinel.prePointer = backSentinel.prePointer.prePointer;
        size -= 1;
        return  itemRemoved;
    }

    @Override
    public T get(int index) {
        if (index == 0) {
            return frontSentinel.nextPointer.item;
        }

        if (size <= index) {
            return null;
        }

        ItemNode getPointer = frontSentinel.nextPointer;
        while (index != 0) {
            getPointer = getPointer.nextPointer;
            index -= 1;
        }
        return getPointer.item;
    }

    public T getRecursive(int index) {
        if (size <= index) {
            return null;
        }

        return getRecursiveItemNode(index).item;
    }

    private ItemNode getRecursiveItemNode(int index) {

        if (index == 0) {
            return frontSentinel.nextPointer;
        }

        return getRecursiveItemNode(index - 1).nextPointer;
    }
}
