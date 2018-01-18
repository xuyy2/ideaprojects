package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.model.YoSalaryExample;
import com.model.YoUserinfosalary;
import com.model.YoUserinfosalaryExample;
import com.service.IUserInfoSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.model.YoSalary;
import com.service.ISalaryService;
import com.util.CalendarUtil;
/**
 * 日历显示
 * @author yexianglei 2016/12/18.
 *
 */
@RequestMapping("Calendar")
@Controller
public class CalendarController {
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	private ISalaryService salaryService;


	public List<YoSalary> getSalary(String userid,String date){
		YoSalaryExample example = new YoSalaryExample();
		YoSalaryExample.Criteria criteria1 = example.createCriteria();
		if (date!=null) criteria1.andSalarydateEqualTo(date);
		if (userid!=null) criteria1.andUseridEqualTo(userid);
		List<YoSalary> query = salaryService.searchYoSalaryByEntity(example);
		if (query.size()>0){
			return(query);
		} else {
			return  null;
		}

	}

	/**
	 * 签到的日历json
	 * @return
	 * @throws ParseException
	 */

	@RequestMapping("/select_BY_RealAttendance.do")
	@ResponseBody
	public List<CalendarUtil> select_BY_RealAnttendance(String userid,String date) throws ParseException{
		List<YoSalary> list = getSalary(userid,date);
		List<CalendarUtil> CalendarList = new ArrayList<CalendarUtil>();
		for(YoSalary yo:list){
			if ("1".equals(yo.getRealityattendance())) {
				CalendarUtil cu = new CalendarUtil();
				cu.setColor("green");
				cu.setStart(sdf.format(yo.getDate()));
				cu.setTitle("签到");
				CalendarList.add(cu);
			}
		}
		return CalendarList;
	}
	/**
	 * 日报的日历json
	 * @return
	 * @throws ParseException
	 */

	@RequestMapping("/select_BY_EffectAttendance.do")
	@ResponseBody
	public List<CalendarUtil> select_BY_EffectAnttendance(String userid,String date) throws ParseException{
		List<YoSalary> list = getSalary(userid,date);
		List<CalendarUtil> CalendarList = new ArrayList<CalendarUtil>();
		for(YoSalary yo:list){
			if ("1".equals(yo.getEffectiveAttendance())) {
				CalendarUtil cu = new CalendarUtil();
				cu.setColor("green");
				cu.setStart(sdf.format(yo.getDate()));
				cu.setTitle("日报");
				CalendarList.add(cu);
			}
		}
		return CalendarList;
	}

    /**
     * 考勤的日历json
     * @return
     * @throws ParseException
     */
	
	@RequestMapping("/select_BY_Attendance.do")
	@ResponseBody
	public List<CalendarUtil> select_BY_Anttendance(String userid,String date) throws ParseException{
		/*sdf.parse(date);
		List<YoSalary> list = salaryService.select_BY_Anttendane(userid, sdf.parse(date));
		List<CalendarUtil> CalendarList = new ArrayList<CalendarUtil>();
		for(YoSalary yo:list){
			CalendarUtil cu = new CalendarUtil();
			cu.setColor("green");
			cu.setStart(sdf.format(yo.getDate()));
			cu.setTitle("打卡");
			CalendarList.add(cu);
		 }*/

		List<YoSalary> list = getSalary(userid,date);
		List<CalendarUtil> CalendarList = new ArrayList<CalendarUtil>();
		for(YoSalary yo:list){
			if ("1".equals(yo.getEffectiveAttendance())) {
				CalendarUtil cu = new CalendarUtil();
				cu.setColor("green");
				cu.setStart(sdf.format(yo.getDate()));
				cu.setTitle("出勤");
				CalendarList.add(cu);
			}
		}
		return CalendarList;
	}
   
	/**
	 * 请假的日历json
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/select_BY_Leavetype.do")
	@ResponseBody
	public List<CalendarUtil> select_BY_Leavetype(String userid,String date) throws ParseException{
		
		
/*		List<YoSalary> list = salaryService.select_BY_Leavetype(userid, sdf.parse(date));
		List<CalendarUtil> CalendarList = new ArrayList<CalendarUtil>();
		for(YoSalary yo:list){
			CalendarUtil cu = new CalendarUtil();
			cu.setColor("red");
			cu.setStart(sdf.format(yo.getDate()));
			cu.setTitle("请假");
			CalendarList.add(cu);
		 }*/

		List<YoSalary> list = getSalary(userid,date);
		List<CalendarUtil> CalendarList = new ArrayList<CalendarUtil>();
		for(YoSalary yo:list){
			if (!"0".equals(yo.getLeavetype())) {
				CalendarUtil cu = new CalendarUtil();
				cu.setColor("red");
				cu.setStart(sdf.format(yo.getDate()));
				cu.setTitle("请假");
				CalendarList.add(cu);
			}
		}
		return CalendarList;
	}
	
	
	/**
	 * 出差的日历json
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/select_BY_Evenctime.do")
	@ResponseBody
	public List<CalendarUtil> select_BY_Evenctime(String userid,String date) throws ParseException{
		
		
/*		List<YoSalary> list = salaryService.select_BY_Evenctime(userid, sdf.parse(date));
		List<CalendarUtil> CalendarList = new ArrayList<CalendarUtil>();
		for(YoSalary yo:list){
			CalendarUtil cu = new CalendarUtil();
			cu.setColor("blue");
			cu.setStart(sdf.format(yo.getDate()));
			cu.setTitle("出差");
			CalendarList.add(cu);
		 }*/

		List<YoSalary> list = getSalary(userid,date);
		List<CalendarUtil> CalendarList = new ArrayList<CalendarUtil>();
		for(YoSalary yo:list){
			if ("1".equals(yo.getEvection())) {
				CalendarUtil cu = new CalendarUtil();
				cu.setColor("blue");
				cu.setStart(sdf.format(yo.getDate()));
				cu.setTitle("出差");
				CalendarList.add(cu);
			}
		}
		return CalendarList;
	}
    
	
	/**
	 * 加班的日历json
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/select_BY_Worktime.do")
	@ResponseBody
	public List<CalendarUtil> select_BY_Worktime(String userid,String date) throws ParseException{
		
		
		/*List<YoSalary> list = salaryService.select_BY_WorkTime(userid, sdf.parse(date));
		List<CalendarUtil> CalendarList = new ArrayList<CalendarUtil>();
		for(YoSalary yo:list){
			CalendarUtil cu = new CalendarUtil();
			cu.setColor("yellow");
			cu.setStart(sdf.format(yo.getDate()));
			cu.setTitle("加班");
			CalendarList.add(cu);
		 }*/
		List<YoSalary> list = getSalary(userid,date);
		List<CalendarUtil> CalendarList = new ArrayList<CalendarUtil>();
		for(YoSalary yo:list){
			if ("1".equals(yo.getWorkovertime())) {
				CalendarUtil cu = new CalendarUtil();
				cu.setColor("yellow");
				cu.setStart(sdf.format(yo.getDate()));
				cu.setTitle("加班");
				CalendarList.add(cu);
			}
		}
		return CalendarList;
	}
	/**
	 * 考勤日历
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/Calendar_Attendance.do")
	public ModelAndView Calendar_Attendance(String userid,String date) throws ParseException{
		ModelAndView mav = new ModelAndView();
		/*String userid="01002626191049";
	    String date="2016-10";*/
	    int month=sdf2.parse(date).getMonth();
		mav.addObject("userid", userid);
		mav.addObject("date", date);
		mav.addObject("month",month);
		mav.setViewName("Calendar/Calendar_Attendance");
		return mav;

	}
   
	/**
	 * 请假日历
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/Calendar_Leavetype.do")
	public ModelAndView Calendar_Leavetype(String userid,String date) throws ParseException{
		ModelAndView mav = new ModelAndView();
		/*String userid="01002626191049";
	    String date="2016-10";*/
	    int month=sdf2.parse(date).getMonth();
		mav.addObject("userid", userid);
		mav.addObject("date", date);	
		mav.addObject("month", month);
		mav.setViewName("Calendar/Calendar_Leavetype");
		return mav;
	}
	
	
	/**
	 * 出差日历
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/Calendar_Evenctime.do")
	public ModelAndView Calendar_Evenctime(String userid,String date) throws ParseException{
		ModelAndView mav = new ModelAndView();
		
	    int month=sdf2.parse(date).getMonth();
		mav.addObject("userid", userid);
		mav.addObject("date",date);	
		mav.addObject("month",month);	
		mav.setViewName("Calendar/Calendar_Evenctime");
		return mav;
	}
	
	/**
	 * 加班日历
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/Calendar_Worktime.do")
	public ModelAndView Calendar_Worktime(String userid,String date) throws ParseException{
		ModelAndView mav = new ModelAndView();
		/*String userid="01002626191049";
		String date="2016-10";*/
		int month=sdf2.parse(date).getMonth();
		mav.addObject("userid", userid);
		mav.addObject("date", date);
		mav.addObject("month", month);
		mav.setViewName("Calendar/Calendar_Worktime");
		return mav;
	}

	/**
	 * 打卡日历
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/Calendar_RealAttendance.do")
	public ModelAndView Calendar_RealAttendance(String userid,String date) throws ParseException{
		ModelAndView mav = new ModelAndView();
		/*String userid="01002626191049";
		String date="2016-10";*/
		int month=sdf2.parse(date).getMonth();
		mav.addObject("userid", userid);
		mav.addObject("date", date);
		mav.addObject("month", month);
		mav.setViewName("Calendar/Calendar_RealAttendance");
		return mav;
	}
	/**
	 * 日报
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/Calendar_EffectAttendance.do")
	public ModelAndView Calendar_EffectAttendance(String userid,String date) throws ParseException{
		ModelAndView mav = new ModelAndView();
		/*String userid="01002626191049";
		String date="2016-10";*/
		int month=sdf2.parse(date).getMonth();
		mav.addObject("userid", userid);
		mav.addObject("date", date);
		mav.addObject("month", month);
		mav.setViewName("Calendar/Calendar_EffectAttendance");
		return mav;
	}
}
