package com.example.petshop.pets.entity;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.petshop.veterinarians.entity.Veterinarians;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visits {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date visit_date;
	private String description;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "id")
	private Pets pet;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "id")
	private Veterinarians vet;
}
