package DAWI_ABANTO_GARCIA_RONALD.cl2.controller;

import DAWI_ABANTO_GARCIA_RONALD.cl2.dto.FilmCreateDto;
import DAWI_ABANTO_GARCIA_RONALD.cl2.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import DAWI_ABANTO_GARCIA_RONALD.cl2.dto.FilmDetailDto;
import DAWI_ABANTO_GARCIA_RONALD.cl2.dto.FilmDto;
import DAWI_ABANTO_GARCIA_RONALD.cl2.service.MaintenanceService;


import java.util.List;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {

    @Autowired
    MaintenanceService maintenanceService;

//
    @Autowired
    private LanguageRepository languageRepository;
     //

    @GetMapping("/start")
    public String start(Model model) {


        List<FilmDto> films = maintenanceService.findAllFilms();
        model.addAttribute("films", films);
        return "maintenance";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        FilmDetailDto filmDetailDto = maintenanceService.findFilmById(id);
        model.addAttribute("film", filmDetailDto);
        return "maintenance_detail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        FilmDetailDto filmDetailDto = maintenanceService.findFilmById(id);
        model.addAttribute("film", filmDetailDto);
        return "maintenance_edit";
    }

    @PostMapping("/edit-confirm")
    public String editConfirm(@ModelAttribute FilmDetailDto filmDetailDto, Model model) {
        maintenanceService.updateFilm(filmDetailDto);
        return "redirect:/maintenance/start";
    }

    @PostMapping("/remove")
    public String remove(@PathVariable Integer id , Model model) {
        maintenanceService.deleteFilmById(id);
        return "redirect:/maintenance/start";
    }


    @GetMapping("/new")
    public String newFilmForm(Model model) {
        FilmCreateDto filmCreateDto = new FilmCreateDto(null, null, null, null, null, null, null, null, null, null);
        model.addAttribute("film", filmCreateDto);
        model.addAttribute("languages", languageRepository.findAll());
        return "maintenance_new";
    }

    // Guardar un nuevo film
    @PostMapping("/save")
    public String saveNewFilm(@ModelAttribute FilmCreateDto filmCreateDto) {
        maintenanceService.saveFilm(filmCreateDto);
        return "redirect:/maintenance/start";
    }




}
