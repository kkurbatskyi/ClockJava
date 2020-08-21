import javax.swing.JFrame;
import java.time.LocalTime;

@SuppressWarnings("serial")
public class ClockFrame extends JFrame {

	private final static int WIDTH = 500;
	private final static int HEIGHT = 500;

	public static void main(String[] args) {
		ClockComponent clock;
		switch (args.length) {
			case 0: // live clock
				clock = new ClockComponent();
				break;
			case 1: // parse a time
				clock = new ClockComponent(LocalTime.parse(args[0]));
				break;
			case 3: // parse time values as ints
				clock = new ClockComponent(LocalTime.of(
					Integer.parseInt(args[0]),
					Integer.parseInt(args[1]),
					Integer.parseInt(args[2])));
				break;
			default:
				throw new IllegalArgumentException("Invlaid command line args");
		}
		JFrame f = new ClockFrame();
		f.setSize(WIDTH, HEIGHT);
		f.setTitle("A Clock");
		f.setContentPane(clock);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
