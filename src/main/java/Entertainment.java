
import java.util.Arrays;
import java.util.Objects;

public class Entertainment {

    private String id;
    private String title;
    private long year;
    private String[] creators;

    public Entertainment (String id, String title, long year, String[] creators) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.creators = creators;
    }

    public Entertainment() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public String[] getCreators() {
        return creators;
    }

    public void setCreators(String[] creators) {
        this.creators = creators;
    }

    @Override
    public String toString() {
        return
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", authors=" + Arrays.toString(creators) +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entertainment entertainment = (Entertainment) o;
        return year == entertainment.year &&
                Objects.equals(title, entertainment.title) &&
                Arrays.equals(creators, entertainment.creators);
    }
}
