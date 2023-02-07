package com.example.holaserver.CustomerSatisfaction.DTO;

import com.example.holaserver.CustomerSatisfaction.CustomerSatisfaction;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerSatisfactionBody {
    private Long userId;
    private Integer type;
    private String content;

    public CustomerSatisfaction createSaveCustomerSatisfactionBuilder(CustomerSatisfactionBody csBody) {
        return CustomerSatisfaction.builder()
                .userId(csBody.getUserId())
                .type(csBody.getType())
                .content(csBody.getContent())
                .build();
    }
}
