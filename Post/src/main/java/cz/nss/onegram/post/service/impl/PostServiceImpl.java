package cz.nss.onegram.post.service.impl;

import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.repository.PostRepository;
import cz.nss.onegram.post.service.interfaces.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public Post findById(String id) {
        return postRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Post> findByAuthorId(Integer authorId) {
        return postRepository.findAllByAuthorIdOrderByCreatedAtDateDescCreatedAtTimeDesc(authorId);
    }

    /**
     * @param authorId
     * @param fromDate
     * @param toDate
     * @return All posts made by an author. The dates are inclusive.
     */
    @Override
    public List<Post> findByAuthorId(Integer authorId, LocalDate fromDate, LocalDate toDate) {
        if (fromDate.isAfter(toDate)){
            throw new InputMismatchException("FromDate is after toDate.");
        }

        return postRepository.findAllByAuthorIdAndCreatedAtDateBetweenOrderByCreatedAtDateDescCreatedAtTimeDesc(authorId, fromDate.minusDays(1), toDate.plusDays(1));
    }

    /**
     * @param fromDate
     * @param toDate
     * @return All posts created. The dates are inclusive.
     */
    @Override
    public List<Post> findAll(LocalDate fromDate, LocalDate toDate) {
        return postRepository.findAllByCreatedAtDateBetweenOrderByCreatedAtDateDescCreatedAtTimeDesc(fromDate.minusDays(1), toDate.plusDays(1));
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public void persist(Post post) {
        post.setCreatedAtDate(LocalDate.now());
        post.setCreatedAtTime(LocalTime.now());
        postRepository.save(post);
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }
}
