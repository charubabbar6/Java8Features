package com.java8;

public class DiamondProblemClass implements DiamondProblemInterface1,DiamondProblemInterface2{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DiamondProblemClass dps=new DiamondProblemClass();
		dps.meth();
	}

	@Override
	public void meth() {
		// TODO Auto-generated method stub
		DiamondProblemInterface2.super.meth();
	}

}
