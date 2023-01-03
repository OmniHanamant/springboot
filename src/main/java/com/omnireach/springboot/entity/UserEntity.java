package com.omnireach.springboot.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="users")

public class UserEntity {
    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Min(value = 1)
    private Integer id;
    @Email
    private String email;
    @NotNull
    @Size(min = 2,max = 20)
    private String password;
    @NotNull
    @Size(min = 2,max = 20)
    private String firstName;
    @NotNull
    @Size(min = 2,max = 20)
    private String lastName;
    private Boolean enable;
}
