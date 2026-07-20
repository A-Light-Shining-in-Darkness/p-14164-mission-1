package com.back.domain.member.member.repository;

import com.back.domain.member.member.entity.Member;
import com.back.domain.member.member.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public long qCount() {
        Long result = queryFactory
                .select(QMember.member.count())
                .from(QMember.member)
                .fetchOne();
        return result != null ? result : 0L;
    }

    @Override
    public Optional<Member> findQByUsername(String username) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(QMember.member)
                        .where(QMember.member.username.eq(username))
                        .fetchOne()
        );
    }
}