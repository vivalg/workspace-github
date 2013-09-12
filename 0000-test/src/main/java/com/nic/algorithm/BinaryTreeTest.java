package com.nic.algorithm;

public class BinaryTreeTest {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(Node.generateRandomNode());
        }
    }
}

class Node {

    private String value;
    private Node root;
    private Node left;
    private Node right;

    public static Node generateRandomNode() {
        Node node = new Node();
        node.setValue("" + Math.round(Math.random() * 100));
        return node;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}