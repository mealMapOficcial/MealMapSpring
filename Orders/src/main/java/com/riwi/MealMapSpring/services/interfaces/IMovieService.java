package com.riwi.MealMapSpring.services.interfaces;

import com.riwi.RiwiMovies.dtos.request.Movies.MovieWithoutRating;
import com.riwi.RiwiMovies.dtos.response.Movies.MovieWithoutPrice;
import com.riwi.RiwiMovies.entities.Movie;
import com.riwi.RiwiMovies.services.CRUD.Create;
import com.riwi.RiwiMovies.services.CRUD.ReadById;

public interface IMovieService extends
        ReadById<MovieWithoutPrice, Long>,
        Create<MovieWithoutRating, Movie> {
}
