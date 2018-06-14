package org.monster.linkedlist.singlylinkedlistlast;

import java.util.Iterator;

/**
 * 类名：
 * 作者：Monster
 * 时间：2016/6/14 18:00
 * 说明：
 */
public class TestSinglyLinkedListLast {

    public static void main(String[] args) {
        new TestSinglyLinkedListLast().test();
    }

    /**
     * 测试
     */
    public void test() {
        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();
        //[1,2,3,4,5]
        singlyLinkedList.addLast(1);
        singlyLinkedList.addLast(2);
        singlyLinkedList.addLast(3);
        singlyLinkedList.addLast(4);
        singlyLinkedList.addLast(5);

        singlyLinkedList.removeLast();
        singlyLinkedList.removeLast();

        singlyLinkedList.forEach();

        System.out.println("---------------------------------------");

        Iterator<Integer> iterator = singlyLinkedList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

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
        /**
         * 最后一个节点
         */
        private Node<E> last;

        public SinglyLinkedList() {
            last = new Node<>();
        }

        /**
         * 添加
         * 头插入法
         *
         * @param element 元素
         */
        void addLast(E element) {

            Node<E> newLast = new Node<>();
            newLast.element = element;
            last.next = newLast;
            last = newLast;
            if (first == null) {
                first = newLast;
            }
        }

        /**
         * 移除
         * TODO 未能实现！如果不是双向链表，如何获取上一个元素？
         */
        void removeLast() {
        }

        /**
         * 循环
         * 函数式编程
         */
        void forEach() {
            Node<E> node = first;
            while (node != null) {
                System.out.println(node.element);
                node = node.next;
            }
        }

        /**
         * 清除
         */
        void clear() {
            this.last = null;
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