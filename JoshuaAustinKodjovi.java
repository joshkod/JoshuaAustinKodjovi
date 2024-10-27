// Source code is decompiled from a .class file using FernFlower decompiler.
package com.mycompany.joshuaaustinkodjovi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class JoshuaAustinKodjovi {
   public JoshuaAustinKodjovi() {
   }

   public static void main(String[] args) {
      String inputFileName = "C:\\\\Users\\\\10 Prestige\\\\Documents\\\\CCT_Dublin\\\\Diploma_in_Applied_Software_Development\\\\Programming&Mathematics_Fundamentals\\\\Assignments\\\\JoshuaAustinKodjovi\\\\src\\\\main\\\\java\\\\com\\\\mycompany\\\\joshuaaustinkodjovi\\\\customers.txt";

      String outputFileName = "customerdiscount.txt";

      try {
         BufferedReader br = new BufferedReader(new FileReader(inputFileName));

         try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName));

      String fullName;
            try {
               while((fullName = br.readLine()) != null) {
                  double valueOfPurchase = Double.parseDouble(br.readLine());
                  int classType = Integer.parseInt(br.readLine());
                  int lastPurchase = Integer.parseInt(br.readLine());
                  if (isValidClassType(classType)) {
                     if (!isValidYear(lastPurchase)) {
                        System.out.println("Error: Invalid year for " + fullName);
                     } else {
                        double finalValue = calculateFinalValue(valueOfPurchase, classType, lastPurchase);
                        bw.write(fullName + "\n");
                        Object[] var10002 = new Object[]{finalValue};
                        bw.write(String.format("%.2f", var10002) + "\n");
                     }
                  }
               }

            } catch (Throwable var14) {
                try {
                   bw.close();
                } catch (Throwable var13) {
                   var14.addSuppressed(var13);
                }
 
                throw var14;
             }
 
             bw.close();
          } catch (Throwable var15) {
             try {
                br.close();
             } catch (Throwable var12) {
                var15.addSuppressed(var12);
             }
 
             throw var15;
          }
 
          br.close();
       } catch (NumberFormatException | IOException var16) {
          var16.printStackTrace();
       }
 
    }