package com.restful.repository;

import com.restful.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    /**
     * When you are working with the native queries, when your id is uuid you have to convert into String.
     *
     * @param userId - id of the user
     * @return - returns boolean, if the user deleted his account then it returns true
     */
    @Query(nativeQuery = true, value = "select is_deleted from users where id = :userId")
    Optional<Integer> findByIdAndIsDeleted(String userId);

    public Optional<User> findByIdAndIsDeleted(UUID userId, boolean isDeleted);

    public List<User> findByIsDeletedIsFalse();

    public Optional<User> findByIdAndIsDeletedIsFalse(UUID userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update users set is_deleted = 1 where id = :userId")
    public void disableAccount(@Param("userId") String userId);
}
