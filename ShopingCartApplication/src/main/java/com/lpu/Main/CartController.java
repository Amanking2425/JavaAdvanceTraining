package com.lpu.Main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.lpu.Service.CartService;
import com.lpu.Service.ProductService;

@Controller
public class CartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @GetMapping("/")
    public String viewProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";   // will map to /view/products.html
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam int productId,
                            @RequestParam int quantity) {

        cartService.addToCart(
                productService.getProductById(productId),
                quantity
        );

        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("totalAmount", cartService.getTotalAmount());
        return "cart";  // will map to /view/cart.html
    }
}