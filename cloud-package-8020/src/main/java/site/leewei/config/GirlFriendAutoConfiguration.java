package site.leewei.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site.leewei.service.GirlFriednService;
import site.leewei.service.GirlFriednServiceImpl;

@Configuration
@ConditionalOnClass(GirlFriednService.class)
@EnableConfigurationProperties(GirlfriendProperties.class)
public class GirlFriendAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public GirlFriednService girlFriendService() {
        return new GirlFriednServiceImpl();
    }
}
