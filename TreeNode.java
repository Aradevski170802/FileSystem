import java.util.ArrayList;
import java.util.List;

public class TreeNode<T extends FileSystemItem<?>> extends FileSystemItem<T> {
    private T data;
    private List<TreeNode<T>> children;

    public TreeNode(T data, String name) {
        super(name, data.getSize());
        this.data = data;
        this.children = new ArrayList<>();
        setPath("");
    }

    public T getData() {
        return data;
    }

    public void setData(T data){this.data = data;}

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void addFileSystemItem(TreeNode<T> item) {
        // System.out.println(item.getSize()+"  <--->  "+item.getData().getSize() + "  "+item.getName());
        item.getData().setPath(getPath() + "/" + item.getData().getName());
        item.setPath(getPath() + "/" + item.getData().getName());
        children.add(item);
        this.setNewSize();     
    }

    protected void setNewSize(){
        // A folder does not have a size of its own but contains files and folders which do.
        // Therefore the size is propagated to all children.
        long currentSize = 0;
        for (int i = 0; i <getChildren().size(); i++) {
            // System.out.println(currentSize +" This is the new size for " + getName());
            currentSize+=getChildren().get(i).getData().getSize();
        }
        this.getData().setSize(currentSize);
        this.setSize(currentSize);
        
    }

    public void printTree() {
        printTree(this, 0);
    }

    private void printTree(TreeNode<T> node, int depth) {
        if (node == null) {
            return;
        }

        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indentation.append("  ");
        }
        if(depth==0)
            System.out.print(indentation + node.getData().toString() + " (Path: " + node.getData().getPath() + ")\n");
        else System.out.print(indentation + node.getData().toString()+"\n");
        for (TreeNode<T> child : node.getChildren()) {
            printTree(child, depth + 1);
        }
    }
}
