package com.transport.uberApp.service.impl;

import com.transport.uberApp.data.dto.request.InviteAdminRequest;
import com.transport.uberApp.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminServiceImplTest {
    @Autowired
    private AdminService adminService;

    private Set<InviteAdminRequest> inviteAdminRequests;

    @BeforeEach
    void setUp() {
        inviteAdminRequests=Set.of(
          new InviteAdminRequest("sam736@gmail.com", "Ernest")
        );
    }

    @Test
    void sendAdminAnInviteRequestTest() {
        var response = adminService.sendInviteRequests(inviteAdminRequests);
        assertThat(response).isNotNull();
    }


}