package com.springboot.project.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String name;

    @Column(name="email_id")
    private String emailId;

    private String password;

    private String about;

	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<Post> posts = new ArrayList<>();
   
}
