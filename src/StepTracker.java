import java.util.ArrayList;
import java.util.HashMap;

public class StepTracker {


    int dayMax = 30;
    int goalStep = 10000;
    int mountMax = 12;

    HashMap<Integer, ArrayList<Integer>> stepMonth = new HashMap<>();
    Converter converter = new Converter();

    public StepTracker() {

        for (int i = 1; i <= mountMax; i++) {
            stepMonth.put(i, new ArrayList<Integer>(dayMax));
            for (int y = 0; y <= dayMax; y++) {
                stepMonth.get(i).add(y, 0);
            }
        }
    }

    void getStepData (int months, int day, int step) {
        HashMap<Integer, ArrayList<Integer>> getStepData;   //пройденные шаги
        if (months < 1 || months > mountMax) {
            System.out.println("Введите месяц правильно.");
            System.out.println("Всего " + mountMax + " месяцев.");
        } else if (day < 1 || day > dayMax) {
            System.out.println("Введите день правильно.");
            System.out.println("В месяце всего " + dayMax + " дней.");
        } else if (step < 0) {
            System.out.println("Введите количество шагов правильно.");
        } else {
            stepMonth.get(months).set(day - 1, step);
            }
        }

    int getStepSum(int monthStat) {
        int stepSum = 0;
        ArrayList<Integer> getStepMonth = stepMonth.get(monthStat);
        for (int i = 0; i < getStepMonth.size(); i++) {
            stepSum += getStepMonth.get(i);
        }
        return stepSum;
    }

    double getStepMax(int monthsStat) { // Максимальное
        int maxStepUser = 0;
        ArrayList<Integer> getStepMonth = stepMonth.get(monthsStat);
        for (int i = 0; i < getStepMonth.size(); i++) {
            if (maxStepUser < getStepMonth.get(i)) {
                maxStepUser = getStepMonth.get(i);
            }
        }
        return maxStepUser;
    }

    void getStepStat(int monthsStat) {
        ArrayList<Integer> getStepMonth = stepMonth.get(monthsStat);
        for (int i = 0; i < dayMax; i++) {
            System.out.println("День " + (i + 1) + ": " + getStepMonth.get(i) + "; ");
        }
    }


    double getStepAverage(int monthsStat) { // Среднее
        double averageStep = (double) getStepSum(monthsStat) / dayMax;
        return averageStep;
    }

    int getStepWin(int monthsStat) {
        int stepSeries = 0;
        int checkDay = 0;
        ArrayList<Integer> stepMonths = stepMonth.get(monthsStat);
        for (int i = 1; i < stepMonths.size(); i++) {
            if (stepMonths.get(i) >= goalStep) {
                checkDay = checkDay + 1;
            } else {
                if (checkDay > stepSeries) {
                    stepSeries = checkDay;
                    checkDay = 0;
                }
            }
        }
        return stepSeries;
    }

    void getStaticMonths(int months) { // Статистика
        if (months >= 1 && months <= mountMax ) {
            System.out.println("Общее количество шагов: " + getStepSum(months));
            getStepStat(months);
            getStepSum(months);
            System.out.println("Максимальное количество шагов составило: " + getStepMax(months));
            System.out.println("Среднее количество шагов:" + getStepAverage(months));
            System.out.println("Пройденная дистанция: " + converter.stepDistance(getStepSum(months)) + " в км.");
            System.out.println("Количество сожжённых килокалорий: " + converter.getCalories(getStepSum(months)) + " кКал");
            System.out.println("Ваша череда достижений составляет: " + getStepWin(months) + " дней.");
        } else {
            System.out.println("Введенного месяца не существует.");
        }
    }

    void objectiveStep(int objective) {
        if (objective < 0) {
            System.out.println(" Введите новую корректную цель");
        } else {
            goalStep = objective;
            System.out.println("Новая цель в " + goalStep + " установлена!");
        }
    }
}
