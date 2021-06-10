package com.jjonami.datajpa.repository;

import com.jjonami.datajpa.dto.MemberDto;
import com.jjonami.datajpa.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * Data JPA > method 이름으로 query 생성
     * => 간단한 query 작성시 사용
     * @param username
     * @param age
     * @return
     */
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    /**
     * @Query
     * => repository method에 query 정의
     * => 일종의 이름 없는 Named Query
     * @param username
     * @param age
     * @return
     */
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUserByUsernameAndAge(@Param("username") String username, @Param("age") int age);

    /**
     * DTO 조회
     * @return
     */
    @Query("select new com.jjonami.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    /**
     * 컬렉션 조회
     * @param username
     * @return
     */
    List<Member> findMemberListByUsername(String username);

    /**
     * 단건 조회
     * @param username
     * @return
     */
    Member findMemberByUsername(String username);

    /**
     * Optional 단건
     * @param username
     * @return
     */
    Optional<Member> findMemberOrEmptyByUsername(String username);

    /**
     * Data JPA paging
     * @param age
     * @param pageable
     * @return
     */
    Page<Member> findByAge(int age, Pageable pageable);

    /**
     * bulk update
     * @param age
     * @return
     * bulk 연산을 하면 [PersistenceContext]에는 반영이 안된다.
     * => bulk 연산 이후에 데이터 조회가 필요하면 [PersistenceContext]를 초기화 해줘야한다.
     * => clearAutomatically = true
     */
    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.age = m.age +1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    /**
     * fetch join
     * => N+1 문제를 해결하기 위해
     * => [member]를 조회 할 때 query 한번으로 [team]까지 가져온다.
     * @return
     */
    @Query("select m from Member m left join fetch m.team")
    List<Member> findMemberFetchJoin();

}
