package cz.nss.onegram.post.model.interfaces;

import cz.nss.onegram.post.model.Like;

import java.util.List;

public interface Likeable {
    public List<Like> getLikes();
}
