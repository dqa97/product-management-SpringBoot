package com.codegym.cms.repo;

import com.codegym.cms.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    AppUser getAppUsersByName(String name);
}
