package Friends;
// ...existing code...
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class FriendsManager {
    private final Path file;

    public FriendsManager() throws IOException {
        this.file = Paths.get("C:", "Users", "ASUS", "Documents", "Programación orientada a objetos", "Actividad 5", "A6friends.txt");
        Path parent = file.getParent();
        if (parent != null && Files.notExists(parent)) {
            Files.createDirectories(parent);
        }
        if (Files.notExists(file)) {
            Files.createFile(file);
        }
    }

    public synchronized boolean addFriend(String name, long number) throws IOException {
        Objects.requireNonNull(name);
        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        boolean exists = lines.stream()
                .map(l -> l.split("!", 2))
                .anyMatch(p -> p.length>0 && p[0].equalsIgnoreCase(name));
        if (exists) return false;
        String entry = name + "!" + number;
        Files.write(file, Collections.singletonList(entry), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        return true;
    }

    public synchronized boolean updateFriend(String name, long newNumber) throws IOException {
        Objects.requireNonNull(name);
        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        List<String> out = new ArrayList<>();
        boolean found = false;
        for (String l : lines) {
            if (l == null || l.trim().isEmpty()) continue;
            String[] p = l.split("!", 2);
            String storedName = p[0];
            if (storedName.equalsIgnoreCase(name)) {
                out.add(storedName + "!" + newNumber);
                found = true;
            } else {
                out.add(l);
            }
        }
        if (found) Files.write(file, out, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
        return found;
    }

    public synchronized boolean removeFriend(String name) throws IOException {
        Objects.requireNonNull(name);
        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        List<String> out = lines.stream()
                .filter(l -> {
                    if (l == null || l.trim().isEmpty()) return false;
                    String[] p = l.split("!", 2);
                    return !p[0].equalsIgnoreCase(name);
                })
                .collect(Collectors.toList());
        boolean removed = out.size() != lines.size();
        if (removed) Files.write(file, out, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
        return removed;
    }

    public synchronized List<String> getAllFriends() throws IOException {
        return Files.readAllLines(file, StandardCharsets.UTF_8).stream()
                .filter(s -> s != null && !s.trim().isEmpty())
                .collect(Collectors.toList());
    }
}
