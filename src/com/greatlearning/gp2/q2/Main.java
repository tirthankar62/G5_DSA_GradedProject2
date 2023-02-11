package com.greatlearning.gp2.q2;

public class Main {
    Node node;

    Node prevNode = null;
    Node headNode = null;

    public void BSTToSkewed(Node root)
    {
        // Base Case
        if(root == null)
        {
            return;
        }

        BSTToSkewed(root.left);

        Node rightNode = root.right;
        Node leftNode = root.left;

        // Condition to check if the root Node
        // of the skewed tree is not defined
        if(headNode == null)
        {
            headNode = root;
            root.left = null;
            prevNode = root;
        }
        else
        {
            prevNode.right = root;
            root.left = null;
            prevNode = root;
        }

        // Similarly recurse for the left / right
        // subtree on the basis of the order required
        BSTToSkewed(rightNode);

    }

    public void traverseRightSkewed(Node root)
    {
        if(root == null)
        {
            return;
        }
        System.out.print(root.val + " ");
        traverseRightSkewed(root.right);
    }

    public static void main(String[] args) {
        Main tree = new Main();
        tree.node = new Node(50);
        tree.node.left = new Node(30);
        tree.node.right = new Node(60);
        tree.node.left.left = new Node(10);
        tree.node.right.left= new Node(55);

        int order = 0;
        tree.BSTToSkewed(tree.node);
        tree.traverseRightSkewed(tree.headNode);
    }
}
