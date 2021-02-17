package com.exams.dao.exam;

import java.util.List;
import com.exams.model.Examination;

public interface ExaminationDao {

    void save(Examination exam) throws Exception;

    Examination findById(Long id);

    List<Examination> getExaminations(Long studentId);

    void delete(Examination exam) throws Exception;
}
