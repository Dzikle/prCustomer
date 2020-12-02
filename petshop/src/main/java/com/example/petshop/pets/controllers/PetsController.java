package com.example.petshop.pets.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.petshop.owners.entity.Owner;
import com.example.petshop.owners.repository.OwnerRepository;
import com.example.petshop.pets.entity.Pets;
import com.example.petshop.pets.entity.Visits;
import com.example.petshop.pets.repository.PetsRepository;
import com.example.petshop.pets.repository.VisitRepository;
import com.example.petshop.veterinarians.entity.Veterinarians;
import com.example.petshop.veterinarians.repository.VetsRepository;

@Controller
public class PetsController {
	
	@Autowired
	PetsRepository petRepo;
	@Autowired
	OwnerRepository ownRepo;
	@Autowired
	VetsRepository vetRepo;
	@Autowired
	VisitRepository visitRepo;
	
	@PostMapping("/owners/addPet/{id}")
	public String addPet(@ModelAttribute Pets pet,@PathVariable("id") Integer id) {
		Pets pet2 = new Pets();
		Owner owner = ownRepo.findById(id).get();
		pet2.setOwner(owner);
		pet2.setName(pet.getName());
		pet2.setAnimalType(pet.getAnimalType());
		pet2.setBday(pet.getBday());
		List<Pets> pets = petRepo.findByOwner(owner);
		pets.add(pet2);
		petRepo.save(pet2);
		owner.setPets(pets);
		ownRepo.save(owner);
		return "redirect:/owners/{id}/pets/new";
	}
	
	@GetMapping("/owners/{id}/pets/new")
	public String getPetForm(Model model, Pets pet,@PathVariable("id") Integer id){
		Pets pet2 = new Pets();
		Owner owner = ownRepo.findById(id).get();
		model.addAttribute("owner", owner);
		model.addAttribute("pet", pet2);
		return "pets/createOrUpdatePetForm";
	}
	@GetMapping("/pets/{id}/visit/new")
	public String getPetForm(Model model, Visits visit,@PathVariable("id") Integer id){
		Visits visit2 = new Visits();
		Pets pet = petRepo.findById(id).get();
		List<Veterinarians> vets = vetRepo.findAll();
		model.addAttribute("pet", pet);
		model.addAttribute("visit2", visit2);
		model.addAttribute("vets", vets);
		return "pets/visitForm";
	}
	@PostMapping("/pets/addVisit/{id}")
	public String addPet(@ModelAttribute Visits visit,@PathVariable("id") Integer id) {
		Visits visit2 = new Visits();
		Pets pet = petRepo.findById(id).get();
		visit2.setPet(pet);;
		visit2.setDescription(visit.getDescription());;
		visit2.setVet(visit.getVet());
		visit2.setVisit_date(visit.getVisit_date());
		List<Visits> visits = visitRepo.findByPet(pet);
		visits.add(visit2);
		visitRepo.save(visit2);
		pet.setVisits(visits);
		petRepo.save(pet);
		return "redirect:/pets/{id}/visit/new";
	}
	@GetMapping("/pet/update/{id}")
	public String ownerUpateForm(Model model,@PathVariable Integer id) {
		Pets pets = petRepo.findById(id).get();
//		Owner owner = ownRepo.findById(id).get();
		model.addAttribute("pets", pets);
//		model.addAttribute("owner", owner);
		return "pets/petUpdateForm";
	}
	@PostMapping("/pets/addPet/{id}")
	public String updatePet(@ModelAttribute Pets pets,@PathVariable Integer id) {
		petRepo.save(pets);
		return "redirect:/owners/" + pets.getOwner().getId();
	}

}
