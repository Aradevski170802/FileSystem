import java.util.Objects;

public class FileSystemItem<T> {
    private String name;
    private String path = null;

    public FileSystemItem(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public FileSystemItem(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
    
        FileSystemItem<?> that = (FileSystemItem<?>) obj;
    
        return Objects.equals(getName(), that.getName());
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
    
}