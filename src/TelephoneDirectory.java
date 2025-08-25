import java.util.*;

public class TelephoneDirectory {
    private Map<String, List<String>> directory;

    public TelephoneDirectory() {
        this.directory = new HashMap<>();
    }

    public void add(String name, String number) {
        directory.putIfAbsent(name, new ArrayList<>());
        directory.get(name).add(number);
    }

    public List<String> get(String name) {
        return directory.getOrDefault(name, Collections.emptyList());
    }
}
