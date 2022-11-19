package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.ProductionStudioDao;
import com.kata.cinema.base.models.ProductionStudio;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.ProductionStudioService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductionStudioServiceImpl extends AbstractServiceImpl<Long, ProductionStudio> implements ProductionStudioService {

    private final ProductionStudioDao productionStudioDao;

    public ProductionStudioServiceImpl(ProductionStudioDao productionStudioDao) {
        super(productionStudioDao);
        this.productionStudioDao = productionStudioDao;
    }

    @Override
    @Transactional
    public void updateById(Long id, ProductionStudio productionStudio) {
        if (!productionStudioDao.existById(id)) {
            throw new RuntimeException("Неверно передан id");
        }
        productionStudio.setId(id);
        productionStudioDao.update(productionStudio);
    }
}
