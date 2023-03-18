package com.gascharge.taemin.domain.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gascharge.taemin.common.oauth.AuthProvider;
import com.gascharge.taemin.domain.entity.reservation.Reservation;
import com.gascharge.taemin.domain.enums.user.UserAuthority;
import com.gascharge.taemin.domain.enums.user.UserEmailVerified;
import com.gascharge.taemin.jpa.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString(exclude = {"password"})
@Entity
@Table(name = "USERS"
//        ,uniqueConstraints = {
//                @UniqueConstraint(columnNames = {"USER_ID", "email"})
//        }
        )
@DynamicInsert
public class User extends BaseTimeEntity implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    @JsonIgnore
    private Long id;

    @Setter
    @NotNull
    private String name;

    @NotNull
    private String email;

    @Setter
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @ColumnDefault(value = "'UNVERIFIED'")
    private UserEmailVerified emailVerified;

    @JsonIgnore
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    @Enumerated(EnumType.STRING)
    @ColumnDefault(value = "'ROLE_USER'")
    private UserAuthority userAuthority;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonManagedReference
    private List<Reservation> reservations = new ArrayList<>();

    @Builder
    public User(String name, String email, String imageUrl, UserEmailVerified emailVerified, String password, AuthProvider provider, String providerId, UserAuthority userAuthority) {
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.emailVerified = emailVerified;
        this.password = password;
        this.provider = provider;
        this.providerId = providerId;
        this.userAuthority = userAuthority;
    }

    @Override
    public User clone() {
        try {
            return (User) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
