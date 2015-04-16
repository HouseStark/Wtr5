package arduinoTest;

import rxtxrobot.*;


public class Turbidity {
	public static void main(String[] args){
		RXTXRobot r = new ArduinoUno();
		r.setPort("COM5");
		r.connect();
		
		for(int i = 0; i < 100; ++i)
		{
			r.refreshDigitalPins();
			//print value from digital pint
			System.out.println("dig 12 " + r.getDigitalPin(12).getValue());
			System.out.println("dig 13 " + r.getDigitalPin(13).getValue());
			System.out.println("analog 12 " + r.getAnalogPin(12).getValue());
			System.out.println("analog 13 " + r.getAnalogPin(13).getValue());
		}
		r.close();
	}//end of
}//end of class
