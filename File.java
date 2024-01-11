import java.util.List;
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
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        File otherFile = (File) obj;
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
