package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.AwardCeremony;

public interface AwardCeremonyService extends AbstractService<Long, AwardCeremony> {
    Long getAwardIdFromCeremonyId(Long ceremonyId);
}
