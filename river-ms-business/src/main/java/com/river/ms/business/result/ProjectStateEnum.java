package com.river.ms.business.result;

public enum ProjectStateEnum {
	CheckIn(0),//登记
	//投前
	Setup(10),//项目立项
	Feasibility(20),
	Decision(30),
	//投中
	building(40),//建设中（包含变更）
	//投后
	Complete(50),//已竣工
	Accept(60),//已验收
	Knot(99);//结项
	private int  mState=0;
	private ProjectStateEnum(int value)
	{
		mState=value;
	}
	/**
	* @return 枚举变量实际返回值
	*/
	  public int getState()
	  {
		  return mState;
	  }  
}
