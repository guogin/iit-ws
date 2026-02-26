package com.yahaha.iitws;

import com.yahaha.iit.calc.IITCalculator;
import com.yahaha.iit.calc.IITCalculatorImpl2024;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IITCalculatorConfig {
    @Bean
    public IITCalculator iitCalculator() {
        return new IITCalculatorImpl2024();
    }
}
