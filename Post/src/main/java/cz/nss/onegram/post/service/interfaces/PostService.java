package cz.nss.onegram.post.service.interfaces;

import cz.nss.onegram.post.model.Post;

import java.util.List;

public interface PostService {
    public List<Post> findAll();
}
