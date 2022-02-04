package rw.ac.rca.ems.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ac.rca.ems.models.Event;
import rw.ac.rca.ems.models.User;
import rw.ac.rca.ems.models.enums.EEventStatus;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IEventRepository extends JpaRepository<Event, Long> {

    List<Event> findByUser(User user);

    List<Event> findByStatus(EEventStatus status);

    List<Event> findByTitleLikeOrDescriptionLike(String title, String description);

    List<Event> findByCreatedAtBetween(LocalDateTime createdAt, LocalDateTime createdAt2);

    Page<Event> findByStatus(EEventStatus status, Pageable pageable);
}
