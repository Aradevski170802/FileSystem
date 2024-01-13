public class Main {
    public static void main(String[] args) {
        // Create a sample tree
        GeneralTree tree = new GeneralTree("Root", 0);
        TreeNode<FileSystemItem<?>> folder1 = new TreeNode<>(new Folder("Folder1"), "Folder1");
        TreeNode<FileSystemItem<?>> folder2 = new TreeNode<>(new Folder("Folder2"), "Folder2");
        tree.addFileSystemItem(folder1);
        tree.addFileSystemItem(folder2); 
 
        // Add files/folders to Folder1
        TreeNode<FileSystemItem<?>> file1 = new TreeNode<>(new FileSystemItem<>("File1",20), "File1");
        TreeNode<FileSystemItem<?>> file69 = new TreeNode<>(new FileSystemItem<>("File1",20), "File1");
            
        TreeNode<FileSystemItem<?>> subfolder1 = new TreeNode<>(new Folder("Subfolder1"), "Subfolder1");
        //folder1.addFileSystemItem(file1);
        folder1.addFileSystemItem(file1);
        folder1.addFileSystemItem(subfolder1);

        // Add files/folders to Subfolder1
        TreeNode<FileSystemItem<?>> file2 = new TreeNode<>(new FileSystemItem<>("File2",20), "File2");
        TreeNode<FileSystemItem<?>> subfolder2 = new TreeNode<>(new Folder("Subfolder2"), "Subfolder2");
        subfolder1.addFileSystemItem(file2);
        folder2.addFileSystemItem(file69);
        file1.addFileSystemItem(subfolder2);
        //subfolder1.addFileSystemItem(file2);
        subfolder1.addFileSystemItem(subfolder2);

        // Print the tree
        //tree.deleteFileSystemItem(folder1);
        System.out.println("Original Tree:");
        tree.deleteFileSystemItem(new TreeNode<>(new FileSystemItem<>("File5",20), "File5"));
        tree.printTree();
        
        //tree.deleteFileSystemItem(subfolder1.getData());
        //tree.deleteFileSystemItem(file1.getData());
        
        
        // System.out.println(file1);
        // // Test the delete operations
        // System.out.println("\nDeleting Subfolder2...");
        
        // // Print the updated tree
        // System.out.println("\nUpdated Tree:");
        // tree.printTree();
        // System.out.println("\nShow folder Folder1:");
        // tree.showFolderContents(folder1);

        // System.out.println(folder1.getData().getSize());
        // tree.deleteFileSystemItem(folder1);
        // tree.printTree();
        // System.out.println(folder1.getData().getSize());
    
        
        //System.out.println(tree.searchItemByName("File1"));
        //tree.showFolderContents(subfolder1);
        //System.out.println(file1.getPath());
        //System.out.println(tree.searchDFS("File1"));
    }
}
