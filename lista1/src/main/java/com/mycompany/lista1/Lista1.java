/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista1;

/**
 *
 * @author largo
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class Lista1{
    public static void main(String[] args){
        Parameters params = new Parameters();
        
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder = 
            new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
            .configure(params.properties()
                .setFileName("src/main/resources/appsettings.properties"));
            
        try{
            Configuration config = builder.getConfiguration();
            System.out.println(config);
            
            String path = config.getString("orders.directory.path");
            System.out.println("Settings directory: " + path);
            
            File directory = new File(path);
            File[] jsonFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
            
            if (jsonFiles == null || jsonFiles.length == 0) {
                System.out.println("️Found no JSON files in this directory... ");
            } else {
                System.out.println("JSON files found, commencing code generation...");
                for (File json : jsonFiles) {
                    //System.out.println(json.getAbsolutePath());
                    try {
                        String code = order.getOrderCode(json);
                        System.out.println("Code for " + json.getName() + ": " + code);
                    } catch (IOException e) {
                        System.out.println("Couldn't load the json file..." + json.getName());
                    }
                }
            }
            
        }
        catch(ConfigurationException cex){
            System.out.println("Something went wrong while loading the configuration...");
        }
        
    }
    
}

class order {
    private String date;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private List<products> products;

    public order() {}

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public List<products> getProducts() { return products; }
    public void setProducts(List<products> products) { this.products = products; }

    public static String getOrderCode(File jsonFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        order order = mapper.readValue(jsonFile, order.class);
        String orderCode = "";
        orderCode +=order.getDate();
        orderCode +=order.getFirstName().charAt(0);
        orderCode +=order.getLastName().charAt(0);
        orderCode +=order.getEmail().charAt(0);
        orderCode +=order.getPhoneNumber().charAt(0);
        for (products p : order.getProducts()){
            orderCode +=p.getProductCode().charAt(0);
            orderCode +=p.getAmount().charAt(0);
            orderCode +=p.getMeasure();
        }
        return orderCode;
    }

}

class products {
    private String productCode;
    private String amount;
    private String measure;

    public products() {}

    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }

    public String getAmount() { return amount; }
    public void setAmount(String amount) { this.amount = amount; }

    public String getMeasure() { return measure; }
    public void setMeasure(String measure) { this.measure = measure; }
}