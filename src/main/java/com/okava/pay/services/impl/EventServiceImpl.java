package com.okava.pay.services.impl;

import com.okava.pay.models.Event;
import com.okava.pay.models.User;
import com.okava.pay.models.enums.EEventStatus;
import com.okava.pay.repositories.IEventRepository;
import com.okava.pay.services.IEventService;
import com.okava.pay.services.IUserService;
import com.okava.pay.utils.dtos.CreateOrUpdateEventDTO;
import com.okava.pay.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventServiceImpl implements IEventService {

    private final IEventRepository eventRepository;

    private final IUserService userService;

    @Autowired
    public EventServiceImpl(IEventRepository eventRepository, IUserService userService) {
        this.eventRepository = eventRepository;
        this.userService = userService;
    }

    @Override
    public List<Event> all() {
        return eventRepository.findAll();
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event", "id", id.toString()));
    }

    @Override
    public List<Event> createdByMe() {
        User user = userService.getLoggedInUser();

        return eventRepository.findByUser(user);
    }

    @Override
    public List<Event> createdBy(Long userId) {
        User user = userService.findById(userId);

        return eventRepository.findByUser(user);
    }

    @Override
    public List<Event> search(String title) {
        return eventRepository.findByTitleLikeOrDescriptionLike(title, title);
    }

    @Override
    public List<Event> between(LocalDateTime start, LocalDateTime end) {
        return eventRepository.findByCreatedAtBetween(start, end);
    }

    @Override
    public Event create(CreateOrUpdateEventDTO dto) {
        Event event = new Event(dto.getTitle(), dto.getDescription(), dto.getTime(), dto.getDurationInHours(), dto.getBannerImage());

        event.setUser(userService.getLoggedInUser());

        return eventRepository.save(event);
    }

    @Override
    public Event update(Long id, CreateOrUpdateEventDTO dto) {
        Event event = findById(id);

        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setTime(dto.getTime());
        event.setDuration(dto.getDurationInHours());
        event.setBannerImage(dto.getBannerImage());

        return eventRepository.save(event);
    }

    @Override
    public Event changeStatus(Long id, EEventStatus newStatus) {
        Event event = findById(id);

        event.setStatus(newStatus);

        return eventRepository.save(event);
    }

    @Override
    public Page<Event> all(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    @Override
    public List<Event> byStatus(EEventStatus status) {
        return eventRepository.findByStatus(status);
    }

    @Override
    public Page<Event> byStatus(EEventStatus status, Pageable pageable) {
        return eventRepository.findByStatus(status, pageable);
    }
}
