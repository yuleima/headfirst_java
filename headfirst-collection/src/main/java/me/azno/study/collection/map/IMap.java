package me.azno.study.collection.map;

/**
 * Created by yulei.ma on 2017/7/8.
 */
public interface IMap<K,V> {
    public void put(K key, V value);
    public V get(K key);
    public int size();

    interface IEntry<K,V>{
        public K getKey();
        public V getValue();
    }
}
