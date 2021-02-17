package com.exams.dao.exam;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.exams.dao.common.GenericDaoImpl;
import com.exams.model.Examination;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class ExaminationDaoImpl extends GenericDaoImpl<Examination, Long> implements ExaminationDao {

    @Override
    public void save(Examination std) throws Exception {
        saveEntity(std);

    }

    @Override
    public Examination findById(Long id) {
        return findEntityById(id);
    }

    @Override
    public List<Examination> getExaminations(Long studentId) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Examination> criteria = builder.createQuery(Examination.class);
        Root<Examination> root = criteria.from(Examination.class);
        return studentId != null ? findByCriteria(-1, -1, criteria.select(root).where(builder.equal(root.get("studentId"), studentId))) : findByCriteria(-1, -1, criteria.select(root));
    }

    @Override
    public void delete(Examination std) throws Exception {
        deleteEntityById(std.getId());
    }
    
}