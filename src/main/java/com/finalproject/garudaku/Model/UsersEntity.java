package com.finalproject.garudaku.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "users")
public class UsersEntity {


    @Id
    private UUID uid_user;
    private String username;
    private String email;
    private String password;
    private String full_name;
    private char gender;
    private String telephone;
    private String photo_profile;
    private String roles;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth_date;

    private Boolean status_active = true;
    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @UpdateTimestamp
    private LocalDateTime modifiedAt;
}
