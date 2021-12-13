package vea.kuc0277.project.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

@Component
@Aspect
public class AOPLogDriver {
    private String path = "LogDriver.txt";

    @After("execution(* vea.kuc0277.project.services.DriverService.*(..))")
    public void log(JoinPoint joinPoint) {
        FileWriter fileWriter;
        // write to file
        try {
            Date date = new Date();
            fileWriter = new FileWriter(path, true);
            fileWriter.write("Action: " + joinPoint.getSignature());
            fileWriter.write("\nTime: " + date.toString() + "\n\n\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
