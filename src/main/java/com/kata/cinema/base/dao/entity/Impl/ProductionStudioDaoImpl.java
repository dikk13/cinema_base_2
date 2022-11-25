package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.ProductionStudioDao;
import com.kata.cinema.base.models.ProductionStudio;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.kata.cinema.base.dao.util.JpaResultHelper.jpaResultHelper;

@Repository
public class ProductionStudioDaoImpl extends AbstractDaoImpl<Long, ProductionStudio> implements ProductionStudioDao {

    @Override
    public Optional<ProductionStudio> getById(Long id) {
        return jpaResultHelper(entityManager.createQuery("select ps from ProductionStudio ps " +
                        "where ps.id =: id", ProductionStudio.class)
                .setParameter("id", id));
    }
}
