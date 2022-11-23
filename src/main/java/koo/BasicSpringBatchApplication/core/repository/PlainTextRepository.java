package koo.BasicSpringBatchApplication.core.repository;

import koo.BasicSpringBatchApplication.core.domain.PlainText;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlainTextRepository extends JpaRepository<PlainText, Long> {
    Page<PlainText> findBy(Pageable pageable); // pageable의 사이즈만큼 데이터를 읽어옮
}
