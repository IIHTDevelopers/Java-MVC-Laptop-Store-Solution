package com.yaksha.training.laptopstore.controller;

import com.yaksha.training.laptopstore.entity.Laptop;
import com.yaksha.training.laptopstore.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = {"/laptop", "/"})
public class LaptopController {

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @Autowired
    private LaptopService laptopService;

    @GetMapping(value = {"/list", "/"})
    public String listLaptops(Model model) {
        List<Laptop> laptops = laptopService.getLaptops();
        model.addAttribute("laptops", laptops);
        return "list-laptops";
    }

    @GetMapping("/addLaptopForm")
    public String showFormForAdd(Model model) {
        Laptop laptop = new Laptop();
        model.addAttribute("laptop", laptop);
        return "add-laptop-form";
    }

    @PostMapping("/saveLaptop")
    public String saveLaptop(@Valid @ModelAttribute("laptop") Laptop laptop, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            if (laptop.getId() != null) {
                return "update-laptop-form";
            }
            return "add-laptop-form";
        } else {
            laptopService.saveLaptop(laptop);
            return "redirect:/laptop/list";
        }
    }

    @GetMapping("/updateLaptopForm")
    public String showFormForUpdate(@RequestParam("laptopId") Long id, Model model) {
        Laptop laptop = laptopService.getLaptop(id);
        model.addAttribute("laptop", laptop);
        return "update-laptop-form";
    }

    @GetMapping("/delete")
    public String deleteLaptop(@RequestParam("laptopId") Long id) {
        laptopService.deleteLaptop(id);
        return "redirect:/laptop/list";
    }

    @PostMapping("/search")
    public String searchLaptops(@RequestParam("theSearchName") String theSearchName,
                                Model theModel) {

        List<Laptop> theLaptops = laptopService.searchLaptops(theSearchName);
        theModel.addAttribute("laptops", theLaptops);
        return "list-laptops";
    }
}
