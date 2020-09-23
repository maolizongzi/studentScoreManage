package com.cndym.util.export;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.apache.log4j.Logger;

/**
 * @author 程禄元
 * @date 2016-4-8 创建Text
 */

public class MakeTxt {

	private static Logger logger = Logger.getLogger(MakeTxt.class);

	public static void getFileExists(String fPath) {
		File file = new File(fPath);
		// 如果文件夹不存在则创建
		if (!file.exists() && !file.isDirectory()) {
			logger.debug("文件目录不存在，创建：" + fPath);
			file.mkdir();
		} else {
			logger.debug(fPath + "   --->文件目录存在");
		}
	}

	public static void contentToTxt(String filePath, String content, boolean b) {
		String str = new String(); // 原有txt内容
		String s1 = new String();// 内容更新

		try {
			File f = new File(filePath);
			if (f.exists()) {
				logger.debug(filePath + "   --->文件目录存在");
			} else {
				f.createNewFile();// 不存在则创建
				logger.debug("文件目录不存在，创建：" + filePath);
			}
			if (false == b) {
				BufferedReader input = new BufferedReader(new FileReader(f));
				while ((str = input.readLine()) != null) {
					s1 += str + "\n";
				}
				input.close();
			}
			s1 += content;

			BufferedWriter output = new BufferedWriter(new FileWriter(f));
			output.write(s1);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("生成TXT或者csv文件，MakeTxt 56行",e);
			
		}
	}

}
