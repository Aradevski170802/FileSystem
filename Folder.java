import java.util.List;

class Folder extends TreeNode<FileSystemItem<?>> {
    public Folder(String name) {
        super(new FileSystemItem<>(name,0), name, name);
    }
    
    public List<TreeNode<FileSystemItem<?>>> getContents() {
        return getChildren();
    }

    public void addFileSystemItem(TreeNode<FileSystemItem<?>> item) {
        String itemPath = getPath() + "/" + item.getData().getName();;
        item.getData().setPath(itemPath);
        item.setPath(itemPath);
        getContents().add(item);
        this.setNewSize();
    }
    

    public void removeFileSystemItem(FileSystemItem<?> item) {
        getContents().remove(item);
    }

    protected void setNewSize(){
        // A folder does not have a size of its own but contains files and folders which do.
        // Therefore the size is propagated to all children.
        long currentSize = 0;
        for (TreeNode<FileSystemItem<?>> child : getContents()) {
            currentSize+=child.getSize();
        }
        this.setSize(currentSize);
        
    }

    @Override
    public String toString() {
        return "Folder: " + super.toString();
    }
}
