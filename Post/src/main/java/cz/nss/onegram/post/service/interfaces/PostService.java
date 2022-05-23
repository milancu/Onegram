package cz.nss.onegram.post.service.interfaces;

import cz.nss.onegram.post.model.Comment;
import cz.nss.onegram.post.model.Like;
import cz.nss.onegram.post.model.Post;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

public interface PostService {
    public Post findById(String id);

    public List<Post> findByAuthorId(Integer authorId);

    public List<Post> findByAuthorId(Integer authorId, LocalDate fromDate, LocalDate toDate);

    public List<Post> findAll();

    public List<Post> findAll(LocalDate fromDate, LocalDate toDate);

    public List<Post> findByAuthorIds(List<Integer> authors);

    public void persist(Post post);

    public void delete(Post post);
}
