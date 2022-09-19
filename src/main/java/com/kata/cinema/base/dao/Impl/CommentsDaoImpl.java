package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.CommentsDao;
import com.kata.cinema.base.dto.CommentsRequestDto;
import com.kata.cinema.base.models.Comments;
import org.springframework.stereotype.Repository;


import java.math.BigInteger;
import java.util.List;

@Repository
public class CommentsDaoImpl extends AbstractDaoImpl<Long, Comments> implements CommentsDao {

    @Override
    public List<Comments> getCommentsListByNewsId(long newsId) {
        return entityManager.
                createQuery("select new com.kata.cinema.base." +
                        "models.Comments(c.id, c.text, c.date, c.user, c.news)" +
                        " from Comments c where c.news.id =: id", Comments.class)
                .setParameter("id", newsId)
                .getResultList();
    }


    public void create(CommentsRequestDto entity, long userId, long newsId) {
        BigInteger id = (BigInteger) entityManager.createNativeQuery("select nextval ('seq_comments_id')").getSingleResult();
        entityManager.createNativeQuery("insert into comments (date, text, news_id, user_id, id) values (?, ?, ?, ?, ?)")
                .setParameter(1, entity.getDate())
                .setParameter(2, entity.getText())
                .setParameter(3, newsId)
                .setParameter(4, userId)
                .setParameter(5, id)
                .executeUpdate();
    }
}
