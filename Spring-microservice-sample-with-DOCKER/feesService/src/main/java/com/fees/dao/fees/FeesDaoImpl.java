package com.fees.dao.fees;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.fees.dao.common.GenericDaoImpl;
import com.fees.model.Fees;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class FeesDaoImpl extends GenericDaoImpl<Fees, Long> implements FeesDao {

    @Override
    public void save(Fees std) throws Exception {
        saveEntity(std);

    }

    @Override
    public Fees findById(Long id) {
        return findEntityById(id);
    }

    @Override
    public List<Fees> getStudentFees(Long studentId) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Fees> criteria = builder.createQuery(Fees.class);
        Root<Fees> root = criteria.from(Fees.class);
        return studentId != null ? findByCriteria(-1, -1, criteria.select(root).where(builder.equal(root.get("studentId"), studentId))) : findByCriteria(-1, -1, criteria.select(root));
    }

    @Override
    public void delete(Fees std) throws Exception {
        deleteEntityById(std.getId());
    }
    
}