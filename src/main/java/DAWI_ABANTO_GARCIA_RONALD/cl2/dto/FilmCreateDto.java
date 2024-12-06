package DAWI_ABANTO_GARCIA_RONALD.cl2.dto;

public record FilmCreateDto(String title,
                            String description,
                            Integer releaseYear,
                            Integer languageId, // ID del idioma seleccionado
                            Integer rentalDuration,
                            Double rentalRate,
                            Integer length,
                            Double replacementCost,
                            String rating,
                            String specialFeatures) {
}
