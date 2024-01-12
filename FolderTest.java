import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

public class FolderTest {

    @Test
    public void testAddFileSystemItem() {
        Folder rootFolder = new Folder("Root");
        Folder subFolder = new Folder("Subfolder");

        rootFolder.addFileSystemItem(subFolder);

        List<TreeNode<FileSystemItem<?>>> contents = rootFolder.getContents();
        assertTrue(contents.contains(subFolder));
        assertEquals("Root/Subfolder", subFolder.getData().getPath());
    }

    @Test
    public void testRemoveFileSystemItem() {
        GeneralTree tree = new GeneralTree("Root");
        Folder folder1 = new Folder("Folder1");
        Folder folder2 = new Folder("Folder2");
        tree.addChild(folder1);
        tree.addChild(folder2); 

        // Add files/folders to Folder1
        TreeNode<FileSystemItem<?>> file1 = new TreeNode<>(new FileSystemItem<>("File1"), "File1", "Folder1/File1");
        TreeNode<FileSystemItem<?>> subfolder1 = new TreeNode<>(new Folder("Subfolder1"), "Subfolder1", "Folder1/Subfolder1");
        folder1.addFileSystemItem(file1);
        folder1.addFileSystemItem(subfolder1);

        tree.deleteFileSystemItem(file1.getData());

        List<TreeNode<FileSystemItem<?>>> contents = folder1.getContents();
        assertFalse(contents.contains(file1));
    }

    @Test
    public void testToString() {
        Folder folder = new Folder("TestFolder");
        assertEquals("Folder: TestFolder", folder.toString());
    }
}
