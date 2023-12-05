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

    public static void main(String[] args) {
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
    }
}
