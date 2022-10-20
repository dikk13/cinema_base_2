package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.entity.Impl.GenreDaoImpl;
import com.kata.cinema.base.dao.entity.Impl.PersonDaoImpl;
import com.kata.cinema.base.dao.dto.MovieResponseDtoDao;
import com.kata.cinema.base.dto.response.MovieResponseDto;
import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.service.dto.MovieResponseDtoService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
//TODO создать подпакет page и перенести все класс связанные с пагинации туда, для дао также актуально
public class MovieResponseDtoServiceImpl extends PaginationDtoServiceImpl <MovieResponseDto> implements MovieResponseDtoService {

    private final MovieResponseDtoDao movieResponseDtoDao;
    private final GenreDaoImpl genreDaoImpl;
    private final PersonDaoImpl personDaoImpl;

    public MovieResponseDtoServiceImpl(MovieResponseDtoDao movieResponseDtoDao, GenreDaoImpl genreDaoImpl, PersonDaoImpl personDaoImpl) {
        super(movieResponseDtoDao);
        this.movieResponseDtoDao = movieResponseDtoDao;
        this.genreDaoImpl = genreDaoImpl;
        this.personDaoImpl = personDaoImpl;
    }

    @Override
    public PageDto<MovieResponseDto> getPageDtoWithParameters(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        PageDto <MovieResponseDto> pageDto = new PageDto<>();
        List<MovieResponseDto> movieResponseDtoList = movieResponseDtoDao.getItemsDto(currentPage, itemsOnPage, parameters);
        System.out.println("CATCH #1");
        if (movieResponseDtoList.size() != 0) {

            Map<Long, MovieResponseDto> movieResponseDtoMap = new LinkedHashMap<>();
            String idQueue = movieResponseDtoList.stream().map(MovieResponseDto::getId).toList().toString()
                    .replace("[", "(").replace("]",")");

            for (MovieResponseDto movieDto: movieResponseDtoList) {
                movieResponseDtoMap.put(movieDto.getId(), movieDto);
            }

            Map<Long, List<String>> genresMap = genreDaoImpl.getGenresMap(idQueue);
            Map<Long, List<String>> producersMap = personDaoImpl.getProducersMap(idQueue);
            Map<Long, List<String>> actorsMap = personDaoImpl.getActorsMap(idQueue);

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