
import java.util.List;
public class GeneralTree<T extends FileSystemItem> {
    private TreeNode<T> root;

    public GeneralTree(T rootData) {
        root = new TreeNode<>(rootData);
        root.getData().setPath(root.getData().getName());
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public void printFolderContents(T targetFolderData) {
        printFolderContents(root, targetFolderData, 0);
    }
    
    private void printFolderContents(TreeNode<T> node, T targetFolderData, int depth) {
        if (node == null) {
            return;
        }
    
        if (node.getData().equals(targetFolderData)) {
            printTree(node, depth);
            return;  // Stop recursion to avoid printing the entire tree
        }
    
        for (TreeNode<T> child : node.getChildren()) {
            printFolderContents(child, targetFolderData, depth + 1);
        }
    }

    public void printTree() {
        printTree(root, 0);
    }
   @SuppressWarnings("unchecked")
    private void printTree(TreeNode<T> node, int depth) {
        if (node == null) {
            return;
        }
    
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println(node.getData());
        
        if (node.getData() instanceof Folder) {
            List<FileSystemItem> contents = ((Folder) node.getData()).getContents();
            for (FileSystemItem item : contents) {
                printTree(new TreeNode<>((T)item), depth + 1);
            }
        }
    
        for (TreeNode<T> child : node.getChildren()) {
            printTree(child, depth + 1);
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
                //If the node to delete is the root, set a new root
                if (!currentNode.getChildren().isEmpty()) {
                    root = currentNode.getChildren().get(0);
                } else{
                    root = null;
                }
            }
    
            // Recursively delete all children (subtree)
            for (int i = 0; i < currentNode.getChildren().size(); i++) {
                TreeNode<T> child = currentNode.getChildren().get(i);
                deleteNode(child, currentNode, child.getData());
            }
            return;
        }
    
        List<TreeNode<T>> children = currentNode.getChildren();
        for (int i = 0; i < children.size(); i++) {
            TreeNode<T> child = children.get(i);
            deleteNode(child, currentNode, data);
        }
    
        // If the current node is a folder, check its contents
        if (currentNode.getData() instanceof Folder) {
            Folder folder = (Folder) currentNode.getData();
            List<FileSystemItem> contents = folder.getContents();
    
            // Search for and delete the specified data from the folder's contents
            contents.removeIf(item -> item.equals(data));
        }
        children = null;

    }
    
    public void addChild(TreeNode<T> child) {
        root.getChildren().add(child);
        child.getData().setPath(root.getData().getName()+"/"+child.getData().getName());
    }

    public static void main(String[] args) {
        // Creating a sample file system tree
        GeneralTree<FileSystemItem> fileSystemTree = new GeneralTree<>(new Folder("Root"));

        Folder documentsFolder = new Folder("Documents");
        Folder picturesFolder = new Folder("Pictures");
        Folder subfolder = new Folder("Subfolder");
        Folder subsubfolder = new Folder("SubSubFolder");
        Folder subsubsubfolder = new Folder("SubSubSubFolder");

        File document1 = new File("Document1.txt", "This is the content of Document1.");
        File document2 = new File("Document2.txt", "Content of Document2.");
        File document3 = new File("Document3.txt", "This is the content of Document3.");
        File picture1 = new File("Picture1.jpg", "Content of Picture1.");

        fileSystemTree.addChild(new TreeNode<>(documentsFolder));
        fileSystemTree.addChild(new TreeNode<>(picturesFolder));

       
        documentsFolder.addFileSystemItem(subfolder);
        subfolder.addFileSystemItem(subsubfolder);
        subsubfolder.addFileSystemItem(subsubsubfolder);
        subsubsubfolder.addFileSystemItem(picture1);
        fileSystemTree.deleteNode(subfolder);
        //documentsFolder.addFolder(subfolder);
        
            

        //fileSystemTree.deleteNode(fileSystemTree.getRoot().getData());
        //fileSystemTree.deleteNode("Document1.txt");

        // Printing the file system tree
        //fileSystemTree.printTree();

        // Deleting a node and its subtree (e.g., a folder or a file)
        //fileSystemTree.deleteNode(picturesFolder);
        
        //fileSystemTree.deleteNode(documentsFolder);

        // Printing the modified file system tree
        fileSystemTree.printTree();
        
        System.out.println(picture1.getPath());
        
        //fileSystemTree.search(where,what)
    }
}