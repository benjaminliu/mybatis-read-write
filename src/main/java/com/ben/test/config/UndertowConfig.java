package com.ben.test.config;

import io.undertow.Undertow;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.undertow.UndertowBuilderCustomizer;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * — <br>
 *
 * @author: 刘恒 <br>
 * @date: 2018/11/12 <br>
 */
@Configuration
public class UndertowConfig {



//    @Bean
//    public UndertowServletWebServerFactory embeddedServletContainerFactory() {
//        UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();
//        factory.addBuilderCustomizers(new UndertowBuilderCustomizer() {
//            @Override
//            public void customize(Undertow.Builder builder) {
//                builder.addHttpListener(8098, "0.0.0.0");
//            }
//        });
//        return factory;
//    }

}
