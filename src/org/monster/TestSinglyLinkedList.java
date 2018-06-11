package org.monster;

import java.util.Iterator;

/**
 * 类       名:
 * 说       明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright © 2016
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 * date   2018/6/11
 * author   tanxiang(monster)
 */
public class TestSinglyLinkedList {

    public static void main(String[] args) {
        new TestSinglyLinkedList().test();
    }

    public void test() {
        Node<Integer> node = new Node<>();
        node.add(1);
        node.add(2);
        node.add(3);
        node.add(4);
        node.add(5);

        for (Integer integer : node) {
            System.out.println(integer);
        }
    }


    class SinglyLinkedList<E> {

        private Node<E> node;
        private Node<E> next;



    }

    class Node<E> implements Iterable<E> {
        private E element;
        private Node<E> next;

        public Node() {
            //NOOP
        }

        public void add(E e) {
            this.element = element;
            this.next = null;
        }

        public void remove(E e) {
        }

        @Override
        public Iterator<E> iterator() {
            return null;
        }
    }

}