package by.karzhou.clevertec.TestTaskClevertec.controllers;

import by.karzhou.clevertec.TestTaskClevertec.models.MarketCheck;
import by.karzhou.clevertec.TestTaskClevertec.models.Product;
import by.karzhou.clevertec.TestTaskClevertec.models.Purchases;
import by.karzhou.clevertec.TestTaskClevertec.services.ProductService;
import by.karzhou.clevertec.TestTaskClevertec.services.PurchasesService;
import by.karzhou.clevertec.TestTaskClevertec.until.MarketCheckNotFoundException;
import by.karzhou.clevertec.TestTaskClevertec.until.ErrorResponse;
import by.karzhou.clevertec.TestTaskClevertec.services.MarketCheckService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


import static java.lang.System.currentTimeMillis;

@Controller
@RequestMapping("/marketCheck")
public class MarketCheckController {

    private final MarketCheckService marketCheckService;
    private final ProductService productService;
    private final PurchasesService purchasesService;

    public MarketCheckController(MarketCheckService marketCheckService,ProductService productService, PurchasesService purchasesService) {
        this.marketCheckService = marketCheckService;
        this.productService = productService;
        this.purchasesService = purchasesService;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("marketCheckList", marketCheckService.findAll());
        return "marketCheck/list";
    }

    @GetMapping("/{id}")
    public String showCheck(@PathVariable("id") int id, Model model){
        model.addAttribute("marketCheck", marketCheckService.findById(id));
        model.addAttribute("sum", marketCheckService.countSum(id));
        model.addAttribute("purchase", new Purchases());
        return "marketCheck/showCheck";
    }


    @GetMapping("/new")
    public String createMarketCheck(@ModelAttribute("marketCheck") MarketCheck marketCheck){
        System.out.println("/new");
        return "marketCheck/new";
    }
    @PostMapping()
    public String saveMarketCheck(@ModelAttribute("marketCheck") @Valid MarketCheck marketCheck,
                                  BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "marketCheck/new";
        }
        marketCheckService.save(marketCheck);
        return "redirect:/marketCheck";
    }

    @PatchMapping("/{id}")
    public String addPurchases(Model model, @PathVariable ("id") int marketCheckId, @ModelAttribute ("purchases") @Valid Purchases purchases,
                               BindingResult bindingResult){
        MarketCheck marketCheck = marketCheckService.findById(marketCheckId);
        if(bindingResult.hasErrors()) {
            model.addAttribute("marketCheck",marketCheck);
            model.addAttribute("sum", marketCheckService.countSum(marketCheck.getId()));
            model.addAttribute("purchase", new Purchases());
            return "marketCheck/showCheck";
        }
        purchasesService.save(purchases,marketCheck);
        return "redirect:/marketCheck/" + marketCheckId;
    }

    @PatchMapping("/close/{id}")
    public String closeMarketCheck(@PathVariable("id") int id){
        marketCheckService.closeCheck(id);
        return "redirect:/marketCheck/" + id;
    }
    @ExceptionHandler()
    private ResponseEntity<ErrorResponse> handlerException(MarketCheckNotFoundException e){
        ErrorResponse errorResponse = new ErrorResponse("Чек с таким id не найден",
                new Date(currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ModelAttribute("productList")
    public List<Product> getProductList(){
        return productService.findAll();
    }
}
