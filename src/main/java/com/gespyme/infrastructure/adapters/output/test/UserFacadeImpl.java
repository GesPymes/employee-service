package com.gespyme.infrastructure.adapters.output.test;

import com.gespyme.commons.exeptions.NotFoundException;
import com.gespyme.commons.model.job.AppointmentModelApi;
import com.gespyme.commons.model.user.UserModelApi;
import com.gespyme.domain.facade.UserFacade;
import com.gespyme.rest.RestCallService;
import com.gespyme.rest.RestRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final RestCallService<UserModelApi, UserModelApi> restCallService;

    @Value("${user.endpoint}")
    private String userEndpoint;

    @Override
    public UserModelApi getUserModelApi(String email) {
        return restCallService.performGetCall(RestRequest.builder()
                .server(userEndpoint)
                .path("/user/{email}")
                .headers(Map.of("Content-Type", "application/json"))
                .pathParams(Map.of("email", email))
                .build(), UserModelApi.class).stream().findFirst().orElseThrow(() -> new NotFoundException("User not found"));
    }
}
