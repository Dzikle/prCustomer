package com.example.petshop.owners.controllers;

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
import com.example.petshop.owners.repository.OwnerRepository;
import com.example.petshop.pets.entity.Pets;
import com.example.petshop.pets.repository.PetsRepository;
import com.example.petshop.pets.repository.VisitRepository;

@Controller
public class OwnerController {

	@Autowired
	OwnerRepository ownRepo;
	@Autowired
	PetsRepository petRepo;
	@Autowired
	VisitRepository visitRepo;

//	Create Owner
	@PostMapping("/owner/create")
	public String createOwner(@ModelAttribute Owner owner) {
		ownRepo.save(owner);
		return "/owner/createOrUpdateOwnerForm";
	}

	// Get the Owner create form
	@GetMapping("/owners/new")
	public String ownerForm(Model model) {
		Owner owner = new Owner();
		model.addAttribute("owner", owner);
		return "owner/createOrUpdateOwnerForm";
	}

    //	Delete owner
	@GetMapping("/owner/delete/{id}")
	public String deleteOwner(@PathVariable Integer id, Owner owner) {
		ownRepo.deleteById(id);
		owner.setLastName("");
		return "redirect:/owners/find";
	}

	@GetMapping("/owners")
	public String processFindOwners(Model model, Owner owner) {
		List<Owner> owners = ownRepo.findByLastName(owner.getLastName());
		if (owner.getLastName().equals("")) {
			List<Owner> owners2 = ownRepo.findAll();
			model.addAttribute("owners", owners2);
			return "owner/ownersList";
		} else if (owners.size() == 1) {
			return "redirect:/owners/" + owners.get(0).getLastName();
		}
		model.addAttribute("owners", owners);
		return "owner/ownersList";
	}

	@GetMapping("/owners/find")
	public String getFindForm(Model model, Owner owner) {
		model.addAttribute("owner", owner);
		return "owner/findOwner";
	}

	@GetMapping("/owners/{lastName}")
	public String getOwner(@PathVariable("lastName") String lastName, Model model) {
		Owner owner = ownRepo.findByLastName(lastName).get(0);
		// Pets pet = new Pets();
		List<Pets> pets = petRepo.findByOwner(owner);

		model.addAttribute("pets", pets);
		model.addAttribute("owner", owner);
		return "owner/ownerDetails";

	}

	@GetMapping("/owners/update/{id}")
	public String ownerUpateForm(Model model,@PathVariable Integer id) {
		Owner owner = ownRepo.findById(id).get();
		model.addAttribute("owner", owner);
		return "owner/updateForm";
	}
}
