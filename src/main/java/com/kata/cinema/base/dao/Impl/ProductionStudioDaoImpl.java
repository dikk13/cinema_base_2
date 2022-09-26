package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.ProductionStudioDao;
import com.kata.cinema.base.models.ProductionStudio;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductionStudioDaoImpl extends AbstractDaoImpl<Long, ProductionStudio> implements ProductionStudioDao {

    @Override
    public void deleteById(Long deletableId) {
        entityManager.createQuery("DELETE from ProductionStudio ent WHERE ent.id = :id")
                .setParameter("id", deletableId).executeUpdate();
    }

    @Override
    public Optional<ProductionStudio> getById(Long id) {
        return Optional.of(entityManager.createQuery("select new com.kata.cinema.base.models.ProductionStudio(ps.id, " +
                        "ps.name, ps.description, ps.dateFoundation) from ProductionStudio ps " +
                        "where ps.id =: id", ProductionStudio.class)
                .setParameter("id", id)
                .getSingleResult());

    }
}
