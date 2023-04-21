package com.jaehonglog.inflearnhodolman.repository;

import com.jaehonglog.inflearnhodolman.entity.Posts;
import com.jaehonglog.inflearnhodolman.request.PostSearch;
import java.util.List;
import lombok.RequiredArgsConstructor;

public interface PostRepositoryCustom {
    List<Posts> getList(PostSearch postSearch);
}
