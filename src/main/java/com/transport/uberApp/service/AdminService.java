package com.transport.uberApp.service;

import java.util.Set;

public interface AdminService {
    ApiResponse sendInviteRequests(Set<InviteAdminRequest> inviteAdminRequestList);
}
