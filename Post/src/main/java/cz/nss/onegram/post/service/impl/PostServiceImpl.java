package cz.nss.onegram.post.service.impl;

import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.repository.PostRepository;
import cz.nss.onegram.post.service.interfaces.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public Post findById(Integer id) {
        return postRepository.findById(id).get();
    }

    @Override
    public List<Post> findByAuthorId(Integer authorId) {
        return postRepository.findAllByAuthorId(authorId);
    }

    @Override
    public List<Post> findByAuthorId(Integer authorId, LocalDate fromDate, LocalDate toDate) {
        return postRepository.findAllByAuthorIdAndCreatedAtBetween(authorId, fromDate.atStartOfDay(), toDate.atStartOfDay());
    }

    @Override
    public List<Post> findAll(LocalDate fromDate, LocalDate toDate) {
        return postRepository.findAllByCreatedAtBetween(fromDate.atStartOfDay(), toDate.atStartOfDay());
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public void persist(Post post) {
        postRepository.save(post);
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }
}
