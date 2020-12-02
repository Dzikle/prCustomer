package com.example.petshop.veterinarians.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.petshop.owners.entity.Owner;
import com.example.petshop.veterinarians.entity.Veterinarians;
import com.example.petshop.veterinarians.repository.VetsRepository;

@Controller
public class VetsController {
	
	@Autowired
	VetsRepository vetRepo;
	
	@PostMapping("/vets/create")
	public String addVet(@ModelAttribute Veterinarians vet) {
		
		vetRepo.save(vet);
		return "redirect:/vets/new";
	}
	
	@GetMapping("/vets/new")
	public String getVetForm(Model model) {
		Veterinarians vet= new Veterinarians();
		List<Veterinarians> vets = vetRepo.findAll();
		model.addAttribute("vet", vet);
		model.addAttribute("vets", vets);
		return "/vets/vetForm";
	}
	@GetMapping("/vets/delete/{id}")
	public String deleteOwner(@PathVariable Integer id,Owner owner) {
		vetRepo.deleteById(id);
		//owner.setLastName("");
		return "redirect:/vets/new";
	}
	@GetMapping("/vet/update/{id}")
	public String ownerUpateForm(Model model,@PathVariable Integer id) {
		Veterinarians veterinarians = vetRepo.findById(id).get();
		model.addAttribute("veterinarians", veterinarians);
		return "vets/vetUpdateForm";
	}
}
