package DAWI_ABANTO_GARCIA_RONALD.cl2.service.impl;

import DAWI_ABANTO_GARCIA_RONALD.cl2.dto.FilmCreateDto;
import DAWI_ABANTO_GARCIA_RONALD.cl2.dto.FilmDetailDto;
import DAWI_ABANTO_GARCIA_RONALD.cl2.dto.FilmDto;
import DAWI_ABANTO_GARCIA_RONALD.cl2.entity.Film;
import DAWI_ABANTO_GARCIA_RONALD.cl2.entity.Language;
import DAWI_ABANTO_GARCIA_RONALD.cl2.repository.FilmRepository;
import DAWI_ABANTO_GARCIA_RONALD.cl2.repository.LanguageRepository;
import DAWI_ABANTO_GARCIA_RONALD.cl2.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
    @Autowired
    FilmRepository filmRepository;
    //
    @Autowired
    private LanguageRepository languageRepository;
    //


    @Override
    public List<FilmDto> findAllFilms() {
        List<FilmDto> films=new ArrayList<FilmDto>();
        Iterable<Film> iterable =filmRepository.findAll();
        iterable.forEach(film -> {
            FilmDto filmDto = new FilmDto(
                    film.getFilmId(),
                    film.getTitle(),
                    film.getLanguage().getName(),
                    film.getRentalDuration(),
                    film.getRentalRate());
            films.add(filmDto);
        });
            return films;
    }

    @Override
    public FilmDetailDto findFilmById(int id) {
        Optional<Film> optional = filmRepository.findById(id);
        return optional.map(film -> new FilmDetailDto(film.getFilmId(),
                film.getTitle(),
                film.getDescription(),
                film.getReleaseYear(),
                film.getLanguage().getLanguageId(),
                film.getLanguage().getName(),
                film.getRentalDuration(),
                film.getRentalRate(),
                film.getLength(),
                film.getReplacementCost(),
                film.getRating(),
                film.getSpecialFeatures(),
                film.getLastUpdate())
        ).orElse(null);
    }

    @Override
    public Boolean updateFilm(FilmDetailDto filmDetailDto) {
        Optional<Film> optional = filmRepository.findById(filmDetailDto.filmId());
        return optional.map(
            film -> {
                film.setTitle(filmDetailDto.title());
                film.setDescription(filmDetailDto.description());
                film.setReleaseYear(filmDetailDto.releaseYear());
                film.setRentalDuration(filmDetailDto.rentalDuration());
                film.setRentalRate(filmDetailDto.rentalRate());
                film.setLength(filmDetailDto.length());
                film.setReplacementCost(filmDetailDto.replacementCost());
                film.setRating(filmDetailDto.rating());
                film.setSpecialFeatures(filmDetailDto.specialFeatures());
                film.setLastUpdate(new Date());
                filmRepository.save(film);
                return true;
            }
        ).orElse(null);
    }
    ///
    @Override
    public void deleteFilmById(Integer id) {
        if (!filmRepository.existsById(id)) {
            throw new RuntimeException("El film con ID " + id + " no existe.");
        }
        filmRepository.deleteById(id);
    }



    @Override
    public void saveFilm(FilmCreateDto filmCreateDto) {
        // Crear un nuevo objeto Film
        Film film = new Film();
        film.setTitle(filmCreateDto.title());
        film.setDescription(filmCreateDto.description());
        film.setReleaseYear(filmCreateDto.releaseYear());
        film.setRentalDuration(filmCreateDto.rentalDuration());
        film.setRentalRate(filmCreateDto.rentalRate());
        film.setLength(filmCreateDto.length());
        film.setReplacementCost(filmCreateDto.replacementCost());
        film.setRating(filmCreateDto.rating());
        film.setSpecialFeatures(filmCreateDto.specialFeatures());

        // Buscar el idioma seleccionado por ID
        Language language = languageRepository.findById(filmCreateDto.languageId())
                .orElseThrow(() -> new RuntimeException("Idioma no encontrado"));
        film.setLanguage(language);

        film.setLastUpdate(new Date());
        // Guardar el film en la base de datos
        filmRepository.save(film);
    }




}
