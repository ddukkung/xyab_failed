package com.miree.xyab.repository;

import com.miree.xyab.domain.User;
import com.miree.xyab.domain.projection.UserOnlyContainName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = UserOnlyContainName.class)
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
