package com.riwi.MealMapSpring.services.impl;

import com.riwi.RiwiMovies.dtos.request.Movies.MovieWithoutRating;
import com.riwi.RiwiMovies.dtos.response.Movies.MovieWithoutPrice;
import com.riwi.RiwiMovies.entities.Movie;
import com.riwi.RiwiMovies.repositories.interfaces.MovieRepository;
import com.riwi.RiwiMovies.services.interfaces.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieImpl implements IMovieService {

    @Autowired
    MovieRepository movieRepository;

    @Override
    public MovieWithoutPrice readById(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow();

        MovieWithoutPrice movieWithoutPrice = new MovieWithoutPrice();

        movieWithoutPrice.setId(movie.getId());
        movieWithoutPrice.setDuration(movie.getDuration());
        movieWithoutPrice.setGenre(movie.getGenre());
        movieWithoutPrice.setRating(movie.getRating());
        movieWithoutPrice.setTitle(movie.getTitle());

        return movieWithoutPrice;
    }

    @Override
    public Movie create(MovieWithoutRating movieDTO) {

        Movie request = Movie.builder()
                .price(movieDTO.getPrice())
                .duration(movieDTO.getDuration())
                .genre(movieDTO.getGenre())
                .title(movieDTO.getTitle())
                .build();

        return movieRepository.save(request);
    }
}
