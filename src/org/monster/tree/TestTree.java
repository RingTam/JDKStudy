package org.monster.tree;

/**
 * @author Monster
 */
public class TestTree<E> {

    /**
     * 符号
     */
    private String[][] symbol = {{"{", "}"}, {"(", ")"}, {"[", "]"}, {"<", ">"}};

    /**
     * 主方法
     *
     * @param args 参数 数组
     */
    public static void main(String[] args) {
        TestTree<String> testTreeString = new TestTree<>();

        testTreeString.testLeft(testTreeString.getFullBinaryTree());
        System.out.println();
        testTreeString.testRight(testTreeString.getFullBinaryTree());

        System.out.println();

        testTreeString.testLeft(testTreeString.getCompleteBinaryTree());
        System.out.println();
        testTreeString.testRight(testTreeString.getCompleteBinaryTree());

        System.out.println();

        TestTree<Integer> testTreeInteger = new TestTree<>();

        testTreeInteger.testLeft(testTreeInteger.getHuffmanBinaryTree());
        System.out.println();
        testTreeInteger.testRight(testTreeInteger.getHuffmanBinaryTree());
    }

    public void testLeft(Node<E> tree) {
        System.out.println("（左方向）先序遍历----------------------------------------");
        testLeftFirst(tree);
        System.out.println();
        System.out.println("（左方向）中序遍历----------------------------------------");
        testLeftMiddle(tree);
        System.out.println();
        System.out.println("（左方向）后序遍历----------------------------------------");
        testLeftLast(tree);
        System.out.println();
    }

    public void testRight(Node<E> tree) {
        System.out.println("（右方向）先序遍历----------------------------------------");
        testRightFirst(tree);
        System.out.println();
        System.out.println("（右方向）中序遍历----------------------------------------");
        testRightMiddle(tree);
        System.out.println();
        System.out.println("（右方向）后序遍历----------------------------------------");
        testRightLast(tree);
        System.out.println();
    }

    /**
     * 获取 满二叉树
     * 如果每一个层的结点数都达到最大值，则这个二叉树就是满二叉树。
     * 也就是说，如果一个二叉树的层数为K，且结点总数是(2^k) -1 ，则它就是满二叉树
     * 公式：(2*dept) - 1
     *
     * @return 满二叉树
     */
    public Node<String> getFullBinaryTree() {

        /**
         *
         * 符号表达法：{A(B[D][E])(C[F][G])}
         *                A
         *              /  \
         *            B     C
         *           / \   / \
         *         D    E F   G  <--都有两个节点
         *
         */
        Node<String> nodeG = new Node<>(2, "G", null, null);
        Node<String> nodeF = new Node<>(2, "F", null, null);

        Node<String> nodeE = new Node<>(2, "E", null, null);
        Node<String> nodeD = new Node<>(2, "D", null, null);

        Node<String> nodeC = new Node<>(1, "C", nodeF, nodeG);
        Node<String> nodeB = new Node<>(1, "B", nodeD, nodeE);

        Node<String> nodeA = new Node<>(0, "A", nodeB, nodeC);

        return nodeA;
    }

    /**
     * 获取 完全二叉树
     * 若设二叉树的深度为h，除第 h 层外，其它各层 (1～h-1) 的结点数都达到最大个数，
     * 第 h 层所有的结点都连续集中在最左边，这就是完全二叉树。
     *
     * @return 二叉树
     */
    public Node<String> getCompleteBinaryTree() {

        /**
         * 符号表达法：{A(B[D<H><I>][E<J>])(C[F][G])}
         *                A
         *              /  \
         *            B     C
         *           / \   / \
         *         D    E F   G
         *       / \   /
         *      H   I J  <-- 所有的结点都连续集中在最左边
         */
        Node<String> nodeJ = new Node<>(3, "J", null, null);

        Node<String> nodeI = new Node<>(3, "I", null, null);
        Node<String> nodeH = new Node<>(3, "H", null, null);

        Node<String> nodeG = new Node<>(2, "G", null, null);
        Node<String> nodeF = new Node<>(2, "F", null, null);

        Node<String> nodeE = new Node<>(2, "E", nodeJ, null);
        Node<String> nodeD = new Node<>(2, "D", nodeH, nodeI);

        Node<String> nodeC = new Node<>(1, "C", nodeF, nodeG);
        Node<String> nodeB = new Node<>(1, "B", nodeD, nodeE);

        Node<String> nodeA = new Node<>(0, "A", nodeB, nodeC);

        return nodeA;
    }


    /**
     * 获取 哈夫曼树
     * 给定n个权值作为n个叶子结点，构造一棵二叉树，若带权路径长度达到最小，称这样的二叉树为最优二叉树，
     * 也称为哈夫曼树(Huffman Tree)。哈夫曼树是带权路径长度最短的树，权值较大的结点离根较近。
     *
     * @return 二叉树
     */
    public Node<Integer> getHuffmanBinaryTree() {

        /**
         * 构造：假设有n个权值，则构造出的哈夫曼树有n个叶子结点。 n个权值分别设为 w1、w2、…、wn，则哈夫曼树的构造规则为：
         * (1) 将w1、w2、…，wn看成是有n 棵树的森林(每棵树仅有一个结点)；
         * (2) 在森林中选出两个根结点的权值最小的树合并，作为一棵新树的左、右子树，且新树的根结点权值为其左、右子树根结点权值之和；
         * (3)从森林中删除选取的两棵树，并将新树加入森林；
         * (4)重复(2)、(3)步，直到森林中只剩一棵树为止，该树即为所求得的哈夫曼树。
         *
         * 例如（满哈夫曼树）：
         * 随机数：{12,2,5,8,5,20,34,23,56}   深度公式：dept=(floor(count/2)))  = 4
         * 排序后：{2,3,5,8,13,20,23,43,56}  如果数不适应，可适当选取。构建时，计算出左子和右子的和(left+right)作新值，插入根节点。
         * 构建：从下到上，左权值小，右权值大，根=左值+右值  构建左后序：left -> right -> root  或右先序：root -> right -> left
         *
         *
         *
         *         5
         *        / \
         *       2   3
         *
         *            13
         *           / \
         *         5    8
         *        / \
         *       2   3
         *
         *
         *            13    43
         *           / \   /  \
         *         5    8 20  23
         *        / \
         *       2   3
         *
         *
         *               56
         *             /    \
         *            13    43
         *           / \   /  \
         *         5    8 20  23
         *        / \
         *       2   3    <--哈夫曼树是带权路径长度最短的树，权值较大的结点离根较近
         *
         *
         */


        Node<Integer> node2 = new Node<>(3, 2, null, null);
        Node<Integer> node3 = new Node<>(3, 3, null, null);

        Node<Integer> node5 = new Node<>(2, 5, node2, node3);
        Node<Integer> node8 = new Node<>(2, 8, null, null);

        Node<Integer> node20 = new Node<>(2, 20, null, null);
        Node<Integer> node23 = new Node<>(2, 23, null, null);

        Node<Integer> node13 = new Node<>(1, 13, node5, node8);
        Node<Integer> node43 = new Node<>(1, 43, node20, node23);

        Node<Integer> node56 = new Node<>(0, 56, node13, node43);

        return node56;
    }

    /**
     * （左方向）先序遍历
     * root -> left - > right
     */
    public void testLeftFirst(Node<E> node) {
        System.out.print(symbol[node.getDept()][0]);
        System.out.print(node.getObject());
        if (node.getLeft() != null) {
            testLeftFirst(node.getLeft());
        }
        if (node.getRight() != null) {
            testLeftFirst(node.getRight());
        }
        System.out.print(symbol[node.getDept()][1]);
    }

    /**
     * （左方向）中序遍历
     * left -> root -> right
     */
    public void testLeftMiddle(Node<E> node) {
        System.out.print(symbol[node.getDept()][0]);
        if (node.getLeft() != null) {
            testLeftMiddle(node.getLeft());
        }
        System.out.print(node.getObject());
        if (node.getRight() != null) {
            testLeftMiddle(node.getRight());
        }
        System.out.print(symbol[node.getDept()][1]);
    }

    /**
     * （左方向）后序遍历
     * left -> right -> root
     */
    public void testLeftLast(Node<E> node) {
        System.out.print(symbol[node.getDept()][0]);
        if (node.getLeft() != null) {
            testLeftLast(node.getLeft());
        }
        if (node.getRight() != null) {
            testLeftLast(node.getRight());
        }
        System.out.print(node.getObject());
        System.out.print(symbol[node.getDept()][1]);
    }

    /**
     * （右方向）先序遍历
     * root -> right - > left
     */
    public void testRightFirst(Node<E> node) {
        System.out.print(symbol[node.getDept()][0]);
        System.out.print(node.getObject());
        if (node.getRight() != null) {
            testRightFirst(node.getRight());
        }
        if (node.getLeft() != null) {
            testRightFirst(node.getLeft());
        }
        System.out.print(symbol[node.getDept()][1]);
    }

    /**
     * （右方向）中序遍历
     * right -> root -> left
     */
    public void testRightMiddle(Node<E> node) {
        System.out.print(symbol[node.getDept()][0]);
        if (node.getRight() != null) {
            testRightMiddle(node.getRight());
        }
        System.out.print(node.getObject());
        if (node.getLeft() != null) {
            testRightMiddle(node.getLeft());
        }
        System.out.print(symbol[node.getDept()][1]);
    }

    /**
     * （右方向）后序遍历
     * right -> left -> root
     */
    public void testRightLast(Node<E> node) {
        System.out.print(symbol[node.getDept()][0]);
        if (node.getRight() != null) {
            testRightLast(node.getRight());
        }
        if (node.getLeft() != null) {
            testRightLast(node.getLeft());
        }
        System.out.print(node.getObject());
        System.out.print(symbol[node.getDept()][1]);
    }

    /**
     * 节点
     *
     * @param <E> 包装类
     */
    class Node<E> {
        private Integer dept;
        private E object;
        private Node<E> left;
        private Node<E> right;

        public Node(Integer dept, E object, Node<E> left, Node<E> right) {
            this.dept = dept;
            this.object = object;
            this.left = left;
            this.right = right;
        }

        public Integer getDept() {
            return dept;
        }

        public void setDept(Integer dept) {
            this.dept = dept;
        }

        public E getObject() {
            return object;
        }

        public void setObject(E object) {
            this.object = object;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }
    }
}
