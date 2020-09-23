package com.cndym.util.export;

import java.util.ArrayList;
import java.util.List;
import com.cndym.test.CheckData;

public class TextMain {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String str = "F:\\tomcat6work\\wtpwebapps\\cooperation_manager\\";
		// String filePath = str + "WEB-INF/xls/test" + Utils.formatDate2Str(new
		// Date(), "yyyyMMddHHmmss") + ".cvs";
		// MakeXlsx.toXls(list, tempPath, filePath, response); File fileName;
		
		List<CheckData> list = new ArrayList<CheckData>();
		try {
			// fileName = mt.createFile(new File(filePath));
			//
			// String content ="hahahahah";
			CheckData ch = new CheckData("camily", "exetest");
			list.add(ch);
			list.add(new CheckData("nihao", "wohao"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String res = ListToString.list2String(list, "%s , %s \n", "name", "value");
	}	
}
