import java.util.Objects;

public class FileSystemItem {
    private String name;
    private String path = null;

    public void setPath(String path){
        this.path = path;
    }

    public String getPath(){return this.path;}
    public FileSystemItem(String name) {
        this.name = name;
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
        FileSystemItem that = (FileSystemItem) obj;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

