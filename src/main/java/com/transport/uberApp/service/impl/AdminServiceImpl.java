package com.transport.uberApp.service.impl;

import com.transport.uberApp.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sendinblue.ApiResponse;

import java.util.Set;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    @Override
    public ApiResponse sendInviteRequests(Set<InviteAdminRequest> inviteAdminRequestList) {
        EmailNotificationRequest request = new EmailNotificationRequest();
        var recipients = inviteAdminRequestList.stream()
                .map(inviteAdminRequest -> createAdminProfile(inviteAdminRequest))
                .map(inviteAdminRequest -> new Recipient(inviteAdminRequest.getUserDetails().getName(), inviteAdminRequest.getUserDetails().getEmail()))
                .toList();
        request.getTo().addAll(recipients);

        String adminMail = AppUtilities.getAdminMailTemplate();
        request.setHtmlContent(String.format(adminMail, "admin", AppUtilities.generateVerificationLink(0L)));
        var response = mailService.sendHtmlMail(request);
        if (response!=null) return ApiResponse.builder().message("invite requests sent").build();
        throw new BusinessLogicException("invite requests sending failed");
    }
}
