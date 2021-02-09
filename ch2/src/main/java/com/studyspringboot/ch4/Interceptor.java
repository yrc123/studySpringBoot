package com.studyspringboot.ch4;


public interface Interceptor {
	public boolean before();
	public boolean after();
	public Object around(Invocation invocation)
			throws Throwable;
	public void afterReturning();
	public void afterThrowing();
	boolean useAround();

}
