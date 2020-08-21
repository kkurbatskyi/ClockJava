import java.time.LocalTime;
import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;

public class Clock {

	/** Radians in a circle. */
	public final static double RADIANS = Math.PI*2;
	/** the size of the clock */
	public final static int SCALE = 200;
	/** the centre of the clock */
	public final static int CENTRE = SCALE/2;

	private final static int NUM_SLIM_TICKS = 60;
	private final static int NUM_TICKS = 12;
	private final static int NUM_FAT_TICKS = 4;
	private final static int TICK_LENGTH = SCALE / 10;

	private final Hand[] hands;

	/** Creates a new clock set to the indicated time.
		@param t the intial time of the clock
	*/
	public Clock() {
		hands = new Hand[4];
		hands[0] = new JumpHand(Hand.TimeUnit.MILL, new Hand.GraphicsInfo(0, 0));
		hands[1] = new SmoothHand(Hand.TimeUnit.SECOND, hands[0], new Hand.GraphicsInfo(90, 2, Color.RED));
		hands[2] = new SmoothHand(Hand.TimeUnit.MINUTE, hands[1], new Hand.GraphicsInfo(60, 3, Color.BLACK));
		hands[3] = new SmoothHand(Hand.TimeUnit.HOUR, hands[2], new Hand.GraphicsInfo(50, 5, Color.BLACK));
	}

	/** Sets the time on the clock. Updates hand positions.
		@param t the time
	*/
	public void setTime(LocalTime t) {
		for(int i = 0; i < hands.length; i++)
		{
			hands[i].setTime(t);
		}
	}

	/** @return the hands of this clock in order: MILL, SECOND, MINUTE, HOUR */
	public Hand[] getHands() {
		return hands;
	}

	/** Draws the clock at the current canvas position. */
	public void draw(Graphics2D g) {
		
		g.setPaint(Color.GRAY);
		g.fill(new Rectangle.Float(0, 0, SCALE, SCALE));
		g.draw(new Rectangle.Float(0, 0, SCALE, SCALE));
		g.setPaint(Color.WHITE);
		
		Ellipse2D clock = new Ellipse2D.Float(0, 0, SCALE, SCALE);
		
		g.fill(clock);
		g.draw(clock);
		Graphics2D g2 = (Graphics2D)g.create();
		g.setPaint(Color.BLACK);
		for(int i = 0; i <= NUM_SLIM_TICKS; i++)
		{
			Rectangle2D rect = new Rectangle(CENTRE, CENTRE, 0, CENTRE);
			g2.rotate(RADIANS / NUM_SLIM_TICKS * i, rect.getX(), rect.getY());
			g2.draw(rect);
			g2 = (Graphics2D)g.create();
		}
		
		for(int i = 0; i <= NUM_TICKS; i++)
		{
			Rectangle2D rect = new Rectangle( CENTRE, CENTRE, CENTRE, CENTRE / 36);
			g2.rotate(RADIANS / NUM_TICKS * i, rect.getX(), rect.getY());
			g2.draw(rect);
			g2.fill(rect);
			g2 = (Graphics2D)g.create();
		}

		for(int i = 0; i <= NUM_FAT_TICKS; i++)
		{
			Rectangle2D rect = new Rectangle(CENTRE, CENTRE - CENTRE / 48, CENTRE, CENTRE / 24);
			g2.rotate(RADIANS / NUM_FAT_TICKS * i, CENTRE, CENTRE);
			g2.draw(rect);
			g2.fill(rect);
			g2 = (Graphics2D)g.create();
		}
		
		g.setPaint(Color.WHITE);
		int topLeft = SCALE / 15;
		g.fill(new Ellipse2D.Float(topLeft, topLeft, SCALE - topLeft * 2, SCALE - topLeft * 2));
		g.draw(new Ellipse2D.Float(0, 0, SCALE, SCALE));
		
		g.setPaint(Color.BLACK);
		g.draw(new Ellipse2D.Float(CENTRE - CENTRE / 20, CENTRE - CENTRE / 20, CENTRE / 10, CENTRE / 10));
		
		g.translate(CENTRE, CENTRE);
		for(int i = 0; i < 4; i++)
		{
			hands[i].draw(g);
		}
	}
}
