package lih.server.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 * Created by huanli on 29/04/2017.
 */
@Configuration
@ComponentScan(basePackages = "lih.services")
@MapperScan("lih.services.mapper")
public class Config {

    @Bean
    @Autowired
    public ShaPasswordEncoder shaPasswordEncoder() {

        return new ShaPasswordEncoder();
    }
}
