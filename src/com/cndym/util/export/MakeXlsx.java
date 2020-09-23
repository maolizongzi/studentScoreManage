package com.cndym.util.export;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.log4j.Logger;

public class MakeXlsx {
	
	private static Logger logger = Logger.getLogger(MakeXlsx.class);

	// 模板地址 tempPath
	// 真实导出路径 filePath

	public static String toXls(List list, String tempPath, String filePath, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("root", list);

		XLSTransformer transformer = new XLSTransformer();
		try {
			transformer.transformXLS(tempPath, map, filePath);
		} catch (ParsePropertyException e1) {
			logger.debug("MakeXlsx 31 :",e1);
		} catch (InvalidFormatException e1) {
			logger.debug("MakeXlsx 32 :",e1);
		} catch (IOException e1) {
			e1.printStackTrace();
			logger.debug("MakeXlsx 33 :",e1);
		}
		FilesUtils.downLoad(filePath, response);
		return null;
	}

}
