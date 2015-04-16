package arduinoTest;

import rxtxrobot.AnalogPin;
import rxtxrobot.ArduinoUno;
import rxtxrobot.RXTXRobot;

public class Operation {
	public static void main(String[] args){
		
		RXTXRobot r = new ArduinoUno();
		Robot y = new Robot();
		r.setPort("COM5"); // Sets the port to COM5 
		r.connect(); 
		
		//****************************************************************
		///////////////////order of operations for robot//////////////////
		//****************************************************************
	
	}
	
}
