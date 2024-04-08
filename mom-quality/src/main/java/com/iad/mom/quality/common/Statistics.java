package com.iad.mom.quality.common;

import java.util.List;
import java.util.Collections;

public class Statistics {

    /**
     * Calculates the average of a list of numbers.
     *
     * @param values the list of numbers
     * @return the arithmetic mean average
     * @throws IllegalArgumentException if values is null or empty
     */
    public double average(List<Double> values) {
        if(values == null || values.isEmpty()) {
            throw new IllegalArgumentException("Values list cannot be null or empty");
        }

        double sum = 0.0;
        for(Double value : values) {
            sum += value;
        }
        return sum / values.size();
    }

    /**
     * Calculates the range between the min and max value.
     *
     * @param values the list of numbers
     * @return the range (max - min)
     * @throws IllegalArgumentException if values is null or empty
     */
    public double range(List<Double> values) {
        if(values == null || values.isEmpty()) {
            throw new IllegalArgumentException("Values list cannot be null or empty");
        }

        double min = Collections.min(values);
        double max = Collections.max(values);

        return max - min;
    }

    /**
     * Calculates the moving range average
     *
     * @param values the list of numbers
     * @return the moving range average
     * @throws IllegalArgumentException if values is null or empty
     */
    public double movingRangeAvg(List<Double> values) {
        if(values == null || values.isEmpty()) {
            throw new IllegalArgumentException("Values list cannot be null or empty");
        }

        double sum = 0.0;
        for(int i=0; i < values.size()-1; i++) {
            double current = values.get(i);
            double next = values.get(i+1);
            sum += Math.abs(next - current);
        }

        return sum / (values.size() - 1);
    }

    /**
     * Calculates the variance of a list of numbers
     *
     * @param values the list of numbers
     * @return the variance
     * @throws IllegalArgumentException if values is null or empty
     */
    public double variance(List<Double> values) {
        if(values == null || values.isEmpty()) {
            throw new IllegalArgumentException("Values list cannot be null or empty");
        }

        double mean = average(values);
        double temp = 0;
        for(Double value : values) {
            double sqDiff = Math.pow(value - mean, 2);
            temp += sqDiff;
        }
        return temp / (values.size() - 1);
    }

    /**
     * Calculates the standard deviation
     *
     * @param values the list of numbers
     * @return the standard deviation
     * @throws IllegalArgumentException if values is null or empty
     */
    public double stdDeviation(List<Double> values) {
        return Math.sqrt(variance(values));
    }

    /**
     * Finds the maximum value
     *
     * @param values the list of numbers
     * @return the maximum value
     * @throws IllegalArgumentException if values is null or empty
     */
    public double maximum(List<Double> values) {
        if(values == null || values.isEmpty()) {
            throw new IllegalArgumentException("Values list cannot be null or empty");
        }

        return Collections.max(values);
    }

    /**
     * Finds the minimum value
     *
     * @param values the list of numbers
     * @return the minimum value
     * @throws IllegalArgumentException if values is null or empty
     */
    public double minimum(List<Double> values) {
        if(values == null || values.isEmpty()) {
            throw new IllegalArgumentException("Values list cannot be null or empty");
        }

        return Collections.min(values);
    }

}