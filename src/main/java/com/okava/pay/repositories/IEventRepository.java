package com.okava.pay.repositories;

import com.okava.pay.models.Event;
import com.okava.pay.models.User;
import com.okava.pay.models.enums.EEventStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
