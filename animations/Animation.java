/**
 * Used to get a changing value, independent from how often it gets called and when
 * This eliminates discrepancies with different framerates
 * 
 * @author SIMULATAN (github.com/SIMULATAN)
 */
public class Animation {
	
	/**
	 * @param duration The total duration of the animation <b>in seconds</b>
	 * @param start The value at the start of the animation
	 * @param end The value at the end of the animation
	 */
	public Animation(float duration, float start, float end) {
		this((long) (duration * 1000), start, end, Easing.LINEAR);
	}
	
	/**
	 * @param duration The total duration of the animation <b>in seconds</b>
	 * @param start The value at the start of the animation
	 * @param end The value at the end of the animation
	 * @param easing The easing of the animation
	 */
	public Animation(float duration, float start, float end, Easing easing) {
		this((long) (duration * 1000), start, end, easing);
	}
	
	/**
	 * @param duration The total duration of the animation <b>in milliseconds</b>
	 * @param start The value at the start of the animation
	 * @param end The value at the end of the animation
	 */
	public Animation(long duration, float start, float end) {
		this(duration, start, end, Easing.LINEAR);
	}
	
	/**
	 * @param duration The total duration of the animation <b>in milliseconds</b>
	 * @param start The value at the start of the animation
	 * @param end The value at the end of the animation
	 * @param easing The easing of the animation
	 */
	public Animation(long duration, float start, float end, Easing easing) {
		this.value = start;
		this.end = end;
		this.start = start;
		this.increasing = end > start;
		float difference = Math.abs(start - end);
		this.changePerMillisecond = difference / duration;
		this.lastTime = System.currentTimeMillis();
		this.easing = easing;
	}

	/**
	 * Instead of constructing the animation with a specific duration construct it with a specific amount to change in a second
	 *
	 * @param changePerSecond How much the value should change per second
	 * @param start The value at the start of the animation
	 * @param end The value at the end of the animation
	 * @return the constructed Animation instance
	 */
	public static Animation fromChangePerSecond(float changePerSecond, float start, float end) {
		return fromChangePerSecond(changePerSecond, start, end, Easing.LINEAR);
	}

	/**
	 * Instead of constructing the animation with a specific duration construct it with a specific amount to change in a second
	 *
	 * @param changePerSecond How much the value should change per second
	 * @param start The value at the start of the animation
	 * @param end The value at the end of the animation
	 * @param easing The easing of the animation
	 * @return the constructed Animation instance
	 */
	public static Animation fromChangePerSecond(float changePerSecond, float start, float end, Easing easing) {
		return new Animation(Math.abs(start - end) / changePerSecond, start, end, easing);
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
	 * The easing of the animation
	 */
	private Easing easing;
	
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
		// easing should never be null but if it is, default to Easing.LINEAR
		return getEased(easing != null ? easing : Easing.LINEAR);
	}
	
	/**
	 * @return The current linear value
	 */
	private float loadValue() {
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

	/**
	 * @param easing The easing that should be used
	 * @return The value with the specific easing applied
	 */
	public float getEased(Easing easing) {
		if (easing == Easing.LINEAR) return loadValue();
		return map(Easings.getEasingValue(map(loadValue(), start, end, 0, 1), easing), 0, 1, start, end);
	}

	@Override
	public String toString() {
		return "Animation{" +
				"value=" + value +
				", lastTime=" + lastTime +
				", changePerMillisecond=" + changePerMillisecond +
				", start=" + start +
				", end=" + end +
				", increasing=" + increasing +
				", easing=" + easing +
				'}';
	}

	/**
	 * This is a utility method, move it to your own utility class if you want
	 */
	private static float map(float value, float minInput, float maxInput, float minMapped, float maxMapped) {
		return (value - minInput) / (maxInput - minInput) * (maxMapped - minMapped) + minMapped;
	}
}