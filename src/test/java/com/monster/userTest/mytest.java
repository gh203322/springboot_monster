package com.monster.userTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mytest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
           String s = "";
           Long l = 9L;
           Float f = 0F;
           Integer i = 3;
           List list = new ArrayList();
           Map map = new HashMap();
           
           System.out.println(map.getClass().toString());
           System.out.println(s.getClass().toString().contains("String"));
           Calendar cal = Calendar.getInstance();
           cal.setTime(new Date());
           System.out.println(cal.get(Calendar.YEAR)+""+cal.get(Calendar.MONTH)+""+cal.get(Calendar.DATE)+"_");
	}

}
