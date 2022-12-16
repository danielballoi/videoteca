package com.training.videoteca.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VideotecaModelMapperConf {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
