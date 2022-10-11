package com.kata.cinema.base.webapp.controllers.unauthorized;


import com.kata.cinema.base.dto.*;
import com.kata.cinema.base.exception.MovieIdNotFoundException;
import com.kata.cinema.base.mappers.MoviePersonMapper;
import com.kata.cinema.base.models.*;
import com.kata.cinema.base.models.enums.*;
import com.kata.cinema.base.service.abstracts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;


@RestController
public class MovieRestController {

    private final ReviewResponseDtoService responseDtoService;

    @Autowired
    public MovieRestController(ReviewResponseDtoService responseDtoService) {
        this.responseDtoService = responseDtoService;
    }

    @GetMapping("/api/movies/{id}/reviews/page/{pageNumber}")
    public PageDto<ReviewResponseDto> getReview(@PathVariable("id") Long movieId, @PathVariable("pageNumber") Integer pageNumber, @RequestParam(value = "itemsOnPage", required = false, defaultValue = "10") Integer itemsOnPage, @RequestParam(value = "typeReview", required = false) TypeReview typeReview, @RequestParam(value = "reviewSortType", required = false, defaultValue = "DATE_ASC") ReviewSortType reviewSortType) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("movieId", movieId);
        parameters.put("typeReview", typeReview);
        parameters.put("reviewSortType", reviewSortType);
        PageDto<ReviewResponseDto> reviewResponseDtoPageDto = responseDtoService.getPageDtoWithParameters(pageNumber, itemsOnPage, parameters);
        return reviewResponseDtoPageDto;
    }

    ///////////////////////////////////////////////////////////////////////////
    @Autowired
    private MovieService movieService;

    @Autowired
    private MoviePersonMapper moviePersonMapper;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private UserService userService;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MoviePersonService moviePersonService;


    @GetMapping("/api/movies/{id}")
    public MovieViewResponseDto getMovie(@PathVariable("id") Long movieId) {

//        Movie movie = movieService.getById(movieId).orElseThrow(() -> new MovieIdNotFoundException("Фильм не найден"));

        Movie movie = (Movie) entityManager.createQuery(
                        "select m from Movie m " +
                                "join fetch m.moviePerson mp " +
                                "join fetch mp.profession p " +
                                "join fetch mp.person per " +
                                "where m.id =: movieId")
                .setParameter("movieId", movieId)
                .getSingleResult();


//
//        List<Object> movie2 = entityManager.createQuery(
//                        "select m.moviePerson from Movie m " +
//                                "join fetch m.moviePerson mp " +
//                                "join fetch mp.profession p " +
////                                "join fetch mp.person per " +
//                                "where m.id =: movieId group by mp.profession.id")
//                .setParameter("movieId", movieId)
//                .getResultList();

        String previewUrl = (String) entityManager.createQuery("select c.content_url from Content c where c.movie.id =: id and c.type = 'PREVIEW'")
                .setParameter("id", movieId)
                .getSingleResult();

        List<Score> scoreList = entityManager.createQuery("select s from Score s where s.movie.id =: id")
                .setParameter("id", movieId)
                .getResultList();


        List<Genre> genreList = entityManager.createQuery("select m.genres from Movie m where m.id =: id")
                .setParameter("id", movieId)
                .getResultList();

        String genres = genreList.stream().map(g -> g.getName() + ", ").collect(Collectors.joining());

        genres = genres.substring(0, genres.length() - 2);

        List<MoviePerson> moviePersonList = movie.getMoviePerson();

        Map<Profession, List<MoviePerson>> mmp = new HashMap<>();


        for (MoviePerson mp : moviePersonList) {
            mmp.put(mp.getProfession(), new ArrayList<>());
        }

        for (Profession profession : mmp.keySet()) {
            for (MoviePerson mp : moviePersonList) {
                if (profession.equals(mp.getProfession()) &&
                        mp.getType() == CharacterType.NO_CHARACTER_MOVIE) {
                    mmp.get(profession).add(mp);
                }
            }
        }

        List<CastResponseDto> casts = new ArrayList<>();

        for (Profession profession : mmp.keySet()) {
            CastResponseDto cast = new CastResponseDto(
                    movieId,
                    profession.getId(),
                    profession.getName(),
                    moviePersonMapper.moviePersonListToMoviePersonResponseDtoList(mmp.get(profession)));
            casts.add(cast);
        }

        Integer myScore;

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Object auth = new Object();

        User user = null;
        if (auth == null) {
            myScore = null;
        } else {
//            Optional<User> userOptional = userService.getByEmail("email@mail.com");

            Optional<User> userOptional = userService.getById(100L);
            if (userOptional.isEmpty()) myScore = null;
            else {
                user = userOptional.get();
                User finalUser = user;
                myScore = scoreList
                        .stream()
                        .filter(score -> score
                                .getUser()
                                .getId()
                                .equals(finalUser.getId()))
                        .toList()
                        .get(0)
                        .getScore();
            }
        }


        MovieViewResponseDto movieViewResponseDto = new MovieViewResponseDto();
        movieViewResponseDto.setId(movieId);
        movieViewResponseDto.setName(movie.getName());
        movieViewResponseDto.setOriginalName(movie.getOriginalName());
        movieViewResponseDto.setCountries(movie.getCountries());
        movieViewResponseDto.setDateRelease(movie.getDateRelease());
        movieViewResponseDto.setRars(movie.getRars());
        movieViewResponseDto.setMpaa(movie.getMpaa());
        movieViewResponseDto.setDescription(movie.getDescription());
        movieViewResponseDto.setPreviewUrl(previewUrl);
        movieViewResponseDto.setGenres(genres);
        movieViewResponseDto.setScore(scoreList.stream().mapToInt(s -> s.getScore()).average().getAsDouble());
        movieViewResponseDto.setCountScore(scoreList.size());
        movieViewResponseDto.setMyScore(myScore);
        movieViewResponseDto.setCasts(casts);

        return movieViewResponseDto;

    }
}
