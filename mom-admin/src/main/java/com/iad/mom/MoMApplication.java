package com.iad.mom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序 ra
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class MoMApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        // https://www.bootschool.net/ascii;bsid=90295A146F9C5205EE49F3724AF01DE1
        SpringApplication.run(MoMApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  后端启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "   ___     ___   \n" +
                "  | _ \\   /   \\  \n" +
                "  |   /   | - |  \n" +
                "  |_|_\\   |_|_|  \n" +
                "_|\"\"\"\"\"|_|\"\"\"\"\"| \n" +
                "\"`-0-0-'\"`-0-0-' \n");
    }
}
