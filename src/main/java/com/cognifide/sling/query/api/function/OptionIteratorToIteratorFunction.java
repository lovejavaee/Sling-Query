package com.cognifide.sling.query.api.function;

import java.util.Iterator;

import com.cognifide.sling.query.api.Function;

public interface OptionIteratorToIteratorFunction<T> extends Function<Iterator<Option<T>>, Iterator<Option<T>>> {
}
