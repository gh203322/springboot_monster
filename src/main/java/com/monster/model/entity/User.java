package com.monster.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {

		@Id
	    @GeneratedValue
	    private Integer id;
	
	    private String userName;
	
	    private String passwd;
	
	    private String nickName;
	
	    private String telephone;
	
	    private String email;
	
	    private Integer authority;
	
	    private String department;
	
	    private String createAt;
	
	    private String updateAt;
}
