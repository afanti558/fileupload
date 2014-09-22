package com.lxf.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.lxf.utils.IpTimeStamp;

public class FileUploadTools {
	
	private HttpServletRequest request = null;//ȡ��HttpServletRequest����
	
	private List<FileItem> items= null;//����ȫ���ϴ��ļ�
	
	private Map<String,List<String>> params = new HashMap<String,List<String>>();//�������еĲ���
	
	private Map<String,FileItem> files = new HashMap<String,FileItem>();//���������ϴ��ļ�
	
	private int maxSize = 3*1024*1024;//Ĭ���ϴ��ļ��Ĵ�С
	
	
	/**
	 * ʵ����FileUploadTools��
	 * @param request request����
	 * @param max ����ϴ��ļ�����
	 * @param tempDir �ϴ���ʱ����·��
	 */
	@SuppressWarnings("unchecked")
	public FileUploadTools(HttpServletRequest request,int maxSize,String tempDir) throws Exception{
		this.request = request;
		DiskFileItemFactory factory = new DiskFileItemFactory();//�������̹���
		if(tempDir != null){//�ж��Ƿ���Ҫ������ʱ�ϴ�Ŀ¼
			factory.setRepository(new File(tempDir));//������ʱ�ļ�����Ŀ¼
		}
		ServletFileUpload upload = new ServletFileUpload(factory);//����������
		if(maxSize>0){//��������˴�С������ʹ���µĴ�С
			this.maxSize = maxSize;
		}
		try {
			this.items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}//����ȫ������
		this.init();
	}
	
	/**
	 * ��ʼ��������������ͨ�������ϴ��ļ�
	 * author linxiaofan
	 * time 2014��9��15��  ����11:27:38
	 */
	private void init(){
		Iterator<FileItem> iter = this.items.iterator();
		while(iter.hasNext()){
			FileItem item = iter.next();
			if(item.isFormField()){//��ͨ���ı�����
				String name = item.getFieldName();
				String value = item.getString();//ȡ�ò���ֵ
				List<String> temp = null;//��������
				if(this.params.containsKey(name)){//�ж������Ƿ��Ѿ����,���縴ѡ������
					temp = this.params.get(name);
				}else{
					temp = new ArrayList<String>();
				}
				temp.add(value);
				this.params.put(name, temp);
				
			}else{//file���
				String fileName = new IpTimeStamp(this.request.getRemoteAddr()).getTimeStamp() + "." +
						item.getName().split("\\.")[1];
				this.files.put(fileName,item);
			}
		}
	}
	/**
	 * ���ݲ�������ȡ��ȫ������
	 * author linxiaofan
	 * time 2014��9��15��  ����11:06:24
	 * @param name
	 * @return
	 */
	public String getParameter(String name){
		String ret = null;
		List<String> temp = this.params.get(name);
		if(null != temp){
			ret = temp.get(0);
		}
		return ret;
	}
	
	/**
	 * ���ݲ�������ȡ��һ���������
	 * author linxiaofan
	 * time 2014��9��15��  ����11:07:30
	 * @param name
	 * @return
	 */
	public String [] getParameterValues(String name){
		String ret[] = null;
		List<String> temp = this.params.get(name);
		if(temp != null){
			ret = temp.toArray(new String[]{});
		}
		return ret;
	}
	
	/**
	 * ȡ��ȫ�����ϴ��ļ�
	 * author linxiaofan
	 * time 2014��9��15��  ����11:09:31
	 * @return
	 */
	public Map<String,FileItem> getUploadFiles(){
		return this.files;
	}
	
	/**
	 * �Զ�����ȫ���ϴ��ļ��������Ѿ��ϴ����ļ������Ʒ��ظ����ó�
	 * author linxiaofan
	 * time 2014��9��15��  ����11:09:52
	 * @param saveDir
	 * @return
	 * @throws IOException
	 */
	public List<String> saveAll(String saveDir) throws IOException{
		List<String> names = new ArrayList<String>();
		if(this.files.size()>0){
			Set<String> keys = this.files.keySet();
			Iterator<String> iter = keys.iterator();
			File saveFile = null;
			InputStream input = null;
			OutputStream out = null;
			while(iter.hasNext()){
				FileItem item = this.files.get(iter.next());
				String fileName = new IpTimeStamp(this.request.getRemoteAddr()).getIpTimeRand() +
					"."+item.getName().split("\\.")[1];
				saveFile = new File(saveDir+fileName);
				names.add(fileName);
				try {
					input = item.getInputStream();
					out = new FileOutputStream(saveFile);
					int temp = 0;
					byte data[] = new byte[512];
					while((temp = input.read(data,0,512)) != -1){
						out.write(data);
					}
				} catch (IOException e) {
					throw e;
				}finally{
					try {
						input.close();
						out.close();
					} catch (IOException e1) {
						throw e1;
					}
				}
			}
		}else{
//			�ļ���СΪ0
		}
		return names;
	}

}
