package main.java.customer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import main.java.customer.entity.Product;
import main.java.customer.services.ProductService;

@Path("/product")
public class ProductController {

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public String createProduct(Product product) {
		ProductService.createProduct(product);
		return "Product " + product.getName() + " is created successfully";
	}

	@PUT
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateProduct(@PathParam(value = "id") Integer id, Product product) {
		ProductService.updateProduct(id, product);
		return "Product is updated successfully";
	}

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAllProducts() {
		return ProductService.getAllProducts();
	}
	
	@PUT
	@Path("/add/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String addProducQuantity(@PathParam(value = "id") Integer id, Product product) {
		ProductService.addProducQuantity(id, product);
		return "the QUANTITY IS UPDATED";
	}

}
