package Untils;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.yaml.snakeyaml.Yaml;

public class Common {

    private  static Boolean newBoolen;
    private  static int newInt;


    /**
     * 使用BufferedReader类读文本文件
     * @param fileName
     */
    public static String readFile(String filePath) throws IOException {
        String line="";
        StringBuffer content= new StringBuffer();
        try
        {
            BufferedReader reader=new BufferedReader(new FileReader(filePath));
            int ch;
            while ((ch = reader.read()) != -1) {
                content.append((char) ch);
            }
            reader.close();
            return content.toString();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return line;
    }

    /**
     * 清空文件内容
     * @param fileName
     */
    public static void clearInfoForFile(String fileName) {
        File file =new File(fileName);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 读Yaml文件
     * @param YamlName
     * @return 返回Map类型
     */
    public static Map readYaml(String yamlpath){

        String yamlname = System.getProperty("user.dir") + "/src/main/java/datas/" + yamlpath;
        Map<String, String> map = null;
        try{
            File dumpFile = new File(yamlpath);
            Yaml yaml = new Yaml();
            map = yaml.load((new FileInputStream(dumpFile)));
        }catch (Exception e){
            System.out.println(String.format("读取%s异常! + \n + %s", yamlname,e));
        }
        return map;
    }


    /**
     * 遍历Map对象加入到capabilities中
     */
    public static DesiredCapabilities Capsmap(Map map, DesiredCapabilities capabilities){

        Iterator entries = map.entrySet().iterator();
        while (entries.hasNext()) {

            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();

            if (value.equalsIgnoreCase("1")){
                capabilities.setCapability(key, true);
            }
            else if (value.equalsIgnoreCase("0")){
                capabilities.setCapability(key, false);
            }else {
                capabilities.setCapability(key, value);
            }

        }
        return capabilities;
    }



}
