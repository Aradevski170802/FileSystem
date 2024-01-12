import static org.junit.Assert.*;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class GeneralTreeTest {

    @Test
    public void testAddChild() {
        GeneralTree tree = new GeneralTree("Root");
        Folder rootFolder = new Folder("RootFolder");
        Folder subFolder = new Folder("Subfolder");

        tree.addChild(rootFolder);
        tree.addChild(subFolder);

        List<TreeNode<FileSystemItem<?>>> children = tree.getChildren();
        assertTrue(children.contains(rootFolder));
        assertTrue(children.contains(subFolder));
        assertEquals("Root/RootFolder", rootFolder.getData().getPath());
        assertEquals("Root/Subfolder", subFolder.getData().getPath());
    }

    @Test
    public void testDeleteFileSystemItem() {
        GeneralTree tree = new GeneralTree("Root");
        Folder rootFolder = new Folder("RootFolder");
        Folder subFolder = new Folder("Subfolder");

        tree.addChild(rootFolder);
        rootFolder.addFileSystemItem(subFolder);

        tree.deleteFileSystemItem(subFolder.getData());

        List<TreeNode<FileSystemItem<?>>> children = tree.getChildren();
        assertFalse(children.contains(subFolder));
    }

    @Test
    public void testShowFolderContents() {
        GeneralTree tree = new GeneralTree("Root");
        Folder rootFolder = new Folder("RootFolder");
        Folder subFolder = new Folder("Subfolder");

        tree.addChild(rootFolder);
        rootFolder.addFileSystemItem(subFolder);

        // Redirect System.out to capture the printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        tree.showFolderContents(tree);

        // Reset System.out to its original state
        System.setOut(System.out);

        // Verify the printed output
        assertEquals("Root (Path: Root)\n  RootFolder (Path: Root/RootFolder)\n    Subfolder (Path: Root/RootFolder/Subfolder)\n", outContent.toString());
    }
}
