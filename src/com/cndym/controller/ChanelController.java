package com.cndym.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cndym.entity.channel.Channel;
import com.cndym.entity.user.User;
import com.cndym.service.IChannelService;
import com.cndym.util.export.Utils;

@Controller
@RequestMapping("/channel")
public class ChanelController extends BaseController {
	@Resource
	private IChannelService channelServiceImpl;

	@RequestMapping("/searchChannel")
	public void toExportFile(HttpServletRequest request, HttpServletResponse response, Model model) {

		String name = Utils.formatStr(request.getParameter("name"), "");
		String code = Utils.formatStr(request.getParameter("channelCode"), "");
		String seaType = Utils.formatStr(request.getParameter("seaType"));

		User user = (User) request.getSession().getAttribute("LOGON_USER");

		if (Utils.isNotEmpty(user) && Utils.isNotEmpty(user.getCompanyCode())) {

			List<Channel> channelList = new ArrayList<Channel>();
			if (Utils.isNotEmpty(name) || Utils.isNotEmpty(code)) {
				if ("NAME".equals(seaType)) {
					channelList = channelServiceImpl.getChannelList(name, null,user.getCompanyCode());
				} else {
					channelList = channelServiceImpl.getChannelList(null, code,user.getCompanyCode());
				}
			}
			JSONObject jsonObject = new JSONObject();
			Map<String, String> channelMap = new HashMap<String, String>();
			if (Utils.isNotEmpty(channelList)) {
				for (Channel cha : channelList) {
					channelMap.put(cha.getSid(), cha.getName());
				}
				jsonObject = JSONObject.fromObject(channelMap);
			}
			try {
				response.getWriter().write(jsonObject.toString());
				response.flushBuffer();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
