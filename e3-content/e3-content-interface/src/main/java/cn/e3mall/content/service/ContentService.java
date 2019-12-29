package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.pojo.TbContent;
import e3.e3mall.common.util.E3Result;

public interface ContentService {
	E3Result addContent(TbContent content);
	List<TbContent> getContentListByCid(long cid);
}
