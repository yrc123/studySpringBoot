package com.studyspringboot.ch2;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("user1")
@Schema(name = "user对象1",description = "user对象2")
public class User {

    //依赖注入
    @Autowired
    Phone phone;
    public User(@Autowired Phone phone){
        this.phone=phone;
    }
    public User(){}

    @Schema(example = "114514",description = "用户id")
    @Value("1")
    private Long id;
    @Value("ctest")
    private String username;
    @Value("ctest")
    private String password;
    //从properties注入
    @Value("${server.port}")
    private int port;

    public String usePhone(){
        return this.phone.use();
    }
    public String callPhoneNum(String num){
        return this.phone.call(num);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
