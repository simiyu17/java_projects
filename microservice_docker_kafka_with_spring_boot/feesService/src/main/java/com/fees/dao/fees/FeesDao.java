package com.fees.dao.fees;

import java.util.List;
import com.fees.model.Fees;

public interface FeesDao {

    void save(Fees fee) throws Exception;

    Fees findById(Long id);

    List<Fees> getStudentFees(Long studentId);

    void delete(Fees fee) throws Exception;
}
