package com.studyspringboot.ch10.upload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FileController {
	@CrossOrigin(origins = "*")
	@RequestMapping("/upload")
	@ResponseBody
	public Map<String,Object> upload(HttpServletRequest req) throws IOException {
		boolean flag=false;
		MultipartHttpServletRequest mreq = null;
		if(req instanceof MultipartHttpServletRequest){
			mreq= (MultipartHttpServletRequest) req;
		}else{
			return dealResultMap(false,"failed");
		}
		List<MultipartFile> mfiles=mreq.getFiles("file");
		for(MultipartFile file : mfiles){
			File up = new File(file.getOriginalFilename());
			int dot = file.getOriginalFilename().lastIndexOf(".");
			String fileName = file.getOriginalFilename().substring(0,dot);
			String suf = up.getName().substring(dot);
			up = new File(fileName+(new Date().getTime())+suf);
			file.transferTo(up);
		}
		return dealResultMap(true,"success");
	}
	private Map<String,Object> dealResultMap(boolean flag, String s){
		HashMap<String, Object> map = new HashMap<>();
		map.put("success",flag);
		map.put("msg",s);
		return map;
	}
}
