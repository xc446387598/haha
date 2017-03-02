package com.shenzhenair.util;

public class MDebug {
	private static boolean info = true;
	private static boolean debug = true;
	
	/*
	 * System.out.println
	 * 
	 * @param o
	 */
	public static void println(Object o)
	{
		System.out.println(o);
	}
	
	/*
	 * System.out.print
	 * 
	 * @param o
	 */
	public static void print(Object o)
	{
		System.out.print(o);
	}
	
	
	/*
	 * System.out.println
	 * Import message, error info etc.
	 * 
	 * @param o
	 */
	public static void infoln(Object o)
	{
		if(info)
		System.out.println(o);
	}
	
	
	/*
	 * System.out.print
	 * Import message, error info etc.
	 * 
	 * @param o
	 */
	public static void info(Object o)
	{
		if(info)
		System.out.println(o);
	}
	
	
	/*
	 * System.out.println
	 * debug message, not display on Server, only View in TestServer 
	 * 
	 * @param o
	 */
	public static void debugln(Object o)
	{
		if(debug)
		System.out.println(o);
	}
	
	/*
	 * System.out.print
	 * debug message, not display on Server, only View in TestServer
	 * 
	 *  @param o
	 */
	public static void debug(Object o)
	{
		if(debug)
		System.out.println(o);
	}

}