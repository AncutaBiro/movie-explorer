package org.fasttrackit.movieexplorer.persistence;

import org.fasttrackit.movieexplorer.domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {

}
