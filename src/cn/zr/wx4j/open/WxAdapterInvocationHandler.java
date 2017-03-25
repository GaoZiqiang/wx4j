package cn.zr.wx4j.open;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class WxAdapterInvocationHandler implements InvocationHandler {
	private Object proxied;

	public WxAdapterInvocationHandler(Object proxied) {
		this.proxied = proxied;

	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		Object result = null;
		if (method.getName().contains("send")) {
			// ��ò������������

			System.out.println("����ǰ" + method.getName() + "������");
			Arrays.toString(args);
			result = method.invoke(proxied, args);
			System.out.println("���ú󣡣���");
		} else {
			System.out.println("����ִ��:"+method.getName());
			result = method.invoke(proxied, args);
		}

		return result;
	}

}
