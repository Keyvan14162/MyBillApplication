/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ismailkeyvan.bill;

import com.ismailkeyvan.project.UserScreen;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public final class BillPrinter {
    
    UserScreen us = new UserScreen();
    
    private static int billNumber = 0;
    /*
    bill.bl gonna save this number 
    So if u close the app,number isn't gonna be 0
    */
    
    
    public void getBillNo() {
        //Getting billNumber from bill.bl
        
        FileInputStream fileInputStream = null;
        
        try {
            fileInputStream = new FileInputStream("bill.bl");
            
            billNumber = fileInputStream.read();

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(us, "bill.bl Not Found Process Terminated");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(us, "IO Error Process Terminated");
        }
        
        finally {
            try {
                
                if (fileInputStream != null )
                    fileInputStream.close();
                        
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(us, "IO Error Process Terminated");;
            } 
        }
    }
    
    
    public void billNo() {
        /*
        This will be increase bill Number when the user Print a bill
        */
        
        FileOutputStream fileOutputStream = null;
        
        try {
            fileOutputStream = new FileOutputStream("bill.bl", false);
            
            fileOutputStream.write(billNumber+1);
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(us, "bill.bl Not Found Process Terminated");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(us, "IO Error Process Terminated");
        }
        
        finally {
            try {
                if(fileOutputStream != null)
                    fileOutputStream.close();
                
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(us, "IO Error Process Terminated");
            }
        } 
    }
    
    
    public BillPrinter(String text) {
        
        File file = new File("Bill.txt");
        
        getBillNo();//Getting billNumber
        billNo();//Increasing it
        getBillNo();//Getting Increased billNumber
        
        text += "\n\nBill no:"+ billNumber +"\n\n"
                + "Thank you for choosing us\nProduced by IsmailKeyvan";
        
        
        try(FileWriter writer = new FileWriter(file ,false)) {
            
            writer.write(text);
            
            JOptionPane.showMessageDialog(us, "Bill.txt Successfully Created to App Location");
            
        } catch (IOException ex) {          
            JOptionPane.showMessageDialog(us, "IO Error Process Terminated");       
        }
    }
}
