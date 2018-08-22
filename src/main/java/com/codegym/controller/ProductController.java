package com.codegym.controller;


import com.codegym.model.Product;
import com.codegym.service.ProductService;
import com.codegym.service.ProductServieImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {
    private ProductService productService = new ProductServieImpl();

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("product", productService.findAll());
        return "list";
    }

    @GetMapping("/product/create")
    public String showCreate(Model model) {
        model.addAttribute("product", new Product());
        return "create";
    }

    @PostMapping("/product/save")
    public String save(
            RedirectAttributes redirectAttributes,
            Product product
    ) {
        int id = (int) (Math.random() * 100);
        product.setId(id);
        productService.save(product);
        redirectAttributes.addFlashAttribute("success", "Saved product successfully");
        return "redirect:/";
    }

    @GetMapping("/product/{id}/edit")
    public String edit(
            Model model,
            @PathVariable("id") int id
    ) {
        model.addAttribute("product", productService.findById(id));
        return "edit";
    }

    @PostMapping("/product/update")
    public String update(
            Product product,
            RedirectAttributes redirectAttributes
    ) {
        productService.update(product.getId(), product);
        redirectAttributes.addFlashAttribute("success", "Update product successfully");
        return "redirect:/";
    }

    @GetMapping("/product/{id}/delete")
    public String delete(
            Model model,
            @PathVariable("id") int id
    ) {
        model.addAttribute("product", productService.findById(id));
        return "delete";
    }

    @PostMapping("/product/remove")
    public String remove(
            Product product,
            RedirectAttributes redirectAttributes
    ) {
        productService.remove(product.getId());
        redirectAttributes.addFlashAttribute("success", "Remove product successfully");
        return "redirect:/";
    }

    @GetMapping("/product/{id}/view")
    public String view(
            @PathVariable ("id") int id,
            Model model
    ){
        model.addAttribute("product", productService.findById(id));
        return "view";
    }

}
