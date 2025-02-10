package zeyaOnline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zeyaOnline.model.Page;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {
}
