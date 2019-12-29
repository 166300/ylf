package cn.e3mall.publish;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Content {
	@Test
	public void publishService() throws Exception{
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		/*//设置死循环两秒钟执行一次(程序不会结束)
		while(true){
			Thread.sleep(2000);
		}*/
		System.out.println("服务开启。。。");
		System.in.read();//输入回车就会停止
		System.out.println("服务停止。。。");
	}
}
