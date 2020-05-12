package com.student.dao.user;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.student.dao.common.GenericDaoImpl;
import com.student.model.UserInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserDaoImpl extends GenericDaoImpl<UserInfo, Long> implements UserDao{

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(UserInfo u) throws Exception {
        u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
        saveEntity(u);

    }

    @Override
    public UserInfo findById(Long id) {
        return findEntityById(id);
    }

    @Override
    public List<UserInfo> getUsers(String username) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<UserInfo> criteria = builder.createQuery(UserInfo.class);
        Root<UserInfo> root = criteria.from(UserInfo.class);
        return username != null ? findByCriteria(-1, -1, criteria.select(root).where(builder.equal(root.get("username"), username))) : findByCriteria(-1, -1, criteria.select(root));
    }

    @Override
    public void delete(UserInfo u) throws Exception {
        deleteEntityById(u.getId());
    }

    @Override
    public UserInfo findByUsername(String username) {
        return this.getUsers(username).size() < 1 ? null : this.getUsers(username).get(0);
    }
    
}