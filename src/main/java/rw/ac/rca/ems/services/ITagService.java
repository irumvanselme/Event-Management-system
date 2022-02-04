package rw.ac.rca.ems.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rw.ac.rca.ems.models.Tag;
import rw.ac.rca.ems.utils.dtos.CreateOrUpdateTagDTO;

import java.util.List;

public interface ITagService {

    List<Tag> all();

    Page<Tag> all(Pageable pageable);

    Tag create(CreateOrUpdateTagDTO dto);

    Tag update(Long id, CreateOrUpdateTagDTO dto);

    Tag findById(Long id);

    List<Tag> searchByName(String name);

    void remove(Long id);
}
