package cn.e3mall.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import e3.e3mall.common.util.FastDFSClient;
import e3.e3mall.common.util.JsonUtils;

/**
 * 
 * 图片上传
 * @author StaryL
 *
 */
@Controller
public class PictureController {
	
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
	@RequestMapping(value="/pic/upload")
//	@RequestMapping(value="/pic/upload",
//		produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
//		指定字符集
	@ResponseBody
	public String uploadFile(MultipartFile uploadFile) {
		try {
			//把图片上传图片服务器
			FastDFSClient fastDFSClient=new FastDFSClient("classpath:conf/client.conf");
			//取文件拓展名
			String originalFilename = uploadFile.getOriginalFilename();
			String extName=originalFilename.substring(originalFilename.lastIndexOf(".")+1);
			//得到图片地址 和文件名
			String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
			//补充完整url
			url=IMAGE_SERVER_URL+url;
			//封装到map中返回
			Map result=new HashMap<>();
			result.put("error", 0);
			result.put("url", url);
			return JsonUtils.objectToJson(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//封装到map中返回(错误)
			Map result=new HashMap<>();
			result.put("error", 1);
			result.put("message", "图片上传失败");
			return JsonUtils.objectToJson(result);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
