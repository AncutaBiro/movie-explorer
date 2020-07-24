package org.fasttrackit.movieexplorer.persistence;

import org.fasttrackit.movieexplorer.domain.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository <WishList, Long> {


}
