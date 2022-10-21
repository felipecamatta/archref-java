package com.felipecamatta.archref.configuration;

import com.felipecamatta.archref.domain.ports.CharacterHttp;
import com.felipecamatta.archref.domain.ports.PersonCache;
import com.felipecamatta.archref.domain.ports.PersonEvent;
import com.felipecamatta.archref.domain.ports.PersonRepository;
import com.felipecamatta.archref.domain.services.PersonServiceImpl;
import com.felipecamatta.archref.infrastructure.amqp.adapter.PersonAmqpImpl;
import com.felipecamatta.archref.application.amqp.outbound.PersonEventPublisher;
import com.felipecamatta.archref.infrastructure.cache.adapter.PersonRedisRepositoryImpl;
import com.felipecamatta.archref.infrastructure.cache.repositories.PersonRedisRepository;
import com.felipecamatta.archref.infrastructure.database.sql.adapter.PersonJpaRepositoryImpl;
import com.felipecamatta.archref.infrastructure.database.sql.repositories.PersonJpaRepository;
import com.felipecamatta.archref.infrastructure.http.adapter.CharacterHttpImpl;
import com.felipecamatta.archref.infrastructure.http.client.CharacterFeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public CharacterHttp characterHttp(CharacterFeignClient characterHttp) {
        return new CharacterHttpImpl(characterHttp);
    }

    @Bean
    public PersonCache personCachePort(PersonRedisRepository personRedisRepository) {
        return new PersonRedisRepositoryImpl(personRedisRepository);
    }

    @Bean
    public PersonEvent personEventPort(PersonEventPublisher personEventPublisher) {
        return new PersonAmqpImpl(personEventPublisher);
    }

    @Bean
    public PersonRepository personRepositoryPort(PersonJpaRepository personRepository) {
        return new PersonJpaRepositoryImpl(personRepository);
    }

    @Bean
    public PersonServiceImpl personService(CharacterHttp characterHttp, PersonCache personCache,
                                           PersonEvent personEvent, PersonRepository personRepository) {
        return new PersonServiceImpl(characterHttp, personCache, personEvent, personRepository);
    }
}
