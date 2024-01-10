import java.util.Objects;

public class File extends FileSystemItem {
    private String content;
    //void setPath

    public File(String name, String content) {
        super(name);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!super.equals(obj)) return false;
        if (obj instanceof File file) {
            return Objects.equals(content, file.content);
        }
        return false;
        
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), content);
    }

    @Override
    public String toString() {
        return "File: " + super.toString();
    }
}
