package main.java.customer.controller;

import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import main.java.customer.entity.Customer;
import main.java.customer.services.CustomerServices;

@Path("/customer")
public class CustomerController {

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public String createUser(Customer customer) {
		CustomerServices.createCustomer(customer);
		return "User " + customer.getName() + " is created successfully";
	}

	@POST
	@Path("/init")
	@Consumes(MediaType.APPLICATION_JSON)
	public String init() {
		CustomerServices.init();
		return "The tables are initialized";
	}

	@PUT
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateCUstomer(@PathParam("id") Integer id, Customer customer) {
		CustomerServices.updateCustomer(id, customer);
		return "customer " + customer.getName() + " is updated successfully";
	}
}
