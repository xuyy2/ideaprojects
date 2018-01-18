package com.service;

import com.model.YoSalaryDaily;
import com.model.YoSalaryDailyExample;
import com.model.YoUserinfosalary;
import com.model.YoUserinfosalaryExample;

import java.util.List;

/**
 * Created by ma on 2016/11/14.
 * 170316刘立人，这个服务接口制度实在是用着麻烦啊！
 */
public interface IUserInfoSalaryService {

    List<YoUserinfosalary> selectSalary(YoUserinfosalaryExample record);

    List<YoUserinfosalary> selectByExample(YoUserinfosalaryExample example);

    List<YoUserinfosalary> searchUserInfoByEntity(YoUserinfosalary yoUserinfosalary);

    //添加工资
    int insert(YoUserinfosalary record);

    int updateByUserSalary(YoUserinfosalary record);
    /**
     * 根据分公司，月份去查询薪资
     * @param company  分公司
     * @param salarydate 月份
     * @return
     */
    List<YoUserinfosalary> search_salary(String company,String salarydate);
    List<YoUserinfosalary> search_Jan_salary(String userid,String salarydate);
    List<YoSalaryDaily> selectDailyByExample(YoSalaryDaily example);
    int updateDailyByUserSalary(YoSalaryDaily record);

    // 根据主键查找实体类
    YoSalaryDaily searchResult(int seqNo);

    // 得到员工日报
    List<YoSalaryDaily> getJournal(String staffid);

    // 得到待审批的日报，只有我和黄照香才能看到
    List<YoSalaryDaily> getJournalOnCheck();

    // 点击打回后，日报状态1变成0，视情况将总表的工资状态3变成0
    void rejectJournal(int seqNo, String staffid);

    // 点击同意后，改日报和工资数据，日报状态从1改为2，视情况将总表的工资状态3变成0
    void approveJournal(int seqNo, String staffid);

}
