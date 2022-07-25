package com.cts.authorization.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "users")
@ApiModel(description="User Model")
public class User {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ApiModelProperty(notes="The User's name")
	private String userName;
	
	@ApiModelProperty(notes="The User's password")
	private String password;
	
}
