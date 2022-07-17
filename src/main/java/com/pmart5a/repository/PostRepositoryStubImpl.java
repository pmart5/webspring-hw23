package com.pmart5a.repository;

import com.pmart5a.exception.NotFoundException;
import com.pmart5a.model.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static com.pmart5a.generator.GeneratorId.getGeneratorId;

@Repository
public class PostRepositoryStubImpl implements PostRepository {

    private final ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<>();

    public List<Post> all() {
        return posts.values()
                .stream()
                .filter(post -> !post.isRemoved())
                .collect(Collectors.toList());
    }

    public Optional<Post> getById(long id) {
        if (posts.containsKey(id) && posts.get(id).isRemoved()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(posts.get(id));
        }
    }

    public Post save(Post post) throws NotFoundException {
        if (post.getId() == 0) {
            post.setId(getGeneratorId().getId());
        } else if (!posts.containsKey(post.getId()) || posts.get(post.getId()).isRemoved()) {
            throw new NotFoundException();
        }
        posts.put(post.getId(), post);
        return post;
    }

    public void removeById(long id) throws NotFoundException {
        if (posts.containsKey(id) && !posts.get(id).isRemoved()) {
            posts.get(id).setRemoved(true);
        } else {
            throw new NotFoundException();
        }
    }
}