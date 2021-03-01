import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static java.lang.Integer.parseInt;

public class Task9_2 {
    private static String PATH = "D:\\GoitJAVA\\Модуль9\\src\\main\\resources\\task2\\file.txt";
    private static String ENDPATH = "D:\\GoitJAVA\\Модуль9\\src\\main\\resources\\task2\\user.json";


    public static void main(String[] args) {
        File file = new File(PATH);
        File jsonfile = new File(ENDPATH);
        List<User> users = new ArrayList<>();
        readTextFromFile(users);
        checkFileAvaliable(file);
        writeObjectsToJson(users);

    }

    private static void writeObjectsToJson(List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ENDPATH))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(users));
            String json = gson.toJson(users);

            System.out.println(json);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void  readTextFromFile(List<User> users) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] parts = line.split(" ");
                if (!parts[0].equals("name") || !parts[1].equals("age")) {
                User user = new User(parts[0], parseInt(parts[1]));
                    users.add(user);
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

class User {
    private String name;
    private int age;

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

