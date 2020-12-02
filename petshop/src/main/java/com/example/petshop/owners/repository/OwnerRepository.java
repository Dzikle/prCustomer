package com.example.petshop.owners.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.petshop.owners.entity.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer>{
	
	@Query("SELECT DISTINCT owner FROM Owner owner WHERE owner.lastName LIKE :lastName%")
	@Transactional(readOnly = true)
	List<Owner> findByLastName(String lastName);

}
