package com.api.auto.utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtils {

	private static String CONFIG_PATH = "./configuration/configs.properties";
	private static String TOKEN_PATH = "./configuration/token.properties";

		
		public static String getProperty(String key) {
	        Properties properties = new Properties();
	        String value = null;
	        FileReader reader = null;

	        try {
	        	reader = new FileReader(CONFIG_PATH);
	            properties.load(reader);
	            value =  properties.getProperty(key);
	            return value;
	        } catch (IOException e) {
	            System.out.println("Xảy ra lỗi khi đọc giá trị của " + key);
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        return value;
	    }

		 public static void saveToken(String token, String value) {
		        Properties prop = new Properties();
		        FileWriter writer = null;

		        try {
		        	writer = new FileWriter(TOKEN_PATH);
		        	prop.setProperty(token, value);
		            prop.store(writer, "Set new value in properties");
		            System.out.println("Set token in file properties success.");
		        } catch (IOException e) {
		            e.printStackTrace();
		        } finally {
					if(writer != null) {
						try {
							writer.close();
						}catch(IOException e) {
							e.printStackTrace();
						}
					}
				}	
		    }
	
		 public static String getToken(String token) {
		        Properties properties = new Properties();
		        String value = null;
		        FileReader reader = null;

		        try {
		        	reader = new FileReader(TOKEN_PATH);
		            properties.load(reader);
		            value = properties.getProperty(token);
		            return value;
		        } catch (IOException e) {
		            System.out.println("Xảy ra lỗi khi đọc giá trị của " + token);
		            e.printStackTrace();
		        } finally {
		            if (reader != null) {
		                try {
		                    reader.close();
		                } catch (IOException e) {
		                    e.printStackTrace();
		                }
		            }
		        }
		        return value;
		    }
}
