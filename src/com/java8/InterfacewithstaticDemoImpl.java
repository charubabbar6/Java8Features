package com.java8;

public class InterfacewithstaticDemoImpl implements InterfacewithstaticDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*This won't be poosible as static methods are not available to implementing classes.
		 * InterfacewithstaticDemoImpl iws=new InterfacewithstaticDemoImpl();
		 * iws.staticMeth();
		 */
		InterfacewithstaticDemo.staticMeth();
	}

}
