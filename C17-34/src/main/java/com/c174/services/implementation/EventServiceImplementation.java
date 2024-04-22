package com.c174.services.implementation;

import com.c174.exception.*;
import com.c174.models.event.*;
import com.c174.repositorys.EventRepository;
import com.c174.services.abstraccion.EventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EventServiceImplementation implements EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    public EventServiceImplementation(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }
    @Override
    @Transactional(readOnly = true)
    public List<EventResponse> getAll() throws EntityNotFoundException {
        List<EventEntity> events = eventRepository.findAllPresent();

        if(events == null || events.isEmpty()){
            throw new EntityNotFoundException("No events found");
        }

        List<EventResponse> eventResponse = eventMapper.toListEventResponse(events);
        return eventResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventResponse> getByName(String name) throws EntityNotFoundException {
        Optional<List<EventEntity>> searchEntities = eventRepository.findByNameIgnoreCaseContaining(name.toLowerCase());
        if(searchEntities.isEmpty() || searchEntities == null || searchEntities.get().isEmpty()){
            throw new EntityNotFoundException("No events found");
        }
        List<EventResponse> response = eventMapper.toListEventResponse(searchEntities.get());
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public EventResponse getById(Long id) throws EntityNotFoundException {
        Optional<EventEntity> searchEntity = eventRepository.findById(id);
        if(searchEntity.isEmpty() || searchEntity == null){
            throw new EntityNotFoundException("No events found");
        }
        EventResponse response = eventMapper.toEventResponse(searchEntity.get());
        return response;
    }

    @Override
    @Transactional
    public EventResponse save(EventRequest event) throws EntityExistsException {
        if(eventRepository.existsByName(event.getName())){
            throw new EntityExistsException("Event already exists");
        }
        EventEntity eventEntity = new EventEntity();
        eventEntity.setName(event.getName());
        eventEntity.setIsPresent(Boolean.TRUE);
        EventEntity eventToResponse = eventRepository.save(eventEntity);
        EventResponse eventResponse = eventMapper.toEventResponse(eventToResponse);
        return eventResponse;
    }


    @Override
    @Transactional
    public EventResponse update(Long id, EventRequest event) throws EntityNotFoundException, EntityUploadException {
        if(!eventRepository.existsById(id) || eventRepository.findById(id).get().getIsPresent() == Boolean.FALSE){
            throw new EntityNotFoundException("Event not found");
        }
        try {
            eventRepository.findById(id).get().getAudit().preUpdate();
            eventRepository.updateEventName(id, event.getName());
            EventEntity eventEntity = eventRepository.findById(id).get();
            EventResponse eventResponse = eventMapper.toEventResponse(eventRepository.save(eventEntity));
            return eventResponse;
        } catch (Exception e){
            throw  new EntityUploadException("Error updating event : " + e.getMessage());
        }
    }
    @Override
    @Transactional
    public String delete(Long id) throws EntityNotFoundException, EntityDeleteException {
        if(!eventRepository.existsById(id)){
            throw new EntityNotFoundException("Event not found");
        }
        try {
            eventRepository.findById(id).get().getAudit().preUpdate();
            eventRepository.updateIsPresent(id,Boolean.FALSE);
            return "Event deleted";
        } catch (Exception e){
            throw  new EntityDeleteException("Error deleting event : " + e.getMessage());
        }
    }

}
