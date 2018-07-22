package com.river.activiti.entity;

public class KeyTaskCode {
	private static String[] startCode={""};
	private static String[] endCode={""};
	private static String[] processKeys={"setupApply","feasibilityApply","decisionApply","modificationApply","afterApply","exitApply","completeApply"};
	  public static boolean isEnd(String taskCode)
	  {
		  for(int i=0;i<endCode.length;i++){
			  if(endCode[i].equals(taskCode))
				  return true;
		  }
		  return false;
	  }
	  public static Integer toFlowType(String processKey){
		  if(processKey.indexOf("setupApply")>-1){
			  return EnumFlowType.SETUP.getFlowType();
		  }else if(processKey.indexOf("feasibilityApply")>-1){
			  return EnumFlowType.FEASIBILITY.getFlowType();
		  }else if(processKey.indexOf("decisionApply")>-1){
			  return EnumFlowType.DECISION.getFlowType();
		  }else if(processKey.indexOf("modificationApply")>-1){
			  return EnumFlowType.MODIFICATION.getFlowType();
		  }else if(processKey.indexOf("afterApply")>-1){
			  return EnumFlowType.AFTER.getFlowType();
		  }else if(processKey.indexOf("completeApply")>-1){
			  return EnumFlowType.COMPLETE.getFlowType();
		  }else if(processKey.indexOf("exitApply")>-1){
			  return EnumFlowType.EXIT.getFlowType();
		  }else {
			  return -99;
		  }
	  }
	  /**
	   * 
	   * @param taskCode
	   * @return
	   */
	  public static boolean isStart(String taskCode)
	  {
		  for(int i=0;i<startCode.length;i++){
			  if(startCode[i].equals(taskCode))
				  return true;
		  }
		  return false;
	  }  
}
