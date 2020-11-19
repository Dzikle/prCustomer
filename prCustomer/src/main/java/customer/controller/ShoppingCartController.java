
package main.java.customer.controller;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import main.java.customer.entity.ShoppingCart;
import main.java.customer.services.ShoppingCartService;

@Path("/buy")
public class ShoppingCartController {

  @POST
  @Path("/cart/{customerId}")
  @Consumes(MediaType.APPLICATION_JSON)
  public void createShoppingCart(@PathParam("customerId") Integer customerId, ShoppingCart cart) {

    ShoppingCartService.createCart(customerId, cart);

  }
  
  
  @POST
  @Path("/cart/byProductId/{customerId}")
  @Consumes(MediaType.APPLICATION_JSON)
  public void createShoppingCartByProductId(@PathParam("customerId") Integer customerId, List<Integer> productIds) {

    ShoppingCartService.createCartByProductId(customerId, productIds);

  }
  @DELETE
  @Path("/remove/{cartid}/{productId}")
  @Consumes(MediaType.APPLICATION_JSON)
  public String removeProductFromCart(@PathParam("cartid") Integer cartid ,@PathParam("productId") Integer productId) {

    ShoppingCartService.removeProductFromCart(cartid,productId);

    return "The product is removed from the cart";
  }
  
  @DELETE
  @Path("/removeAll/{cartid}")
  @Consumes(MediaType.APPLICATION_JSON)
  public String removeAllProductFromCart(@PathParam("cartid") Integer cartid ) {

    ShoppingCartService.removeAllProductFromCart(cartid);

    return "The cart is empty";
  }

}
