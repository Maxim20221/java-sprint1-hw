public class Converter {

    double stepDistance(int summa) {
        double lengthStep = 75 * 1e-5;
        double distance = summa * lengthStep;
        return distance;
    }

    double getCalories(int summa) {
        double stepCal = 50;
        double cal = ((stepCal) / 1000) * summa;
        return cal;
    }

}
