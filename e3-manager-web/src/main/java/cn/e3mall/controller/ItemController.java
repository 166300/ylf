package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ItemService;
import e3.e3mall.common.poji.EasyUIDataGridResult;
import e3.e3mall.common.util.E3Result;

@Controller
public class ItemController {
	@Autowired
	//查询一条信息
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page,Integer rows){
		//调用服务查询商品列表
		EasyUIDataGridResult result = itemService.getItemList(page,rows);
		return result;
	}
	
	/**
	 * 商品添加功能
	 * 
	 */
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	public E3Result addItem(TbItem item,String desc){
		E3Result result = itemService.additem(item, desc);
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
