import java.io.FileWriter;
import java.io.IOException;

public class JournalWriter {
    public static void write(String text) {
        try (FileWriter writer = new FileWriter("output.txt", true)) {
            writer.write(text + "#ENTRYBREAK#");
        } catch (IOException e) {}
        try (FileWriter writer = new FileWriter("backup.txt", true)) {
            writer.write(text + "#ENTRYBREAK#");
        } catch (IOException e) {}
    }
    public static void clear() {
        try (FileWriter writer = new FileWriter("output.txt")) {
        } catch (Exception e) {}
    }
}
