package com.java.csv;


// import com.journaldev.csv.model.Employee;
// import com.opencsv.CSVWriter;
import java.io.FileWriter;
// import com.opencsv.CSVWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.*;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
// import com.opencsv.CSVWriter;
import java.io.FileWriter;
// import com.opencsv.CSVWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import java.util.Scanner;  
class Bank { 
    private ArrayList<Customer> myCustomer = new ArrayList<>();

    public Bank() {
        this.myCustomer = new ArrayList<Customer>();
    }
    Scanner sc = new Scanner(System.in);  


    public boolean addNewCustomer(Customer customer) {
        if(findCustomer(customer.getName()) >=0) {
            System.out.println("Customer is already on file");
            return false;
        }

        myCustomer.add(customer);
        return true;

    }

    public boolean updateCustomer(Customer oldCustomer, Customer newCustomer) {
        int foundPosition = findCustomer(oldCustomer);
        if(foundPosition <0) {
            System.out.println(oldCustomer.getName() +", was not found.");
            return false;
        }

        this.myCustomer.set(foundPosition, newCustomer);
        System.out.println("All Done");
        return true;
    }

    public boolean removeCustomer(Customer customer) {
        int foundPosition = findCustomer(customer);
        if(foundPosition <0) {
            System.out.println(customer.getName() +", was not found.");
            return false;
        }
        this.myCustomer.remove(foundPosition);
        System.out.println(customer.getName() + ", was deleted.");
        return true;
    }

    private int findCustomer(Customer customer) {
        return this.myCustomer.indexOf(customer);
    }

    private int findCustomer(String customerName) {
        for(int i=0; i<this.myCustomer.size(); i++) {
            Customer customer = this.myCustomer.get(i);
            if(customer.getName().equals(customerName)) {
                return i;
            }
        }
        return -1;
    }


    public String queryCustomer(Customer customer) {
        if(findCustomer(customer) >=0) {
            return customer.getName() + customer.getAccountNumber();
        }
        return null;
    }

    public Customer queryCustomer(String name) {
        int position = findCustomer(name);
        if(position >=0) {
            return this.myCustomer.get(position);
        }

        return null;
    }
    public void FileeReader() throws IOException{
        String row;
        BufferedReader csvReader = new BufferedReader(new FileReader("./BankingDetails.csv"));
        while ((row = csvReader.readLine()) != null) {
        String[] data = row.split(",");
        // do something with the data
        }
        csvReader.close();
    }

    public void FileWriter() throws IOException{
        File file = new File("./BankingDetails.csv");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
    
        bw.write("Customer,Account Number,Account Balance");
        bw.newLine();
        for (int i = 0; i < myCustomer.size(); i++) {
            bw.write(myCustomer.get(i).getName() + " ,");
            bw.write(myCustomer.get(i).getAccountNumber() + " ,");
            bw.write(myCustomer.get(i).getAccountBalance());
            bw.newLine();
        }
        // bw.flush();
        bw.close();
        fw.close();
    }

// public class WritingToCSV throws IOException{
//    public static void main(String args[]) throws Exception {
//       //Instantiating the CSVWriter class
//       CSVWriter writer = new CSVWriter(new FileWriter("D://output.csv"));
//       //Writing data to a csv file
//       String line1[] = {"id", "name", "salary", "start_date", "dept"};
//       String line2[] = {"1", "Krishna", "2548", "2012-01-01", "IT"};
//       String line3[] = {"2", "Vishnu", "4522", "2013-02-26", "Operations"};
//       String line4[] = {"3", "Raja", "3021", "2016-10-10", "HR"};
//       String line5[] = {"4", "Raghav", "6988", "2012-01-01", "IT"};
//       //Writing data to the csv file
//       writer.writeNext(line1);
//       writer.writeNext(line2);
//       writer.writeNext(line3);
//       writer.writeNext(line4);
//       //Flushing data from writer to file
//       writer.flush();
//       System.out.println("Data entered");
//    }
// }
    public void printCustomer(){
        // File csvFile = new File("./employees.csv");
        // FileWriter fileWriter = new FileWriter(csvFile);
        // BufferedWriter bw = new BufferedWriter(fw);

        System.out.println("Customer List");

        for(int i=0; i<this.myCustomer.size(); i++) {
            System.out.println((i+1) + " - " +
            this.myCustomer.get(i).getName() + "'s Account Number is : " +                
            this.myCustomer.get(i).getAccountNumber() +"\n have Account Balance : " + this.myCustomer.get(i).getAccountBalance());
            // String line1[] = {'"'+myCustomer.get(i).getName()+'"'+","+'"'+myCustomer.get(i).getAccountNumber()+'"'+","+'"'+myCustomer.get(i).getAccountBalance()+'"'};
            // for (int j=0; j<3;  j++){
            //     System.out.println(line1[j]);
            // }
        }
    }
}
