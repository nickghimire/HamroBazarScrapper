/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.scrapping.util;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author apple
 */
public class Grabber {
    public URLConnection connect(String link) throws IOException{
        URL url = new URL(link);
        return url.openConnection();
    }

    public String grab(String link) throws IOException {

 

        URLConnection conn = connect(link);
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = "";
        StringBuilder content = new StringBuilder();
         while ((line = reader.readLine()) != null) {
            content.append(line);
        }

        reader.close();
        return content.toString();

    }
  public void  imgDownloader(String imgURL) throws IOException{
      
     URL url = new URL(imgURL); 
     URLConnection conn= url.openConnection();
     String tokens[]=imgURL.split("/");
      FileOutputStream os= new FileOutputStream(tokens[tokens.length-1]);
    
      byte[] data= new byte [1024*5];
      int i=0;
      InputStream is= conn.getInputStream();
      while((i=is.read(data))!=-1){
          os.write(data, 0, i);
          
      } 
      os.close();
      is.close();
    
  }
  
}
