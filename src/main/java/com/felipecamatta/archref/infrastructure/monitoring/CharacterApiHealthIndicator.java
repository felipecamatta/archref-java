package com.felipecamatta.archref.infrastructure.monitoring;

import com.felipecamatta.archref.configuration.ApplicationConfig;
import com.felipecamatta.archref.infrastructure.http.client.CharacterFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component("CharacterServiceHealth")
public class CharacterApiHealthIndicator implements HealthIndicator {

    private final ApplicationConfig applicationConfig;
    private final CharacterFeignClient characterFeignClient;

    @Override
    public Health health() {
        String url = applicationConfig.getCharacterServiceUrl();

        try {
            characterFeignClient.findAll();
        } catch (Exception e) {
            log.warn("Failed to connect to: {}", url);
            return Health.down()
                    .withDetail("error", e.getMessage())
                    .build();
        }
        return Health.up().build();
    }
}
