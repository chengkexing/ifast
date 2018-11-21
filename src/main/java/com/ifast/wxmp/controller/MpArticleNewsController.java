package com.ifast.wxmp.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ifast.common.annotation.Log;
import com.ifast.common.base.AdminBaseController;
import com.ifast.common.utils.Result;
import com.ifast.wxmp.domain.MpArticleDO;
import com.ifast.wxmp.service.MpArticleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 
 * <pre>
 * 
 * </pre>
 * <small> 2018-10-18 21:21:36 | Aron</small>
 */
@Controller
@RequestMapping("/wxmp/mpArticle/news")
public class MpArticleNewsController extends AdminBaseController {
	@Autowired
	private MpArticleService mpArticleService;
	
	@GetMapping()
	@RequiresPermissions("wxmp:mpArticleNews:mpArticle")
	String MpArticle(){
	    return "wxmp/mpArticleNews/mpArticle";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("wxmp:mpArticleNews:mpArticle")
	public Result<Page<MpArticleDO>> list(MpArticleDO mpArticleDTO){
        Wrapper<MpArticleDO> wrapper = new EntityWrapper<>(mpArticleDTO).orderBy("id", false);
        Page<MpArticleDO> page = mpArticleService.selectPage(getPage(MpArticleDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("wxmp:mpArticleNews:add")
	String add(){
	    return "wxmp/mpArticleNews/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("wxmp:mpArticleNews:edit")
	String edit(@PathVariable("id") Long id,Model model){
		MpArticleDO mpArticle = mpArticleService.selectById(id);
		model.addAttribute("mpArticle", mpArticle);
	    return "wxmp/mpArticleNews/edit";
	}
	
	@Log("添加")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("wxmp:mpArticleNews:add")
	public Result<String> save( MpArticleDO mpArticle){
		mpArticleService.insert(mpArticle);
        return Result.ok();
	}
	
	@Log("修改")
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("wxmp:mpArticleNews:edit")
	public Result<String>  update( MpArticleDO mpArticle){
		boolean update = mpArticleService.updateById(mpArticle);
		return update ? Result.ok() : Result.fail();
	}
	
	@Log("删除")
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("wxmp:mpArticleNews:remove")
	public Result<String>  remove( Long id){
		mpArticleService.deleteById(id);
        return Result.ok();
	}
	
	@Log("批量删除")
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("wxmp:mpArticleNews:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		mpArticleService.deleteBatchIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}