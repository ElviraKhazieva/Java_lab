package jdbc;
import models.Student;
import repositories.StudentRepository;
import repositories.StudentRepositoryJdbcImpl;
import java.sql.*;

public class Main {
    private static final String URL = "jdbc:postgresql://localhost:5432/java_lab_pract_2020";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Qwer1234";

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        StudentRepository studentRepository = new StudentRepositoryJdbcImpl(connection);
        System.out.println(studentRepository.findById(2L));
        System.out.println(studentRepository.findAll());
        System.out.println(studentRepository.findAllByAge(26));
        Student s = new Student(3L, "Катя", "Царева", 19, 901);
        studentRepository.update(s);
        Student s2 = new Student(null, "Рустем", "Хакимуллин", 19, 901);
        studentRepository.save(s2);
        Student s3 = new Student(null, "Максим", "Иванов", 19, 904);
        studentRepository.save(s3);
        connection.close();
    }
}
