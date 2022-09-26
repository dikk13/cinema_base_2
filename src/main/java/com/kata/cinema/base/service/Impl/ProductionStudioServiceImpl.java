package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.AbstractDao;
import com.kata.cinema.base.dao.abstracts.ProductionStudioDao;
import com.kata.cinema.base.models.ProductionStudio;
import com.kata.cinema.base.service.abstracts.ProductionStudioService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductionStudioServiceImpl extends AbstractServiceImpl<Long, ProductionStudio>
        implements ProductionStudioService{

    private final ProductionStudioDao productionStudioDao;

    public ProductionStudioServiceImpl(AbstractDao<Long, ProductionStudio> abstractDao, ProductionStudioDao productionStudioDao) {
        super(abstractDao);
        this.productionStudioDao = productionStudioDao;
    }

//    @Override
//    @Transactional
//    public void create(ProductionStudio productionStudio) {
//        productionStudioDao.create(productionStudio);
//    }

//    @Override
//    public Optional<ProductionStudio> getById(Long id) {
//        return productionStudioDao.getById(id);
//    }

//    @Override
//    @Transactional
//    public void deleteById(Long id) {
//        super.deleteById(id);
//    }


    @Override
    @Transactional
    public void updateById(Long id, ProductionStudio productionStudio) {
        if (this.getById(id).isPresent()) {
            productionStudio.setId(id);
            productionStudioDao.update(productionStudio);
        }
    }
}
