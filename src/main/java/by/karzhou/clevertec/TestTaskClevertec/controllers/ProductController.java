package by.karzhou.clevertec.TestTaskClevertec.controllers;

import by.karzhou.clevertec.TestTaskClevertec.until.ErrorResponse;
import by.karzhou.clevertec.TestTaskClevertec.until.ProductNotFoundException;
import by.karzhou.clevertec.TestTaskClevertec.models.Product;
import by.karzhou.clevertec.TestTaskClevertec.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static java.lang.System.currentTimeMillis;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("products", productService.findAll());
        return "product/list";
    }

    @GetMapping("/{id}")
    public String showProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("product",productService.findById(id));
        return "product/showProduct";
    }

    @GetMapping("/new")
    public String createProduct(@ModelAttribute("product")Product product){
        return "product/new";
    }

    @PostMapping()
    public String saveProduct(@ModelAttribute("product") @Valid Product product,
                              BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "product/new";
        productService.save(product);
        return "redirect:/product";
    }

    @GetMapping("/{id}/edit")
    public String editProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("product",productService.findById(id));
        return "/product/edit";
    }

    @PatchMapping("/{id}")
    public String updateProduct(@PathVariable("id") int id, @ModelAttribute("product") @Valid Product product,
                                BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "product/edit";
        productService.update(product,id);
        return "redirect:/product";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        productService.delete(id);
        return "redirect:/product";
    }

    @ExceptionHandler()
    private ResponseEntity<ErrorResponse> handlerException(ProductNotFoundException e){
        ErrorResponse errorResponse = new ErrorResponse("Продукт с таким id не найден",
                new Date(currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


}
