import java.io.Serializable;
import java.util.regex.Pattern;

public class Regex implements Serializable {
    public int id;
    public Pattern pattern;
    public String description;
    public int rating;
    public static int nextID = 0;

    public Regex(Pattern pattern, String description){
        this.pattern = pattern;
        this.description = description;
        this.rating = 0;
        this.id = nextID++;
    }

    @Override
    public String toString(){
        return "Regex{" + "Id: " + id + ", Pattern: " + pattern.toString() + ", Description: " + description + ", Rating: " + rating + "}";
    }

    @Override
    public boolean equals(Object o){
        return true;
        //TODO: finish
    }


    //TODO: hascode()


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
