package com.yaksha.training.laptopstore.controller;

import com.yaksha.training.laptopstore.entity.Laptop;
import com.yaksha.training.laptopstore.service.LaptopService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public String listLaptops(@PageableDefault(size = 5) Pageable pageable, Model model) {
        Page<Laptop> laptops = laptopService.getLaptops(pageable);
        model.addAttribute("laptops", laptops.getContent());
        model.addAttribute("theSearchName", "");
        model.addAttribute("totalPage", laptops.getTotalPages());
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("sortBy", pageable.getSort().get().count() != 0 ?
                pageable.getSort().get().findFirst().get().getProperty() + "," + pageable.getSort().get().findFirst().get().getDirection() : "");
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

    @RequestMapping("/search")
    public String searchLaptops(@RequestParam(value = "theSearchName", required = false) String theSearchName,
                                @PageableDefault(size = 5) Pageable pageable,
                                Model theModel) {

        Page<Laptop> theLaptops = laptopService.searchLaptops(theSearchName, pageable);
        theModel.addAttribute("laptops", theLaptops.getContent());
        theModel.addAttribute("theSearchName", theSearchName != null ? theSearchName : "");
        theModel.addAttribute("totalPage", theLaptops.getTotalPages());
        theModel.addAttribute("page", pageable.getPageNumber());
        theModel.addAttribute("sortBy", pageable.getSort().get().count() != 0 ?
                pageable.getSort().get().findFirst().get().getProperty() + "," + pageable.getSort().get().findFirst().get().getDirection() : "");

        return "list-laptops";
    }

    @GetMapping("/updateBestSeller")
    public String updateBestSeller(@RequestParam("id") Long id) {
        laptopService.updateBestSeller(id);
        return "redirect:/laptop/list";
    }
}
