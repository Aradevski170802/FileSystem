import java.util.List;
import java.util.Objects;

public class File<T> extends FileSystemItem<T> {
    private long size;
    
    public File(String name, long size) {
        super(name,size);
    }

    public long getSize() {
        return size;
    }

    protected void setSize(long size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        File<T> otherFile = (File) obj;
        return this.getPath().equals(otherFile.getPath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getPath());
    }

    @Override
    public String toString() {
        return "File: " + super.toString();
    }
}
