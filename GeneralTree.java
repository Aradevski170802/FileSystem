import java.util.Iterator;
import java.util.List;

public class GeneralTree extends TreeNode<FileSystemItem<?>> {
    public GeneralTree(String rootName) {
        super(new FileSystemItem<>(rootName), rootName, rootName);
    }

    public void addChild(TreeNode<FileSystemItem<?>> child) {
        
        if(!getPath().equals(null)){
            child.getData().setPath(getPath() + "/" + child.getData().getName());
            child.setPath(getPath() + "/" + child.getData().getName());
        }else{
            child.getData().setPath(this.getName() + "/" + child.getData().getName());
            child.setPath(this.getName() + "/" + child.getData().getName());
        }
        getChildren().add(child);   
    }
    
    public void deleteFileSystemItem(FileSystemItem<?> itemToDelete) {
        if (!isDeletable(itemToDelete)) {
            // Cannot delete non-FileSystemItem items
            System.out.println("Cannot delete the specified item.");
            return;
        }

        deleteFileSystemItem(this, itemToDelete);
    }

    private void deleteFileSystemItem(TreeNode<FileSystemItem<?>> currentNode, FileSystemItem<?> itemToDelete) {
        List<TreeNode<FileSystemItem<?>>> children = currentNode.getChildren();
        Iterator<TreeNode<FileSystemItem<?>>> iterator = children.iterator();

        while (iterator.hasNext()) {
            TreeNode<FileSystemItem<?>> child = iterator.next();
            if (child.getData().equals(itemToDelete)) {
                iterator.remove();
                child.getChildren().removeAll(children);
                return;
            }
            // Recursively search for the item in the children, considering FileSystemItem
            if (child.getData() instanceof FileSystemItem) {
                deleteFileSystemItem(child, itemToDelete);
            }
        }
    }

    private boolean isDeletable(FileSystemItem<?> item) {
        return item instanceof FileSystemItem;
    }

    public void showFolderContents(TreeNode<FileSystemItem<?>> node) {
        printTree(node,0);
    }

    @Override
    public void printTree() {
        printTree(this, 0);
    }

    private void printTree(TreeNode<FileSystemItem<?>> node, int depth) {
        if (node == null) {
            return;
        }

        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indentation.append("  ");
        }

        System.out.println(indentation + node.getData().getName() + " (Path: " + node.getPath() + ")");

        for (TreeNode<FileSystemItem<?>> child : node.getChildren()) {
            printTree(child, depth + 1);
        }
    }
}