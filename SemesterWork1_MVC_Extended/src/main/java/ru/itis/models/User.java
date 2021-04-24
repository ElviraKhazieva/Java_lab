package ru.itis.models;

import lombok.*;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

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

    @Column(length = 30)
    private String firstName;

    @Column(length = 30)
    private String lastName;

    @Column(length = 30)
    private String nickname;

    @Column
    private String confirmCode;

    @Column(length = 100)
    private String hashPassword;

    @Column(length = 100)
    private String email;

    @Column
    private Date birthDate;

    @ManyToMany
    @JoinTable(name = "subscription",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "follower_id", referencedColumnName = "id"))
    private List<User> followers;

    @ManyToMany
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
    private State state;

    public enum EmailState {
        CONFIRMED, NOT_CONFIRMED
    }

    public enum State {
        ACTIVE, BANNED
    }

    public enum Role {
        USER, ADMIN
    }

    public boolean isActive() {
        return this.state == State.ACTIVE;
    }

    public boolean isBunned() {
        return this.state == State.BANNED;
    }

    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }

}

