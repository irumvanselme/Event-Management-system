package rw.ac.rca.ems.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.ems.models.User;
import rw.ac.rca.ems.models.enums.ERole;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {

    Page<User> findByRole(ERole role, Pageable pageable);

    Optional<User> findByEmailOrPhoneNumberOrNationalId(String email, String phoneNumber, String nationalId);

    Optional<User> findByEmailOrPhoneNumber(String email, String phoneNumber);

    Optional<User> findByEmail(String email);
}
