package com.ie.repository.admin;

import com.ie.entity.AUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository {

    List<AUser> login(AUser aUser);
}
