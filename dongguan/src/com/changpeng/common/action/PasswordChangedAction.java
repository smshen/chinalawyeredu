/**
 * PasswordChangedException.java
 */

package com.changpeng.common.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.Constants;
import com.changpeng.models.system.SysUser;
import com.changpeng.system.service.SysUserService;

/**
 * 
 * 修改密码
 * 
 * @author 华锋 2008-2-29 下午01:50:06
 * 
 */
public class PasswordChangedAction extends AbstractAction {

	private static Log LOG = LogFactory.getLog(PasswordChangedAction.class);
	/**
	 * 旧密码
	 */
	private String oldpass;
	/**
	 * 新密码
	 */
	private String newpass;
	/**
	 * 再次输入的新密码
	 */
	private String passagain;

	public void setOldpass(String oldpass) {
		this.oldpass = oldpass;
	}

	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}

	public void setPassagain(String passagain) {
		this.passagain = passagain;
	}

	public String getOldpass() {
		return this.oldpass;
	}

	public String getNewpass() {
		return this.newpass;
	}

	public String getPassagain() {
		return this.passagain;
	}
	
	public PasswordChangedAction(){
		this.rightCode="passwdChange";
	}

	@Override
	protected String go() throws Exception {
		LOG.debug("旧密码:" + oldpass + ",新密码:" + newpass + ",确认密码:" + passagain);
		this.nextPage = "passwdChange!input.pl";
		if (!newpass.equals(passagain)) {
			this.message = "您2次输入的密码不符合,请重新输入";

			// 重新返回到输入页面
			return Constants.ACTION_MESSAGE;
		}
		SysUser user = (SysUser) get(Constants.LOGIN_USER);
		SysUserService service = (SysUserService) getBean("sysUserService");
		int result = service.changePass(user.getUserid(), oldpass, newpass);
		if (result == 1) {
			this.message = "您输入的旧密码不符合,请返回重新输入";

			return Constants.ACTION_MESSAGE;
			// 重新返回到输入页面
		}
		message = "您的密码修改成功,请牢记!";
		this.opResult="密码修改成功,旧密码:"+oldpass+",新密码:"+newpass;//记录日志
		// 关闭密码修改页面
		return SUCCESS;
	}
}
