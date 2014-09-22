package com.spring.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lxf.tools.FileUploadTools;

//��ʾ����һ�����������������Щע��ע�붼��Ҫ��springmvc�������ļ������ã�����@Service��@Repository
@Controller 
public class ServletController {

	private String path = null;//��תҳ������
	
	//���빦��ҳ��
	@RequestMapping( value = "/")
	public ModelAndView welcome(HttpServletRequest request) {
		this.path = "upload";
		return new ModelAndView(path);
	}
	
	/**
	 * �ύform���������ܵ�ʵ��
	 */
	@RequestMapping( value = "/uploadinfomation")
	public ModelAndView fileupload(HttpServletRequest request) {
		System.out.println("�����ύ��servlet-controller����");
		String tempDir = request.getServletContext().getRealPath("/")+"uploadtemp";//������ʱ�ļ�
		String saveDir = request.getServletContext().getRealPath("/")+"upload"+java.io.File.separator;
		System.out.println("tempDir::"+tempDir);
		System.out.println("saveDir::"+saveDir);
		tempDir = null;
		FileUploadTools fut = null;
		List<String> all = null;
		try {//��ʼ�����������ָ�������ȡ������
			fut = new FileUploadTools(request,1024,tempDir);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String name = fut.getParameter("name");
		String inst[] = fut.getParameterValues("inst");
		System.out.println("name:"+name);
		for(int i=0;i<inst.length;i++){
			System.out.println("��Ȥ��"+inst[i]);
		}
		try {
			all = fut.saveAll(saveDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Iterator<String> it = all.iterator();
		while(it.hasNext()){
			String fileName = it.next();
			System.out.println("�ϴ����ļ����Ƴ�Ϊ��"+fileName);
		}
		this.path = "upload";
		return new ModelAndView(path);
	}
	
}











