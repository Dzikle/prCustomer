package com.example.petshop.veterinarians.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.petshop.veterinarians.entity.Veterinarians;
@Repository
public interface VetsRepository extends JpaRepository<Veterinarians, Integer> {

}
