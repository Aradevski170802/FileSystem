import java.util.ArrayList;
import java.util.List;
public class GeneralTree<T extends FileSystemItem> {
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

    private void printTree(TreeNode<T> node, int depth) {
        if (node == null) {
            return;
        }

        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println(node.getData());

        for (TreeNode<T> child : node.getChildren()) {
            printTree(child, depth + 1);
        }

        if (node.getData() instanceof Folder) {
            List<FileSystemItem> contents = ((Folder) node.getData()).getContents();
            for (FileSystemItem item : contents) {
                for (int i = 0; i < depth + 1; i++) {
                    System.out.print("  ");
                }
                System.out.println(item);
            }
        }
    }

    public void deleteNode(T data) {
        deleteNode(root, null, data);
    }
    
    private void deleteNode(TreeNode<T> currentNode, TreeNode<T> parentNode, T data) {
        if (currentNode == null) {
            return;
        }
    
        if (currentNode.getData().equals(data)) {
            if (parentNode != null) {
                parentNode.getChildren().remove(currentNode);
            } else {
                // If the node to delete is the root, set a new root
                if (!currentNode.getChildren().isEmpty()) {
                    root = currentNode.getChildren().get(0);
                } else {
                    root = null;
                }
            }
    
            // Recursively delete all children (subtree)
            for (TreeNode<T> child : currentNode.getChildren()) {
                deleteNode(child, currentNode, child.getData());
            }
            return;
        }
    
        // Recursively search for the node to delete in children
        for (TreeNode<T> child : currentNode.getChildren()) {
            deleteNode(child, currentNode, data);
        }
    
        // If the current node is a folder, check its contents
        if (currentNode.getData() instanceof Folder) {
            Folder folder = (Folder) currentNode.getData();
            List<FileSystemItem> contents = folder.getContents();
    
            // Search for and delete the specified data from the folder's contents
            contents.removeIf(item -> item.equals(data));
        }
    }

    public static void main(String[] args) {
        // Creating a sample file system tree
        GeneralTree<FileSystemItem> fileSystemTree = new GeneralTree<>(new Folder("Root"));

        Folder documentsFolder = new Folder("Documents");
        Folder picturesFolder = new Folder("Pictures");

        File document1 = new File("Document1.txt", "This is the content of Document1.");
        File document2 = new File("Document2.txt", "Content of Document2.");
        File picture1 = new File("Picture1.jpg", "Content of Picture1.");

        fileSystemTree.getRoot().addChild(new TreeNode<>(documentsFolder));
        fileSystemTree.getRoot().addChild(new TreeNode<>(picturesFolder));

        documentsFolder.addFileSystemItem(document1);
        documentsFolder.addFileSystemItem(document2);

        picturesFolder.addFileSystemItem(picture1);

        // Printing the file system tree
        fileSystemTree.printTree();

        // Deleting a node and its subtree (e.g., a folder or a file)
        fileSystemTree.deleteNode(picture1);
        fileSystemTree.deleteNode(documentsFolder);

        // Printing the modified file system tree
        fileSystemTree.printTree();
    }
}