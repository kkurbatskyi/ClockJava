import java.awt.*;
import java.time.LocalTime;
import java.time.temporal.ChronoField;

/** A hand which indicates a time.
	Primarily represents a single time unit
*/
public abstract class Hand {

	protected final static double RADIANS = Clock.RADIANS;
	
	private final TimeUnit unit;
	
	protected Hand(TimeUnit unit) {
		this.unit = unit;
	}

	/** Sets the time of this hand. 
		@param t the new time for this hand to indicate. */
	public abstract void setTime(LocalTime t);
	
	/** Gets the angle, clockwise from 12 o'clock, in radians, for this hand.
		@return the angle of this hand clockwise from to 12 o'clock. Positive.
			eg: Hour hand at 3 o'clock is +PI/2
	*/
	public abstract double getAngle();
	
	/** Gets the time unit of this hand. */
	public TimeUnit getTimeUnit() {
		return unit;
	}

	/** Draws this hand of the clock at the current canvas position */
	public abstract void draw(Graphics2D g2);


	/**	Describes the temporal attributes of a hand. */
	public enum TimeUnit {
		MILL(ChronoField.MILLI_OF_SECOND),
		SECOND(ChronoField.SECOND_OF_MINUTE),
		MINUTE(ChronoField.MINUTE_OF_HOUR),
		HOUR(ChronoField.HOUR_OF_AMPM);

		/** The ChonoField of this hand. eg: MINUTE_OF_HOUR */
		private final ChronoField cf;
		/** the max value for this ChronoField */
		private final int maxValue;

		TimeUnit(ChronoField aCF) {
			cf = aCF;
			maxValue = (int) cf.range().getMaximum() + 1;
		}

		/** @return the value of the time field reported by this hand.
			eg: 6 for the hour hand at 06:45:30pm
		*/
		public int getValue(LocalTime aTime) {
			return aTime.get(cf);
		}

		/** @return the exclusive upper bounds of this TimeUnit's values.
			eg: 60 for MINUTE_OF_HOUR
		*/
		public int getMax() {
			return maxValue;
		}
	}

	/**	Describes the graphical attributes of a hand. */
	public static class GraphicsInfo {

		/** The graphical length of this hand
			expressed as a percentage of the readius. */
		public final int length;
		/** The brush stroke used to braw this hand. */
		public final BasicStroke stroke;
		/** The color of the hand. */
		public final Color color;

		/**
			@param aLength the length of the hand as a percentage of the radius
				of the clock face.
			@param aWidth the width of the hand
		*/
		public GraphicsInfo(int aLength, int aWidth) {
			this(aLength, aWidth, Color.BLACK);
		}

		/**
			@param aLength the length of the hand as a percentage of the radius
				of the clock face.
			@param aWidth the width of the hand
			@param Color the color of the hand
		*/
		public GraphicsInfo(int aLength, float aWidth, Color aColor) {
			length = (Clock.SCALE * aLength) / (100 * 2);
			stroke = new BasicStroke(aWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
			color = aColor;
		}
	}
}
