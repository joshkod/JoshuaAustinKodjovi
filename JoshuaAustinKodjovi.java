/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
// I did not know I have to make multiple commit untill sunday evening when I
//was going through the file again, so I have to create a new repository and did multiple commit same day
//that makes the path to the file different from the path on the repository 
// link to my assignment on github - https://github.com/joshkod/JoshuaAustinKodjovi
package com.mycompany.joshuaaustinkodjovi;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class JoshuaAustinKodjovi {

 
    public static void main(String[] args) {
         // Input file path with customer details
        String inputFileName = "C:\\\\Users\\\\10 Prestige\\\\Documents\\\\CCT_Dublin\\\\Diploma_in_Applied_Software_Development\\\\Programming&Mathematics_Fundamentals\\\\Assignments\\\\JoshuaAustinKodjovi\\\\src\\\\main\\\\java\\\\com\\\\mycompany\\\\joshuaaustinkodjovi\\\\customers.txt"; 
        // Output file to store the results with discounts applied
        String outputFileName = "customerdiscount.txt";
        
        // Use try-with-resources to automatically close the BufferedReader and BufferedWriter after use
        // BufferedReader reads text from a file and BufferedWriter writes text to a file.
        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {
            
            // Declaring variables to store data read from the input file
            String fullName;  // Combined first and second name   
            double valueOfPurchase; // Variable to store the value of a purchase 
            int classType; // Variable to store a class type (1 to 3)
            int lastPurchase; // Variable to store when the last purchase was made 

            // Loop through the file and read each customer's data
            while ((fullName = br.readLine()) != null) {
                // Read the purchase details
                valueOfPurchase = Double.parseDouble(br.readLine());
                // Read the next line, which indicates the class type (membership or category) of the customer,
                // and convert the string into an integer.
                classType = Integer.parseInt(br.readLine());
                // Read the next line, which represents the year of the customer's last purchase,
                // and convert the string into an integer.
                lastPurchase = Integer.parseInt(br.readLine());

                // Validate the class type (must be 1, 2, or 3)
                if (!isValidClassType(classType)) {           
                    continue;  // Skip this customer and move to the next one
                }
                
                // Check if the last purchase year is valid using the isValidYear function
                // If the year is not valid (i.e., falls outside the acceptable range),
                // print an error message including the customer's full name and skip to the next customer
                if (!isValidYear(lastPurchase)) {
                    System.out.println("Error: Invalid year for " + fullName); // Output the error message for invalid year
                    continue; // Skip this iteration and move to the next customer in the loop
                }

                // Calculate the final value after applying the appropriate discount
                double finalValue = calculateFinalValue(valueOfPurchase, classType, lastPurchase);

                // Write the full name of the customer to the output file, followed by a newline character.
                // 'fullName' is a string that contains the customer's first and second names.
                bw.write(fullName + "\n");
                // Write the final purchase value (after discount) to the output file.
                // The value is formatted to two decimal places using String.format, followed by a newline.
                bw.write(String.format("%.2f", finalValue) + "\n");
            }

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    // This method checks whether the classType is valid by ensuring it falls within a specific range.
    // The classType must be between 0 and 3, inclusive.
    private static boolean isValidClassType(int classType) {
        return classType >= 0 && classType <= 3; // Returns true if classType is between 0 and 3, otherwise false
    }

    // This method checks whether the input 'year' is valid by comparing it to a valid range.
    private static boolean isValidYear(int year) {
        // Get the current year using the Calendar class
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return year >= 1900 && year <= currentYear;  // Check if the 'year' is between 1900 and the current year
    }

    // This method calculates the final value after applying a discount
    // based on the classType and the lastPurchaseYear.
    private static double calculateFinalValue(double value, int classType, int lastPurchaseYear) {
        // Get the current year dynamically using the Calendar class
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        // Initialize discount as 0, which will be adjusted based on conditions
        double discount = 0;

        // Determine the discount based on class type and last purchase year
        switch (classType) {
            case 1:
                // Initialize discount as 0, which will be adjusted based on conditions
                if (lastPurchaseYear == currentYear) {
                    discount = 0.30; // 30% discount for purchases made this year
                } else if (lastPurchaseYear >= currentYear - 5) {
                    discount = 0.20; // 20% discount for purchases made in the last 5 years
                } else {
                    discount = 0.10; // 10% discount for purchases older than 5 years
                }
                break;
            case 2:
                // Class type 2 discount logic
                if (lastPurchaseYear == currentYear) {
                    discount = 0.15; // 15% discount for purchases made this year
                } else if (lastPurchaseYear >= currentYear - 5) {
                    discount = 0.13; // 13% discount for purchases made in the last 5 years
                } else {
                    discount = 0.05; // 5% discount for purchases older than 5 years
                }
                break;
            case 3:
                // Class type 3 discount logic
                if (lastPurchaseYear == currentYear) {
                    discount = 0.03; // 3% discount for purchases made this year
                }
                break;
            default:
                discount = 0;  // No discount for invalid class types (already validated)
        }
        // Calculate the final value by applying the discount
        return value - (value * discount);
    }
}
