import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class StepTracker {


    int dayMax = 30; // как в задании написано 30 дней
    int goalStep = 10000;


    HashMap<Integer, ArrayList<Integer>> stepMonth = new HashMap<>();
    Converter converter = new Converter();

    public StepTracker() {

        for (int i = 1; i <= 12; i++) {
            stepMonth.put(i, new ArrayList<Integer>(dayMax));
            for (int y = 0; y <= dayMax; y++) {
                stepMonth.get(i).add(y, 0);
            }
        }
    }

    HashMap<Integer, ArrayList<Integer>> getStepData(int months, int day, int step) { //пройденные шаги
        if (day < 1) {
            System.out.println("Введите день правильно.");
        } else if (day <= dayMax) {
            stepMonth.get(months).set(day - 1, step);
        } else {
            System.out.println("В месяце всего " + dayMax + " дней.");
            System.out.println("Введите день правильно.");
        }
        return stepMonth;
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
        System.out.println("Максимальное количество шагов составило: " + maxStepUser);
        return maxStepUser;
    }

    void getStepStat(int monthsStat) {
        ArrayList<Integer> getStepMonth = stepMonth.get(monthsStat);
        for (int i = 0; i < dayMax; i++) {
            System.out.println("День " + (i + 1) + ": " + getStepMonth.get(i) + "; ");
        }
    }



    double getStepAverage(int monthsStat) { // Среднее
        double averageStep = getStepSum(monthsStat) / dayMax;
        System.out.println("Среднее количество шагов:" + averageStep);
        return averageStep;
    }

    int getStepWin(int monthsStat) {
        int stepSeries = 0;
        int checkDay = 0;
        ArrayList<Integer> stepMonths = stepMonth.get(monthsStat);
        for (int i = 0; i < stepMonths.size(); i++) {
            if (stepMonths.get(i) >= goalStep) {
                checkDay = checkDay + 1;
            } else {
                if (checkDay > stepSeries) {
                    stepSeries = checkDay;
                    checkDay = 0;
                }
            }
        }
        System.out.println("Ваша череда достижений составляет: " + stepSeries + " дней.");
        return stepSeries;
    }

    void getStaticMonths(int months) { // Статистика
        System.out.println("Общее количество шагов: " + getStepSum(months));
        getStepStat(months);
        getStepSum(months);
        getStepMax(months);
        getStepAverage(months);
        converter.stepDistance(getStepSum(months));
        converter.getCalories(getStepSum(months));
        getStepWin(months);
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

