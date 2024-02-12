package shop.remindermemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.remindermemo.domain.Memo;

/**
 * JpaRepository 메서드를 상속받은 Memo 레포
 */
public interface MemoRepository extends JpaRepository<Memo, Long> {
}
