package com.jobsity.challenge.model.lambdas;
/**
 * Lambda to specify how to calculate the score
 * @param <T>
 */
@FunctionalInterface
public interface CalculateScore<T> {
	Integer calculate(T ob);
}
