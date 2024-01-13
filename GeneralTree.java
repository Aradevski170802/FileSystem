import java.util.Iterator;
import java.util.List;

public class GeneralTree extends TreeNode<FileSystemItem<?>> {
    public GeneralTree(String rootName, long size) {
        super(new FileSystemItem<>(rootName, size), rootName);
        getData().setPath(rootName);
    }

    public void addChild(TreeNode<FileSystemItem<?>> child) {
        
        if(!getPath().equals("")){
            child.getData().setPath(getPath() + "/" + child.getData().getName());
            child.setPath(getPath() + "/" + child.getData().getName());
        }else{
            child.getData().setPath(this.getName() + "/" + child.getData().getName());
            child.setPath(this.getName() + "/" + child.getData().getName());
        }
        getChildren().add(child);
        getData().setSize(getSize()+child.getSize());   
    }
    
    public void deleteFileSystemItem(FileSystemItem<?> itemToDelete) {
        if (!isDeletable(itemToDelete)) {
            // Cannot delete non-FileSystemItem items
            System.out.println("Cannot delete the specified item.");
            return;
        }

        deleteFileSystemItem(this, itemToDelete);
    }


    public String searchDFS(TreeNode<FileSystemItem<?>> currentNode, String targetName) {
        // Base case: If the current node is null or the target is found, return the node
        if (currentNode == null || currentNode.getData().getName().equals(targetName)) {
            return currentNode.getPath();
        }

        // Recursive case: Search in each child of the current node
        for (TreeNode<FileSystemItem<?>> child : currentNode.getChildren()) {
            String result = searchDFS(child, targetName);
            if (result != null) {
                // If the target is found in any subtree, return the result
                return result;
            }
        }


    // Target not found in the current node or its subtrees
        return null;
    }

    private void deleteFileSystemItem(TreeNode<FileSystemItem<?>> currentNode, FileSystemItem<?> itemToDelete) {
        List<TreeNode<FileSystemItem<?>>> children = currentNode.getChildren();
        Iterator<TreeNode<FileSystemItem<?>>> iterator = children.iterator();
    
        while (iterator.hasNext()) {
            TreeNode<FileSystemItem<?>> child = iterator.next();
            if (child.getData().equals(itemToDelete)) {
                iterator.remove();
                child.getChildren().removeAll(children);
                child.setData(null);
                child.setPath(null);
                child.setName(null);
                if (child instanceof Folder) {
                    ((Folder) child).removeFileSystemItem(itemToDelete);
                }
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
}