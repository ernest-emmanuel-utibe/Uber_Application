package com.transport.uberApp.service.impl;

import com.transport.uberApp.data.dto.request.RegisterDriverRequest;
import com.transport.uberApp.service.DriverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DriverServiceImplTest {
    @Autowired
    private DriverService driverService;
    private RegisterDriverRequest request;
    @BeforeEach
    void setUp() {
        request=new RegisterDriverRequest();
        request.setPassword("test_password");
        request.setName("Hong Kong");
        request.setEmail("sam123@email.com");
    }

//    @Test
//    void register() throws IOException {
//        MockMultipartFile file =
//                new MockMultipartFile("test_license",
//                        new FileInputStream(UBER_APP_TEST_IMAGE));
//        request.setLicenseImage(file);
//        var response = driverService.register(request);
//        assertThat(response).isNotNull();
//        assertThat(response.isSuccess()).isTrue();
//
//    }
}