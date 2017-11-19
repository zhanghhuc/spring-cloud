package com.zssq.push;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.comet4j.core.CometConnection;
import org.comet4j.core.CometContext;
import org.comet4j.core.CometEngine;


public class CometPush {
	
	public static final String CHANNEL1 = "channel1";

	private static final Map<String, CometConnection> userconnections = new HashMap<String, CometConnection>();

	/**
	 * 推送给指定的用户
	 * @param userCodes
	 * @param message
	 */
	public static void sendToSingle(List<String> userCodes, String message) {
		CometEngine engine = CometContext.getInstance().getEngine();
		
		for (String userCode : userCodes) {
			engine.sendTo(CHANNEL1, userconnections.get(userCode), message);
		}
	}

	/**
	 * 推送给所有的用户
	 * @param message
	 */
	public static void sendToAll(String message) {
		CometEngine engine = CometContext.getInstance().getEngine();

		engine.sendToAll(CHANNEL1, message);
	}
	
	public static void putUser(String userCode, CometConnection conn) {
		userconnections.put(userCode, conn);
	}
}
