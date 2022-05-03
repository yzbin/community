package com.qianhang.community.service;

import com.qianhang.community.enums.CommentTypeEnum;
import com.qianhang.community.exception.CustomizeErrorCode;
import com.qianhang.community.exception.CustomizeException;
import com.qianhang.community.mapper.CommentMapper;
import com.qianhang.community.mapper.QuestionExtMapper;
import com.qianhang.community.mapper.QuestionMapper;
import com.qianhang.community.model.Comment;
import com.qianhang.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;


    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Transactional
    public void insert(Comment comment) {
        if(comment.getParentId() == null || comment.getParentId() == 0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType() == null || !CommentTypeEnum.isExists(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

        if(comment.getType() == CommentTypeEnum.COMMENT.getType()){
            // 回复评论
            Comment dbCommnt = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbCommnt == null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        }else {
            // 回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId().intValue());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
        }
    }
}
