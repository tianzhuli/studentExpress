package com.me.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.me.domain.PostOrder;
import com.me.domain.ReceiveOrder;
import com.me.service.PostOrderService;
import com.me.service.ReceiveOrderService;
import com.me.util.DataCache;

public class InitializeProcessor implements
		ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ReceiveOrderService receiveOrderService;

	@Autowired
	private PostOrderService postOrderService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getApplicationContext().getParent() == null) {// root
																// application
																// context
																// 没有parent，他就是老大.

			// 需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
			List<ReceiveOrder> list = receiveOrderService.selectALLDataCache();
			List<PostOrder> postOrders = postOrderService.selectALLDataCache();
			// System.out.println(list.size());
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					ReceiveOrder receiveOrder = list.get(i);
					DataCache.orderList.add(receiveOrder.getOrderID());
					DataCache.ordermap.put(receiveOrder.getOrderID(),
							receiveOrder);
				}

			} else {
				System.out.println("Receiveorders is null");
			}

			if (postOrders != null) {
				for (int i = 0; i < postOrders.size(); i++) {
					PostOrder postOrder = postOrders.get(i);
					DataCache.postorderList.add(postOrder.getPostorderID());
					DataCache.postordermap.put(postOrder.getPostorderID(),
							postOrder);
				}
			} else {
				System.out.println("Postorders is null");
			}
		}
	}

}
