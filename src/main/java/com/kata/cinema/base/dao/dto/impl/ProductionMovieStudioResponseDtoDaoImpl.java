package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.ProductionMovieStudioResponseDtoDao;
import com.kata.cinema.base.dto.response.ProductionMovieStudioResponseDto;
import com.kata.cinema.base.models.enums.ProductionSort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Repository
public class ProductionMovieStudioResponseDtoDaoImpl implements ProductionMovieStudioResponseDtoDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProductionMovieStudioResponseDto> getItemsDto(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        return entityManager.createQuery("select new com.kata.cinema.base.dto.response.ProductionMovieStudioResponseDto(" +
                                "ps.id, ps.name, ps.performance.id, ps.performance.name) " +
                                "from ProductionStudio ps where ps.name like :filterPattern" +
                                sortType((ProductionSort) parameters.get("productionSort")),
                        ProductionMovieStudioResponseDto.class)
                .setParameter("filterPattern", "%" + parameters.get("filterPattern") + "%")
                .setFirstResult((currentPage - 1) * itemsOnPage)
                .setMaxResults(itemsOnPage)
                .getResultList();
    }

    @Override
    public Long getResultTotal(Map<String, Object> parameters) {
        return entityManager.createQuery("select count(ps) from ProductionStudio ps " +
                        "where ps.name like :filterPattern", Long.class)
                .setParameter("filterPattern", "%" + parameters.get("filterPattern") + "%")
                .getSingleResult();
    }
    private String sortType(ProductionSort productionSort) {
        return switch (productionSort) {
            case NAME_ACS -> " order by ps.name asc";
            case NAME_DESC -> " order by ps.name desc";
            case DATE_ACS -> " order by ps.dateFoundation asc";
            case DATE_DESC -> " order by ps.dateFoundation desc";
        };
    }
}
