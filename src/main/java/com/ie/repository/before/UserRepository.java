package com.ie.repository.before;

import com.ie.entity.BUser;

import java.util.List;

public interface UserRepository {
    public int register(BUser bUser);

    public List<BUser> login(BUser bUser);
}
