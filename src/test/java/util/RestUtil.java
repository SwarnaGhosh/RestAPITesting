package util;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeTest;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class RestUtil {


    public String readFile(String input){

        String str="";
        try{
            FileReader file=new FileReader("src/test/resources/Configuration.properties");
            Properties properties=new Properties();
            properties.load(file);

         str=  properties.getProperty(input);
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return str;

    }

    public static String getLastName(){

        String generatedString= RandomStringUtils.randomAlphabetic(1);
        return ("Smith"+generatedString);
    }

    public static String getFirstName(){

        String generatedString= RandomStringUtils.randomAlphabetic(1);
        return ("John"+generatedString);
    }

    public static String getUserName(){

        String generatedString= RandomStringUtils.randomAlphabetic(3);
        return ("Jsmith"+generatedString);
    }

    public static String getPassword(){

        String generatedString= RandomStringUtils.randomAlphabetic(3);
        return ("JS"+generatedString);
    }

    public static String getEmail(){

        String generatedString= RandomStringUtils.randomAlphabetic(3);
        return (generatedString+"@gmail.com");
    }

    public static String getSalary(){

        String generatedString= RandomStringUtils.randomNumeric(5);
        return ("Smith"+generatedString);
    }


}
