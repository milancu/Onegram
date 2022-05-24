package cz.nss.onegram.post.util;

import cz.nss.onegram.post.graphql.input.comment.CreateCommentInput;
import cz.nss.onegram.post.graphql.input.comment.CreateSubcommentInput;
import cz.nss.onegram.post.graphql.input.like.CreateLikeInput;
import cz.nss.onegram.post.graphql.input.post.CreatePostInput;
import cz.nss.onegram.post.model.Comment;
import cz.nss.onegram.post.model.Like;
import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.model.SubComment;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InputMapper {
    private final ModelMapper mapper;

    public Post convertToEntity(CreatePostInput postInput, List<String> paths) {
        Post post = mapper.map(postInput, Post.class);
        post.setComments(new ArrayList<>());
        post.setLikes(new ArrayList<>());
        post.setTags(new ArrayList<>());
        post.setImagePaths(paths);
        return post;
    }

    public Comment convertToEntity(CreateCommentInput commentInput, Integer authorId) {
        Comment comment = new Comment();
        comment.setContent(commentInput.getContent());
        comment.setAuthorId(authorId);
        comment.setLikes(new ArrayList<>());
        comment.setSubComments(new ArrayList<>());
        return comment;
    }

    public SubComment convertToEntity(CreateSubcommentInput commentInput, Integer authorId) {
        SubComment subComment = new SubComment();
        subComment.setContent(commentInput.getContent());
        subComment.setLikes(new ArrayList<>());
        subComment.setAuthorId(authorId);
        return subComment;
    }

    public Like convertToEntity(CreateLikeInput likeInput, Integer authorId) {
        Like like = new Like();
        like.setAuthorId(authorId);
        return like;
    }
}
