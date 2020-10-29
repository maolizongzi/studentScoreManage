package org.graduate.student.controller.utility;

import org.graduate.student.repository.model.ExamScore;
import org.graduate.student.service.entity.ExamScoreEntity;
import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ExamScoreUtil {

    public static ExamScoreEntity toExamScoreEntity(ExamScore examScore) {
        ExamScoreEntity examScoreEntity = new ExamScoreEntity();
        BeanUtils.copyProperties(examScore, examScoreEntity);
        return examScoreEntity;
    }

    public static ExamScore toExamScore(ExamScoreEntity examScoreEntity) {
        ExamScore examScore = new ExamScore();
        BeanUtils.copyProperties(examScoreEntity, examScore);
        return examScore;
    }
    public  String list2String(List list, String format, String... params){
        String res = "";
        if(list==null || list.size()<1){
            return res;
        }
        int count=0;
        res = "\"编号\",\"学号\",\"学生名称\",\"语文\",\"数学\",\"英语\",\"物理\",\"化学\",\"生物\",\"政治\",\"地理\",\"历史\",\"总分\" \n";
        for(Object obj : list){
            try{
                count++;
                res+=count+",";
                res += object2String(obj, format, params);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }
    public static String object2String(Object obj,String format,String... params) throws IllegalArgumentException, IllegalAccessException{
        String res = "";
        if(obj == null){
            return res;
        }
        List<Object> values = new ArrayList<Object>();
        for(String param:params){
            for(Field f : obj.getClass().getDeclaredFields()){
                if(f.getName().equals(param)){
                    f.setAccessible(true);
                    Object value = f.get(obj);
                    values.add(value);
                    break;
                }
            }
        }
        res = String.format(format, values.toArray());
        return res;
    }
    public  void contentToTxt(String filePath, String content, boolean b) {
        String str = new String(); // 原有txt内容
        String s1 = new String();// 内容更新
        try {
            File f = new File(filePath);
            f.createNewFile();// 不存在则创建
            s1 += content;
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f),"GBK"));
            output.write(s1);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public  void downLoad3(String filePath, HttpServletResponse response) {
        filePath = filePath.replace("/", File.separator);
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
        String path = filePath.substring(0, filePath.lastIndexOf(File.separator));

        FileInputStream fis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(filePath);
            os = response.getOutputStream();
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            response.setContentType("application/x-download");
            byte[] mybyte = new byte[1024];
            int len = 0;
            while ((len = fis.read(mybyte)) != -1) {
                os.write(mybyte, 0, len);
            }
            File tempDir = new File(path);
            if (tempDir.exists() && tempDir.isDirectory()) {
                if (tempDir.listFiles().length > 0) {
                    File[] files = tempDir.listFiles();
                    for (int i = 0; i < tempDir.listFiles().length; i++) {
                        if (files[i].isFile()) {
                            files[i].delete();
                        }
                    }
                }
            }

        } catch (Exception e) {
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
