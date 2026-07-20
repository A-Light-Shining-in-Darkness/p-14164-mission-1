package com.back.domain.wiseSaying.wiseSaying.repository;

import com.back.domain.wiseSaying.wiseSaying.entity.QWiseSaying;
import com.back.domain.wiseSaying.wiseSaying.entity.WiseSaying;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class WiseSayingRepositoryImpl implements WiseSayingRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<WiseSaying> findQById(int id) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(QWiseSaying.wiseSaying)
                        .where(QWiseSaying.wiseSaying.id.eq(id))
                        .fetchOne()
        );
    }

    @Override
    public List<WiseSaying> findQAll() {
        return queryFactory
                .selectFrom(QWiseSaying.wiseSaying)
                .fetch();
    }

    @Override
    public long qCount() {
        Long result = queryFactory
                .select(QWiseSaying.wiseSaying.count())
                .from(QWiseSaying.wiseSaying)
                .fetchOne();
        return result != null ? result : 0L;
    }
}