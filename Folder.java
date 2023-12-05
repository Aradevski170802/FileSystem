import java.util.ArrayList;
import java.util.List;

public class Folder extends FileSystemItem {
    private List<FileSystemItem> contents;

    public Folder(String name) {
        super(name);
        this.contents = new ArrayList<>();
    }

    public List<FileSystemItem> getContents() {
        return contents;
    }

    public void addFileSystemItem(FileSystemItem item) {
        contents.add(item);
    }

    public void removeFileSystemItem(FileSystemItem item) {
        contents.remove(item);
    }

    @Override
    public String toString() {
        return "Folder: " + super.toString();
    }
}
