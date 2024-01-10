import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Folder extends FileSystemItem {
    private List<FileSystemItem> contents;
    

    public Folder(String name) {
        super(name);
        this.contents = new ArrayList<>();
    }

    public List<FileSystemItem> getContents() {
        return contents;
    }

    public void addFileSystemItem(FileSystemItem item) {
        
        item.setPath(this.getPath()+"/"+item.getName());
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
        return super.equals(obj) && Objects.equals(contents, folder.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), contents);
    }

    @Override
    public String toString() {
        return "Folder: " + super.toString();
    }
}
