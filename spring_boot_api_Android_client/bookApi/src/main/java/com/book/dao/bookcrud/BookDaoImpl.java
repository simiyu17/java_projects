package com.book.dao.bookcrud;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.book.dao.common.GenericDaoImpl;
import com.book.model.Book;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
@Transactional
public class BookDaoImpl extends GenericDaoImpl<Book, Long> implements BookDao {

    @Override
    public Book save(Book book) throws Exception {
        return saveEntity(book);
    }

    @Override
    public Book findById(Long id) {
        return findEntityById(id);
    }

    @Override
    public List<Book> getBooks() {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Book> criteria = builder.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);
        return findByCriteria(-1, -1, criteria.select(root));
    }

    @Override
    public void delete(Book book) throws Exception {
        deleteEntityById(book.getId());

    }


}
