package com.codegym.cms.service.appuser;

import com.codegym.cms.model.AppUser;
import com.codegym.cms.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserService implements IAppUserService, UserDetailsService {

    @Autowired
    private AppUserRepo appUserRepo;

    @Override
    public AppUser getUserByName(String name) {
        return appUserRepo.getAppUsersByName(name);
    }

    @Override
    public AppUser getCurrentUser() {
        AppUser appUser;
        String name;
        Object ob = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ob instanceof UserDetails){
            name = ((UserDetails) ob).getUsername();
        } else {
            name = ob.toString();
        }
        appUser = this.getUserByName(name);
        return appUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = this.getUserByName(username);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(appUser.getRole());
        UserDetails userDetails = new User(
                appUser.getName(),
                appUser.getPassword(),
                authorityList);
        return userDetails;
    }
}
