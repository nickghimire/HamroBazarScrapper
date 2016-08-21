/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.scrapping;

import com.leapfrog.scrapping.util.Grabber;

import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author apple
 */
public class Program {

    public static void main(String[] args) {

        try {

            Scanner input = new Scanner(System.in);
            System.out.println("Enter the keyword");
            String keyword = input.next();
            
             FileWriter writer = new FileWriter("/users/apple/desktop/hamrobazar");

          StringBuilder data = new StringBuilder();

            Grabber grabber = new Grabber();
          
            String link = "http://hamrobazaar.com/search.php?do_search=Search&searchword="+keyword+"&Search.x=0&Search.y=0&catid_search=0";
            
            grabber.connect(link);
            
            String content = grabber.grab(link);
           // System.out.println(content);

            String regEx = "<font class=\"txt\">Showing (.*) of total <b>([0-9]+)</b>";
            Pattern pattern1 = Pattern.compile(regEx);
            
            Matcher match = pattern1.matcher(content);
         
            if (match.find()) {
                int total = Integer.parseInt(match.group(2));

                double page = Math.ceil(total);
                int offset = 0;
                for (int i = 1; i <= page; i++) {
                    String link1 = link + "offset=" + offset;
                    String content1 = grabber.grab(link1);
                    offset += 20;

                    String regex = "<div class=\"sold_div\"></div><a href=\"(.*?)\"> <img src='(.*?)'.*?><b>(.*?)</b>.*?<img src=.*?><b>(.*?)</b>";

                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(content1);

                    while (matcher.find()) {

                        System.out.println("Link " + matcher.group(1));
                        System.out.println("Title " + matcher.group(3));
                        System.out.println("Price " + matcher.group(4));
                        String imgURL= matcher.group(2);

                         grabber.imgDownloader(imgURL);
                    
                           }
                }
            }
                    writer.write(data.toString());
                  writer.close();
                
            
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }
}
