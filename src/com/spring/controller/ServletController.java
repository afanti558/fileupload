package com.spring.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lxf.tools.FileUploadTools;

//表示这是一个控制器类组件，这些注解注入都需要在springmvc的配置文件中配置，还有@Service和@Repository
@Controller 
public class ServletController {

	private String path = null;//跳转页面名称
	
	//进入功能页面
	@RequestMapping( value = "/")
	public ModelAndView welcome(HttpServletRequest request) {
		this.path = "upload";
		return new ModelAndView(path);
	}
	
	/**
	 * 提交form表单基本功能的实现
	 */
	@RequestMapping( value = "/uploadinfomation")
	public ModelAndView fileupload(HttpServletRequest request) {
		System.out.println("进入提交的servlet-controller中了");
		String tempDir = request.getServletContext().getRealPath("/")+"uploadtemp";//保存临时文件
		String saveDir = request.getServletContext().getRealPath("/")+"upload"+java.io.File.separator;
		System.out.println("tempDir::"+tempDir);
		System.out.println("saveDir::"+saveDir);
		tempDir = null;
		FileUploadTools fut = null;
		List<String> all = null;
		try {//初始化，并且区分各个表单域取得内容
			fut = new FileUploadTools(request,1024,tempDir);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String name = fut.getParameter("name");
		String inst[] = fut.getParameterValues("inst");
		System.out.println("name:"+name);
		for(int i=0;i<inst.length;i++){
			System.out.println("兴趣："+inst[i]);
		}
		try {
			all = fut.saveAll(saveDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Iterator<String> it = all.iterator();
		while(it.hasNext()){
			String fileName = it.next();
			System.out.println("上传的文件名称称为："+fileName);
		}
		this.path = "upload";
		return new ModelAndView(path);
	}
	
}











