package com.example.holaserver.CustomerSatisfaction;

import com.example.holaserver.Common.response.ResponseTemplate;
import com.example.holaserver.CustomerSatisfaction.DTO.CustomerSatisfactionBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CustomerSatisfactionController {
    private final CustomerSatisfactionService customerSatisfactionService;
    @PostMapping("/cs")
    public ResponseTemplate<Map<String, Object>> customerSatisfactionSave(@RequestBody CustomerSatisfactionBody csBody) {
        return new ResponseTemplate<>(customerSatisfactionService.saveCustomerSatisfaction(csBody), "CS 접수 성공");
    }

}
