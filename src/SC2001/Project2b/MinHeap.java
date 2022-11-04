package SC2001.Project2b;

public class MinHeap {
    int capacity;
    int size;
    Graph.Edge[] heap;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        size = 0;
        heap = new Graph.Edge[capacity + 1];
        heap[0] = new Graph.Edge(-1, Integer.MIN_VALUE);
    }

    private int parent(int i) {
        return i / 2;
    }

    private int left(int i) {
        return 2 * i;
    }

    private int right(int i) {
        return 2 * i + 1;
    }

    private boolean isLeaf(int i) {
        return (2 * i > size);
    }

    private boolean isNode(int i) {
        return (i >= 1 && i <= size);
    }

    private int smaller(int left, int right) {
        if (heap[left].Weight < heap[right].Weight) {
            return left;
        } else {
            return right;
        }
    }

    private void swap(int a, int b) {
        var tmp = heap[a];
        heap[a] = heap[b];
        heap[b] = tmp;
    }


    private void heapify(int i) {
        if (!isLeaf(i)) {
            int pos;
            if (isNode(right(i))) {
                pos = smaller(left(i), right(i));
            } else {
                pos = left(i);
            }

            if (heap[i].Weight > heap[left(i)].Weight || heap[i].Weight > heap[right(i)].Weight) {
                swap(i, pos);
                heapify(pos);
            }
        }
    }

    public void push(Graph.Edge k) {
        if (size >= capacity) {
            return;
        }
        heap[++size] = k;
        int current = size;

        while (heap[current].Weight < heap[parent(current)].Weight) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public Graph.Edge pop() {
        var min = heap[1];
        heap[1] = heap[size--];
        heapify(1);
        return min;
    }

    public void remove(Graph.Edge k) {
        for (int i = 1; i <= size; i++) {
            if (heap[i].Vertex == k.Vertex) {
                heap[i] = heap[size--];
                heapify(i);
            }
        }
    }

    public void print() {
        for (int i = 1; i <= size; i++) {
            System.out.println(heap[i].Vertex + " : " + heap[i].Weight);
        }
    }


}
