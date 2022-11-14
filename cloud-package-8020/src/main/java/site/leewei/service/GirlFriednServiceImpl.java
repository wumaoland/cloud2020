package site.leewei.service;

import org.springframework.beans.factory.annotation.Autowired;
import site.leewei.config.GirlfriendProperties;

public class GirlFriednServiceImpl implements GirlFriednService{
    @Autowired
    private GirlfriendProperties girlFriendServiceProperties;


    @Override
    public void say() {
        String message = girlFriendServiceProperties.getMessage();
        System.out.println("Girl Friend: " + message);
    }
}
