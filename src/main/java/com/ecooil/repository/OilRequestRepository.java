package com.ecooil.repository;

import com.ecooil.entity.OilRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OilRequestRepository extends JpaRepository<OilRequest, Long> {

    List<OilRequest> findByUserId(Long userId);

    // 🔥 FIXED
    OilRequest findTopByUserIdOrderByRequestDateDesc(Long userId);

}