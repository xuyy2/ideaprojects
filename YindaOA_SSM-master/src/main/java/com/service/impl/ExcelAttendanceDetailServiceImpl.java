package com.service.impl;

import com.dao.YoAtteninfoMapper;
import com.model.YoAtteninfo;
import com.model.YoAtteninfoExample;
import com.service.IExcelAttendanceDetailService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 打卡明细记录表的导入
 * 第3行是表头，第4行开始才是内容
 */
@Service
public class ExcelAttendanceDetailServiceImpl implements IExcelAttendanceDetailService {

    @Autowired
    public YoAtteninfoMapper yoAtteninfoMapper;

    /*
     循环地从excel中插入每一行数据
     如果工号+打卡时间相同，就更新数据
     */
    public Map<String, Object> insertAndUpdate(HSSFWorkbook hssfWorkbook) {
        Map<String, Object> mapInsert = new HashMap<String, Object>();
        List<YoAtteninfo> listFail = new ArrayList<YoAtteninfo>();

        // 得到第3张表
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(2);
        // 这里不要用物理行数，要用最后一行的编号，不然很容易跳坑
        int rowLastNo = hssfSheet.getLastRowNum();

        // 设定一个变量，记录for循环当中操作成功的条目数目
        int successAmount = 0;

        // 接下来从第4行开始，对每一行进行操作
        for (int rowNo=3; rowNo<=rowLastNo; rowNo++) {
            HSSFRow hssfRow = hssfSheet.getRow(rowNo);
            // 同理，这里也不要用单元格的总数，要用最后一个单元格的序号
            int cellLastNo = hssfRow.getLastCellNum();

            /*
            现在开始做真正的功能！
            第1步，把得到的hssfRow中每一个不为null的cell都设为文本类型，确保每一个整形数值在toString后不会自动加上.0
            */
//            for (int cellNo=0; cellNo<=cellLastNo; cellNo++) {
//                // cell不为空时才操作，为空也就不用管他是什么类型了
//                // 想管也管不起，因为会报NullPointerException，而我们编程时应当避免异常，而不是积极处理异常
//                if (hssfRow.getCell(cellNo) != null) hssfRow.getCell(cellNo).setCellType(1);
//            }

            /*
            第2步，检查当前行是不是空行，如果是就跳过后面的，操作下一行
            检查方法：空的单元格的数目是否等于最后单元格序号+1
             */
//            int emptyCellAmount = 0;
//            for (int cellNo=0; cellNo<=cellLastNo; cellNo++) {
//                // cell不为空时才操作，为空也就不用管他是什么类型了
//                // 想管也管不起，因为会报NullPointerException，而我们编程时应当避免异常，而不是积极处理异常
//                if (hssfRow.getCell(cellNo)==null || hssfRow.getCell(cellNo).toString().equals("")) {
//                    // 这里就可以用++i了，听说运算速度更快= =
//                    ++emptyCellAmount;
//                }
//            }
//            if (emptyCellAmount == cellLastNo+1) continue;

            /*
            第3步，由于考勤日期，考勤时间和打卡时间需要用Date类型，所以按照一定的格式来读取
             */
            String attDateStr = hssfRow.getCell(4).toString();
            String attTimeStr = hssfRow.getCell(5).toString();
            String attEndTime = hssfRow.getCell(6).toString();

            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat formatTime = new SimpleDateFormat("yyyy/MM/dd HH:mm");

            Date dateAttdate = null;
            Date dateAtttime = null;
            Date dateAttendtime = null;

            try {
                dateAttdate = formatDate.parse(attDateStr);
                dateAtttime = formatTime.parse(attTimeStr);
                dateAttendtime = formatTime.parse(attEndTime);
            } catch (Exception e) {
                System.out.println(e.toString());
            }

            /*
            第4步，对于不为空的行，将数据注入引用过来的实体对象
             */
            int cellNo = -1;
            YoAtteninfo yoAtteninfo = new YoAtteninfo();
            if (hssfRow.getCell(++cellNo) != null) yoAtteninfo.setDepartment(hssfRow.getCell(cellNo).toString());
            if (hssfRow.getCell(++cellNo) != null) yoAtteninfo.setStaffId(hssfRow.getCell(cellNo).toString());
            if (hssfRow.getCell(++cellNo) != null) yoAtteninfo.setUserid(hssfRow.getCell(cellNo).toString());
            if (hssfRow.getCell(++cellNo) != null) yoAtteninfo.setName(hssfRow.getCell(cellNo).toString());
            if (hssfRow.getCell(++cellNo) != null) yoAtteninfo.setAttdate(dateAttdate);
            if (hssfRow.getCell(++cellNo) != null) yoAtteninfo.setAtttime(dateAtttime);
            if (hssfRow.getCell(++cellNo) != null) yoAtteninfo.setAttendtime(dateAttendtime);
            if (hssfRow.getCell(++cellNo) != null) yoAtteninfo.setAttendresult(hssfRow.getCell(cellNo).toString());
            if (hssfRow.getCell(++cellNo) != null) yoAtteninfo.setAttaddress(hssfRow.getCell(cellNo).toString());
            if (hssfRow.getCell(++cellNo) != null) yoAtteninfo.setIfactivity(hssfRow.getCell(cellNo).toString());
            if (hssfRow.getCell(++cellNo) != null) yoAtteninfo.setRemark(hssfRow.getCell(cellNo).toString());
            if (hssfRow.getCell(++cellNo) != null) yoAtteninfo.setImgone(hssfRow.getCell(cellNo).toString());
            if (hssfRow.getCell(++cellNo) != null) yoAtteninfo.setImgtwo(hssfRow.getCell(cellNo).toString());
            if (hssfRow.getCell(++cellNo) != null) yoAtteninfo.setDevice(hssfRow.getCell(cellNo).toString());
            if (hssfRow.getCell(++cellNo) != null) yoAtteninfo.setDeviceid(hssfRow.getCell(cellNo).toString());

            /*
            第5步，根据工号和打卡时间来查询，如果查询列表为空，就插入记录
            之所以有失败的可能性，是因为单元格内容有可能超过数据库长度
             */
            String userId = yoAtteninfo.getUserid();
            YoAtteninfoExample yoAtteninfoExample = new YoAtteninfoExample();
            yoAtteninfoExample.createCriteria().andUseridEqualTo(userId).andAttendtimeEqualTo(dateAttendtime);
            List<YoAtteninfo> listAtteninfo = yoAtteninfoMapper.selectByExample(yoAtteninfoExample);

            if (listAtteninfo.size() == 0) {
                try {
                    yoAtteninfoMapper.insert(yoAtteninfo);
                } catch (Exception e) {
                    listFail.add(yoAtteninfo);
                    continue;
                }
            }
            /*
            第6步，如果列表不为空，说明原来有记录，就进行更新
            同样，也有失败的可能性
             */
            else {
                YoAtteninfo yoAtteninfoOld = listAtteninfo.get(0);
                int init = yoAtteninfoOld.getInid();
                yoAtteninfo.setInid(init);
                try {
                    yoAtteninfoMapper.updateByPrimaryKey(yoAtteninfo);
                } catch (Exception e) {
                    listFail.add(yoAtteninfo);
                    continue;
                }
            }

            // 到了这一步，说明插入或更新成功，数目自加！
            successAmount++;
            if (successAmount % 1000 == 0) System.out.println(successAmount);
        }

        // for循环之后，把成功数目和失败列表返回到map
        mapInsert.put("successAmount", successAmount);
        mapInsert.put("listFail", listFail);
        System.out.println("successAmount = "+successAmount);
        System.out.println("listFail.size() = "+listFail.size());
        return mapInsert;
    }

}
