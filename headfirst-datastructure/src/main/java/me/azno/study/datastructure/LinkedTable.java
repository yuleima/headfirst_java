package me.azno.study.datastructure;

public class LinkedTable<T> implements ILinkedTable {
    private LinkedNode head;
    private LinkedNode now;
    private LinkedNode end;

    public LinkedTable(T headItem) {
        this.head = new LinkedNode(headItem);
        now = head;
        end = head;
    }

    public LinkedTable addItem(T item) {
        LinkedNode node = new LinkedNode(item);
        end.setNext(node);
        end = node;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("LinkedTable{");
        while (hasNext()) {
            sb.append(now);
            sb.append(",");
            next();
        }
        sb.append('}');
        now = head;
        return sb.toString();
    }

    @Override
    public boolean hasNext() {
        return now.hasNext();
    }

    @Override
    public T next() {
        if(hasNext()) {
            now = now.getNext();
            return (T) now.getItem();
        }
        return null;
    }

    public LinkedNode getHead() {
        return head;
    }

    class LinkedNode<T> {
        private T item;
        private LinkedNode next;

        public LinkedNode(T item) {
            this.item = item;
        }

        public T getItem() {
            return item;
        }

        public void setItem(T item) {
            this.item = item;
        }

        public LinkedNode getNext() {
            return next;
        }

        public void setNext(LinkedNode next) {
            this.next = next;
        }

        public boolean hasNext() {
            return next == null ? false : true;
        }

        @Override
        public String toString() {
//            return "LinkedNode{" +
//                    "item=" + item +
//                    '}';
            return String.valueOf(item);
        }
    }
}
