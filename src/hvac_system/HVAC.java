package hvac_system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Rahul
 */
public class HVAC implements Heater, AC, Fan, HumidityController, Ventilation
{
boolean startNavigation = false;
    private static double temp;
    private static boolean acStatus;
    private double acTemp;
    private Double humid;
    private Double aqi;
    private boolean heaterStatus;
    private boolean fanStatus;
    private int fanSpeed;
    private boolean humditiyController;
    private boolean exhaustStatus;
    private int exhaustSpeed;
    private double heaterTemp;
    String mode;
    String aqimeaning;
    String ac_mail;
    String heater_mail;
    String fan_mail;
    String exhaust_mail;
    String humditiyController_mail;
    String User_name;
    String Password;
    public void init() {
        // TODO start asynchronous download of heavy resources
    	
    }

    public static void main(String[] args) {
        // TODO code application logic here
        login ln = new login();
        HvacController hcon = new HvacController();
        HvacNavigation hnav = new HvacNavigation();
        emailScreen email = new emailScreen();
        HVAC hvac = new HVAC();
        
        ln.setBounds(0,40,590,380);
        ln.setResizable(false);
        ln.setVisible(true);
       
        hnav.setResizable(false);
        hcon.setResizable(false);
        email.setResizable(false);
        

        hvac.startNavigation = false;
        
        ln.Submit.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent ae) {
                   String user= ln.User.getText();
        String pass=ln.Pass.getText();
        
        if(user.equals("Admin") && pass.equals("Admin@123"))
        {
            //c++;
            hnav.setVisible(true);
       hcon.setVisible(true);
       ln.setVisible(false);
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Wrong id and password try again","Login",JOptionPane.WARNING_MESSAGE);
            ln.User.setText("");
            ln.Pass.setText("");
        }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }
       });
        hcon.jButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(hcon.autoMode.isSelected() || hcon.winterMode.isSelected() || hcon.summerMode.isSelected())
				{
					System.out.println("Get input from the sensors");
					String tempMode;

					double tempt, temphu;
					int tempaqi;
					
					
					if(hcon.autoMode.isSelected())
					{
						Random rand = new Random();
						tempt = rand.nextDouble()*10+20;
						temphu = rand.nextDouble()*100;
						tempaqi = rand.nextInt(500);
					}
					else if(hcon.winterMode.isSelected())
					{
						Random rand = new Random();
						tempt =(( rand.nextDouble()*25) - 5);
						temphu = rand.nextDouble()*100;
                                               
						tempaqi = rand.nextInt(500);
				
					}
					else {
						Random rand = new Random();
						tempt = rand.nextDouble()*27 + 22;
						temphu = rand.nextDouble()*100;
						tempaqi = rand.nextInt(500);
				
					}
				
					tempt = ((double)Math.round(tempt * 100))/100;
					temphu = ((double)Math.round(temphu * 100))/100;
				
					hcon.temperature.setText(Double.toString(tempt));
					hcon.humidity.setText(Double.toString(temphu));
					hcon.airQuality.setText(Integer.toString(tempaqi));
				
				}
				else {
					String message = "Please select any mode";
					JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
    		
    	});
        hcon.Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                
                
                HVAC.temp = Double.parseDouble(hcon.temperature.getText());
                hvac.humid = Double.parseDouble(hcon.humidity.getText());
                
                hvac.aqi = Double.parseDouble(hcon.airQuality.getText());
                
                
            
                
                if(hcon.fanOn.isSelected())
                {
                    hvac.FanOn();
                    hcon.fanSpeed.setEnabled(true);
                    hvac.setFanSpeed( hcon.fanSpeed.getValue() );
                }
                else
                {
                    
                    hvac.FanOff();
                }
                if(hcon.autoMode.isSelected())
		    hvac.mode = "AUTO";
		else if(hcon.summerMode.isSelected())
		    hvac.mode = "SUMMER";
		else
		    hvac.mode = "WINTER";
                if((hvac.temp >50 || hvac.temp < -50) && (hvac.humid <0 || hvac.humid> 100) && (hvac.aqi < 0 || hvac.aqi > 500))
                {
                    String message = "Please give the correct input";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
                                        hcon.clear();
                    
                }
                else if((hvac.temp >50 || hvac.temp < -50) && (hvac.humid <0 || hvac.humid> 100))
                {
                 String message = "Temperature should be between -50 to 50 C and Humidity should be between 0 to 100 %. ";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
                                        hcon.clear();   
                }
                else if((hvac.temp >50 || hvac.temp < -50) && (hvac.aqi < 0 || hvac.aqi > 500))
                {
                    String message="Temperature should be between -50 to 50 C and AQI level should be between 0 to 500.";
                     JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
                                        hcon.clear();  
                }
                else if ((hvac.aqi < 0 || hvac.aqi > 500) && (hvac.humid <0 || hvac.humid> 100))
                {
                     String message="Humidity should be between 0 to 100 % and AQI level should be between 0 to 500.";
                     JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
                                        hcon.clear();
                }
               else if(HVAC.temp > 55 || HVAC.temp < -50)
				{
					String message = "Temperature should be between -50 to 50 C.";
					JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
                                        hcon.clear();
                                     
				}
				else if(hvac.humid < 0 || hvac.humid > 100)
				{
					String message = "Humidity should be between 0 to 100 %.";
					JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
                                        hcon.clear();
                                      
				}
				else if(hvac.aqi < 0 || hvac.aqi > 500)
				{
					String message = "AQI level should be between 0 to 500.";
					JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
                                        hcon.clear();
                                    
				}
                                else
                                 {
                                     
                                     JOptionPane.showMessageDialog(new JFrame(),"Thank you for the input","Output",JOptionPane.PLAIN_MESSAGE);
                                     hvac.startNavigation = true;
                                    
                                        hvac.ExhaustOn();
                                        hvac.ACOn();
                                        if(hvac.fanStatus)
                                          {
                                                    hnav.currentfanStatus.setText("ON");
                                                    hnav.currentfanSpeed.setText(Integer.toString(hvac.fanSpeed));
                                          }
                                        else
                                           {
                                                hnav.currentfanStatus.setText("OFF");
                                                hnav.currentfanSpeed.setText(Integer.toString(hvac.fanSpeed));
                                                
                                                
                                           }
                                        if(hvac.exhaustStatus)
                                            {
                                                    hnav.currentexhaustStatus.setText("ON");
                                             }
                                        else
                                            {
                                                    hnav.currentexhaustStatus.setText("OFF");
                                            }
		    	if(          "AUTO".equals(hvac.mode)) {
		    		hvac.HeaterOff();
                                hvac.ACOff();
		    		if(HVAC.temp > 29)
		    		{
                                        hvac.ACOn();
		    			hvac.HeaterOff();
		    			hvac.setACTemp(24);
		    		
		    		}
		    		else if(HVAC.temp < 21)
		    		{
                                        hvac.HeaterOn();
                                        hvac.ACOff();
		    			hvac.setHeaterTemp(24);
		    			
		    		}
		    	}
		    	else if(     "WINTER".equals(hvac.mode))
		    	{
		    		hvac.ACOff();
                                hvac.HeaterOff();
		    		hnav.currentacTemperature.setText(" ");
		    		if(HVAC.temp < 18)
		    		{
		    			hvac.HeaterOn();
		    			hvac.setHeaterTemp(22);
		    		}
		    	}
		    	else
		    	{
		    		hvac.HeaterOff();
                                hvac.ACOff();
		    		if(HVAC.temp > 27)
		    		{
                                        hvac.ACOn();
		    			hvac.setACTemp(25);
		    			hnav.currentacTemperature.setText("25 C");
		    		}
		    	}
                        hnav.currenthumidity.setText("50");
                        if(HVAC.acStatus == true)
                        {
                            hnav.currentacStatus.setText("ON");
                            hnav.currentacTemperature.setText(Double.toString(hvac.acTemp) + " C");
                        }
                        else
                        {
                            hnav.currentacStatus.setText("OFF");
                            hnav.currentacTemperature.setText(" ");
                        }
                        
                        if(hvac.heaterStatus == true)
                        {
                             hnav.currentheaterStatus.setText("ON");
                             hnav.currentheaterTemperature.setText(Double.toString(hvac.heaterTemp) + " C");
                        }
                        else
                        {
                            hnav.currentheaterStatus.setText("OFF");
                            hnav.currentheaterTemperature.setText(" ");
                        }
                        if(hvac.humid > 60)
                         {
                             hvac.HumidityControlOn();
                         }
                         else
                         {
                            hvac.HumidityControlOff();
                         }
                        if(hvac.humditiyController == true)
                        {
                           hnav.currenthumiditycontrollerStatus.setText("ON"); 
                        }
                        else
                       {
                        hnav.currenthumiditycontrollerStatus.setText("OFF");
                       }
			hvac.ExhaustOn();
			hvac.setExhaustSpeed();
                        hnav.currentexhaustSpeed.setText(Integer.toString(hvac.exhaustSpeed) + " FPM");
		        hnav.aqi.setText(Double.toString(hvac.aqi));
                        
                        
                        if(hvac.aqi <=50.0)
                        {
                            hvac.aqimeaning = "Good air:- Air quality is considered satisfactory,and air pollution poses little or no risk .";
//                        hnav.aqimeaning.setText("Good air:- Air quality is considered satisfactory,and air pollution poses little or no risk .");
                        }
                        else if(hvac.aqi >=51.0   &&  hvac.aqi <= 100)
                        {
                              hvac.aqimeaning = "Moderate air:- Air quality is acceptable,however,for some pollutants there may be a moderate health concern for a very small number of people"+ "\n"+"who are unusually sensitive to air pollution.";
//                        hnav.aqimeaning.setText("Moderate air:- Air quality is acceptable,however,for some pollutants there may be a moderate health concern for a very small number of people who are unusually sensitive to air pollution.");
                        }
                        else if(hvac.aqi >=101.0   &&  hvac.aqi <= 150)
                        {
                            hvac.aqimeaning = "Unhealth for Sensitive Group:- Members for sensitive groups may experience health effects.The general public is not likely to be affeccted.";
//                        hnav.aqimeaning.setText("Unhealth for Sensitive Group:- Members for sensitive groups may experience health effects. The general public is not likely to be affeccted.");
                        }
                        else if(hvac.aqi >=151.0   &&  hvac.aqi <= 200)
                        {
                            hvac.aqimeaning = "Unhealth:- Everyone may begin to experience health effects; members of sensitive groups may experience more serious health effects.";
//                        hnav.aqimeaning.setText("Unhealth:- Everyone may begin to experience health effects; members of sensitive groups may experience more serious health effects");
                        }
                        else if(hvac.aqi >=201.0   &&  hvac.aqi <= 300)
                        {
                            hvac.aqimeaning = "Very Unhealth:- Health alert: everyone may experience more serious health effects .";
//                        hnav.aqimeaning.setText("Very Unhealth:- Health alert: everyone may experience more serious health effects");
                        }
                        else if(hvac.aqi >=301.0   &&  hvac.aqi <= 500)
                        {
                            hvac.aqimeaning = "Hazardous:- Health warnings of emergency conditions. The entire population is more likely to be affected .";
//                        hnav.aqimeaning.setText("Hazardous:- Health warnings of emergency conditions. The entire population is more likely to be affected");
                        
                        }
                        
                        
                        hnav.aqiMeaning.setText(hvac.aqimeaning);
                       hnav.editfalse();
                       if(HVAC.acStatus)
                        {
                            hvac.ac_mail=  "Air Conditioner Status:-"  + "ON" +"\n" +"Air Conditioner Temperature:-"+  hvac.acTemp;
                        }
                       else
                       {
                           hvac.ac_mail=  "Air Conditioner Status:-"  + "OFF";
                       }
                       
                       if(hvac.heaterStatus)
                        {
                            hvac.heater_mail= "Heater Status:-" + "ON" +"\n" +"Heater Temperature:-" + hvac.heaterTemp ;
                        }
                       else
                       {
                           hvac.heater_mail=  "Heater Status:-" + "OFF";
                       }
                       if(hvac.fanStatus)
                       {
                           hvac.fan_mail ="Fan Status:-" + "ON" + "\n" +"Fan Speed:-" + hvac.fanSpeed;
                       }
                       else
                       {
                           hvac.fan_mail ="Fan Status:-" + "OFF";
                       }
                       if(hvac.exhaustStatus)
                       {
                           hvac.exhaust_mail ="Exhaust Status:-" + "ON" + "\n" +"Exhaust Speed:-" + hvac.exhaustSpeed ;
                       }
                       else
                       {
                           hvac.exhaust_mail="Exhaust Status:-" + "OFF";
                       }
                       if(hvac.humditiyController)
                       {
                           hvac.humditiyController_mail ="Humidity Controller Status:-" + "ON" ;
                       }
                       else
                       {
                            hvac.humditiyController_mail ="Humidity Controller Status:-" + "OFF" ;
                       }
                       
                        email.textFrom.setText("billisingh94@gmail.com");
                        email.textSubject.setText("Regarding actions taken by HVAC System");
                         email.textMessage.setText("Respected Sir/Ma'am,\n" +"                                     Here is the report from the HVACsystem "+"around the CC3 Building\n" +hvac.ac_mail+ "\n" +hvac.heater_mail + "\n" +hvac.fan_mail + "\n" + hvac.exhaust_mail + "\n" + hvac.humditiyController_mail + "\n" +"Air Quality (in ppm):-" + hvac.aqi +"\n" + "Meaning (Acc. to Air Quality):-"+ hvac.aqimeaning);
                         email.false1();
                         email.setVisible(true);
                                 }
}
        }); 
       
    }

    @Override
    public void ACOn() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          this.acStatus = true;
    }

    @Override
    public void ACOff() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          this.acStatus = false;
    }

    @Override
    public void setACTemp(double temp) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          this.acTemp = temp;
    }

    @Override
    public void incACTemp(double temp) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void decACTemp(double temp) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void HeaterOn() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.heaterStatus = true;
    }

    @Override
    public void HeaterOff() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.heaterStatus = false;
    }

    @Override
    public void incHeaterTemp(double temp) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void decHeaterTemp(double temp) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setHeaterTemp(double temp) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.heaterTemp = temp;
    }

    @Override
    public void FanOn() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.fanStatus = true;
    }

    @Override
    public void FanOff() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.fanStatus = false;
        setFanSpeed(0);
    }

    @Override
    public void setFanSpeed(int speed) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.fanSpeed = speed;
    }

    @Override
    public void HumidityControlOn() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.humditiyController = true;
    }

    @Override
    public void HumidityControlOff() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.humditiyController = false;
    }

    @Override
    public void incHumidity() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void decHumidity() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ExhaustOn() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.exhaustStatus = true;
    }

    @Override
    public void ExhaustOff() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.exhaustStatus = false;
    }

    @Override
    public void setExhaustSpeed() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.exhaustSpeed = 500;
    }

    @Override
    public void incExhaustSpeed() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void decExhaustSpeed() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
