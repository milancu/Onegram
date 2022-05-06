package cz.nss.onegram.post.service.interfaces;

import cz.nss.onegram.post.model.Comment;
import cz.nss.onegram.post.model.Like;
import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.model.SubComment;
import cz.nss.onegram.post.model.interfaces.Likeable;
import cz.nss.onegram.post.security.model.UserDetailsImpl;

public interface LikeService {
    public Like findLikeByUser(Likeable likeable, UserDetailsImpl user);

    public void persist(Like like, Likeable likeable, Post post);

    public void delete(Like like, Likeable likeable, Post post);

    public Likeable findLikeableById(String id, Post post);
}
