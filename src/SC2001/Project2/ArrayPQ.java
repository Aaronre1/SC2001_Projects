package SC2001.Project2;

// min queue arranged in desc order.
public class ArrayPQ {
    private int _capacity;
    private int _size;
    private Graph.Edge[] _array;

    public ArrayPQ(int capacity) {
        _capacity = capacity;
        _array = new Graph.Edge[capacity + 1];
        _size = 0;
    }

    public boolean isEmpty() {
        return _size == 0;
    }

    public int size() {
        return _size;
    }

    public void push(Graph.Edge k) {
        if (_size > _capacity) {
            throw new IndexOutOfBoundsException();
        }
        int i = _size - 1;
        // while k is bigger than i
        while (i >= 0 && k.Weight > _array[i].Weight) {
            _array[i + 1] = _array[i]; //right shift
            i--;
        }
        try {
            _array[i + 1] = k;
        } catch (Exception e) {
            throw e;
        }

        _size++;
    }

    public Graph.Edge pop() {
        var min = _array[--_size];
        return min;
    }

    public void remove(Graph.Edge k) {
        int index = 0;

        for (int i = 0; i < _size; i++) {
            if (_array[i].Vertex == k.Vertex) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return;
        }

        while (index <= _size) {
            _array[index] = _array[index + 1];
            index++;
        }

        _size--;
    }

    public void print() {
        for (int i = 0; i < _size; i++) {
            System.out.println(_array[i].Vertex + " : " + _array[i].Weight);
        }
    }

    private void swap(int a, int b) {
        var tmp = _array[a];
        _array[a] = _array[b];
        _array[b] = tmp;
    }

    private int smaller(int left, int right) {
        if (_array[left].Weight < _array[right].Weight) {
            return left;
        } else {
            return right;
        }
    }
}
