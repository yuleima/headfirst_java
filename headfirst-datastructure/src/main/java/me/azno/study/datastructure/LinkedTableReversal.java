package me.azno.study.datastructure;

public class LinkedTableReversal {
    public static void main(String[] args) {
        LinkedTable<Integer> linkedTable = new LinkedTable<>(1);
        linkedTable.addItem(2).addItem(3).addItem(4).addItem(5);
        System.out.println(linkedTable);
        LinkedTable.LinkedNode head = linkedTable.getHead();
        LinkedTable.LinkedNode node = head;
        LinkedTable.LinkedNode tmp,next;
        while(linkedTable.hasNext()){
//            tmp =
//            next = node.getNext();
//            node.setNext();
//            node = node.getNext();
        }
    }
}
