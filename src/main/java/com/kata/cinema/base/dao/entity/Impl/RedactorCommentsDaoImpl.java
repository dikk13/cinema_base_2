package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.RedactorCommentsDao;
import com.kata.cinema.base.models.RedactorComment;
import org.springframework.stereotype.Repository;

@Repository
public class RedactorCommentsDaoImpl extends AbstractDaoImpl<Long, RedactorComment> implements RedactorCommentsDao {
}
