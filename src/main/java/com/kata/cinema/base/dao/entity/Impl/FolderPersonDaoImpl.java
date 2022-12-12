package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.FolderPersonDao;
import com.kata.cinema.base.models.FolderMovie;
import com.kata.cinema.base.models.FolderPerson;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.kata.cinema.base.dao.util.JpaResultHelper.jpaResultHelper;

@Repository
public class FolderPersonDaoImpl extends AbstractDaoImpl<Long, FolderPerson> implements FolderPersonDao {
    @Override
    public List<FolderPerson> getFolderPersonById(Long folderPersonId) {
        return entityManager.createQuery("select fp from FolderPerson fp where fp.user.id=: id", FolderPerson.class)
                .setParameter("id", folderPersonId)
                .getResultList();

    }

    public List<FolderPerson> getFolderPersonByName(String name) {
        return entityManager.createQuery("select fp from FolderPerson fp where fp.name=:name", FolderPerson.class)
                .setParameter("name", name)
                .getResultList();
    }
}
