package com.cndym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/link")
public class AllLinksController extends BaseController {
	/**
	 * @Name: toLogonPage
	 * @Description:根据传入的对象，实现增加功能
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date:2016年3月4日13:49:18
	 * @Parameters:无
	 * @Return: 到达用户登录页面
	 */
	@RequestMapping("/toLinkPage")
	public String toLogonPage(){
		return "/linkPage/link";
	}
}
 