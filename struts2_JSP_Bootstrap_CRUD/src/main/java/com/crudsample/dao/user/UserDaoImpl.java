package com.crudsample.dao.user;

import com.crudsample.dao.common.GenericDaoImpl;
import com.crudsample.model.UserInfo;
import com.crudsample.util.Helper;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@Transactional
public class UserDaoImpl extends GenericDaoImpl<UserInfo, Long> implements UserDao {

    @Override
    public void save(UserInfo u) throws Exception {
        Helper h = new Helper();
        u.setPassword(h.hash(u.getPassword()));
        saveEntity(u);

    }

    @Override
    public UserInfo findById(Long id) {
        return findEntityById(id);
    }

    @Override
    public List<UserInfo> getUsers(String username) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
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
