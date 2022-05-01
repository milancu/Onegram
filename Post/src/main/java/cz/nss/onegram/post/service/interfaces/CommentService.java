package cz.nss.onegram.post.service.interfaces;

import cz.nss.onegram.post.model.Comment;
import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.model.SubComment;

public interface CommentService {
    public void persist(Comment comment, Post post);

    public void persistSubComment(SubComment subComment, Comment comment, Post post);

    public void delete(Comment comment, Post post);
}
