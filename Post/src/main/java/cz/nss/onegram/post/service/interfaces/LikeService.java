package cz.nss.onegram.post.service.interfaces;

import cz.nss.onegram.post.model.Comment;
import cz.nss.onegram.post.model.Like;
import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.model.SubComment;
import cz.nss.onegram.post.model.interfaces.Likeable;

public interface LikeService {
    public void persist(Like like, Likeable likeable, Post post);

    public void delete(Like like, Post post);
}
