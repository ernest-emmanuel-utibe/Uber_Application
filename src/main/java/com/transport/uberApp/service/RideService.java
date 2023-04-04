package com.transport.uberApp.service;

import com.transport.uberApp.data.dto.request.BookRideRequest;
import com.transport.uberApp.data.dto.response.ApiResponse;
import com.transport.uberApp.data.dto.response.PageDto;
import com.transport.uberApp.data.dto.response.RideDto;

import org.springframework.data.domain.Pageable;

public interface RideService {
    ApiResponse bookRide(BookRideRequest request);
    PageDto<RideDto> getRideHistory(Pageable pageable);
}
