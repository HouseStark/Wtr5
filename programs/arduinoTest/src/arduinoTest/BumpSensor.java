package arduinoTest;
import rxtxrobot.ArduinoUno;
import rxtxrobot.RXTXRobot;
import rxtxrobot.*;
//bump sensor works!
public class BumpSensor {
		public static void main(String[] args){
			RXTXRobot r = new ArduinoUno();
			r.setPort("COM6");
			r.connect();
			//Checks for push sensor for about 10 seconds
			for(int i = 0; i < 100; ++i)
			{
				r.refreshDigitalPins();
				System.out.println(r.getDigitalPin(11).getValue());
				/*if (r.getDigitalPin(11).getValue() == 1)
				{
					System.out.println("touch sensor!");
					//r.close();
				}*/
				//else if (r.getDigitalPin(13).getValue() == 1)
					//System.out.println("touch sensor");
			}
			
			//Encoder for morotor 1: D2, Encoder Motor 2: D3
			//Motor 1 -- D
			r.close();
			
		}
}
