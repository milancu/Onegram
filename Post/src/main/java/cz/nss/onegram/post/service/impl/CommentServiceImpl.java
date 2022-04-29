package cz.nss.onegram.post.service.impl;

import cz.nss.onegram.post.model.Comment;
import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.model.SubComment;
import cz.nss.onegram.post.repository.PostRepository;
import cz.nss.onegram.post.service.interfaces.CommentService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final PostRepository postRepository;

    @Override
    public void persist(Comment comment, Post post) {
        comment.setObjectId(new ObjectId().toString());
        post.getComments().add(comment);
        postRepository.save(post);
    }

    @Override
    public void persistSubComment(SubComment subComment, Comment comment, Post post) {
        for (Comment c : post.getComments()){
            if (c.getObjectId().equals(comment.getObjectId())){
                c.getSubComments().add(subComment);
                postRepository.save(post);
                break;
            }
        }
    }

    @Override
    public void delete(Comment comment, Post post) {
        if (post.getComments().remove(comment)){
            postRepository.save(post);
        }
    }
}
