import java.util.Iterator;
import java.util.List;

public class GeneralTree extends TreeNode<FileSystemItem<?>> {
    public GeneralTree(String rootName, long size) {
        super(new FileSystemItem<>(rootName, size), rootName);
        getData().setPath(rootName);
        setPath(rootName);
    }
    
    public String searchDFS(String targetName){
        StringBuilder str = new StringBuilder();
        return searchDFS(this, targetName, str);
    } 

    private String searchDFS(TreeNode<FileSystemItem<?>> currentNode, String targetName, StringBuilder allFoundItems) {
        // Base case: If the current node is null or the target is found, return the node
        if (currentNode == null || currentNode.getData().getName().equals(targetName)) {
            allFoundItems.append(currentNode.getPath()+"\n");
        }

        // Recursive case: Search in each child of the current node
        for (TreeNode<FileSystemItem<?>> child : currentNode.getChildren()) {
            searchDFS(child, targetName, allFoundItems);
        }

        // Target not found in the current node or its subtrees
        if(allFoundItems.equals("")){
            return "Item not found!";
        }

        return allFoundItems.toString();
    }
    
    public void deleteFileSystemItem(TreeNode<FileSystemItem<?>> itemToDelete) {
        if(searchDFS(itemToDelete.getName())==""){
            System.out.println( itemToDelete.getName() +" does not exist within the drive.");
        }
        deleteFileSystemItem(this, itemToDelete);
    }


    private void deleteFileSystemItem(TreeNode<FileSystemItem<?>> currentNode, TreeNode<FileSystemItem<?>> itemToDelete) {
        List<TreeNode<FileSystemItem<?>>> children = currentNode.getChildren();
        Iterator<TreeNode<FileSystemItem<?>>> iterator = children.iterator();
    
        while (iterator.hasNext()) {
            TreeNode<FileSystemItem<?>> child = iterator.next();
            if (child.equals(itemToDelete)) {
                iterator.remove();
                child.getChildren().removeAll(children);
                child.setData(null);
                child.setPath(null);
                child.setName(null);
                return;
            }
            // Recursively search for the item in the children, considering FileSystemItem
            if (child.getData() instanceof FileSystemItem) {
                deleteFileSystemItem(child, itemToDelete);
            }
        }
    }
}