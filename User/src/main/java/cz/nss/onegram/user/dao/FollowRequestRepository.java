package cz.nss.onegram.user.dao;

import cz.nss.onegram.user.model.FollowRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRequestRepository extends JpaRepository<FollowRequest, Integer> {
}
