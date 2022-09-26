package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.ProductionStudioDao;
import com.kata.cinema.base.models.ProductionStudio;
import org.springframework.stereotype.Repository;

@Repository
public class ProductionStudioDaoImpl extends AbstractDaoImpl<Long, ProductionStudio> implements ProductionStudioDao {
}
