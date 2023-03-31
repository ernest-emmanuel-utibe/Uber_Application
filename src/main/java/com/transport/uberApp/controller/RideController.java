package com.transport.uberApp.controller;

import com.transport.uberApp.data.dto.request.BookRideRequest;
import com.transport.uberApp.data.dto.response.ApiResponse;
import com.transport.uberApp.data.dto.response.PageDto;
import com.transport.uberApp.data.dto.response.RideDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.summary;

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

    @GetMapping("")
//    @Operation(summary = "Get ride history")
    public ResponseEntity<PageDto<RideDto>> getHistory(@ParameterObject Pageable pageable){
        return ResponseEntity.ok(rideService.getRideHistory(pageable));
    }
}
