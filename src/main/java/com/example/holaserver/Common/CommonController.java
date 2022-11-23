package com.example.holaserver.Common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CommonController {
    @GetMapping("heartbeat")
    public boolean heartBeat() {
        return true;
    }
}
