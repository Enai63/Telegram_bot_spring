package ru.enai.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;


import java.time.format.DateTimeFormatter;


public class DateConfig {


    public FormattingConversionService conversionService() {
        DefaultFormattingConversionService conversionService =
                new DefaultFormattingConversionService(false);

        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setDateFormatter(DateTimeFormatter.ofPattern("yyyyMMdd")); //yyyyMMdd dd.MM.yyyy
        registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("yyyyMMddHHmm")); //yyyyMMddHHmm dd.MM.yyyy HH:mm:ss
        registrar.registerFormatters(conversionService);
        return conversionService;
    }
}
