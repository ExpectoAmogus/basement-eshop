package com.eshop.userservice.repository.user;

import com.eshop.userservice.models.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends BaseUserRepository<User> {

    List<User> findAll();

    @Modifying
    @Query("update BaseUser u set u.enabled = ?2 where u.id = ?1") // метод на сет(ансет) в базе (бан-анбан)
    void updateUserEnabledById(Long id, boolean enable);


}
