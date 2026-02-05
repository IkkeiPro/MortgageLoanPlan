package org.example.controller;

import jakarta.validation.Valid;
import org.example.model.SimulationRequest;
import org.example.model.SimulationResult;
import org.example.service.FinanceSimulationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SimulationController {

    private final FinanceSimulationService simulationService;

    public SimulationController(FinanceSimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @GetMapping("/")
    public String index(Model model) {
        SimulationRequest request = SimulationRequest.defaultValues();
        SimulationResult result = simulationService.simulate(request);
        model.addAttribute("request", request);
        model.addAttribute("result", result);
        return "index";
    }

    @PostMapping("/simulate")
    public String simulate(@Valid @ModelAttribute("request") SimulationRequest request,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("result", null);
            return "index";
        }

        SimulationResult result = simulationService.simulate(request);
        model.addAttribute("result", result);
        return "index";
    }
}
