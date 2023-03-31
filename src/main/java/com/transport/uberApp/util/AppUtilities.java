package com.transport.uberApp.util;

import com.transport.uberApp.data.dto.request.LocationDto;
import com.transport.uberApp.exception.BusinessLogicException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.stream.Collectors;

public class AppUtilities {
    public static final int NUMBER_OF_ITEMS_PER_PAGE = 3;
    private static final String USER_VERIFICATION_BASE_URL="localhost:9090/api/v1/user/account/verify";
    public static  final String WELCOME_MAIL_TEMPLATE_LOCATION="C:\\Users\\semicolon\\Documents\\code\\springboot-projects\\uber_deluxe\\src\\main\\resources\\welcome.txt";

    public static final String EMAIL_REGEX_STRING="^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String ADMIN_INVITE_MAIL_TEMPLATE_LOCATION="C:\\Users\\semicolon\\Documents\\code\\springboot-projects\\uber_deluxe\\src\\main\\resources\\adminMail.txt";

    public static final String JSON_CONSTANT="json";

    public static final String TRANSPORT_MODE="driving";

    public static final String UBER_DELUXE_TEST_IMAGE="C:\\Users\\semicolon\\Documents\\code\\springboot-projects\\uber_deluxe\\src\\main\\resources\\th.webp";

    public static String getMailTemplate(){
        try (BufferedReader reader = new BufferedReader(new FileReader(
                WELCOME_MAIL_TEMPLATE_LOCATION))){
            return reader.lines().collect(Collectors.joining());
        }catch (IOException exception){
            throw new BusinessLogicException(exception.getMessage());
        }
    }

    public static String getAdminMailTemplate(){
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(ADMIN_INVITE_MAIL_TEMPLATE_LOCATION))){
            return reader.lines().collect(Collectors.joining());
        }catch (IOException exception){
            throw new BusinessLogicException(exception.getMessage());
        }
    }

    public static String generateVerificationLink(Long userId){
        return USER_VERIFICATION_BASE_URL+"?userId="+userId+"&token="+generateVerificationToken();
    }

    private static String generateVerificationToken() {
        return Jwts.builder()
                .setIssuer("uberApp")
                .signWith(SignatureAlgorithm.HS256,
                        TextCodec.BASE64.decode("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E="))
                .setIssuedAt(new Date())
                .compact();
    }

    public static boolean isValidToken(String token){
        return Jwts.parser()
                .isSigned(token);
    }


    public static String buildLocation(LocationDto locationDto){
        return locationDto.getHouseNumber() + "," + locationDto.getStreet() + "," + locationDto.getCity()+ locationDto.getState();
    }


    public static BigDecimal calculateRideFare(String distance){
        return BigDecimal
                .valueOf(Double.parseDouble(distance.split("k")[0]))
                .multiply(BigDecimal.valueOf(1000));
    }
}
