package by.hackaton.bookcrossing.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "roles")
public class Role {
	@Id
	@Column(name = "role_id")
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	
}
