    import java.util.List;

    class Folder extends TreeNode<FileSystemItem<?>> {
        public Folder(String name) {
            super(new FileSystemItem<>(name,0), name);
        }
        
        public List<TreeNode<FileSystemItem<?>>> getContents() {
            return getChildren();
        }

        public void addFileSystemItem(TreeNode<FileSystemItem<?>> item) {
            // System.out.println(item.getSize()+"  <--->  "+item.getData().getSize() + "  "+item.getName());
            String itemPath = getPath() + "/" + item.getData().getName();;
            item.getData().setPath(itemPath);
            item.setPath(itemPath);
            getContents().add(item);
            this.setNewSize();
        }
        

        public void removeFileSystemItem(FileSystemItem<?> item) {
            getContents().remove(item);
            this.setSize(this.getData().getSize()-item.getSize());
            this.setSize(this.getSize()-item.getSize());
            item = new FileSystemItem<>(null, 0);
        }

        protected void setNewSize(){
            // A folder does not have a size of its own but contains files and folders which do.
            // Therefore the size is propagated to all children.
            long currentSize = 0;
            for (int i = 0; i <getContents().size(); i++) {
                System.out.println(currentSize +" This is the new size for " + getName());
                currentSize+=getContents().get(i).getData().getSize();
            }
            this.setSize(currentSize);
            
        }

        @Override
        public String toString() {
            return "Folder: " + super.toString();
        }
    }
