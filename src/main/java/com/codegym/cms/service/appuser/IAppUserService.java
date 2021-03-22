package com.codegym.cms.service.appuser;

import com.codegym.cms.model.AppUser;

public interface IAppUserService {
    AppUser getUserByName(String name);
    AppUser getCurrentUser();
}
