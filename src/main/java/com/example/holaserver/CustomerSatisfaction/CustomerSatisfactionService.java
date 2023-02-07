package com.example.holaserver.CustomerSatisfaction;

import com.example.holaserver.Auth.AuthService;
import com.example.holaserver.CustomerSatisfaction.DTO.CustomerSatisfactionBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerSatisfactionService {
    private final CustomerSatisfactionRepository customerSatisfactionRepository;
    private final AuthService authService;

    public Map<String, Object> saveCustomerSatisfaction(CustomerSatisfactionBody csBody) {
        authService.getPayloadByToken();
        ModelMap result = new ModelMap();
        result.addAttribute("customerSatisfactionId", customerSatisfactionRepository.save(csBody.createSaveCustomerSatisfactionBuilder(csBody)).getId());
        return result;
    }
}
