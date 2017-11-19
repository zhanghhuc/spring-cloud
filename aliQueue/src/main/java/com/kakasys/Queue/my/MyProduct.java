package com.kakasys.Queue.my;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.Message;

/**
 * Created by zhang on 2017/10/31.
 */
public class MyProduct {

    protected static MNSClient sMNSClient;

    public static void main(String[] args) {
        try {
            CloudAccount account = new CloudAccount("LTAIUrAbMrvdgGMd", "9ifjzvDJgRgY4gwLcqapYCfPz6qfdC", "http://1829207864102583.mns.cn-hangzhou.aliyuncs.com/");
            sMNSClient = account.getMNSClient();
            sMNSClient.getQueueRef("TestQueue").delete();
            sMNSClient.getQueueRef("TestQueue").create();


            int n = 0;
            while(true){
                Message message = new Message("zhang ......   "+ n);
                sMNSClient.getQueueRef("TestQueue").asyncPutMessage(message,null);
                n++;
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
