package ru.enai.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ru.enai.bot.messages.MessageFactory;

@org.springframework.context.annotation.Configuration
@ComponentScan(basePackages = "ru.enai")
public class Configuration {


    @Bean
    MessageFactory messageFactory() {
        return new MessageFactory();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
