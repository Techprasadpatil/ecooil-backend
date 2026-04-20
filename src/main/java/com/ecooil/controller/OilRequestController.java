package com.ecooil.controller;

import com.ecooil.entity.OilRequest;
import com.ecooil.service.OilRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/oil")
public class OilRequestController {

    @Autowired
    private OilRequestService oilService;

    // ✅ ADD
    @PostMapping("/add")
    public OilRequest addOil(@RequestBody OilRequest request) {
        return oilService.addOil(request);
    }

    // ✅ APPROVE
    @PutMapping("/approve/{id}")
    public OilRequest approve(@PathVariable Long id,
                              @RequestParam Double price) {
        return oilService.approveRequest(id, price);
    }

    // ✅ REJECT (NEW)
    @PutMapping("/reject/{id}")
    public OilRequest reject(@PathVariable Long id) {
        return oilService.rejectRequest(id);
    }

    // ✅ DASHBOARD SUMMARY (NEW)
    @GetMapping("/dashboard-summary")
    public Map<String, Object> dashboardSummary(
            @RequestParam String role,
            @RequestParam Long userId) {

        return oilService.getDashboardSummary(role, userId);
    }
}