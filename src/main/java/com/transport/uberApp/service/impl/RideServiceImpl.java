package com.transport.uberApp.service.impl;

import com.transport.uberApp.config.distance.DistanceConfig;
import com.transport.uberApp.data.dto.request.BookRideRequest;
import com.transport.uberApp.data.dto.response.ApiResponse;
import com.transport.uberApp.data.dto.response.DistanceMatrixElement;
import com.transport.uberApp.data.dto.response.PageDto;
import com.transport.uberApp.data.dto.response.RideDto;
import com.transport.uberApp.data.models.Location;
import com.transport.uberApp.data.models.Passenger;
import com.transport.uberApp.data.models.Ride;
import com.transport.uberApp.data.repositories.RideRepository;
import com.transport.uberApp.service.PassengerService;
import com.transport.uberApp.service.RideService;
import com.transport.uberApp.util.AppUtilities;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class RideServiceImpl implements RideService {
    private final RideRepository rideRepository;
    private final DistanceConfig directionConfig;
    private final PassengerService passengerService;
    private final ModelMapper modelMapper;


    @Override
    public ApiResponse bookRide(BookRideRequest request) {
        //1. find passenger
        Passenger foundPassenger = passengerService.getCurrentPassenger();
        //2. calculate distance between origin and destination
        DistanceMatrixElement distanceInformation = getDistanceInformation(request.getOrigin(), request.getDestination());
        //3. calculate eta
        String eta = distanceInformation.getDuration().getText();
        //4. calculate price
        BigDecimal fare = AppUtilities.calculateRideFare(distanceInformation.getDistance().getText());
        Ride ride = Ride.builder()
                .passenger(foundPassenger)
                .origin(modelMapper.map(request.getOrigin(), Location.class))
                .destination(modelMapper.map(request.getDestination(), Location.class))
                .eta(eta)
                .fare(fare)
                .build();
        rideRepository.save(ride);
        return ApiResponse.builder().fare(fare).estimatedTimeOfArrival(eta).build();
    }

    @Override
    public PageDto<RideDto> getRideHistory(Pageable pageable) {
        Passenger passenger = passengerService.getCurrentPassenger();
        Page<Ride> rides = rideRepository.findAllByPassenger(passenger, pageable);

        Type pageDtoTypeToken = new TypeToken<PageDto<RideDto>>() {
        }.getType();
        return modelMapper.map(rides, pageDtoTypeToken);
    }

    private DistanceMatrixElement getDistanceInformation(LocationDto origin, LocationDto destination) {
        RestTemplate restTemplate = new RestTemplate();
        String url = buildDistanceRequestUrl(origin, destination);
        ResponseEntity<GoogleDistanceResponse> response =
                restTemplate.getForEntity(url, GoogleDistanceResponse.class);
        return Objects.requireNonNull(response.getBody()).getRows().stream()
                .findFirst().orElseThrow(() -> new BusinessLogicException("Distance error"))
                .getElements().stream()
                .findFirst()
                .orElseThrow(() -> new BusinessLogicException("Distance error"));
    }

    private String buildDistanceRequestUrl(LocationDto origin, LocationDto destination) {
        ;
        return directionConfig.getGoogleDistanceUrl() + "/" + AppUtilities.JSON_CONSTANT + "?"
                + "destinations=" + AppUtilities.buildLocation(destination) + "&origins="
                + AppUtilities.buildLocation(origin) + "&mode=driving" + "&traffic_model=pessimistic"
                + "&departure_time=" + LocalDateTime.now().toEpochSecond(ZoneOffset.of("+01:00"))
                + "&key=" + directionConfig.getGoogleApiKey();
    }


}
