package cn.e3mall.pagehelper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemExample;

public class PagehelpTest {
	@Test
	public void textPagehelper() throws Exception{
		//初始化spring容器
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		//从容器获得mapper代理对象
		TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
		//执行分页前设置分页信息使用pagehelper的startPage的方法  第一页第十条
		PageHelper.startPage(1, 10);
		//执行查询
		TbItemExample example=new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		//取分页信息PageInfo.1、总记录数  2、总页数 当前页码
		PageInfo<TbItem> pageInfo=new PageInfo<>(list);
		System.out.println(pageInfo.getTotal());//总记录数
		System.out.println(pageInfo.getPages());//总页数
		System.out.println(list.size());//当前的记录数
		System.out.println(list.get(1));
		
	}
}
