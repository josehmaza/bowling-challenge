package com.jobsity.challenge.model.lambdas;
/**
 * Lambda to specify how to format output of a model in console
 * @param <T>
 */
@FunctionalInterface
public interface FormatOutput<T> {
	String format(T ob);
}
