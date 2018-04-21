package me.azno.study.collection.map;

/**
 * Created by yulei.ma on 2017/7/8.
 */
public class MyHashMap<K, V> implements IMap<K, V> {

    private int defaultLength = 1 << 4;
    // 扩容因子
    private double defaultLoadFactor = 0.75d;
    private IEntry<K, V>[] table = null;

    public MyHashMap() {

    }

    public MyHashMap(int length, double factor) {
        defaultLength = length;
        defaultLoadFactor = factor;
        table = new MyEntry[defaultLength];
    }

    public void put(K key, V value) {
        if (size() > defaultLength * defaultLoadFactor)
            upCapacity();
        int index = getIndex(key);
        if (null == table[index]) {
            table[index] = new MyEntry<>(key, value, null);
        } else {
            table[index] = new MyEntry<>(key, value, table[index]);
        }
        System.out.println();

    }

    private void upCapacity() {
        defaultLength <<= 1;
        IEntry<K, V>[] newTable = new MyEntry[defaultLength];
        // 将当前数组中的对象再次散列到newTable


    }

    private int getIndex(K key) {
        //todo 扩容后如何处理？
        return key.hashCode() % defaultLength;
    }

    public V get(K key) {
        if (size() > defaultLength * defaultLoadFactor)
            upCapacity();
        int index = getIndex(key);
        if (null == table[index]) {
            return null;
        }
        return table[index].getValue();
    }

    public int size() {
        return table.length;
    }

    class MyEntry<K, V> implements IEntry {
        K key;
        V value;
        IEntry<K, V> next;

        MyEntry(K key, V value, IEntry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public Object getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }


    }
}
