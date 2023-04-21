package com.jaehonglog.inflearnhodolman.repository;

import com.jaehonglog.inflearnhodolman.entity.Posts;
import com.jaehonglog.inflearnhodolman.entity.QPosts;
import com.jaehonglog.inflearnhodolman.request.PostSearch;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<Posts> getList(PostSearch postSearch) {
        return jpaQueryFactory.selectFrom(QPosts.posts)
                .limit(postSearch.getSize())
                .offset( postSearch.getOffset())
                .fetch();
    }
}
