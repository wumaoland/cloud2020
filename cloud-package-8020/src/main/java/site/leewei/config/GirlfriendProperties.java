package site.leewei.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.girlfriend")
public class GirlfriendProperties {
    /** 默认输出 */
    private String message = "Hi, good morning !";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
