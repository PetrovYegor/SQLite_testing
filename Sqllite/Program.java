 import java.sql.*;
import java.util.Scanner;

public class Program {
    Connection co;

    public static void main(String[] args) throws SQLException {

        Program program = new Program();
        program.open();
        program.insert();
        program.close();
    }

    void open() {
        try {
            Class.forName("org.sqlite.JDBC");
            co = DriverManager.getConnection("jdbc:sqlite:users.db");
            System.out.println("Connected");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void insert() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        System.out.print("Enter user phone: ");
        String phone = scanner.nextLine();

        String query = "INSERT INTO users (name, phone) VALUES ('" + name + "', '" + phone + "');";    //формируем запрос для БД

        //Для выполнения запроса в БД нужен объект Statement. Он используется для любого скл запроса и он используется не просто так, а из объекта нашего подключения

        Statement statement = co.createStatement();

        //Дальше нам необходимо его выполнить

        //запросы бывают на получение данных и на обновление
        statement.executeUpdate(query);//сюда передаём запрос, который мы выполняем

        System.out.println("Rows added");//уведомляшка, что строки добавлены в БД

    }

    void close() {
        try {
            co.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}