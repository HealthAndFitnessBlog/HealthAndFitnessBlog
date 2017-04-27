package HealthAndFitnessBlog.controller;

import HealthAndFitnessBlog.entity.Calculator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.print.attribute.IntegerSyntax;


@Controller
public class CalculatorController {
    @GetMapping("/calculator")
    public String index(Model model) {
        model.addAttribute("Gender", "1");
        model.addAttribute("Activity", "1");
        model.addAttribute("view", "calculator/calculator");
        return "base-layout";
    }

    @PostMapping("/calculator")
    public String index(
            @RequestParam String Age,
            @RequestParam String Gender,
            @RequestParam String Weight,
            @RequestParam String Height,
            @RequestParam String Activity,
            Model model
    )
    {
        int years;
        double kg;
        double cm;

        try {

            years = Integer.parseInt(Age);
        }
        catch (NumberFormatException ex){
            years = 0;
        }

        try {

            kg = Double.parseDouble(Weight);
        }
        catch (NumberFormatException ex){
            kg = 0;

        }

        try {

            cm = Double.parseDouble(Height);
        }
        catch (NumberFormatException ex){

            cm = 0;
        }

        Calculator calculator = new Calculator(

                years,
                Gender,
                kg,
                cm,
                Activity
        );

        int calories = calculator.calculateCal();


        model.addAttribute("Age", years);
        model.addAttribute("Gender",Gender);
        model.addAttribute("Activity",Activity);
        model.addAttribute("Weight", kg);
        model.addAttribute("Height", cm);
        model.addAttribute("calories", calories);


        model.addAttribute("view", "calculator/calculator");
        return "base-layout";

    }
}
