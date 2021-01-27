import java.util.HashMap;
import java.util.Map;

public class Driver {
    public static void main(String[] args) {
        Map<String, String> object = new HashMap<>();
        object.put("hello", "world");

        System.out.println(object.get("not here"));
    }
}
