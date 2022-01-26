package com.okava.pay.services;

import com.okava.pay.models.Tag;
import com.okava.pay.utils.dtos.CreateOrUpdateTagDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
