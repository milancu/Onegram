package cz.nss.onegram.post.util;

import cz.nss.onegram.post.graphql.input.post.CreatePostInput;
import cz.nss.onegram.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class InputMapper {
    private final ModelMapper mapper;

    public Post convertToEntity(CreatePostInput postInput) {
        Post post = mapper.map(postInput, Post.class);
        post.setComments(new ArrayList<>());
        post.setLikes(new ArrayList<>());
        post.setTags(new ArrayList<>());
        return post;
    }
}
