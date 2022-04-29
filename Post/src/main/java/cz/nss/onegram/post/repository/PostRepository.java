package cz.nss.onegram.post.repository;

import cz.nss.onegram.post.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findAllByCreatedAtBetween(LocalDateTime fromDateTime, LocalDateTime toDateTime);

    List<Post> findAllByAuthorId(Integer authorId);

    List<Post> findAllByAuthorIdAndCreatedAtBetween(Integer authorId, LocalDateTime fromDateTime, LocalDateTime toDateTime);
}
