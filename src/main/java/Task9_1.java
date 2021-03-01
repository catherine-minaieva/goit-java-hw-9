import java.io.*;
import java.util.regex.Pattern;

public class Task9_1 {
    private static final String PATH = "D:\\GoitJAVA\\Модуль9\\src\\main\\resources\\file.txt";

    public static void main(String[] args) {
        File file = new File(PATH);
        checkFileAvaliable(file);

        String pattern1 = "^\\d{3}-\\d{3}-\\d{4}$";
        String pattern2 = "^\\(\\d{3}\\)\\s\\d{3}-\\d{4}$";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                if (Pattern.matches(pattern1, line) || Pattern.matches(pattern2, line)) {
                    System.out.println(line);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void checkFileAvaliable(File file) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();

            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
