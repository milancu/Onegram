package cz.nss.onegram.user.dao;

import cz.nss.onegram.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByUsername(String username);

    public User findByEmail(String email);

    public boolean existsByEmail(String email);

    public boolean existsByUsername(String username);
}
