package services;

public class RoundingService {

	public static float round(float value, int scale) {
	    return (float) (Math.round(value * Math.pow(10, scale)) / Math.pow(10, scale));
	}
}
