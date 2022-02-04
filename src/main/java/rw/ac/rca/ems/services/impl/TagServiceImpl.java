package rw.ac.rca.ems.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rw.ac.rca.ems.models.Tag;
import rw.ac.rca.ems.repositories.ITagRepository;
import rw.ac.rca.ems.services.ITagService;
import rw.ac.rca.ems.utils.dtos.CreateOrUpdateTagDTO;
import rw.ac.rca.ems.utils.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
public class TagServiceImpl implements ITagService {

    private final ITagRepository tagRepository;

    @Autowired
    public TagServiceImpl(ITagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> all() {
        return tagRepository.findAll();
    }

    @Override
    public Page<Tag> all(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public Tag create(CreateOrUpdateTagDTO dto) {
        return tagRepository.save(new Tag(dto.getTitle(), dto.getDescription()));
    }

    @Override
    public Tag update(Long id, CreateOrUpdateTagDTO dto) {
        Tag tag = findById(id);

        tag.setTitle(dto.getTitle());
        tag.setDescription(tag.getDescription());

        return tagRepository.save(tag);
    }

    @Override
    public Tag findById(Long id) {
        return tagRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tag", "id", id.toString()));
    }

    @Override
    public List<Tag> searchByName(String name) {
        return tagRepository.findByTitleLikeOrDescriptionLike(name, name);
    }

    @Override
    public void remove(Long id) {
        Tag tag = findById(id);

        tagRepository.delete(tag);
    }
}
