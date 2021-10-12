/**
 * @author sixfalls#0001 (github.com/6ixfalls) for the original, SIMULATAN (github.com/SIMULATAN) for the updated version with enums and the advanced bouncy easings
 */
public class Easings {

	public static float linear(float x) {
		return x;
	}

	public static float easeInSine(float x) {
		return (float) (1 - cos((x * PI) / 2));
	}

	public static float easeOutSine(float x) {
		return (float) (sin((x * PI) / 2));
	}

	public static float easeInOutSine(float x) {
		return (float) (-(cos(PI * x) - 1) / 2);
	}

	public static float easeInCubic(float x) {
		return (float) (x * x * x);
	}

	public static float easeOutCubic(float x) {
		return (float) (1 - pow(1 - x, 3));
	}

	public static float easeInOutCubic(float x) {
		return (float) (x < 0.5 ? 4 * x * x * x : 1 - pow(-2 * x + 2, 3) / 2);
	}

	public static float easeInQuint(float x) {
		return (float) (x * x * x * x * x);
	}

	public static float easeOutQuint(float x) {
		return (float) (1 - pow(1 - x, 5));
	}

	public static float easeInOutQuint(float x) {
		return (float) (x < 0.5 ? 16 * x * x * x * x * x : 1 - pow(-2 * x + 2, 5) / 2);
	}

	public static float easeInCirc(float x) {
		return (float) (1 - sqrt(1 - pow(x, 2)));
	}

	public static float easeOutCirc(float x) {
		return (float) (sqrt(1 - pow(x - 1, 2)));
	}

	public static float easeInOutCirc(float x) {
		return (float) (x < 0.5 ? (1 - sqrt(1 - pow(2 * x, 2))) / 2 : (sqrt(1 - pow(-2 * x + 2, 2)) + 1) / 2);
	}
	
	public static float easeInElastic(float x) {
		if (x <= 0) return 0;
		if (x >= 1) return 1;
		return (float) (-pow(2,  10 * x - 10) * sin((x * 10 - 10.75) * ((2 * PI) / 3)));
	}
	
	public static float easeOutElastic(float x) {
		if (x <= 0) return 0;
		if (x >= 1) return 1;
		return (float) (pow(2, -10 * x) * sin((x * 10 - 0.75) * ((2 * PI) / 3)) + 1);
	}
	
	public static float easeInOutElastic(float x) {
		if (x <= 0) return 0;
		if (x >= 1) return 1;
		return (float) (x < 0.5 ? -(pow(2, 20 * x - 10) * sin((20 * x - 11.125) * ((2 * PI) / 4.5))) / 2 : (pow(2, -20 * x + 10) * sin((20 * x - 11.125) * ((2 * PI) / 4.5))) / 2 + 1);
	}

	public static float easeInQuad(float x) {
		return (float) (x * x);
	}

	public static float easeOutQuad(float x) {
		return (float) (1 - (1 - x) * (1 - x));
	}

	public static float easeInOutQuad(float x) {
		return (float) (x < 0.5 ? 2 * x * x : 1 - pow(-2 * x + 2, 2) / 2);
	}

	public static float easeInQuart(float x) {
		return (float) (x * x * x * x);
	}

	public static float easeOutQuart(float x) {
		return (float) (1 - pow(1 - x, 4));
	}

	public static float easeInOutQuart(float x) {
		return (float) (x < 0.5 ? 8 * x * x * x * x : 1 - pow(-2 * x + 2, 4) / 2);
	}

	public static float easeInExponential(float x) {
		return (float) (x == 0 ? 0 : pow(2, 10 * x - 10));
	}

	public static float easeOutExponential(float x) {
		return (float) (x == 1 ? 1 : 1 - pow(2, -10 * x));
	}

	public static float easeInOutExponential(float x) {
		return (float) (x == 0 ? 0 : x == 1 ? 1 : x < 0.5 ? pow(2, 20 * x - 10) / 2 : (2 - pow(2, -20 * x + 10)) / 2);
	}
	
	public static float easeInBack(float x) {
		float c1 = 1.70158F;
		return (float) ((c1 + 1) * x * x * x - c1 * x * x);
	}
	
	public static float easeOutBack(float x) {
		float c1 = 1.70158F;
		return (float) (1 + (c1 + 1) * pow(x - 1, 3) + c1 * pow(x - 1, 2));
	}
	
	public static float easeInOutBack(float x) {
		float c1 = 1.70158F;
		float c2 = c1 * 1.525F;
		
		return (float) (x < 0.5 ? (pow(2 * x, 2) * ((c2 + 1) * 2 * x - c2)) / 2 : (pow(2 * x - 2, 2) * ((c2 + 1) * (x * 2 - 2) + c2) + 2) / 2);
	}
	
	public static float easeInBounce(float x) {
		return 1 - easeOutBounce(1 - x);
	}
	
	public static float easeOutBounce(float x) {
		float n1 = 7.5625F;
		float d1 = 2.75F;

		if (x < 1 / d1) {
		    return n1 * x * x;
		} else if (x < 2 / d1) {
		    return (float) (n1 * (x -= 1.5 / d1) * x + 0.75);
		} else if (x < 2.5 / d1) {
		    return (float) (n1 * (x -= 2.25 / d1) * x + 0.9375);
		} else {
		    return (float) (n1 * (x -= 2.625 / d1) * x + 0.984375);
		}
	}
	
	public static float easeInOutBounce(float x) {
		return x < 0.5 ? (1 - easeOutBounce(1 - 2 * x)) / 2 : (1 + easeOutBounce(2 * x - 1)) / 2;
	}
	
	public static float getEasingValue(float x, Easing easing) {
		if (x <= 0) return 0;
		if (x >= 1) return 1;
		switch (easing) {
		case LINEAR:
			return linear(x);
		case EASE_IN_SINE:
			return easeInSine(x);
		case EASE_OUT_SINE:
			return easeOutSine(x);
		case EASE_IN_OUT_SINE:
			return easeInOutSine(x);
		case EASE_IN_CUBIC:
			return easeInCubic(x);
		case EASE_OUT_CUBIC:
			return easeOutCubic(x);
		case EASE_IN_OUT_CUBIC:
			return easeInOutCubic(x);
		case EASE_IN_QUINT:
			return easeInQuint(x);
		case EASE_OUT_QUINT:
			return easeOutQuint(x);
		case EASE_IN_OUT_QUINT:
			return easeInOutQuint(x);
		case EASE_IN_CIRC:
			return easeInCirc(x);
		case EASE_OUT_CIRC:
			return easeOutCirc(x);
		case EASE_IN_OUT_CIRC:
			return easeInOutCirc(x);
		case EASE_IN_ELASTIC:
			return easeInElastic(x);
		case EASE_OUT_ELASTIC:
			return easeOutElastic(x);
		case EASE_IN_OUT_ELASTIC:
			return easeInOutElastic(x);
		case EASE_IN_QUAD:
			return easeInQuad(x);
		case EASE_OUT_QUAD:
			return easeOutQuad(x);
		case EASE_IN_OUT_QUAD:
			return easeInOutQuad(x);
		case EASE_IN_QUART:
			return easeInQuart(x);
		case EASE_OUT_QUART:
			return easeOutQuart(x);
		case EASE_IN_OUT_QUART:
			return easeInOutQuart(x);
		case EASE_IN_EXPONENTIAL:
			return easeInExponential(x);
		case EASE_OUT_EXPONENTIAL:
			return easeOutExponential(x);
		case EASE_IN_OUT_EXPONENTIAL:
			return easeInOutExponential(x);
		case EASE_IN_BACK:
			return easeInBack(x);
		case EASE_OUT_BACK:
			return easeOutBack(x);
		case EASE_IN_OUT_BACK:
			return easeInOutBack(x);
		case EASE_IN_BOUNCE:
			return easeInBounce(x);
		case EASE_OUT_BOUNCE:
			return easeOutBounce(x);
		case EASE_IN_OUT_BOUNCE:
			return easeInOutBounce(x);
		default:
			System.err.println("Unkown Easing type " + easing.name());
			return x;
		}
	}
}