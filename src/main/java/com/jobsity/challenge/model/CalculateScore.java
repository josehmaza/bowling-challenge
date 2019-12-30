package com.jobsity.challenge.model;
@FunctionalInterface
public interface CalculateScore<T> {
	Integer calculate(T ob);
}
