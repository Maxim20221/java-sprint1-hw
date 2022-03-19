public class Converter {

    double stepDistance(int summa) {
        double lengthStep = 75 * 1e-5;
        return summa * lengthStep;
    }

    double getCalories(int summa) {
        double stepCal = 50;
        return ((stepCal) / 1000) * summa;
    }

}
