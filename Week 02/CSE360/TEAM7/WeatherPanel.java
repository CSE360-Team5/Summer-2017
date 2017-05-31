package cse360;

/**
 *
 * @author pdreiter
 * filename: Team7.java
 * description: file containing Team7 class for CSE360 Summer C Project 1
 * Team members:
 * Michael Xhu (wilson.xhy@outlook.com)
 * Chen Yang (cyang112@asu.edu)
 * Sarah Goddard (slgoddar@asu.edu)
 * Pemma Reiter (pdreiter@asu.edu)
 * Notes from pdreiter: This file was originally created via NetBeans Java Form, but during 
 * testing, class diverged from Form, autorelationships via embedded form macros
 * have been severed
 * 
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.util.Date;


/**
 *
 * @author pdreiter
 * 
 */
public class WeatherPanel extends JPanel {

    private WeatherInfo geoLocation;
    private ImageIcon weatherIcon;
    private JLabel weatherLabel;


    /**
     * Creates new form WeatherPanel
     */
    public WeatherPanel(double latitude, double longitude)  {
        geoLocation = new WeatherInfo(latitude,longitude);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents()  {
        TextHumidity = new javax.swing.JTextField();
        TextTemperature = new javax.swing.JTextField();
        TextPrecipitationProbability = new javax.swing.JTextField();
        TextCloudCover = new javax.swing.JTextField();
        weatherIcon = new ImageIcon("src\\cse360\\images\\"+geoLocation.getWeatherFieldString("currently","icon")+".png");
        
        weatherLabel = new JLabel("", weatherIcon, JLabel.CENTER);
        weatherLabel.setIcon(weatherIcon);
        setLayout(new java.awt.BorderLayout());

        add(weatherLabel,java.awt.BorderLayout.CENTER);
        TextHumidity.setText(geoLocation.getWeatherFieldString("currently", "humidity")+" [Humidity]");
        add(TextHumidity, java.awt.BorderLayout.PAGE_START);

        TextTemperature.setText(geoLocation.getWeatherFieldString("currently", "temperature")+"\u00b0"+" F [Temperature]");
        add(TextTemperature, java.awt.BorderLayout.PAGE_END);

        TextPrecipitationProbability.setText(geoLocation.getWeatherFieldString("currently", "precipProbability")+" [Probability of Precipitation]");
        add(TextPrecipitationProbability, java.awt.BorderLayout.LINE_END);

        TextCloudCover.setText(geoLocation.getWeatherFieldString("currently", "cloudCover")+" [CloudCover]");
        add(TextCloudCover, java.awt.BorderLayout.LINE_START);
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }


    // Variables declaration - do not modify                     
    private javax.swing.JPanel IconImage;
    private javax.swing.JTextField TextCloudCover;
    private javax.swing.JTextField TextPrecipitationProbability;
    private javax.swing.JTextField TextTemperature;
    private javax.swing.JTextField TextHumidity;
    // End of variables declaration                   
    
}
