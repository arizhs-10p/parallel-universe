package com.parallel.universe.base;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;

public class AppiumServer {
    static AppiumDriverLocalService server;
    static void setInstance()
    {
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        System.out.println("t");
        builder.withAppiumJS(new File("C:\\Users\\Administrator\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
        .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
        .usingPort(4723)
        .withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
        .withLogFile(new File("AppiumLog.txt"))
        .withIPAddress("127.0.0.1");
               // .withArgument(GeneralServerFlag.BASEPATH);
        System.out.println("ty");
        server = AppiumDriverLocalService.buildService(builder);
        //server.start();
    }
static AppiumDriverLocalService getInstance()
{
    if (server==null)
    {
        setInstance();
    }
    return server;
}

    public static void start(){
            getInstance().start();
        System.out.println(server.getUrl());
        System.out.println(server.isRunning());
    }
    public static void stop(){
            if (server!=null){
                getInstance().stop();
                System.out.println("Appium Server Stopped");
            }
    }

    public static void main(String[] args) throws InterruptedException {
        AppiumServer.start();
        System.out.println("Cl");
        Thread.sleep(2000);
        AppiumServer.stop();
    }
}
