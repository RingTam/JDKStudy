package org.monster.linkedlist.singlylinkedlistfirst;

import java.util.Iterator;

/**
 * 类名：
 * 作者：Monster
 * 时间：2016/6/14 18:00
 * 说明：
 */
public class TestSinglyLinkedListFirst {

    public static void main(String[] args) {
        new TestSinglyLinkedListFirst().test();
    }

    /**
     * 测试
     */
    public void test() {
        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();

        System.out.println("[添加]-----------------------------------");
        //[5,4,3,2,1]
        singlyLinkedList.addFirst(1);
        singlyLinkedList.addFirst(2);
        singlyLinkedList.addFirst(3);
        singlyLinkedList.addFirst(4);
        singlyLinkedList.addFirst(5);

        singlyLinkedList.forEach();

        System.out.println("[移除]-----------------------------------");

        singlyLinkedList.removeFirst();
        singlyLinkedList.removeFirst();

        Iterator<Integer> iterator = singlyLinkedList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("[插入]-----------------------------------");
        singlyLinkedList.insertAfter(3, 33);
        singlyLinkedList.insertAfter(2, 22);
        singlyLinkedList.insertAfter(1, 11);

        singlyLinkedList.forEach();

    }


    /**
     * 节点
     *
     * @param <E>
     */
    class Node<E> {

        /**
         * 元素
         */
        private E element;
        /**
         * 下一个节点
         */
        private Node<E> next;
    }

    class SinglyLinkedList<E> implements Iterable {

        /**
         * 第一个节点
         */
        private Node<E> first;

        public SinglyLinkedList() {
            first = new Node<>();
        }

        /**
         * 添加
         * 头插入法
         *
         * @param element 元素
         */
        void addFirst(E element) {
            Node<E> newFirst = new Node<>();
            newFirst.element = element;
            newFirst.next = first;
            first = newFirst;
        }

        /**
         * 插入一个元素后
         * TODO 不推荐使用，插入时需要遍历整个链表，只作插入原理演示。
         * v         v
         * [A,&B]->[B,&C]->[C,null]  >>> [A,&G]-> [G, &B] -> [B,&C]->[C,null]
         *
         * @param target  目标
         * @param element 元素
         */
        void insertAfter(E target, E element) {
            Node<E> node = first;
            while (node.element != null) {
                if (node.element == target) {
                    Node<E> newNext = new Node<>();
                    newNext.element = element;
                    newNext.next = node.next;
                    node.next = newNext;
                }
                node = node.next;
            }
        }

        /**
         * 移除头
         */
        void removeFirst() {
            Node<E> newFirst = first.next;
            first.element = null;
            first.next = null;
            first = newFirst;
        }

        /**
         * 循环
         * 函数式编程
         */
        void forEach() {
            Node<E> node = first;
            while (node.element != null) {
                System.out.println(node.element);
                node = node.next;
            }
        }

        /**
         * 清除
         */
        void clear() {
            this.first = null;
        }

        /**
         * 迭代器
         *
         * @return 迭代器
         */
        @Override
        public Iterator<E> iterator() {
            return new SinglyLinkedListIterator(first);
        }

        /**
         * 单链表迭代器
         */
        class SinglyLinkedListIterator implements Iterator<E> {

            /**
             * 节点
             */
            private Node<E> node;

            /**
             * 构造方法
             *
             * @param node 节点
             */
            public SinglyLinkedListIterator(Node<E> node) {
                this.node = node;
            }

            /**
             * 有下个
             *
             * @return 是否
             */
            @Override
            public boolean hasNext() {
                return node != null
                        && node.element != null;
            }

            /**
             * 下一个
             *
             * @return 元素
             */
            @Override
            public E next() {
                E element = node.element;
                node = node.next;
                return element;
            }
        }
    }

}