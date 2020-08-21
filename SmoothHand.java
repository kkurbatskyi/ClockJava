import java.awt.*;
import java.awt.geom.Line2D;
import java.time.LocalTime;

/** A hand on the face of a clock.  Each hand is responsible for drawing itself
	for the given time-unit, with the given graphical properties.
	Hand moves smoothly between ticks based on the value of the previous hand.
*/
public class SmoothHand extends JumpHand {

	private final Hand prev;

	/** Construct a hand to display the given time with the given time unit.
		Adds a proportion of the previous hand for partial movement. */
	public SmoothHand(TimeUnit unit, Hand prev, GraphicsInfo ginfo) {
		super(unit, ginfo);
		this.prev = prev;
	}
	
	@Override
	public void setTime(LocalTime t) {
		super.setTime(t);
	}

	@Override
	public double getAngle() {
		return super.getAngle() + (prev.getAngle() / super.getTimeUnit().getMax());
	}
}
