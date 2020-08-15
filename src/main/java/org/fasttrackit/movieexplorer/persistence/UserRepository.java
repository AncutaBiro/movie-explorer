package org.fasttrackit.movieexplorer.persistence;

import org.fasttrackit.movieexplorer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

}
