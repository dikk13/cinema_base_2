package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.MovieResponseDtoDao;
import com.kata.cinema.base.dto.MovieResponseDto;
import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.service.abstracts.MovieResponseDtoService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieResponseDtoServiceImpl extends PaginationDtoServiceImpl <MovieResponseDto> implements MovieResponseDtoService {

    private final MovieResponseDtoDao movieResponseDtoDao;
    public MovieResponseDtoServiceImpl(MovieResponseDtoDao movieResponseDtoDao) {
        super(movieResponseDtoDao);
        this.movieResponseDtoDao = movieResponseDtoDao;
    }

    @Override
    public PageDto<MovieResponseDto> getPageDtoWithParameters(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        PageDto <MovieResponseDto> pageDto = new PageDto<>();
        List<MovieResponseDto> movieResponseDtoList = movieResponseDtoDao.getItemsDto(currentPage, itemsOnPage, parameters);

        if (movieResponseDtoList.size() != 0) {

            Map<Long, MovieResponseDto> movieResponseDtoMap = new LinkedHashMap<>();
            String idQueue = movieResponseDtoList.stream().map(MovieResponseDto::getId).toList().toString()
                    .replace("[", "(").replace("]",")");

            for (MovieResponseDto movieDto: movieResponseDtoList) {
                movieResponseDtoMap.put(movieDto.getId(), movieDto);
            }

            Map<Long, List<String>> genresMap = movieResponseDtoDao.getGenresMap(idQueue);
            Map<Long, List<String>> producersMap = movieResponseDtoDao.getProducersMap(idQueue);
            Map<Long, List<String>> actorsMap = movieResponseDtoDao.getActorsMap(idQueue);

            for (Long item : movieResponseDtoMap.keySet()) {
                movieResponseDtoMap.get(item).setGenres((genresMap.get(item) != null) ? genresMap.get(item).toString().replaceAll("[\\[\\]]", "") : "");
                movieResponseDtoMap.get(item).setDirectors((producersMap.get(item) != null) ? producersMap.get(item).toString().replaceAll("[\\[\\]]", "") : "");
                movieResponseDtoMap.get(item).setRoles((actorsMap.get(item) != null) ? actorsMap.get(item).toString().replaceAll("[\\[\\]]","") : "");
            }

            pageDto.setCount(movieResponseDtoDao.getResultTotal(parameters));
            pageDto.setEntities(movieResponseDtoList);
            return pageDto;
        }
        pageDto.setCount(0L);
        pageDto.setEntities(null);
        return pageDto;
    }
}