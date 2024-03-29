package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.pojo.TbContent;
import e3.e3mall.common.poji.EasyUIDataGridResult;
import e3.e3mall.common.poji.EasyUITreeNode;
import e3.e3mall.common.util.E3Result;

public interface ContentCategoryService {
	List<EasyUITreeNode> getContentCatList(long parentId);
	E3Result addContentCategory(long parentId,String name);
	boolean delContentCategory(long id);
	void updContentCategory(long id,String name);
	EasyUIDataGridResult getContentList(long categoryId,int page, int rows);
	E3Result delContent(long parentId);
	E3Result updContent(TbContent content);
}
