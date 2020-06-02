public class LinkedListDeque<T> {

    private class TNode{
        private T item;
        private TNode next;
        private TNode prev;

        private TNode(T item, TNode p, TNode n){
            this.item = item;
            this.next = n;
            this.prev = p;
        }
    }

    private TNode sentinel;
    private int size;

    public LinkedListDeque(){    // sentinel.next point to head, while sentinel.prev point to the tail
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    // create a deep copy of other
    public LinkedListDeque(LinkedListDeque other){
        TNode copy_sentinel = new TNode(null, null, null);
        copy_sentinel.prev = copy_sentinel;
        copy_sentinel.next = copy_sentinel;

        size = 0;

        for(int i = 0; i < size; i++){
            addLast((T) other.get(i));
        }
    }

    public void addFirst(T item){
        sentinel.next = new TNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T item){
        sentinel.prev = new TNode(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    public T removeFirst(){
        T remove = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        if (!isEmpty()){
            size -= 1;
        }
        return remove;
    }

    // Remove and return the item at the back of the deque
    public T removeLast(){
        T remove = (T) sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        if (!isEmpty()){
            size -= 1;
        }
        return remove;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        TNode curr = sentinel.next;
        for(int i = 0; i < size; i++){
            System.out.println(curr.item);
            curr = curr.next;
        }
        System.out.println();
    }

    // If no such item exists, returns null. Must not alter the deque! index starts with 0
    public T get(int index){
        if(index > size - 1){
            return null;
        }else{
            TNode curr = sentinel.next;
            for(int i = 0; i < index; i++){
                curr = curr.next;
            }
            return (T) curr.item;
        }
    }

    public T getRecursive_helper(int index, TNode curr){
        if(index == 0){
            return (T) curr.item;
        }
        return (T) getRecursive_helper(index-1, curr.next);
    }

    public T getRecursive(int index){
        TNode curr = sentinel.next;
        return getRecursive_helper(index, curr);
    }

}
