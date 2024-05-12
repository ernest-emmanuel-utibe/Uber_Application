 package com.transport.uberApp.service.impl;

import com.transport.uberApp.data.dto.request.EmailNotificationRequest;
import com.transport.uberApp.data.dto.request.InviteAdminRequest;
import com.transport.uberApp.data.dto.request.Recipient;
import com.transport.uberApp.data.dto.response.ApiResponse;
import com.transport.uberApp.data.models.Admin;
import com.transport.uberApp.data.models.AppUser;
import com.transport.uberApp.data.repositories.AdminRepository;
import com.transport.uberApp.exception.BusinessLogicException;
import com.transport.uberApp.notification.MailService;
import com.transport.uberApp.service.AdminService;
import com.transport.uberApp.util.AppUtilities;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final MailService mailService;

    @Override
    public ApiResponse sendInviteRequests(Set<InviteAdminRequest> inviteAdminRequestList) {
        EmailNotificationRequest request = new EmailNotificationRequest();
        var recipients = inviteAdminRequestList.stream()
                .map(inviteAdminRequest -> createAdminProfile(inviteAdminRequest))
                .map(inviteAdminRequest -> new Recipient(inviteAdminRequest.getUserDetails().getName() ,
                        inviteAdminRequest.getUserDetails().getEmail()))
                .toList();
        request.getTo().addAll(recipients);
     

        String adminMail = AppUtilities.getAdminMailTemplate();
        request.setHtmlContent(String.format(adminMail, "admin", AppUtilities.generateVerificationLink(0L)));
        var response = mailService.sendHtmlMail(request);
        if (response != null) return ApiResponse.builder().message("Invite request sent").build();
        throw new BusinessLogicException("Invite request sending failed");
    }

    private Admin createAdminProfile(InviteAdminRequest inviteAdminRequest) {
        Admin admin = new Admin();
        admin.setUserDetails(new AppUser());
        admin.getUserDetails().setName(inviteAdminRequest.getName());
        admin.getUserDetails().setEmail(inviteAdminRequest.getEmail());
        return admin;
    }
}
