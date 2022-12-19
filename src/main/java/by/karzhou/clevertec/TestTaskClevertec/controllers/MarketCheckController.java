package by.karzhou.clevertec.TestTaskClevertec.controllers;

import by.karzhou.clevertec.TestTaskClevertec.until.MarketCheckNotFoundException;
import by.karzhou.clevertec.TestTaskClevertec.until.ErrorResponse;
import by.karzhou.clevertec.TestTaskClevertec.services.MarketCheckService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "marketCheck/showCheck";
    }


    @ExceptionHandler()
    private ResponseEntity<ErrorResponse> handlerException(MarketCheckNotFoundException e){
        ErrorResponse errorResponse = new ErrorResponse("Чек с таким id не найден",
                new Date(currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
