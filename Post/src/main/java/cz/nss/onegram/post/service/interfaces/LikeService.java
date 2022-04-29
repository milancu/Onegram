package cz.nss.onegram.post.service.interfaces;

import cz.nss.onegram.post.model.Like;
import cz.nss.onegram.post.model.Post;

public interface LikeService {
    public void persist(Like like, Post post);

    public void delete(Like like, Post post);
}
