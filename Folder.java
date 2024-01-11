import java.util.ArrayList;
import java.util.List;

class Folder extends TreeNode<FileSystemItem<?>> {

    public Folder(String name) {
        super(new FileSystemItem<>(name), name, name);
    }

    public List<TreeNode<FileSystemItem<?>>> getContents() {
        return getChildren();
    }

    public void addFileSystemItem(TreeNode<FileSystemItem<?>> item) {
        item.getData().setPath(getPath() + "/" + item.getData().getName());
        getContents().add(item);
    }

    public void removeFileSystemItem(FileSystemItem<?> item) {
        getContents().remove(item);
    }

    @Override
    public String toString() {
        return "Folder: " + super.toString();
    }
}
