package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dto.CastResponseDto;
import com.kata.cinema.base.dto.MovieViewResponseDto;
import com.kata.cinema.base.exception.MovieIdNotFoundException;
import com.kata.cinema.base.mappers.MoviePersonMapper;
import com.kata.cinema.base.models.*;
import com.kata.cinema.base.models.enums.CharacterType;
import com.kata.cinema.base.models.enums.ContentType;
import com.kata.cinema.base.service.abstracts.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieViewResponseDtoServiceImpl implements MovieViewResponseDtoService {

    private final MovieService movieService;
    private final ContentService contentService;
    private final ScoreService scoreService;
    private final GenreService genreService;
    private final MoviePersonMapper moviePersonMapper;
    private final UserService userService;

    public MovieViewResponseDtoServiceImpl(MovieService movieService, ContentService contentService,
                                           ScoreService scoreService, GenreService genreService,
                                           MoviePersonMapper moviePersonMapper, UserService userService) {
        this.movieService = movieService;
        this.contentService = contentService;
        this.scoreService = scoreService;
        this.genreService = genreService;
        this.moviePersonMapper = moviePersonMapper;
        this.userService = userService;
    }

    @Override
    public MovieViewResponseDto getMovieViewResponseDtoByMovieId(Long movieId) {
        Movie movie = movieService.getMovieWithMoviePersonsWithProfessionsAndPersonsByMovieId(movieId)
                .orElseThrow(() -> new MovieIdNotFoundException("Фильм не найден"));

        String previewUrl = contentService.getContentByMovieIdAndContentType(movieId, ContentType.PREVIEW);

        List<Score> scoreList = scoreService.getScoreListByMovieId(movieId);

        String genres = genreService.getGenresOfMovieByMovieId(movieId);

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

        Integer myScore = null;
//Нет проверки аутентификации
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            String username = auth.getName();
            try {
//            Optional<User> userOptional = userService.getById(100L);
                User user = userService.getByEmail(username).get();
                myScore = scoreList
                        .stream()
                        .filter(score -> score
                                .getUser()
                                .getId()
                                .equals(user.getId()))
                        .toList()
                        .get(0)
                        .getScore();
            } catch (UsernameNotFoundException ex) {
                System.out.println(ex.getMessage());
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
        movieViewResponseDto.setScore(scoreList.stream().mapToInt(Score::getScore).average().getAsDouble());
        movieViewResponseDto.setCountScore(scoreList.size());
        movieViewResponseDto.setMyScore(myScore);
        movieViewResponseDto.setCasts(casts);

        return movieViewResponseDto;
    }
}
