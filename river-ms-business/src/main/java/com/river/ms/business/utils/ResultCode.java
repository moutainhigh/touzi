package com.river.ms.business.utils;

public class ResultCode {

	public static int getReslt(Integer r) {
		switch (r) {
		case -1:
			return 0;
		case 0:
			return 0;
		case 1:
			return 1;
		case 2:
			return 2;
		case 3:
			return 2;
		default:
			return 2;
		}
	}
}
