package com.studyspringboot.ch2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value={"classpath:user.properties"},ignoreResourceNotFound = true)
public class Ch2Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Ch2Application.class, args);
//        User user = (User)ctx.getBean("user");
        User user1 = (User) ctx.getBean("user1");
        User user2 = (User) ctx.getBean("propUser");
//        System.out.println(user);
        System.out.println(user1);
        System.out.println(user1.getPort());
        System.out.println(user2.getPort());

        Myprop myprop = ctx.getBean(Myprop.class);
        System.out.println(myprop.getPassword());

        PScope pScope1 = ctx.getBean(PScope.class);
        PScope pScope2 = ctx.getBean(PScope.class);
        System.out.println(pScope1.toString()+" "+pScope2.toString());

        SScope sScope1 = ctx.getBean(SScope.class);
        SScope sScope2 = ctx.getBean(SScope.class);
        System.out.println(sScope1.toString()+" "+sScope2.toString());
    }

}
