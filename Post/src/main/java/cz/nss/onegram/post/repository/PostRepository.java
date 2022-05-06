package cz.nss.onegram.post.repository;

import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.model.SubComment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findAllByCreatedAtDateBetween(LocalDate fromDate, LocalDate toDate);

    List<Post> findAllByAuthorId(Integer authorId);

    List<Post> findAllByAuthorIdAndCreatedAtDateBetween(Integer authorId, LocalDate fromDate, LocalDate toDate);
}
