
package polluxPak;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;


public class Actionneurs {
	 RegulatedMotor l1;
	 RegulatedMotor r1;
	private RegulatedMotor pince;
	private static final int QuartT =360;
	private static final boolean DROITE=true;
	private static final boolean GAUCHE=false;



	public Actionneurs (Port A,Port B,Port D) {
		l1 = new EV3LargeRegulatedMotor(A);
		r1= new EV3LargeRegulatedMotor(B);
		pince= new EV3LargeRegulatedMotor(D);
		pince.setSpeed(700);
		l1.synchronizeWith(new RegulatedMotor[] {r1});
		l1.startSynchronization();
	}
	public Actionneurs() {
		// TODO Auto-generated constructor stub
	}
	public  void avance(int t) {
		l1.startSynchronization();
			l1.forward();
			r1.forward();
			l1.endSynchronization();
			Delay.msDelay(t);
			
	}
	public  void avance() {
		l1.startSynchronization();
		l1.forward();
		r1.forward();
		l1.endSynchronization();
		
}
	public void recule() {
		l1.backward();
		r1.backward();
		
	}
	public void stop() {
		l1.stop();
		l1.stop();
	}
	public void actionPince() {
		pince.rotate(3*QuartT);
		pince.rotate(-3*QuartT);
	}
	public void tourner(boolean dir,int nbQuartT) {
		l1.endSynchronization();
		if(dir==DROITE) {
			r1.stop();
			l1.rotate(QuartT*nbQuartT);
		}else if(dir==GAUCHE) {
			l1.stop();
			r1.rotate(QuartT*nbQuartT);
		}
		l1.startSynchronization();
	}


	public static void main (String[]args) {
		Actionneurs a=new Actionneurs(MotorPort.A,MotorPort.B,MotorPort.D);
		a.avance(10000);
	

	}
}
