package pt.ipb.phenolic.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.ipb.phenolic.models.Food;
import pt.ipb.phenolic.repos.FoodRepository;

import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController {

    private final FoodRepository foodRepo;

    public FoodController(FoodRepository foodRepo) {
        this.foodRepo = foodRepo;
    }

    @GetMapping
    public List<Food> index() {
        return foodRepo.findAll();
    }
}
