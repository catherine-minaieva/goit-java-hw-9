import java.io.*;
import java.util.*;

public class Task9_3 {
    private static String PATH = "D:\\GoitJAVA\\Модуль9\\src\\main\\resources\\Task3\\words.txt";

    private static void countQuantityOfWords(File file) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder wordsInLine = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                wordsInLine.append(line + " ");
                line = bufferedReader.readLine();
            }

            String[] wordsToArray = wordsInLine.toString().split(" ");
            Map<String,Integer> wordsMap = new TreeMap<String,Integer>();
            for (String word : wordsToArray) {
                Integer counter = wordsMap.get(word);
                if (counter == null) {
                    counter = 0;
                }
                wordsMap.put(word, counter + 1);
            }

            wordsMap.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .forEach(System.out::println);

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

    public static void main(String[] args) {
        File file = new File(PATH);
        checkFileAvaliable(file);
        countQuantityOfWords(file);
    }
}

