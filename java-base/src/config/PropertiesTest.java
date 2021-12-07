package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 配置测试
 *
 */
public class PropertiesTest {
    public static void main(String[] args) throws IOException {

//          ************读取配置文件**********
//        1.获取properties对象
//        2.properties.load 加载 文件流对象
//        3.properties.getProperties获取属性值
        String path="D:/test.properties";
        Properties properties = new Properties();
        properties.load(new FileInputStream(path));
        System.out.println(properties.getProperty("name"));

//        **************写入配置文件***********
//        1.获取properties对象
//        2.给对象写入内容
//        3.properties.store 输入文件流对象
        Properties writeProperties = new Properties();
        writeProperties.setProperty("username","liwei");
        writeProperties.setProperty("password","123456");
        writeProperties.store(new FileOutputStream(path),"这是注释啊");
    }

}
