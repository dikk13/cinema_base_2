package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.ProductionStudioDao;
import com.kata.cinema.base.models.ProductionStudio;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductionStudioDaoImpl extends AbstractDaoImpl<Long, ProductionStudio> implements ProductionStudioDao {

    @Override
    public Optional<ProductionStudio> getById(Long id) {
        return Optional.of(entityManager.createQuery("select ps from ProductionStudio ps " +
                        "where ps.id =: id", ProductionStudio.class)
                .setParameter("id", id)
                .getSingleResult());
    }
}
