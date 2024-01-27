import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

public class Regex implements Serializable {
    public int id;
    public Pattern pattern;
    public String description;
    public int rating;
    public static int nextID = 0;

    public Regex(Pattern pattern, String description) {
        this.pattern = pattern;
        this.description = description;
        this.rating = 0;
        this.id = nextID++;
    }

    @Override
    public String toString() {
        return "Regex{" + "Id: " + id + ", Pattern: " + pattern.toString() + ", Description: " + description + ", Rating: " + rating + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Regex regex = (Regex) o;
        return Objects.equals(id, regex.id) && Objects.equals(pattern, regex.pattern) && Objects.equals(description, regex.description) && Objects.equals(rating, regex.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pattern, description, rating);
    }

    public int getId() {
        return id;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public String getDescription() {
        return description;
    }

    public int getRating() {
        return rating;
    }
}
