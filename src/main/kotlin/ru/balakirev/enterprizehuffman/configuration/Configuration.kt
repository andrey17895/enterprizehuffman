package ru.balakirev.enterprizehuffman.configuration

import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Configuration {

    @Bean
    fun modelMapper(): ModelMapper{
        return ModelMapper()
    }
}