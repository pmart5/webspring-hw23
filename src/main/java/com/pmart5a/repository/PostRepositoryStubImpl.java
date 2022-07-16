package com.pmart5a.repository;

import com.pmart5a.exception.NotFoundException;
import com.pmart5a.model.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static com.pmart5a.generator.GeneratorId.getGeneratorId;

@Repository
public class PostRepositoryStubImpl implements PostRepository {

    private final ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<>();

    public List<Post> all() {
        return new ArrayList<>(posts.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(posts.get(id));
    }

    public Post save(Post post) throws NotFoundException {
        if (post.getId() == 0) {
            post.setId(getGeneratorId().getId());
        } else if (!posts.containsKey(post.getId())) {
            throw new NotFoundException();
        }
        posts.put(post.getId(), post);
        return post;
    }

    public void removeById(long id) throws NotFoundException {
        if (posts.containsKey(id)) {
            posts.remove(id);
        } else {
            throw new NotFoundException();
        }
    }
}