import java.util.Objects;

public class FileSystemItem<T> {
    private String name;
    private long size;
    private String path = null;

    public FileSystemItem(String name, String path, long size) {
        this.name = name;
        this.path = path;
        this.size = size;
    }

    public FileSystemItem(String name, long size) {
        this.name = name;
        this.size = size;
    }
    public long getSize() {
        return this.size;
    }

    protected void setSize(long size) {
        this.size = size;
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