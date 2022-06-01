package cz.nss.onegram.post.service.impl;

import cz.nss.onegram.post.exception.PostserviceException;
import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.repository.PostRepository;
import cz.nss.onegram.post.service.interfaces.FileService;
import cz.nss.onegram.post.service.interfaces.PostService;
import cz.nss.onegram.post.service.interfaces.SystemInitializer;
import cz.nss.onegram.post.util.PostTimeComparator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    private final FileService fileService;

    @Override
    public Post findById(String id) {
        return postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Post not found id: " + id));
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
        if (fromDate.isAfter(toDate)){
            throw new InputMismatchException("FromDate is after toDate.");
        }

        return postRepository.findAllByCreatedAtDateBetweenOrderByCreatedAtDateDescCreatedAtTimeDesc(fromDate.minusDays(1), toDate.plusDays(1));
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findByAuthorIds(List<Integer> authors) {
        List<Post> result = new ArrayList<>();
        authors.stream()
                .forEach(authorId -> result.addAll(this.findByAuthorId(authorId)));

        result.sort(new PostTimeComparator());
        Collections.reverse(result);

        return result;
    }

    @Override
    public void persist(Post post) {
        post.setCreatedAtDate(LocalDate.now());
        post.setCreatedAtTime(LocalTime.now());
        if (post.getImagePaths().size() == 0 || post.getImagePaths().size() > 5){
            throw new PostserviceException("Failed to create a post. At least one and at most five images must be present.");
        }
        postRepository.save(post);
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);

        post.getImagePaths().forEach(path -> {
            if (!fileService.extractFilenameFromPath(path).equals(SystemInitializer.getDefaultImageName()))
            fileService.deleteFile(fileService.extractFilenameFromPath(path));
        });
    }
}
