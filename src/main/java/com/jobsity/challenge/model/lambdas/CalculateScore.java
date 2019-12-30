package com.jobsity.challenge.model.lambdas;
@FunctionalInterface
public interface CalculateScore<T> {
	Integer calculate(T ob);
}
