package polluxPak;
import java.io.IOException;
import java. util.Scanner;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.EncoderMotor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.MeanFilter;
import lejos.robotics.filter.PublishFilter;
import lejos.hardware.Device;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import lejos.robotics.Color;

public class Sensors {
	EV3UltrasonicSensor uss;
	EV3TouchSensor touch;
	EV3ColorSensor color;
	public Sensors() {
		uss = new EV3UltrasonicSensor(SensorPort.S1);
		touch = new EV3TouchSensor(SensorPort.S3);
		color = new EV3ColorSensor(SensorPort.S4);
		// TODO Auto-generated constructor stub
	}
	
	public getUltrason() {
		// TODO obtenir la distance captée par le capteur uss
	}
	public getTouch() {
		// TODO obtenir l'info captée par le capteur touch
	}
	public getColor() {
		// TODO obtenir la couleur captée par le capteur color
	}
}
