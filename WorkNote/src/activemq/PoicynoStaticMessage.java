//package com.sinosoft.ebusiness.task.listenter;
//
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//import javax.annotation.Resource;
//import javax.jms.Message;
//import javax.jms.MessageListener;
//import javax.jms.TextMessage;
//
//import org.springframework.stereotype.Service;
//
//import com.sinosoft.ebusiness.task.model.GeStatisDay;
//import com.sinosoft.ebusiness.task.service.facade.PoicynoStaticService;
//
//@Service("poicynoStaticMessage")
//public class PoicynoStaticMessage implements MessageListener{
//
//	@Resource
//	private PoicynoStaticService poicynoStaticService;
//	
//	@Override
//	public void onMessage(Message message) {
//		System.out.println("取到的信息"+message);
//		String[] strArr = null;
//		if (message instanceof TextMessage) {  
//        	try {
//    			TextMessage textMessage = (TextMessage) message;
//    			String content = textMessage.getText();
//    			strArr= content.split("\\|");
//    			List<String> list=Arrays.asList(strArr);
//    			// 产品名称   产品代码  合作方名称  合作方代码   保费   件数 
//    			String productName = list.get(0).trim();
//    			String productCode = list.get(1).trim();
//    			String cooperateName = list.get(2).trim();
//    			String cooperateCode  = list.get(3).trim();
//    			long sumPremium = Long.parseLong(list.get(4).trim());
//    			int count = Integer.parseInt(list.get(5).trim());
//    			String provincecode = list.get(6).trim();
//    			Date date = new Date();
//    			int year = date.getYear() + 1900;
//    			int month = date.getMonth();
//    			int day = date.getDate();
//    			GeStatisDay  geStatisDay = poicynoStaticService.queryPoicynoStaticOne(provincecode, cooperateCode, productCode, year, month, day);
//    			// 当天年   月  日 ，渠道编码，产品代码   查询是否有对应的记录,如果 有就执行更细，否则执行插入操作
//    			if(geStatisDay != null && geStatisDay.getCooperateCode() != null){
//    					// 更新操作是将 保费加到 原来的记录中
//    					long postSumPremium = sumPremium + geStatisDay.getSumPremium() ;
//    					int resultCount = count + geStatisDay.getSumCount();
//    					int version = geStatisDay.getVersion() + 1;
//    					poicynoStaticService.updatePoicynoStaticOneNoVersion(provincecode, cooperateCode, productCode,
//    							year, month, day, postSumPremium, resultCount);
//    			}else{
//    				poicynoStaticService.savePoicynoStaticOne(provincecode ,productCode, cooperateCode, cooperateName, sumPremium, 
//    						count, year, month, day);
//    			}
//        	} catch (Exception e) {
//                 e.printStackTrace();
//            }
//         }
//	}	
//	
//}
