package com.pyo.pyostagram.service;


import com.pyo.pyostagram.domain.comment.Comment;
import com.pyo.pyostagram.domain.comment.CommentRepository;
import com.pyo.pyostagram.domain.image.Image;
import com.pyo.pyostagram.domain.user.User;
import com.pyo.pyostagram.domain.user.UserRepository;
import com.pyo.pyostagram.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;






    @Transactional
    public Comment 댓글쓰기(String content, int imageId, int userId){

        //tip 가짜객체 만들어서 넣으면 편함 id값만 담아서 db에 넣을수있음
        //만약 이렇게 안하면 또 select 해와서 넣어야함
        //대신 return시에 image객체와 user객체는 오직 id값만 가진 빈 객체를 리턴받음
        Image image = new Image();
        image.setId(imageId);

        User userEntity = userRepository.findById(userId).orElseThrow(()->{
                throw new CustomApiException("유저 아이디 찾기 불가");
                });
       // 이렇게 username도 필요하기 때문에 repository로 찾아오는거
        //즉 이렇게

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setImage(image);
        comment.setUser(userEntity);

        return commentRepository.save(comment);


    }


    @Transactional
    public void 댓글삭제(int id){
        commentRepository.deleteById(id);
        //나중에 터지면 try catch 걸면됨
    }
}
