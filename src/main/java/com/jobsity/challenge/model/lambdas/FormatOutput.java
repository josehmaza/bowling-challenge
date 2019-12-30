package com.jobsity.challenge.model.lambdas;
@FunctionalInterface
public interface FormatOutput<T> {
	String format(T ob);
}
