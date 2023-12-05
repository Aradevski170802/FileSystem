public class GeneralTree<T> {
    private TreeNode<T> root;

    public GeneralTree(T rootData) {
        root = new TreeNode<>(rootData);
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public void printTree() {
        printTree(root, 0);
    }

    public void deleteNode(T data) {
        TreeNode<T> nodeToDelete = findNode(root, data);
        if (nodeToDelete != null) {
            // Remove the node from its parent's children list
            TreeNode<T> parent = findParent(root, data);
            if (parent != null) {
                parent.getChildren().remove(nodeToDelete);
            } else {
                // If the node to delete is the root, set a new root
                if (!nodeToDelete.getChildren().isEmpty()) {
                    root = nodeToDelete.getChildren().get(0);
                } else {
                    root = null;
                }
            }
        }
    }
    // Find a node in the tree
    private TreeNode<T> findNode(TreeNode<T> currentNode, T data) {
        if (currentNode == null) {
            return null;
        }

        if (currentNode.getData().equals(data)) {
            return currentNode;
        }

        for (TreeNode<T> child : currentNode.getChildren()) {
            TreeNode<T> foundNode = findNode(child, data);
            if (foundNode != null) {
                return foundNode;
            }
        }

        return null;
    }

    // Find the parent of a node in the tree
    private TreeNode<T> findParent(TreeNode<T> currentNode, T data) {
        if (currentNode == null) {
            return null;
        }

        for (TreeNode<T> child : currentNode.getChildren()) {
            if (child.getData().equals(data)) {
                return currentNode;
            }

            TreeNode<T> foundParent = findParent(child, data);
            if (foundParent != null) {
                return foundParent;
            }
        }

        return null;
    }
    private void printTree(TreeNode<T> node, int depth) {
        if (node == null) {
            return;
        }

        // Print the current node
        for (int i = 0; i < depth; i++) {
            System.out.print("  "); // Indentation for better visualization
        }
        System.out.println(node.getData());

        // Recursively print the children
        for (TreeNode<T> child : node.getChildren()) {
            printTree(child, depth + 1);
        }
    }

    // Other methods for tree traversal, manipulation, etc. can be added here.

    public static void main(String[] args) {
        // Creating a sample general tree
        GeneralTree<String> tree = new GeneralTree<>("Root");

        TreeNode<String> node1 = new TreeNode<>("Node 1");
        TreeNode<String> node2 = new TreeNode<>("Node 2");
        TreeNode<String> node3 = new TreeNode<>("Node 3");

        TreeNode<String> node11 = new TreeNode<>("Node 1.1");
        TreeNode<String> node12 = new TreeNode<>("Node 1.2");

        TreeNode<String> node21 = new TreeNode<>("Node 2.1");
        TreeNode<String> node22 = new TreeNode<>("Node 2.2");

        tree.getRoot().addChild(node1);
        tree.getRoot().addChild(node2);
        tree.getRoot().addChild(node3);

        node1.addChild(node11);
        node1.addChild(node12);

        node2.addChild(node21);
        node2.addChild(node22);
        tree.deleteNode("Node 1");
        tree.printTree();
        // Perform tree traversal or other operations as needed
    }
}
