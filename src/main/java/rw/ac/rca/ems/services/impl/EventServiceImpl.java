package rw.ac.rca.ems.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rw.ac.rca.ems.models.Event;
import rw.ac.rca.ems.models.User;
import rw.ac.rca.ems.models.enums.EEventStatus;
import rw.ac.rca.ems.repositories.IEventRepository;
import rw.ac.rca.ems.services.IEventService;
import rw.ac.rca.ems.services.IUserService;
import rw.ac.rca.ems.utils.dtos.CreateOrUpdateEventDTO;
import rw.ac.rca.ems.utils.exceptions.ResourceNotFoundException;

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
