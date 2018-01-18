package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ecache.Impl.SystemCacheImpl;
import com.model.StaffInfo;
import com.model.YoRoleModule;
import com.model.YoUserRole;
import com.service.IStaffInfoService;
import com.service.OrgService;
import com.util.GlobalConstant;

@Controller
public class LoginController {
  @Autowired 
  public IStaffInfoService staffInfoService;
  @Autowired
  private OrgService orgService;

	ValueWrapper roleCache =SystemCacheImpl.cache.get("user_role");
  	ValueWrapper moduleCache =SystemCacheImpl.cache.get("role_module");

  /**
   * 登录验证
   * @param request
   * @param name
   * @param password
   * @return
   */
  @RequestMapping("/login.do")
  @ResponseBody
  public String login(HttpServletRequest request,String name,String password){
	  StaffInfo staffInfo=new StaffInfo();
	  staffInfo.setStaffId(name);
	 List<StaffInfo> staffList = staffInfoService.searchStaffInfoByEntity(staffInfo);
	 if(staffList == null || staffList.isEmpty()){
		 return "fail";
	 }else{
		 staffInfo = staffList.get(0);
		 request.getSession().setAttribute(GlobalConstant.user_department,staffInfo.getDepartment());
		 request.getSession().setAttribute(GlobalConstant.user_name, staffInfo.getName());
		 request.getSession().setAttribute(GlobalConstant.user_staffId,staffInfo.getStaffId());
		 request.getSession().setAttribute(GlobalConstant.user_staff_user_id,staffInfo.getStaffUserId());
		 List <YoUserRole> userRoleList =orgService.getAllUserRole();//(List<YoUserRole>) roleCache.get();
		 List<String> strList  = new ArrayList<String>();
		 for(YoUserRole userRole :userRoleList){
			 if(staffInfo.getStaffId().equals(userRole.getStaffId()) && password.equals(userRole.getPassword())){			//同时验证用户名密码
				 strList.add(userRole.getRole());
				 request.getSession().setAttribute("user_role",userRole.getRole());
				 break;
			 }
		 }
		
		 if(strList.size()==0){
			System.out.println("当前用户数据库中存在，但是没有任何界面的访问信息");
			return "error";
		}
		   //角色对应模块的信息
			List<YoRoleModule> moduleList = (List<YoRoleModule>) moduleCache.get();
			//当前用户对应的角色
			List<String>       urlList    = new ArrayList<String>();
		    for(String str:strList){        //遍历用户的所有角色信息
		    	for(YoRoleModule roleModule: moduleList){
		    		if(roleModule.getRole().equals(str)){
		    			urlList.add(roleModule.getModuleUrl());
		    		}
		    	}
		    }
		    System.out.println("user_url的长度为："+urlList.size());
		    request.getSession().setAttribute("urlList",urlList);
		    
		 return "success";
	 }
  }

  /**
   * 登录成功界面跳转
   * 170315注意这里的index是jsp里面的而不是和jsp同级的
   * 项目中只有这一个入口调用标题栏，可以放心使用
   * @return
   */
  @RequestMapping("/loginSuccess.do")
  public String loginSuccess(Model m, HttpServletRequest request){
	  // 从request中得到工号，再传到jsp中用于判断
	  String staffid = request.getSession().getAttribute(GlobalConstant.user_staffId).toString();
	  m.addAttribute("staffid", staffid);
	  return "index";
  }

}
