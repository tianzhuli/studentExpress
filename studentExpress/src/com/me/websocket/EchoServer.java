/**
 * 
 */
package com.me.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.Session;

import net.sf.json.JSONObject;

/**
 * @author Administrator
 * 
 */
@ServerEndpoint("/echo")
public class EchoServer {

	private Session session;
	List<Session> userSessions = new ArrayList<Session>();
	JSONObject jsonObject = new JSONObject();

	@OnOpen
	public void whenOpen(Session session) {
		userSessions.add(session);
		System.out.println("start");
		System.out.println(session);

	}

	@OnMessage
	public void onMessage(String message) {

		System.out.println("i got (" + message + ")");
		if (message.equals("test")) {
			jsonObject.put("mimi", "test");
		} else if (message.equals("hehe")) {
			jsonObject.put("mimi", "hehe");
		} else {
			jsonObject.put("mimi", "other");
		}
		Thread sendThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boolean send = true;
				while (send) {

					for (int i = 0; i < userSessions.size(); i++) {
						Session session = userSessions.get(i);
						try {
							session.getBasicRemote().sendText(
									jsonObject.toString());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if (userSessions.size() < 1) {
						send = false;
					}

				}
			}

		});

		// return jsonObject.toString();
		sendThread.start();

	}

	@OnClose
	public void onClose(Session session) {
		userSessions.remove(session);
		System.out.println("连接关闭");
	}
	
	@OnError
	public void onError(Throwable throwable){
		System.err.println(throwable);
	}
}
