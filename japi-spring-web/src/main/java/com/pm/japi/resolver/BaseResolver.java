package com.pm.japi.resolver;

import com.pm.japi.sacnner.ModelProvider;

import java.lang.reflect.Type;

public interface BaseResolver {

    TypeInfo resolved(Type type, ModelProvider modelProvider, ResolverType lastNode);
}
