package com.me.websocket;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import net.sf.json.JSONArray;

import com.me.util.DataCache;

@ServerEndpoint("/postOrder")
public class PostOrderWebsocket {
	boolean send=false;
	ExecutorService cachedThreadPool;
	@OnOpen
	public void whenOpen(Session session) {
		System.out.println("start");
		System.out.println(session);
		cachedThreadPool=Executors.newCachedThreadPool();
	}

	@OnMessage
	public void onMessage(String message,final Session session) {		
		cachedThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				send = true;
				while (send) {
						try {
							JSONArray jsonArray=DataCache.sendPostOrderToExpress();							
							if (jsonArray!=null) {
								session.getBasicRemote().sendText(
										jsonArray.toString());
								System.out.println("send");
							}							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					try {
						TimeUnit.MILLISECONDS.sleep(10000);
						//Thread.sleep(20000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		// return jsonObject.toString();
		//sendThread.start();
	}

	@OnClose
	public void onClose(Session session) {
//		userSessions.remove(session);
		send=false;
		System.out.println("连接关闭");
	}
	
	@OnError
	public void onError(Throwable throwable){
		send=false;
		System.err.println(throwable);
	}
}
