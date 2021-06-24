package com.interview.sii.repository;

import com.interview.sii.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> getByLogin(String login);

    User save(User user);

    Optional<User> getUserByLogin(String login);
}
