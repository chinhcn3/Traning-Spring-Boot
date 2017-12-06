package com.chinhvd.respository;

import com.chinhvd.domain.Book;
import com.chinhvd.domain.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ChinhVD on 11/30/17.
 */
public interface ReadingListRepository extends JpaRepository<Book, Long> {
    List<Book> findByReader(Reader reader);
}
