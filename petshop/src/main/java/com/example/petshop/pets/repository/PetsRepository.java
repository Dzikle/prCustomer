package com.example.petshop.pets.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.petshop.owners.entity.Owner;
import com.example.petshop.pets.entity.Pets;
import com.example.petshop.pets.entity.Visits;

@Repository
public interface PetsRepository extends JpaRepository<Pets, Integer>{

	List<Pets> findByOwner(Owner owner);


}
