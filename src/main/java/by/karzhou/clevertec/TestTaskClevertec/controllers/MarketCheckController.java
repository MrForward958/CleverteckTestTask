package by.karzhou.clevertec.TestTaskClevertec.controllers;

import by.karzhou.clevertec.TestTaskClevertec.models.MarketCheck;
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


import static java.lang.System.currentTimeMillis;

@Controller
@RequestMapping("/marketCheck")
public class MarketCheckController {

    private final MarketCheckService marketCheckService;

    public MarketCheckController(MarketCheckService marketCheckService) {
        this.marketCheckService = marketCheckService;
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
        System.out.println("перед иф");
        if(bindingResult.hasErrors()) {
            return "marketCheck/new";
        }
        marketCheckService.save(marketCheck);
        return "redirect:/marketCheck";
    }


    @ExceptionHandler()
    private ResponseEntity<ErrorResponse> handlerException(MarketCheckNotFoundException e){
        ErrorResponse errorResponse = new ErrorResponse("Чек с таким id не найден",
                new Date(currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
