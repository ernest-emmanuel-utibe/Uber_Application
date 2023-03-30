package com.transport.uberApp.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotificationRequest {
    private  final Sender sender = new Sender("uber_deluxe", "noreply@uberdeluxe.net");
    private List<Recipient> to=new ArrayList<>();
    private final String subject="Welcome to uber_deluxe";
    private String htmlContent;
}
