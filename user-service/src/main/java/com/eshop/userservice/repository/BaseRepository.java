package com.eshop.userservice.repository;

import com.eshop.userservice.models.entity.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<E extends BaseUser> extends JpaRepository<E,Long> { }
