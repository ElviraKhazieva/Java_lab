package repositories;
import models.Mentor;
import models.Student;
import models.Subject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryJdbcImpl implements StudentRepository {

    //language=PostgreSQL
    private static final String SQL_SELECT_BY_ID = "select student.id as s_id, mentor.id as m_id," +
            " student.first_name as s_fn, student.last_name as s_ln, mentor.first_name as m_fn," +
            " mentor.last_name as m_ln, subject.id as sub_id, * from " +
            "(student left join mentor on student.id = mentor.student_id) " +
            "left join subject on mentor.subject_id = subject.id where student.id = %d";
    //language=PostgreSQL
    private static final String SQL_INSERT_INTO_STUDENT =  "insert into student " +
            "(first_name, last_name, age, group_number) values ('%s', '%s', %d, %d) returning id";
    //language=PostgreSQL
    private static final String SQL_SELECT_ALL_BY_Age = "select student.id as s_id, mentor.id as m_id," +
            " student.first_name as s_fn, student.last_name as s_ln, mentor.first_name as m_fn," +
            " mentor.last_name as m_ln, subject.id as sub_id, * from " +
            "(student left join mentor on student.id = mentor.student_id) " +
            "left join subject on mentor.subject_id = subject.id WHERE age = %d " +
            "order by student.id";
    //language=PostgreSQL
    private static final String SQL_INSERT_INTO_MENTOR = "insert into mentor " +
            "(first_name, last_name, student_id, subject_id) values (%s, %s, %d, %d)";
    private static final String SQL_INSERT_INTO_MENTOR_VAL = ", ('%s', '%s', %d, %d)";
    private static final String SQL_RETURNING_ID = " returning id";
    //language=PostgreSQL
    private static final String SQL_UPDATE_STUDENT ="update student set " +
            "first_name = '%s', last_name = '%s', age = %d, group_number = %d where id = %d";
    //language=PostgreSQL
    private static final String SQL_DELETE_MENTORS = "delete from mentor where student_id = %d";
    //language=PostgreSQL
    private static final String SQL_SELECT_ALL = "select student.id as s_id, mentor.id as m_id," +
            " student.first_name as s_fn, student.last_name as s_ln, mentor.first_name as m_fn," +
            " mentor.last_name as m_ln, subject.id as sub_id, * from" +
            " (student left join mentor on student.id = mentor.student_id) " +
            " left join subject on mentor.subject_id = subject.id order by student.id";


    private Connection connection;

    public StudentRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Student> findAllByAge(int age) {
        List<Student> students = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(String.format(SQL_SELECT_ALL_BY_Age, age));
            long lastStudentId = -1;
            Student student = new Student(-1L,"", "", 0,0);
            while(resultSet.next()) {
                if (lastStudentId != resultSet.getLong("s_id")) {
                    student = new Student(
                            resultSet.getLong("s_id"),
                            resultSet.getString("s_fn"),
                            resultSet.getString("s_ln"),
                            resultSet.getInt("age"),
                            resultSet.getInt("group_number")
                    );
                    if (resultSet.getObject("m_id") != null) {
                        student.getMentors().add(new Mentor(
                                resultSet.getLong("m_id"),
                                resultSet.getString("m_fn"),
                                resultSet.getString("m_ln"),
                                new Subject(resultSet.getLong("sub_id"), resultSet.getString("title")),
                                student));
                    }
                    lastStudentId = student.getId();
                    students.add(student);
                }
                else {
                    student.getMentors().add(new Mentor(
                            resultSet.getLong("m_id"),
                            resultSet.getString("m_fn"),
                            resultSet.getString("m_ln"),
                            new Subject(resultSet.getLong("sub_id"), resultSet.getString("title")),
                            student));
                    lastStudentId = student.getId();
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    // ignore
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    // ignore
                }
            }
        }
        return students;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_ALL);
            long lastStudentId = -1;
            Student student = new Student(-1L, "", "", 0, 0);
            while (resultSet.next()) {
                if (lastStudentId != resultSet.getLong("s_id")) {
                    student = new Student(
                            resultSet.getLong("s_id"),
                            resultSet.getString("s_fn"),
                            resultSet.getString("s_ln"),
                            resultSet.getInt("age"),
                            resultSet.getInt("group_number")
                    );
                    if (resultSet.getObject("m_id") != null) {
                        student.getMentors().add(new Mentor(
                                resultSet.getLong("m_id"),
                                resultSet.getString("m_fn"),
                                resultSet.getString("m_ln"),
                                new Subject(resultSet.getLong("sub_id"), resultSet.getString("title")),
                                student
                        ));
                    }
                    lastStudentId = student.getId();
                    students.add(student);
                } else {
                    student.getMentors().add(new Mentor(
                            resultSet.getLong("m_id"),
                            resultSet.getString("m_fn"),
                            resultSet.getString("m_ln"),
                            new Subject(resultSet.getLong("sub_id"), resultSet.getString("title")),
                            student
                    ));
                    lastStudentId = student.getId();
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    // ignore
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    // ignore
                }
            }
        }
        return students;
    }

    @Override
    public Student findById(Long id) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(String.format(SQL_SELECT_BY_ID, id));
            Student student = new Student(-1L,"","",0,0);
            if (resultSet.next()) {
                student = new Student(
                        resultSet.getLong("s_id"),
                        resultSet.getString("s_fn"),
                        resultSet.getString("s_ln"),
                        resultSet.getInt("age"),
                        resultSet.getInt("group_number")

                );
                if (resultSet.getObject("m_id") != null) {
                    student.getMentors().add(new Mentor(
                            resultSet.getLong("m_id"),
                            resultSet.getString("m_fn"),
                            resultSet.getString("m_ln"),
                            new Subject(resultSet.getLong("sub_id"), resultSet.getString("title")),
                            student
                    ));
                }
            }
            while (resultSet.next()) {
                student.getMentors().add(new Mentor(
                        resultSet.getLong("m_id"),
                        resultSet.getString("m_fn"),
                        resultSet.getString("m_ln"),
                        new Subject(resultSet.getLong("sub_id"), resultSet.getString("title")),
                        student
                ));
            }
            return student;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
        }
    }


    @Override
    public void save(Student entity) {

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(String.format(SQL_INSERT_INTO_STUDENT,
                    entity.getFirstName(),
                    entity.getLastName(),
                    entity.getAge(),
                    entity.getGroupNumber()
            ));
            resultSet.next();
            entity.setId(resultSet.getLong("id"));
            StringBuilder q1 = null;
            List<Mentor> mentors = entity.getMentors();
            boolean flag = true;
            for (Mentor m : mentors) {
                if (flag) {
                    q1 = new StringBuilder(String.format(SQL_INSERT_INTO_MENTOR,
                            m.getFirstName(),
                            m.getLastName(),
                            m.getStudent().getId(),
                            m.getSubject().getId()
                    ));
                    flag = false;
                } else {
                    q1.append(String.format(SQL_INSERT_INTO_MENTOR_VAL,
                            m.getFirstName(),
                            m.getLastName(),
                            m.getStudent().getId(),
                            m.getSubject().getId()
                    ));
                }
            }
            if (q1 != null) {
                q1.append(SQL_RETURNING_ID);
                resultSet = statement.executeQuery(q1.toString());
            }
            for (int i = 0; i < mentors.size(); i++) {
                resultSet.next();
                mentors.get(i).setId(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    // ignore
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    // ignore
                }
            }

        }

    }
    @Override
    public void update(Student entity) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(String.format(SQL_UPDATE_STUDENT,
                    entity.getFirstName(),
                    entity.getLastName(),
                    entity.getAge(),
                    entity.getGroupNumber(),
                    entity.getId()
            ));
            statement.execute(String.format(SQL_DELETE_MENTORS, entity.getId()));
            List<Mentor> mentors = entity.getMentors();
            boolean flag = true;
            StringBuilder q1 = null;
            for (Mentor m : mentors) {
                if (flag) {
                    q1 = new StringBuilder(String.format(SQL_INSERT_INTO_MENTOR,
                            m.getFirstName(),
                            m.getLastName(),
                            m.getStudent().getId(),
                            m.getSubject().getId()
                    ));
                    flag = false;
                } else {
                    q1.append(String.format(SQL_INSERT_INTO_MENTOR_VAL,
                            m.getFirstName(),
                            m.getLastName(),
                            m.getStudent().getId(),
                            m.getSubject().getId()
                    ));
                }
            }
            if (q1 != null) {
                q1.append(SQL_RETURNING_ID);
                resultSet = statement.executeQuery(q1.toString());
            }
            for (int i = 0; i < mentors.size(); i++) {
                resultSet.next();
                mentors.get(i).setId(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    //ignore
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    //ignore
                }
            }
        }
    }
}
