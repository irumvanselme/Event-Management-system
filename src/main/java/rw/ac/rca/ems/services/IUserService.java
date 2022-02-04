package rw.ac.rca.ems.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rw.ac.rca.ems.models.User;
import rw.ac.rca.ems.models.enums.ERole;
import rw.ac.rca.ems.utils.dtos.RegisterDTO;

import java.util.List;

public interface IUserService {

    Page<User> all(Pageable pageable);

    User findById(Long id);

    Page<User> byRole(ERole role, Pageable pageable);

    User create(RegisterDTO dto);

    boolean isUnique(User user);

    User getLoggedInUser();

    User addTag(Long userId, Long tagId);

    User removeTag(Long userId, Long tagId);

    List<User> findMany(List<Long> userIds);
}
