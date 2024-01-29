import java.util.ArrayList;
import java.util.List;

public class RegexTester {
    protected static List<Boolean> test(Regex regex, String[] strings){
        List<Boolean> results = new ArrayList<>(strings.length);
        for (String string : strings){
            if (regex.getPattern().matcher(string).matches())
                results.add(true);
            else
                results.add(false);
        }
        return results;
    }
}