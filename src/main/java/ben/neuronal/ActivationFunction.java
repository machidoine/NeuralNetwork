package ben.neuronal;

public class ActivationFunction {

    public static double sigmoid(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    public static double add10(double x) {
        return x + 10;
    }

}
