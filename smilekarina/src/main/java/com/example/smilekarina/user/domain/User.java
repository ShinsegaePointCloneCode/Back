package com.example.smilekarina.user.domain;

import com.example.smilekarina.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

@Builder
@ToString
@Entity
@Getter
@NoArgsConstructor // new User() 막는 용도
@AllArgsConstructor
public class User extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 100, name = "UUID")
    private String UUID;
    @Column(nullable = false, length = 45, name = "login_id", unique = true)
    private String loginId;
    @Column(nullable = false, length = 100, name = "user_name")
    private String userName;
    @Column(length = 100, name = "email")
    private String email;
    @Column(nullable = false, length = 100, name = "password")
    private String password; // todo: security
    @Column(length = 15, name = "phone")
    private String phone;
    @Column(length = 100, name = "address")
    private String address;
    @Column(nullable = false, length = 1, name = "status", columnDefinition = "int default 1")
    private Integer status;
    @Column(length = 100, name = "point_password")
    private String pointPassword; // todo: Hashing
    @Column(length = 100, name = "pre_password")
    private String prePassword; // todo: Hashing
    @Enumerated(EnumType.STRING)
    @Column(nullable = true, length = 10, name = "roll")
    private Roll roll;
    public String getName() {
        return userName;
    }
    public void setPassword(String password) { this.password = password; }
    public void setPrePassword(String prePassword) { this.prePassword = prePassword; }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPointPassword(String pointPassword) { this.pointPassword = pointPassword; }
//     security impl
    public void setStatus(Integer status) { this.status = status; }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roll.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
