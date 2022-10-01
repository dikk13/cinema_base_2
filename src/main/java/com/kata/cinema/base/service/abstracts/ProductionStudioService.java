package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.models.ProductionStudio;

public interface ProductionStudioService extends AbstractService<Long, ProductionStudio> {

    void updateById(Long id, ProductionStudio productionStudio);
}
