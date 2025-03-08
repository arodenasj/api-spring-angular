package es.insinno.repository;

import es.insinno.entity.SwaggerLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwaggerLogRepository extends JpaRepository<SwaggerLog, Long> {
}