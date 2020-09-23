package com.cndym.service.impl;

import java.util.List;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.cndym.email.MailEngine;
import com.cndym.entity.data.pick.DataInfo;
import com.cndym.entity.email.UserEmail;
import com.cndym.service.ISendEmailService;
import com.cndym.util.ConfigUtils;
import com.cndym.util.SpringUtils;
import com.cndym.util.export.Utils;

@Service("sendEmailServiceImpl")
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public class SendEmailServiceImpl implements ISendEmailService {
	private Logger logger = Logger.getLogger(getClass());

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void sendEmail(List<DataInfo> list ,UserEmail email, String permissionType,String flag) {
		logger.info("query email info start");
		send(list,email,permissionType, flag);
	}

	public void send(List<DataInfo> list ,UserEmail email, String permissionType,String flag) {
		MailEngine mailEngine = (MailEngine) SpringUtils.getBean("mailEngine");
		StringBuilder  body=new StringBuilder("<table style='border-collapse: collapse;' >");
		if("fa".equals(permissionType)){
			dataInfoAll(list, body);
		}
		if("fb".equals(permissionType)){
			dataInfoChongZhi(list, body,flag);
		}
		
		if("fc".equals(permissionType)){
			dataInfoTouZhu(list, body,flag);
		}
		body.append("</table>");
		try {
			mailEngine.sendMessage(new String[]{email.getReceiveEmail()},
					ConfigUtils.getValue("MAIL.DEFAULT.FROM"), null,
					body.toString(),
					email.getTitle(), null);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private void dataInfoTouZhu(List<DataInfo> list, StringBuilder body,String flag) {
		String message="count"==flag?"汇总":"";
		String style="style = 'border: 1px solid black;'";
		String styleAlign="style = 'border: 1px solid black;text-align: right;'";
		body.append("<tr>                                   ");
		body.append("	<th "+style+"> 日期"+message+"</th>                       ");
		body.append("	<th "+style+"> 渠道名称"+message+"              </th>        ");
		body.append("	<th "+style+"> 激活数"+message+"                </th>       ");
		body.append("	<th "+style+"> 注册用户数"+message+"              </th>       ");
		body.append("	<th "+style+"> 投注用户数"+message+"              </th>       ");
		body.append("	<th "+style+"> 总投注金额"+message+"              </th>       ");
		body.append("	<th "+style+"> 投注新用户数"+message+"             </th>       ");
		body.append("	<th "+style+"> 新用户投注金额"+message+"            </th>       ");
		body.append("	<th "+style+"> 新用户注册投注转化率"+message+"         </th>       ");
		body.append("	<th "+style+"> 全部用户投注ARPU"+message+"         </th>       ");
		body.append("	<th "+style+"> 新用户投注ARPU"+message+"          </th>       ");
		body.append("	<th "+style+"> 赠金消耗"+message+"               </th>       ");
		body.append("</tr>                                  ");
		for (DataInfo book : list) {
			body.append("<tr>");
			body.append("<td "+styleAlign+">"+book.getTimeFormat()             +"</td>");
			if("count".equals(flag)){
				body.append("<td "+styleAlign+">"+book.getSid()                    +"</td>");
			}else{
				body.append("<td "+styleAlign+">"+book.getChannelName()                    +"</td>");
			}
			
			body.append("<td "+styleAlign+">"+book.getJiHuo()                  +"</td>");
			body.append("<td "+styleAlign+">"+book.getRegisteredUserCount()    +"</td>");
			body.append("<td "+styleAlign+">"+book.getTouZhuUserCount()      +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getTouZhuAllMoney())       +"</td>");
			body.append("<td "+styleAlign+">"+book.getTouZhuNewUserCount()   +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getTouZhuNewUserMonery())  +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getTouZhuNewUserRate())    +"%</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getTouZhuAllUserArpu())    +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getTouZhuNewUserArpu())	+"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getGrantsConsume())        +"</td>");
			body.append("</tr>");
		}
	}

	private void dataInfoAll(List<DataInfo> list, StringBuilder body) {
		String style="style = 'border: 1px solid black;'";
		String styleAlign="style = 'border: 1px solid black;text-align: right;'";
		body.append("<tr>                                 ");
		body.append("	<th "+style+"> 日期</th>                     ");
		body.append("	<th "+style+">渠道汇总              </th>      ");
		body.append("	<th "+style+">激活数汇总               </th>      ");
		body.append("	<th "+style+">DAU汇总               </th>      ");
		body.append("	<th "+style+">注册用户数汇总             </th>      ");
		body.append("	<th "+style+">充值用户数汇总             </th>      ");
		body.append("	<th "+style+">总充值金额汇总             </th>      ");
		body.append("	<th "+style+">全部用户人均充值金额汇总        </th>      ");
		body.append("	<th "+style+">新用户充值金额汇总           </th>      ");
		body.append("	<th "+style+">新用户充值人数汇总           </th>      ");
		body.append("	<th "+style+">新用户人均充值金额汇总         </th>      ");
		body.append("	<th "+style+">新用户充值转化率汇总          </th>      ");
		body.append("	<th "+style+">投注用户数汇总             </th>      ");
		body.append("	<th "+style+">总投注金额汇总             </th>      ");
		body.append("	<th "+style+">全部用户投注arpu汇总        </th>      ");
		body.append("	<th "+style+">新用户投注人数汇总           </th>      ");
		body.append("	<th "+style+">新用户投注金额汇总           </th>      ");
		body.append("	<th "+style+">新用投注转化率汇总           </th>      ");
		body.append("	<th "+style+">新用户投注ARPU汇总         </th>      ");
		body.append("	<th "+style+">赠金金额汇总              </th>      ");
		body.append("	<th "+style+">赠金消耗汇总              </th>      ");
		body.append("</tr>                                ");
		
		for (DataInfo book : list) {
			body.append("<tr>");
			body.append("<td "+styleAlign+">"+book.getTimeFormat()           +"</td>");
			body.append("<td "+styleAlign+">"+book.getSid()                   +"</td>");
			body.append("<td "+styleAlign+">"+book.getJiHuo()                  +"</td>");
			body.append("<td "+styleAlign+">"+book.getDau()  	 +"</td>");
			body.append("<td "+styleAlign+">"+book.getRegisteredUserCount()      +"</td>");
			body.append("<td "+styleAlign+">"+book.getChongZhiUserCount()       +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getChongZhiAllMoney())   	+"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getUserAvgChongZhiMoney())  	+"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getChongZhiNewUserMonery())    +"</td>");
			body.append("<td "+styleAlign+">"+book.getChongZhiNewUserCount()  	+"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getChongZhiNewUserAvgMoney())		+"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getChongZhiNewUserRate())                 +"%</td>");
			body.append("<td "+styleAlign+">"+book.getTouZhuUserCount()           +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getTouZhuAllMoney())                   +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getTouZhuAllUserArpu())                  +"</td>");
			body.append("<td "+styleAlign+">"+book.getTouZhuNewUserCount()  	 +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getTouZhuNewUserMonery())      +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getTouZhuNewUserRate())       +"%</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getTouZhuNewUserArpu())   	+"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getGrants())  	+"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getGrantsConsume())    +"</td>");
			body.append("</tr>");
		}
	}

	private void dataInfoChongZhi(List<DataInfo> list, StringBuilder body,String flag) {
		String message="count"==flag?"汇总":"";
		String style="style = 'border: 1px solid black;'";
		String styleAlign="style = 'border: 1px solid black;text-align: right;'";
		body.append("<tr>                              "); 
		body.append("	<th "+style+">日期 </th>                   "); 
		body.append("	<th "+style+">渠道名称"+message+"            </th>      "); 
		body.append("	<th "+style+">激活数"+message+"            </th>       "); 
		body.append("	<th "+style+">注册用户数"+message+"          </th>       "); 
		body.append("	<th "+style+">充值用户数"+message+"          </th>       "); 
		body.append("	<th "+style+">总充值金额"+message+"          </th>       "); 
		body.append("	<th "+style+">充值新用户数"+message+"         </th>       "); 
		body.append("	<th "+style+">新用户充值金额"+message+"        </th>       "); 
		body.append("	<th "+style+">新用户注册充值转化率"+message+"     </th>       "); 
		body.append("	<th "+style+">全部用户人均充值金额"+message+"     </th>       "); 
		body.append("	<th "+style+">新用户人均充值金额"+message+"      </th>       "); 
		body.append("	<th "+style+">赠金"+message+"             </th>       "); 
		body.append("</tr>                             "); 
		for (DataInfo book : list) {
			body.append("<tr>");
			body.append("<td "+styleAlign+">"+book.getTimeFormat()             +"</td>");
			if("count".equals(flag)){
				body.append("<td "+styleAlign+">"+book.getSid()                    +"</td>");
			}else{
				body.append("<td "+styleAlign+">"+book.getChannelName()                    +"</td>");
			}
			body.append("<td "+styleAlign+">"+book.getJiHuo()                  +"</td>");
			body.append("<td "+styleAlign+">"+book.getRegisteredUserCount()    +"</td>");
			body.append("<td "+styleAlign+">"+book.getChongZhiUserCount()      +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getChongZhiAllMoney())      +"</td>");
			body.append("<td "+styleAlign+">"+book.getChongZhiNewUserCount()   +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getChongZhiNewUserMonery())  +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getChongZhiNewUserRate())    +"%</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getUserAvgChongZhiMoney())   +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getChongZhiNewUserAvgMoney()) +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getGrants())                  +"</td>");
			body.append("</tr>");
		}
	}
}
