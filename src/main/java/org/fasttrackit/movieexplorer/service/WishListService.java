package org.fasttrackit.movieexplorer.service;

import org.fasttrackit.movieexplorer.persistence.WishListRepository;
import org.springframework.stereotype.Service;

@Service
public class WishListService {

    private final WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }





}
