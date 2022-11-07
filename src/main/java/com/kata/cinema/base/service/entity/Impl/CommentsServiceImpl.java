//package com.kata.cinema.base.service.entity.Impl;
//
//import com.kata.cinema.base.dao.entity.CommentsDao;
//import com.kata.cinema.base.models.Comments;
//import com.kata.cinema.base.service.entity.AbstractServiceImpl;
//import com.kata.cinema.base.service.entity.CommentsService;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//public class CommentsServiceImpl extends AbstractServiceImpl<Long, Comments> implements CommentsService {
//
//    private final CommentsDao commentsDao;
//
//    public CommentsServiceImpl(CommentsDao commentsDao) {
//        super(commentsDao);
//        this.commentsDao = commentsDao;
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<Comments> getAllCommentsByNewsId(long id) {
//        return commentsDao.getCommentsListByNewsId(id);
//    }
//
//    @Override
//    @Transactional
//    public void create(Comments comments) {
//        commentsDao.create(comments);
//    }
//}
