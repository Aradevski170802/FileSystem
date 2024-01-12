public class Main {
    public static void main(String[] args) {
        // Create a sample tree
        GeneralTree tree = new GeneralTree("Root", 0);
        Folder folder1 = new Folder("Folder1");
        Folder folder2 = new Folder("Folder2");
        tree.addChild(folder1);
        tree.addChild(folder2); 
 
        // Add files/folders to Folder1
        TreeNode<FileSystemItem<?>> file1 = new TreeNode<>(new FileSystemItem<>("File1",20), "File1", "WTF/Folder1/File1");
        TreeNode<FileSystemItem<?>> subfolder1 = new TreeNode<>(new Folder("Subfolder1"), "Subfolder1", "WTF/Folder1/Subfolder1");
        folder1.addFileSystemItem(file1);
        folder1.addFileSystemItem(subfolder1);

        // Add files/folders to Subfolder1
        TreeNode<FileSystemItem<?>> file2 = new TreeNode<>(new FileSystemItem<>("File2",20), "File2", "Root/Folder1/Subfolder1/File2");
        TreeNode<FileSystemItem<?>> subfolder2 = new TreeNode<>(new Folder("Subfolder2"), "Subfolder2", "WTF/Folder1/Subfolder1/Subfolder2");
        subfolder1.addFileSystemItem(file2);
        subfolder1.addFileSystemItem(subfolder2);

        // Print the tree
        System.out.println("Original Tree:");
        //tree.deleteFileSystemItem(subfolder2.getData());
        tree.printTree();
        System.out.println(subfolder2.getData());
        // // Test the delete operation
        // System.out.println("\nDeleting Subfolder2...");
        
        // // Print the updated tree
        // System.out.println("\nUpdated Tree:");
        // tree.printTree();
        // System.out.println("\nShow folder Folder1:");
        // tree.showFolderContents(folder1);

        System.out.println(subfolder1.getSize());
    }
}
