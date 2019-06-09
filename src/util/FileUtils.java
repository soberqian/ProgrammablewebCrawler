/*
 * Copyright (C) 2014 hu
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author hu
 */
public class FileUtils {

	public static void deleteDir(File dir){
		File[] filelist=dir.listFiles();
		for(File file:filelist){
			if(file.isFile()){
				file.delete();
			}else{
				deleteDir(file);
			}
		}
		dir.delete();
	}

	public static void copy(File origin,File newfile) throws FileNotFoundException, IOException{
		if(!newfile.getParentFile().exists()){
			newfile.getParentFile().mkdirs();
		}
		FileInputStream fis=new FileInputStream(origin);
		FileOutputStream fos=new FileOutputStream(newfile);
		byte[] buf=new byte[2048];
		int read;
		while((read=fis.read(buf))!=-1){
			fos.write(buf,0,read);
		}
		fis.close();
		fos.close();
	}

	public static void writeFile(String filename,String contentStr,String charset) throws FileNotFoundException, IOException{
		byte[] content=contentStr.getBytes(charset);
		FileOutputStream fos=new FileOutputStream(filename);
		fos.write(content);
		fos.close();
	}

	public static void writeFile(File file,String contentStr,String charset) throws FileNotFoundException, IOException{
		byte[] content=contentStr.getBytes(charset);
		FileOutputStream fos=new FileOutputStream(file);
		fos.write(content);
		fos.close();
	}

	public static void writeFileWithParent(String filename,String contentStr,String charset) throws FileNotFoundException, IOException{
		File file=new File(filename);
		File parent=file.getParentFile();
		if(!parent.exists()){
			parent.mkdirs();
		}
		byte[] content=contentStr.getBytes(charset);
		FileOutputStream fos=new FileOutputStream(file);
		fos.write(content);
		fos.close();
	}

	public static void writeFileWithParent(File file,String contentStr,String charset) throws FileNotFoundException, IOException{
		File parent=file.getParentFile();
		if(!parent.exists()){
			parent.mkdirs();
		}
		byte[] content=contentStr.getBytes(charset);
		FileOutputStream fos=new FileOutputStream(file);
		fos.write(content);
		fos.close();
	}

	public static void writeFile(String filename,byte[] content) throws FileNotFoundException, IOException{
		FileOutputStream fos=new FileOutputStream(filename);
		fos.write(content);
		fos.close();
	}



	public static void writeFile(File file,byte[] content) throws FileNotFoundException, IOException{
		FileOutputStream fos=new FileOutputStream(file);
		fos.write(content);
		fos.close();
	}

	public static void writeFileWithParent(String filename,byte[] content) throws FileNotFoundException, IOException{
		File file=new File(filename);
		File parent=file.getParentFile();
		if(!parent.exists()){
			parent.mkdirs();
		}
		FileOutputStream fos=new FileOutputStream(file);
		fos.write(content);
		fos.close();
	}

	public static void writeFileWithParent(File file,byte[] content) throws FileNotFoundException, IOException{

		File parent=file.getParentFile();
		if(!parent.exists()){
			parent.mkdirs();
		}
		FileOutputStream fos=new FileOutputStream(file);
		fos.write(content);
		fos.close();
	}

	public static byte[] readFile(File file) throws IOException{
		FileInputStream fis = new FileInputStream(file);
		byte[] buf = new byte[2048];
		int read;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((read = fis.read(buf)) != -1) {
			bos.write(buf, 0, read);
		}

		fis.close();
		return bos.toByteArray();
	}
	//删除指定字符
	public static String truncateHeadString(String origin, int count) {
		if (origin == null || origin.length() < count) {
			return null;
		}

		char[] arr = origin.toCharArray();
		char[] ret = new char[arr.length - count];
		for (int i = 0; i < ret.length; i ++) {
			ret[i] = arr[i + count];
		}

		return String.copyValueOf(ret);
	}
	//将坏的json数据里面的双引号，改为中文的双引号(啥都行，只要不是双引号就行)  
	public String jsonStringConvert(String s){  
		char[] temp = s.toCharArray();         
		int n = temp.length;  
		for(int i =0;i<n;i++){  
			if(temp[i]==':'&&temp[i+1]=='"'){  
				for(int j =i+2;j<n;j++){  
					if(temp[j]=='"'){  
						if(temp[j+1]!=',' &&  temp[j+1]!='}'){  
							temp[j]='”';  
						}else if(temp[j+1]==',' ||  temp[j+1]=='}'){  
							break ;  
						}  
					}  
				}     
			}  
		}         
		return new String(temp);  
	}
	//去空格
	public String replaceBlank(String str) {  
		String dest = "";  
		if (str != null) {  
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");  
			Matcher m = p.matcher(str);  
			dest = m.replaceAll("");  
			// Pattern p2 = Pattern.compile("\\s*\"");  
			// Matcher m2 = p2.matcher(dest);  
			// dest = m2.replaceAll("\'");  
			dest = dest.replace("=\"", "='");  
			p = Pattern.compile("\"\0*>");  
			m = p.matcher(dest);  
			dest = m.replaceAll(">'");  
		}  
		return dest;  
	}
}
