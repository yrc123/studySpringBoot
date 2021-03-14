package com.studyspringboot.ch10.inteceptor;

import com.studyspringboot.ch2.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Tag(name="user")
@Controller
@RequestMapping("/interceptor")
public class MyController {
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "成功", content = {@Content(mediaType = "application/json")})
	})
	@Operation(summary = "返回传入的用户", description = "test")
	@RequestMapping("test")
	@ResponseBody
	public User test(@Parameter(description = "一个用户",required = true) @RequestBody User user){
		return user;
	}
}
