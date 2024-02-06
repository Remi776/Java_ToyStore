import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;
import java.io.IOException;
public class ToyDatabase {

    private int toyID;
    private String toyName;
    private int chanceVal;

    public static void createToyDatabase(int maxToys) {
        ToyDatabase[] toys = new ToyDatabase[maxToys];
        Random random = new Random();

        for (int i = 0; i < maxToys; i++) {
            ToyDatabase toy = new ToyDatabase();
            toy.toyID = i + 1;
            toy.toyName = generateToyName(random);
            toy.chanceVal = random.nextInt(101);
            toys[i] = toy;
        }

        try {
            FileWriter writer = new FileWriter("toys_db.csv");

            for (int i = 0; i < maxToys; i++) {
                writer.append(Integer.toString(toys[i].toyID));
                writer.append(",");
                writer.append(toys[i].toyName);
                writer.append(",");
                writer.append(Integer.toString(toys[i].chanceVal));
                writer.append("\n");
            }

            writer.close();

            File file = new File("toys_db.csv");
            if (file.exists()) {
                System.out.println("Файл БД игрушек <toys_db.csv> был успешно создан");
            } else {
                System.out.println("Ошибка: файл БД игрушек <toys_db.csv> не был создан");
            }

            BufferedReader reader = new BufferedReader(new FileReader("toys_db.csv"));
            if (reader.readLine() != null) {
                System.out.println("Сгерированные игрушки записаны в файл БД игрушек <toys_db.csv>");
            } else {
                System.out.println("Ошибка: файл БД игрушек <toys_db.csv> пустой");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateToyName(Random random) {
        String[] toyNames = {"Мяч", "Кукла", "Юла", "Робот", "Машинка", "Мягкая игрушка", "Конструктор"};
        return toyNames[random.nextInt(toyNames.length)];
    }
}