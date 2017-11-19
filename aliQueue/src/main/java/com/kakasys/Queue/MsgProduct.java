package com.kakasys.Queue;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;

/**
 * Created by zhang on 2017/10/30.
 *
 * AccessKeyId	AccessKeySecret
 LTAIUrAbMrvdgGMd	9ifjzvDJgRgY4gwLcqapYCfPz6qfdC

 http(s)://1829207864102583.mns.cn-hangzhou.aliyuncs.com/

 */
public class MsgProduct {
    protected static MNSClient sMNSClient;

    public static void WorkerFunc(int workerId)
    {
        MessageReceiver receiver = new MessageReceiver(workerId, sMNSClient, "TestQueue");
        while (true) {
            Message message = receiver.receiveMessage();
            System.out.println("Thread" + workerId + " GOT ONE MESSAGE! " + message.getMessageBodyAsString());
            sMNSClient.getQueueRef("TestQueue").deleteMessage(message.getReceiptHandle());
        }
    }

    public static void main(String[] args) {
        try {
            CloudAccount account = new CloudAccount("LTAIUrAbMrvdgGMd", "9ifjzvDJgRgY4gwLcqapYCfPz6qfdC", "http://1829207864102583.mns.cn-hangzhou.aliyuncs.com/");
            sMNSClient = account.getMNSClient();
            sMNSClient.getQueueRef("TestQueue").delete();
            sMNSClient.getQueueRef("TestQueue").create();

            Thread thread1 = new Thread(new Runnable() {
                public void run() {
                    WorkerFunc(1);
                }
            });
            Thread thread2 = new Thread(new Runnable() {
                public void run() {
                    WorkerFunc(2);
                }
            });
            Thread thread3 = new Thread(new Runnable() {
                public void run() {
                    WorkerFunc(3);
                }
            });

            thread1.start();
            thread2.start();
            thread3.start();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        /*    Message message = new Message("OneMessage");
            List<Message> messages = new ArrayList<Message>();
            messages.add(message);
            messages.add(message);
            messages.add(message);
            messages.add(message);
            messages.add(message);
            messages.add(message);
            messages.add(message);
            messages.add(message);
            messages.add(message);
            sMNSClient.getQueueRef("TestQueue").batchPutMessage(messages);

            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

            // keep looping util the end of world
            try {
                thread1.join();
                thread2.join();
                thread3.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
