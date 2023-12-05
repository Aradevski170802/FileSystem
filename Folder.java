import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Folder folder = (Folder) obj;
        return Objects.equals(getName(), folder.getName()) &&
                Objects.equals(contents, folder.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), contents);
    }

    @Override
    public String toString() {
        return "Folder: " + super.toString();
    }
}
