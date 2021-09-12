/**
 * Used to get a linear changing value, independent from how often it gets called
 * 
 * @author _SIM_
 */
public class Animation {
	
	/**
	 * @param duration The total duration of the animation <b>in seconds</b>
	 * @param start The value at the start of the animation
	 * @param end The value at the end of the animation
	 */
	public Animation(float duration, float start, float end) {
		this((long) (duration * 1000), start, end);
	}
	
	/**
	 * @param duration The total duration of the animation <b>in milliseconds</b>
	 * @param start The value at the start of the animation
	 * @param end The value at the end of the animation
	 */
	public Animation(long duration, float start, float end) {
		this.value = start;
		this.end = end;
		this.start = start;
		this.increasing = end > start;
		float difference = Math.abs(start - end);
		this.changePerMillisecond = difference / duration;
		this.lastTime = System.currentTimeMillis();
	}
	
	/**
	 * @param changePerSecond How much the value should change per second
	 * @param start The value at the start of the animation
	 * @param end The value at the end of the animation
	 * @return the constructed Animation instance
	 */
	public static Animation fromChangePerSecond(float changePerSecond, float start, float end) {
		return new Animation(Math.abs(start - end) / changePerSecond, start, end);
	}

	/**
	 * The current value
	 */
	private float value;
	/**
	 * The last time the value got modified
	 */
	private long lastTime;
	/**
	 * How much the number should increase / decrease with each second
	 */
	private float changePerMillisecond;

	/**
	 * The initial value
	 */
	private float start;
	/**
	 * The value at the end of the animation
	 */
	private float end;
	
	/**
	 * Wheter the value is constantly decreasing or increasing
	 */
	boolean increasing;
	
	/**
	 * Resets the animation to the start
	 */
	public void reset() {
		value = start;
		lastTime = System.currentTimeMillis();
	}

	/**
	 * @return The current value
	 */
	public float getValue() {
		if (value == end) return value;
		if (increasing) {
			if (value >= end) {
				value = end;
				return value;
			}
			value += (changePerMillisecond) * (System.currentTimeMillis() - lastTime);
			if (value > end)
				value = end;
			this.lastTime = System.currentTimeMillis();
			return value;
		} else {
			if (value <= end) {
				value = end;
				return value;
			}
			value -= (changePerMillisecond) * (System.currentTimeMillis() - lastTime);
			if (value < end)
				value = end;
			this.lastTime = System.currentTimeMillis();
			return value;
		}
	}
	
	/**
	 * @return If the animation is already over (value = end)
	 */
	public boolean isDone() {
		return value == end;
	}
}
