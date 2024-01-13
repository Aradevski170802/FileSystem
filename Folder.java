public class Folder extends TreeNode<FileSystemItem<?>> {
    public Folder(String name) {
        super(new FileSystemItem<>(name,0), name);
    }

    // protected void setNewSize(){
    //     // A folder does not have a size of its own but contains files and folders which do.
    //     // Therefore the size is propagated to all children.
    //     long currentSize = 0;
    //     for (int i = 0; i <getChildren().size(); i++) {
    //         System.out.println(currentSize +" This is the new size for " + getName());
    //         currentSize+=getChildren().get(i).getData().getSize();
    //     }
    //     this.setSize(currentSize);
        
    // }

    @Override
    public String toString() {
        return "Folder: " + super.toString();
    }
}
