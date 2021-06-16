package ru.itis.rest.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String redisId;

    @Column(length = 30)
    private String firstName;

    @Column(length = 30)
    private String lastName;

    @Column(length = 30, unique = true)
    private String username;

    @Column
    private String confirmCode;

    @Column(length = 100)
    private String hashPassword;

    @Column(length = 100, unique = true)
    private String email;

    @Column
    private Date birthDate;

    @ManyToMany//(fetch = FetchType.EAGER)
    @JoinTable(name = "subscription",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "follower_id", referencedColumnName = "id"))
    private List<User> followers;

    @ManyToMany//(fetch = FetchType.EAGER)
    @JoinTable(name = "subscription",
            joinColumns = @JoinColumn(name = "follower_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> subscriptions;

    @ManyToMany(mappedBy = "likeUsers")
    private List<Post> likedPostsByUser;

    @OneToMany(mappedBy = "author")
    private List<Comment> comments;

    @OneToMany(mappedBy = "author")
    private List<Post> posts;

    @Enumerated(value = EnumType.STRING)
    private EmailState emailState;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private ProfileState profileState;

    private Boolean isDeleted;

    public enum EmailState {
        CONFIRMED, NOT_CONFIRMED
    }

    public enum ProfileState {
        ACTIVE, BANNED
    }

    public enum Role {
        USER, ADMIN
    }

    public boolean isDeleted() {
        return isDeleted;
    }
    public boolean isActive() {
        return this.profileState == ProfileState.ACTIVE;
    }

    public boolean isBunned() {
        return this.profileState == ProfileState.BANNED;
    }

    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }

}

