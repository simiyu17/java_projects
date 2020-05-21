package com.sample.dao.user;

import com.sample.model.UserInfo;
import java.util.List;

public interface UserDao {

    void save(UserInfo u) throws Exception;

    UserInfo findById(Long id);

    UserInfo findByUsername(String username);

    List<UserInfo> getUsers(String username);

    void delete(UserInfo u) throws Exception;
}
