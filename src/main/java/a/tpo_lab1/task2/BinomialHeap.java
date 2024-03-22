package a.tpo_lab1.task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

import lombok.Getter;

class BinomialHeapNode {

    int key, degree;
    BinomialHeapNode parent;
    BinomialHeapNode sibling;
    BinomialHeapNode child;

    public BinomialHeapNode(int k) {
        key = k;
        degree = 0;
        parent = null;
        sibling = null;
        child = null;
    }

    public BinomialHeapNode reverse(BinomialHeapNode sibl) {
        BinomialHeapNode ret;
        if (sibling != null)
            ret = sibling.reverse(this);
        else
            ret = this;
        sibling = sibl;
        return ret;
    }

    public BinomialHeapNode findMinNode() {
        BinomialHeapNode x = this, y = this;
        int min = x.key;
        while (x != null) {
            if (x.key < min) {
                y = x;
                min = x.key;
            }
            x = x.sibling;
        }
        return y;
    }

    public BinomialHeapNode findANodeWithKey(int value) {
        BinomialHeapNode temp = this, node = null;
        while (temp != null) {
            if (temp.key == value) {
                node = temp;
                break;
            }
            if (temp.child == null)
                temp = temp.sibling;
            else {
                node = temp.child.findANodeWithKey(value);
                if (node == null)
                    temp = temp.sibling;
                else
                    break;
            }
        }
        return node;
    }

    public int getSize() {
        return (
                1 + ((child == null) ? 0 : child.getSize())
                        + ((sibling == null) ? 0 : sibling.getSize()));
    }
}

public class BinomialHeap {
    private BinomialHeapNode Nodes;
    @Getter
    private int size;

    public BinomialHeap() {
        Nodes = null;
        size = 0;
    }

    public boolean isEmpty() {
        return Nodes == null;
    }

    public void clearHeap() {
        Nodes = null;
        size = 0;
    }


    public void insert(int value) {
        if (value > 0) {
            BinomialHeapNode temp = new BinomialHeapNode(value);
            if (Nodes == null) {
                Nodes = temp;
                size = 1;
            } else {
                unionNodes(temp);
                size++;
            }
        }
    }


    private void merge(BinomialHeapNode binHeap) {
        BinomialHeapNode left = Nodes, right = binHeap;

        while ((left != null) && (right != null)) {

            if (left.degree == right.degree) {
                BinomialHeapNode tmp = right;
                right = right.sibling;
                tmp.sibling = left.sibling;
                left.sibling = tmp;
                left = tmp.sibling;
            } else {
                if (left.degree < right.degree) {
                    if ((left.sibling == null) || (left.sibling.degree > right.degree)) {
                        BinomialHeapNode tmp = right;
                        right = right.sibling;
                        tmp.sibling = left.sibling;
                        left.sibling = tmp;
                        left = tmp.sibling;
                    } else {
                        left = left.sibling;
                    }
                } else {
                    BinomialHeapNode tmp = left;
                    left = right;
                    right = right.sibling;
                    left.sibling = tmp;
                    if (tmp == Nodes) {
                        Nodes = left;
                    }
                }
            }
        }

        if (left == null) {
            left = Nodes;
            while (left.sibling != null) {
                left = left.sibling;
            }
            left.sibling = right;
        }
    }


    private void unionNodes(BinomialHeapNode binHeap) {
        merge(binHeap);

        BinomialHeapNode prevTemp = null, temp = Nodes, nextTemp = Nodes.sibling;

        while (nextTemp != null) {

            if ((temp.degree != nextTemp.degree) || ((nextTemp.sibling != null) && (nextTemp.sibling.degree == temp.degree))) {
                prevTemp = temp;
                temp = nextTemp;
            } else {

                if (temp.key <= nextTemp.key) {
                    temp.sibling = nextTemp.sibling;
                    nextTemp.parent = temp;
                    nextTemp.sibling = temp.child;
                    temp.child = nextTemp;
                    temp.degree++;
                } else {

                    if (prevTemp == null) {
                        Nodes = nextTemp;
                    } else {
                        prevTemp.sibling = nextTemp;
                    }

                    temp.parent = nextTemp;
                    temp.sibling = nextTemp.child;
                    nextTemp.child = temp;
                    nextTemp.degree++;
                    temp = nextTemp;
                }
            }
            nextTemp = temp.sibling;
        }
    }


    public int findMinimum() {
        return Nodes.findMinNode().key;
    }


    public void delete(int value) {
        if ((Nodes != null) && (Nodes.findANodeWithKey(value) != null)) {
            BinomialHeapNode temp = Nodes.findANodeWithKey(value);
            if (temp == null)
                return;
            temp.key = findMinimum() - 1;
            BinomialHeapNode tempParent = temp.parent;

            while ((tempParent != null) && (temp.key < tempParent.key)) {
                int z = temp.key;
                temp.key = tempParent.key;
                tempParent.key = z;
                temp = tempParent;
                tempParent = tempParent.parent;
            }

            extractMin();
        }
    }

    public int extractMin() {
        if (Nodes == null)
            return -1;

        BinomialHeapNode temp = Nodes, prevTemp = null;
        BinomialHeapNode minNode = Nodes.findMinNode();

        while (temp.key != minNode.key) {
            prevTemp = temp;
            temp = temp.sibling;
        }

        if (prevTemp == null) {
            Nodes = temp.sibling;
        } else {
            prevTemp.sibling = temp.sibling;
        }

        temp = temp.child;
        BinomialHeapNode fakeNode = temp;

        while (temp != null) {
            temp.parent = null;
            temp = temp.sibling;
        }

        if ((Nodes == null) && (fakeNode == null)) {
            size = 0;
        } else {
            if ((Nodes == null) && (fakeNode != null)) {
                Nodes = fakeNode.reverse(null);
                size = Nodes.getSize();
            } else {
                if ((Nodes != null) && (fakeNode == null)) {
                    size = Nodes.getSize();
                } else {
                    unionNodes(fakeNode.reverse(null));
                    size = Nodes.getSize();
                }
            }
        }

        return minNode.key;
    }


    public void displayHeap() {
        System.out.print("\nHeap : ");
        displayHeap(Nodes);
        System.out.println("\n");
    }

    private void displayHeap(BinomialHeapNode r) {
        if (r != null) {
            displayHeap(r.child);
            System.out.print(r.key + " ");
            displayHeap(r.sibling);
        }
    }

//    public void displayChildren() {
//        BinomialHeapNode temp = Nodes;
//        while(temp != null){
//            pppp(temp);
//            temp = temp.child;
//        }
//    }
//
//    public void pppp(BinomialHeapNode node){
//        System.out.println(node.key);
//        System.out.println(node.sibling.key);
//        System.out.println();
//    }


    public int[] getArray() {

        ArrayList<Integer> out = new ArrayList<>();

        getNodeArray(Nodes, out);

        return out.stream().mapToInt(i -> i).toArray();
    }

    public ArrayList<Integer> getArrayList() {

        ArrayList<Integer> out = new ArrayList<>();

        getNodeArray(Nodes, out);

        return new ArrayList<>(out.stream().toList());

    }

    public void getNodeArray(BinomialHeapNode n, ArrayList<Integer> list) {

        if (n != null) {
            getNodeArray(n.child, list);
            list.add(n.key);
            getNodeArray(n.sibling, list);
        }


    }


    public static void main(String[] args) {
        BinomialHeap binHeap = new BinomialHeap();
        int[] arr = {33, 54, 12, 775, 24, 19, 75};
        for (int i : arr) {
            binHeap.insert(i);
        }
        binHeap.displayHeap();
        //binHeap.delete(12);
        binHeap.extractMin();
        binHeap.displayHeap();
        //[24, 775, 54, 75, 19, 33, 12]
    }
}