package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.AvailableOnlineMovieDAO;
import com.kata.cinema.base.models.AvailableOnlineMovie;
import com.kata.cinema.base.models.FolderMovie;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AvailableOnlineMovieDAOImpl extends AbstractDaoImpl<Long, AvailableOnlineMovie> implements AvailableOnlineMovieDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<AvailableOnlineMovie> getAvailableMovie() {
        return entityManager.createQuery("SELECT aom FROM AvailableOnlineMovie aom",
                AvailableOnlineMovie.class).getResultList();

    }
}
//   public List<GenreResponseDto> getAllGenreResponseDto() {
//        return entityManager.
//                createQuery("select new com.kata.cinema.base." +
//                        "dto.response.GenreResponseDto(g.id, g.name)" +
//                        " from Genre g ", GenreResponseDto.class).getResultList();
