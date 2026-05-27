package Azov;
//Слава Україні

import robocode.*;

import java.awt.*;

public class OrcKiller extends AdvancedRobot {
	boolean movingForward;

	/**
	 * Aqui fica o comportamento geral do robo
	 */
	public void run() {
		// Aqui vamos deixar ele coloridinho
		setBodyColor(new Color(0, 87, 183));     //  azul principal
		setGunColor(new Color(0, 60, 130));      //  azul mais escuro
		setRadarColor(new Color(100, 149, 237)); //  azul claro
		setBulletColor(new Color(255, 215, 0));  //  amarelo
		setScanColor(new Color(255, 255, 102));  //  amarelo claro

		// Loop Infinito ate que algum evento seja acionado
		while (true) {
			setAhead(40000);
			movingForward = true;
			setTurnRight(90);
			waitFor(new TurnCompleteCondition(this));
			setTurnLeft(180);
			waitFor(new TurnCompleteCondition(this));
			setTurnRight(180);
			waitFor(new TurnCompleteCondition(this));
			//As linhas acima fazem o robô ficar em zig zag infinito ate que algum evento ocorra
			setTurnRadarRight(360);
			//Radar ficará scaneando infinitamente ate encontrar algum robô
		}
	}
	public void onHitWall(HitWallEvent e) {
		// Bounce off!
		reverseDirection();
	}

	public void reverseDirection() {
		if (movingForward) {
			setBack(40000);
			movingForward = false;
		} else {
			setAhead(40000);
			movingForward = true;
		}
	}
	//Finca fogo de força 2 quando le algum robô no radar
	public void onScannedRobot(ScannedRobotEvent e) {
		setFire(2);
	}

	//Caso atinja um robô e ele for o "culpado" faz ele andar de ré
	public void onHitRobot(HitRobotEvent e) {
		if (e.isMyFault()) {
			reverseDirection();
		}
	}
}
