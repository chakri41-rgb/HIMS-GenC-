package com.genc.healthins.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import java.util.Map;

@Controller
@RequestMapping("/api/admin/settings")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminSettingsController {

    @PutMapping
    @ResponseBody
    public ResponseEntity<?> updateSettings(@RequestBody Map<String,Object> settings){
        // For now, accept and return -- replace with persistence if needed
        return ResponseEntity.ok(settings);
    }
}
