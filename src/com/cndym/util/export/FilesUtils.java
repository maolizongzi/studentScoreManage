package com.cndym.util.export;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import net.sf.jxls.exception.ParsePropertyException;

public class FilesUtils {
	
	private static Logger logger = Logger.getLogger(FilesUtils.class);
	/**
	 * ɾ����Ŀ¼filePath�µ������ļ�
	 * 
	 * @param filePath
	 *            �ļ�Ŀ¼·��
	 */
	public static void deleteFiles(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					files[i].delete();
				}
			}
		}
	}

	/**
	 * ɾ�������ļ�
	 * 
	 * @param filePath
	 *            �ļ�Ŀ¼·��
	 * @param fileName
	 *            �ļ�����
	 */
	public static void deleteFile(String filePath, String fileName) {
		File file = new File(filePath);
		if (file.exists()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					if (files[i].getName().equals(fileName)) {
						files[i].delete();
						return;
					}
				}
			}
		}
	}
	
	
	
	/**
	 * �����ļ�
	 * @param fileName
	 * @return
	 */
	public static File createFile(File file) throws Exception {		
		try {
			if (!file.exists()) {
				file.createNewFile();			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}

	
	public static void downLoad(String filePath, HttpServletResponse response) {
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
            
            
        } catch (ParsePropertyException e) {
        	logger.debug("FilesUtils down 107 :",e);
        }catch (FileNotFoundException e) {
        	logger.debug("FilesUtils down 109 :",e);
        } catch (IOException e) {
        	logger.debug("FilesUtils down 111 :",e);
        } catch (Exception e) {
        	logger.debug("FilesUtils down 113 :",e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                	logger.debug("FilesUtils down 119 :",e);
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                	logger.debug("FilesUtils down 126 :",e);
                }
            }
        }
        
        
	}


}
