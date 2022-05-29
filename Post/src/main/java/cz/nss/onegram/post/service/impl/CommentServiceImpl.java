package cz.nss.onegram.post.service.impl;

import cz.nss.onegram.post.model.Comment;
import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.model.SubComment;
import cz.nss.onegram.post.repository.PostRepository;
import cz.nss.onegram.post.service.interfaces.CommentService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final PostRepository postRepository;

    @Override
    public Comment findById(String id, Post post) {
        return post.getComments().stream()
                .filter(comment -> comment.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Comment for post not found. Comment id: " + id
                        + " . Post id: " + post.getId()));
    }

    @Override
    public SubComment findSubCommentById(String id, Post post) {
        for (Comment comment : post.getComments()){
            Optional<SubComment> optional = comment.getSubComments()
                    .stream()
                    .filter(subComment -> subComment.getId().equals(id))
                    .findFirst();

            if (optional.isPresent()){
                return optional.get();
            }
        }

        throw new NoSuchElementException("Subcomment for post not found. Subcomment id: " + id
                + " . Post id: " + post.getId());
    }

    @Override
    public void persist(Comment comment, Post post) {
        comment.setId(new ObjectId().toString());
        comment.setCreatedAtDate(LocalDate.now());
        comment.setCreatedAtTime(LocalTime.now());
        post.getComments().add(comment);
        postRepository.save(post);
    }

    @Override
    public void persistSubComment(SubComment subComment, Comment comment, Post post) {
        subComment.setId(new ObjectId().toString());
        subComment.setCreatedAtDate(LocalDate.now());
        subComment.setCreatedAtTime(LocalTime.now());

        for (Comment c : post.getComments()){
            if (c.getId().equals(comment.getId())){
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

        else {
            throw new NoSuchElementException("Comment id: " + comment.getId() + " not found on post id: " +  post.getId());
        }
    }

    @Override
    public void delete(SubComment subComment, Post post) {
        Optional<Comment> optional = post.getComments().stream()
                .filter(comment -> comment.getSubComments().contains(subComment))
                .findFirst();

        if (optional.isPresent()){
            Comment comment = optional.get();
            comment.getSubComments().remove(subComment);
            postRepository.save(post);
        }

        else{
            throw new NoSuchElementException("Subcomment id: " + subComment.getId() + " not found on post id: " + post.getId());
        }
    }
}
