package com.jobsity.challenge.model;
@FunctionalInterface
public interface FormatOutput<T> {
	String format(T ob);
}
