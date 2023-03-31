package com.transport.uberApp.service;

import com.transport.uberApp.data.dto.request.InviteAdminRequest;
import com.transport.uberApp.data.dto.response.ApiResponse;

import java.util.Set;

public interface AdminService {
    ApiResponse sendInviteRequests(Set<InviteAdminRequest> inviteAdminRequestList);
}
