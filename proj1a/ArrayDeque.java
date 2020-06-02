public class ArrayDeque <T> {

    private T[] array;
    private int front;
    private int rear;
    private int size;

    public ArrayDeque(){
        // Java does not allow to create new generic array directly. So need cast.
        array = (T[]) new Object[8];
        this.front = 0;   //point to the first element of array, treat it as circular
        this.rear = 1;
        size = 0;
    }

    // create a deep copy of other
    public ArrayDeque(ArrayDeque other){
        T[] a = (T[]) new Object[other.size];
        front = other.front;
        rear = other.rear;
        size = other.size;
        System.arraycopy(other, 0, a, 0, size);
    }

    public void resize(int capacity){
        T[] a = (T[]) new Object[capacity];
        if (capacity < array.length){
            System.arraycopy(array, front+1, a, 0, array.length-front-1);
            System.arraycopy(array, 0, a, array.length-front-1, rear);
        } else {
            System.arraycopy(array, front+1, a, 0, size);
        }

        array = a;
    }

    public void addFirst(T item){
        if(size == array.length){
            resize(2 * array.length);
        }else {
            array[front] = item;
            front = (front - 1 + array.length) % array.length;
            size += 1;
        }
    }

    public void addLast(T item){
        if(size == array.length){
            resize(2 * array.length);
        }
        array[rear] = item;
        rear = (rear + 1) % array.length;
        size += 1;
    }

    public T removeFirst(){
        front = (front + 1) % array.length;
        T remove = array[front];
        size -= 1;

        if (array.length <= 16 && size / array.length <= 0.25) {
            resize(array.length / 2);
        }
        front = (array.length-1) % array.length;
        rear = size;
        return remove;
    }

    public T removeLast(){
        rear = (rear - 1 ) % array.length;
        T remove = array[rear];
        size -= 1;

        if (array.length <= 16 && size / array.length <= 0.25) {
            resize(array.length / 2);
        }
        front = (array.length-1) % array.length;
        rear = size;
        return remove;
    }


    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int curr = (front + 1) % array.length;

        while(curr != rear) {
            System.out.print(array[curr]);
            curr = (curr + 1) % array.length;
        }
        System.out.println();
    }

    // If no such item exists, returns null. Must not alter the deque! index starts with 0
    public T get(int index){
        if(index < array.length){
            return array[(front + index+1) % array.length];
        }else{
            return null;
        }

    }

}
