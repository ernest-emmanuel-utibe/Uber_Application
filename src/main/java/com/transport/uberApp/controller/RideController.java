package com.transport.uberApp.controller;

import com.transport.uberApp.data.dto.request.BookRideRequest;
import com.transport.uberApp.data.dto.response.ApiResponse;
import com.transport.uberApp.data.dto.response.PageDto;
import com.transport.uberApp.data.dto.response.RideDto;
import com.transport.uberApp.service.RideService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/api/v1/ride")
@AllArgsConstructor
public class RideController {
    private final RideService rideService;

    @PostMapping("/book")
//    @Operation(summary = "Book a ride")
    public ResponseEntity<ApiResponse> bookRide(@RequestBody BookRideRequest request){
        return ResponseEntity.ok(rideService.bookRide(request));
    }

    @GetMapping("/history")
//    @Operation(summary = "Get ride history")
    public ResponseEntity<PageDto<RideDto>> getRideHistory(Pageable pageable){
        return ResponseEntity.ok(rideService.getRideHistory(pageable));
    }
}
