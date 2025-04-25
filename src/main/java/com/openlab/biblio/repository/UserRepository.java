package com.openlab.biblio.repository;

import com.openlab.biblio.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
