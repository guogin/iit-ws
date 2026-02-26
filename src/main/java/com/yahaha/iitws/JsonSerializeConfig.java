package com.yahaha.iitws;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.yahaha.iit.calc.MonetaryAmountSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.money.MonetaryAmount;

@Configuration
public class JsonSerializeConfig {
    @Bean
    public Module monetaryAmountModule() {
        SimpleModule module = new SimpleModule("MonetaryAmountSerializer",
                new Version(1, 0, 0, null, null, null));
        module.addSerializer(MonetaryAmount.class, new MonetaryAmountSerializer());
        return module;
    }
}
