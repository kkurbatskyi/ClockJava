import java.awt.*;

import javax.swing.JComponent;
import javax.swing.Timer;

import java.time.LocalTime;

@SuppressWarnings("serial")
public class ClockComponent extends JComponent {

	private final static int FPS = 60;
	private final static int INTERVAL = 1000/FPS;

	private final Clock clock;

	/** Make a clock that displays the current time & updates 60 times a second. */
	public ClockComponent() {
		this(LocalTime.now());
		// create a timer to update our clock 60 times per second
		new Timer(INTERVAL, (a) -> {tick();}).start();
	}

	/** A clock that displays a given time.  Static. */
	public ClockComponent(LocalTime aTime) {
		clock = new Clock();
		clock.setTime(aTime);
	}

	/** Update the clock to display the current time. */
	private void tick() {
		clock.setTime(LocalTime.now());
		repaint();
	}

	/** Paints the clock. */
	@Override
	public void paintComponent(Graphics g) {
		// the component's dimensions
		final int w = getWidth(); // width
		final int h = getHeight(); // height
		g.clearRect(0, 0, w, h);
		
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		int min = Math.min(w, h);
		double scaleFactor = min/(double)Clock.SCALE;
		g2.translate(w/2, h/2);
		g2.scale(scaleFactor, scaleFactor);
		g2.translate(-Clock.SCALE/2, -Clock.SCALE/2);
		clock.draw((Graphics2D)g2);
		g2.dispose();
		
	}
}
