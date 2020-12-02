package com.example.petshop.pets.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.petshop.owners.entity.Owner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pets {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Date bday;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "id")
	private Owner owner;
	@ManyToOne(cascade = CascadeType.ALL)
	private Type animalType;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Visits> visits;

}
