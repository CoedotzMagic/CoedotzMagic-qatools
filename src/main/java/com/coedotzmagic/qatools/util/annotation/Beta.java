package com.coedotzmagic.qatools.util.annotation;

import com.coedotzmagic.qatools.failurehandling.TellMeWhy;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, MODULE, PARAMETER, TYPE})
@Documented
public @interface Beta {
    String description() default TellMeWhy.IN_BETA;
}
