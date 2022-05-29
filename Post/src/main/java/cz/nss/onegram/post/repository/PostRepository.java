package cz.nss.onegram.post.repository;

import cz.nss.onegram.post.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findAllByCreatedAtDateBetweenOrderByCreatedAtDateDescCreatedAtTimeDesc(LocalDate fromDate, LocalDate toDate);

    List<Post> findAllByAuthorIdOrderByCreatedAtDateDescCreatedAtTimeDesc(Integer authorId);

    List<Post> findAllByAuthorIdAndCreatedAtDateBetweenOrderByCreatedAtDateDescCreatedAtTimeDesc(Integer authorId, LocalDate fromDate, LocalDate toDate);
}
