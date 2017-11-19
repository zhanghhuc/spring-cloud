package com.zssq.push.copy;

import java.util.Map;

import com.zssq.push.bean.AndroidNoticeBean;
import com.zssq.push.bean.IOSNoticeBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToAllRequest;
import com.baidu.yun.push.model.PushMsgToAllResponse;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;

import net.sf.json.JSONObject;
/**
 * 百度云推送
 * @author power
 *
 */
public class BaiduYunPush {
	
	public Logger logger = LoggerFactory.getLogger(BaiduYunPush.class);

	// 访问令牌，可通过该值获得开发者app的信息
	//private static final String API_KEY = "QAUv7vl71R89c9OGI8h5nPCm";
	/// 与apiKey成对出现，用于app的合法身份验证
	//private static final String SECRET_KEY = "sqi3OsSCXVTpeMnvtotDcN2sr6sbGanB";
	//String CHANNELID = "3867395514780662309";
	/**
	 * 推送给指定的android设备
	 * @param bean
	 * @param channelId
	 * @throws PushClientException
	 * @throws PushServerException
	 */
	public static void androidPushMsgToSingleDevice(String apiKey, String secretKey, AndroidNoticeBean bean, String channelId)
			throws PushClientException, PushServerException {

		PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

		BaiduPushClient pushClient = new BaiduPushClient(pair, BaiduPushConstants.CHANNEL_REST_URL);

		pushClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});

		try {
			JSONObject notification = new JSONObject();
			notification.put("title", bean.getTitle());
			notification.put("description", bean.getDescription());
			notification.put("notification_builder_id", bean.getNotificationBuilderId());
			notification.put("notification_basic_style", bean.getNotificationBasicStyle());
			notification.put("open_type", bean.getOpenType());
			notification.put("url", bean.getUrl());
			notification.put("custom_content", bean.getCustomContent());

			PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest().addChannelId(channelId)
					.addMsgExpires(new Integer(3600)) // message有效时间
					.addMessageType(1)// 1：通知,0:透传消息. 默认为0 注：IOS只有通知.
					.addMessage(notification.toString()).addDeviceType(3);// deviceType
																			// =>
																			// 3:android,
																			// 4:ios
			PushMsgToSingleDeviceResponse response = pushClient.pushMsgToSingleDevice(request);
			System.out.println("msgId: " + response.getMsgId() + ",sendTime: " + response.getSendTime());
		} catch (PushClientException e) {
			/*
			 * ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,'true' 表示抛出, 'false' 表示捕获。
			 */
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				e.printStackTrace();
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				System.out.println(String.format("requestId: %d, errorCode: %d, errorMessage: %s", e.getRequestId(),
						e.getErrorCode(), e.getErrorMsg()));
			}
		}
	}
	/**
	 * 推送给所有的android设备
	 * @param message
	 * @throws PushClientException
	 * @throws PushServerException
	 */
	public static void androidPushMsgToAll(String apiKey, String secretKey, String message) throws PushClientException, PushServerException {
		PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

		BaiduPushClient pushClient = new BaiduPushClient(pair, BaiduPushConstants.CHANNEL_REST_URL);

		pushClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});

		try {
			PushMsgToAllRequest request = new PushMsgToAllRequest().addMsgExpires(new Integer(3600)).addMessageType(0)
					.addMessage(message) // 添加透传消息
					.addSendTime(System.currentTimeMillis() / 1000 + 120) // 设置定时推送时间，必需超过当前时间一分钟，单位秒.实例2分钟后推送
					.addDeviceType(3);
			PushMsgToAllResponse response = pushClient.pushMsgToAll(request);
			System.out.println("msgId: " + response.getMsgId() + ",sendTime: " + response.getSendTime() + ",timerId: "
					+ response.getTimerId());
		} catch (PushClientException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				e.printStackTrace();
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				System.out.println(String.format("requestId: %d, errorCode: %d, errorMessage: %s", e.getRequestId(),
						e.getErrorCode(), e.getErrorMsg()));
			}
		}
	}
	/**
	 * 推送给指定的ios设备
	 * @param bean
	 * @param channelId
	 * @throws PushClientException
	 * @throws PushServerException
	 */
	public static void iosPushNotificationToSingleDevice(String apiKey, String secretKey, IOSNoticeBean bean, String channelId) throws PushClientException, PushServerException {
		PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

		BaiduPushClient pushClient = new BaiduPushClient(pair, BaiduPushConstants.CHANNEL_REST_URL);

		pushClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});

		try {
			JSONObject notification = new JSONObject();
			JSONObject jsonAPS = new JSONObject();
			jsonAPS.put("alert", bean.getAlert());
			jsonAPS.put("sound", bean.getSound()); // 设置通知铃声样式，例如"ttt"，用户自定义。
			jsonAPS.put("badge", bean.getBadge());
			notification.put("aps", jsonAPS);
			// 放入自定义参数
			if (bean.getCustomParam() != null) {
				for (Map.Entry<String, String> m : bean.getCustomParam().entrySet()) {
					notification.put(m.getKey(), m.getValue());
				}
			}

			PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest().addChannelId(channelId)
					.addMsgExpires(new Integer(3600)) // 设置message的有效时间
					.addMessageType(1)// 1：通知,0:透传消息.默认为0 注：IOS只有通知.
					.addMessage(notification.toString()).addDeployStatus(2) // IOS,
																			// DeployStatus
																			// =>
																			// 1:
																			// Developer
																			// 2:
																			// Production.
					.addDeviceType(4);// deviceType => 3:android, 4:ios
			PushMsgToSingleDeviceResponse response = pushClient.pushMsgToSingleDevice(request);
			// Http请求结果解析打印
			System.out.println("msgId: " + response.getMsgId() + ",sendTime: " + response.getSendTime());
		} catch (PushClientException e) {
			/*
			 * ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,'true' 表示抛出, 'false' 表示捕获。
			 */
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				e.printStackTrace();
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				System.out.println(String.format("requestId: %d, errorCode: %d, errorMessage: %s", e.getRequestId(),
						e.getErrorCode(), e.getErrorMsg()));
			}
		}
	}
	/**
	 * 推送给所有的ios设备
	 * @param bean
	 * @throws PushClientException
	 * @throws PushServerException
	 */
	public static void iosPushNotificationToAll(String apiKey, String secretKey, IOSNoticeBean bean) throws PushClientException, PushServerException {
		PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

		BaiduPushClient pushClient = new BaiduPushClient(pair, BaiduPushConstants.CHANNEL_REST_URL);

		pushClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});

		try {
			// 创建IOS通知
			JSONObject notification = new JSONObject();
			JSONObject jsonAPS = new JSONObject();
			jsonAPS.put("alert", bean.getAlert());
			jsonAPS.put("sound", bean.getSound()); // 设置通知铃声样式,例如"ttt"，用户自定义。
			jsonAPS.put("badge", bean.getBadge());
			notification.put("aps", jsonAPS);
			// 放入自定义参数
			if (bean.getCustomParam() != null) {
				for (Map.Entry<String, String> m : bean.getCustomParam().entrySet()) {
					notification.put(m.getKey(), m.getValue());
				}
			}

			PushMsgToAllRequest request = new PushMsgToAllRequest().addMsgExpires(new Integer(3600)).addMessageType(1)
					.addMessage(notification.toString()).addSendTime(System.currentTimeMillis() / 1000 + 120) // 设置定时推送时间，必需超过当前时间一分钟，单位秒.实例2分钟后推送
					.addDepolyStatus(2).addDeviceType(4);
			PushMsgToAllResponse response = pushClient.pushMsgToAll(request);
			// Http请求结果解析打印
			System.out.println("msgId: " + response.getMsgId() + ",sendTime: " + response.getSendTime() + ",timerId: "
					+ response.getTimerId());
		} catch (PushClientException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				e.printStackTrace();
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				System.out.println(String.format("requestId: %d, errorCode: %d, errorMessage: %s", e.getRequestId(),
						e.getErrorCode(), e.getErrorMsg()));
			}
		}
	}
}
