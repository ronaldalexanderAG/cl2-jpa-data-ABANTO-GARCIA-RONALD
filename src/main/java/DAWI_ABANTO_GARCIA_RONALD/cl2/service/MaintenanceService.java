package DAWI_ABANTO_GARCIA_RONALD.cl2.service;

import DAWI_ABANTO_GARCIA_RONALD.cl2.dto.FilmCreateDto;
import DAWI_ABANTO_GARCIA_RONALD.cl2.dto.FilmDetailDto;
import DAWI_ABANTO_GARCIA_RONALD.cl2.dto.FilmDto;



import java.util.List;

public interface MaintenanceService {
    List<FilmDto> findAllFilms();
    FilmDetailDto findFilmById(int id);

    Boolean updateFilm(FilmDetailDto filmDetailDto);
    void deleteFilmById(Integer id);
    void saveFilm(FilmCreateDto filmCreateDto);
}
