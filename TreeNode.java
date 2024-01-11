import java.util.ArrayList;
import java.util.List;

public class TreeNode<T extends FileSystemItem<?>> extends FileSystemItem<T> {
    private T data;
    private List<TreeNode<T>> children;

    public TreeNode(T data, String name, String path) {
        super(name, path);
        this.data = data;
        this.children = new ArrayList<>();
    }

    public T getData() {
        return data;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void addFileSystemItem(TreeNode<T> item) {
        item.getData().setPath(getPath() + "/" + item.getData().getName());
        children.add(item);
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

        System.out.println(indentation + node.getData().toString() + " (Path: " + node.getData().getPath() + ")");

        for (TreeNode<T> child : node.getChildren()) {
            printTree(child, depth + 1);
        }
    }
}
