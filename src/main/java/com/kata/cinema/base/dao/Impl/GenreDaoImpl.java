package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.GenreDao;
import com.kata.cinema.base.models.Genre;
import com.kata.cinema.base.models.dto.GenreResponseDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class GenreDaoImpl extends AbstractDaoImpl <Long,Genre> implements GenreDao {

    @Override
    public List<GenreResponseDto> getAllGenreResponseDto() {
        return entityManager.
                createQuery("select new com.kata.cinema.base." +
                        "models.dto.GenreResponseDto(g.id, g.name)" +
                        " from Genre g ", GenreResponseDto.class).getResultList();
    }


}
