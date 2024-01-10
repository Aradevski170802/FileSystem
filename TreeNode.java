import java.util.ArrayList;
import java.util.List;

class TreeNode<T> {
    private T data;
    private List<TreeNode<T>> children;

    public TreeNode(T data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    public T getData() {
        return data;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }
}

