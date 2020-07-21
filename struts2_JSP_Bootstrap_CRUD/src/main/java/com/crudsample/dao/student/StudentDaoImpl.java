package com.crudsample.dao.student;

import com.crudsample.dao.common.GenericDaoImpl;
import com.crudsample.model.Student;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@Transactional
public class StudentDaoImpl extends GenericDaoImpl<Student, Long> implements StudentDao {

    @Override
    public void save(Student std) throws Exception {
        saveEntity(std);

    }

    @Override
    public Student findById(Long id) {
        return findEntityById(id);
    }

    @Override
    public List<Student> getStudents() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Student> criteria = builder.createQuery(Student.class);
        Root<Student> root = criteria.from(Student.class);
        return findByCriteria(-1, -1, criteria.select(root));
    }

    @Override
    public void delete(Student std) throws Exception {
        deleteEntityById(std.getId());
    }
    
}