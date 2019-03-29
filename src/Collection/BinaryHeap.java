package Collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Binary heap.
 */
public class BinaryHeap {

    private List<Integer> list;

    /**
     * Instantiates a new Binary heap.
     */
    public BinaryHeap()
    {
        this.list = new ArrayList<>();
    }

    /**
     * Is empty boolean.
     *
     * @return the boolean
     */
    public boolean isEmpty()
    {
        return this.list.isEmpty();
    }

    /**
     * Insert.
     *
     * @param i the
     */
    public void insert(int i)
    {
        list.add(i);
        Integer current = list.size() -1;


        while(current !=0 && list.get(current).compareTo(list.get(getParentIndex(current))) <0)
        {
            Integer temp = list.get(getParentIndex(current));
            list.set(getParentIndex(current), list.get(current));
            list.set(current, temp);
            current = getParentIndex(current);
        }
    }

    /**
     * Delete the smallest number of the heap and percolate down the heap.
     *
     * @return the int
     */
    public int deleteMin() {
        int to = list.get(0);
        int toPercolateDown = list.remove(list.size() - 1);
        list.set(0, toPercolateDown);
        int currentIndex = 0;

        while (currentIndex <= Math.log(list.size())
                && (toPercolateDown > Math.min(list.get(getIndexLeftChild(currentIndex)), list.get(getIndexRightChild(currentIndex))))) {
            int leftIndex = getIndexLeftChild(currentIndex);
            int rightIndex = getIndexRightChild(currentIndex);
            int indexMin = list.get(leftIndex) > list.get(rightIndex) ? rightIndex : leftIndex;
            Collections.swap(list, indexMin, currentIndex);
            currentIndex = indexMin;
        }

        return to;
    }


    @Override
    public String toString()
    {
        return this.list.toString();
    }

    /**
     * Simple calculation method that return the parent index in a binary heap
     * @param index the child's index
     * @return the parent's index
     */
    private int getParentIndex(int index){
        return (index -1)/2;
    }

    /**
     * Simple calculation method that return the left child's index in a binary heap
     * @param index the parent's index
     * @return the left child's index
     */
    private int getIndexLeftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * Simple calculation method that return the right child's index in a binary heap
     * @param index the parent's index
     * @return the right child's index
     */
    private int getIndexRightChild(int index) {
        return index * 2 + 2;
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args)
    {
        BinaryHeap b = new BinaryHeap();

        b.insert(6);
        b.insert(7);
        b.insert(10);
        b.insert(15);
        b.insert(12);
        b.insert(17);
        b.insert(5);

        System.out.println(b.toString());
        System.out.println(b.deleteMin());
        System.out.println(b.toString());
        System.out.println(b.deleteMin());

        System.out.println(b.toString());

        BinaryHeap bh = new BinaryHeap();
        for (int i = 10; i > 0; i--) {
            bh.insert(i);
        }
        System.out.println("Expected:\n[1, 2, 5, 4, 3, 9, 6, 10, 7, 8]");
        System.out.println("Actual:\n" + bh.toString());
        bh.deleteMin();
        System.out.println("Expected:\n[2, 3, 5, 4, 8, 9, 6, 10, 7]");
        System.out.println("Actual:\n" + bh.toString());
    }

}
