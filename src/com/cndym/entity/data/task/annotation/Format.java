package com.cndym.entity.data.task.annotation;

public @interface Format {
	
	String value();
	String fileType() default "";
	Class formatType() default String.class;

}
