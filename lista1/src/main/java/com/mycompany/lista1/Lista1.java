/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista1;

/**
 *
 * @author largo
 */
import java.io.File;
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
                System.out.println("️Brak plików JSON w katalogu");
            } else {
                System.out.println("Znaleziono pliki JSON:");
                for (File json : jsonFiles) {
                    System.out.println(json.getAbsolutePath());
                }
            }
            
        }
        catch(ConfigurationException cex){
            System.out.println("Something went wrong while loading the configuration...");
        }
        
    }
    
}