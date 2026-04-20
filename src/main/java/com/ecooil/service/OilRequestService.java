package com.ecooil.service;

import com.ecooil.entity.OilRequest;
import com.ecooil.repository.OilRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OilRequestService {

    @Autowired
    private OilRequestRepository oilRepository;

    // ✅ ADD REQUEST
    public OilRequest addOil(OilRequest request) {
        request.setStatus("PENDING");
        request.setRequestDate(LocalDateTime.now());

        OilRequest saved = oilRepository.save(request);

        System.out.println("🔔 Notification: New oil request added by user " + request.getUserId());

        return saved;
    }

    // ✅ APPROVE REQUEST
    public OilRequest approveRequest(Long id, Double price) {
        OilRequest request = oilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus("APPROVED");
        request.setPrice(price);
        request.setActionDate(LocalDateTime.now());

        OilRequest saved = oilRepository.save(request);

        System.out.println("🔔 Notification: Request approved for user " + request.getUserId());

        return saved;
    }

    // ✅ REJECT REQUEST
    public OilRequest rejectRequest(Long id) {
        OilRequest request = oilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus("REJECTED");
        request.setActionDate(LocalDateTime.now());

        OilRequest saved = oilRepository.save(request);

        System.out.println("🔔 Notification: Request rejected for user " + request.getUserId());

        return saved;
    }

    // ✅ DASHBOARD SUMMARY (COUNT + DATA)
    public Map<String, Object> getDashboardSummary(String role, Long userId) {

        List<OilRequest> list;

        if (role.equals("COMPANY")) {
            list = oilRepository.findAll();
        } else {
            list = oilRepository.findByUserId(userId);
        }

        long total = list.size();
        long pending = list.stream().filter(r -> "PENDING".equals(r.getStatus())).count();
        long approved = list.stream().filter(r -> "APPROVED".equals(r.getStatus())).count();

        Map<String, Object> response = new HashMap<>();
        response.put("total", total);
        response.put("pending", pending);
        response.put("approved", approved);
        response.put("data", list);

        return response;
    }
}