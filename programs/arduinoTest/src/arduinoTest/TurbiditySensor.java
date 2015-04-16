package arduinoTest;

import rxtxrobot.*;

public class TurbiditySensor {
	    public static void main(String[] args) 
	    {     
		    // All sensor data will be read from the analog pins 
			 
		    RXTXRobot r = new ArduinoUno(); //Create RXTXRobot object 
	 
			r.setPort("COM6"); // Sets the port to COM5 
			 
			r.connect(); 
	 
			r.refreshAnalogPins(); // Cache the Analog pin information 
				 
			for (int x=0; x < 5; ++x) 
			{ 
				AnalogPin temp = r.getAnalogPin(0); 
				System.out.println("Turbidity Sensor " + x + " has value: " + temp.getValue()); 
			} 
			AnalogPin temp = r.getAnalogPin(1); 
			//turbidity equation test+
			double turbidityTotal = 0;
			double turbidityValue =  temp.getValue();
			turbidityTotal = (-0.8096 * turbidityValue) + 533.07;
			turbidityTotal = (turbidityTotal * 3) / 5;
			System.out.println("Turbidity value and pick up: " + turbidityTotal + " Ping pong balls");
			
			
			r.close(); 
	    } 
	} 

