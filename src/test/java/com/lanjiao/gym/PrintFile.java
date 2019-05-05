package com.lanjiao.gym;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintFile {


    public static  void println(String centent,String filename){
        FileWriter fw = null;
        try {
          fw  = new FileWriter(new File("G:\\tem\\"+filename));
          fw.write(centent);
        }catch (IOException e){
            System.out.println("打印出错");
        }finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
