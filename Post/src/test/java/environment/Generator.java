package environment;

import cz.nss.onegram.post.model.Comment;
import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.model.SubComment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.util.*;

public class Generator {

    private static Random random = new Random();

    public static Post generateRandomPost(Integer authorId){
        Post post = Post.builder()
                .authorId(authorId)
                .comments(new ArrayList<>())
                .createdAtDate(LocalDate.now())
                .createdAtTime(LocalTime.now())
                .description("Random post" + random.nextInt(1, 1000))
                .likes(new ArrayList<>())
                .tags(new ArrayList<>())
                .imagePaths(List.of("foo"))
                .build();

        return post;
    }

    public static Comment generateRandomComment(Integer authorId){
        Comment comment = Comment.builder()
                .authorId(authorId)
                .content("Random comment: " + random.nextInt(1, 1000))
                .likes(new ArrayList<>())
                .subComments(new ArrayList<>())
                .build();

        return comment;
    }

    public static SubComment generateRandomSubComment(Integer authorId){
        SubComment subComment = SubComment.builder()
                .authorId(authorId)
                .content("Random comment: " + random.nextInt(1, 1000))
                .likes(new ArrayList<>())
                .build();

        return subComment;
    }
}
