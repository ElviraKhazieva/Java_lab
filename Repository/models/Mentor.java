package models;

import java.util.Objects;

public class Mentor {
    private Long Id;
    private String firstName;
    private String lastName;
    private Subject subject;
    private Student student;

    public Mentor(Long id, String firstName, String lastName, Subject subject, Student student) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
        this.student = student;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mentor mentor = (Mentor) o;
        return Objects.equals(Id, mentor.Id) &&
                Objects.equals(firstName, mentor.firstName) &&
                Objects.equals(lastName, mentor.lastName) &&
                Objects.equals(subject, mentor.subject) &&
                Objects.equals(student, mentor.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, firstName, lastName, subject, student);
    }

    @Override
    public String toString() {
        return "Mentor{" +
                "Id=" + Id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", subject=" + subject +
                ", student=" + student.getFirstName() + student.getLastName() + "id = " + student.getId() +
                '}';
    }
}
