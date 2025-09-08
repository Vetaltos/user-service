package ru.sechko.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sechko.user.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
