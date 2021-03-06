package com.student.dao.user;

import com.student.model.UserInfo;
import java.util.List;

public interface UserDao {
    
    void save(UserInfo u) throws Exception;
	
	UserInfo findById(Long id);

	UserInfo findByUsername(String username);
	
	List<UserInfo> getUsers(String username);
	
	void delete(UserInfo u) throws Exception;
}