package timer;

import java.util.LinkedList;

/**
 * 线程安全的栈
 *
 * @author 马美强
 * @date 2010-12-23 下午04:47:11
 */
public class ConcurrentStack<E> extends LinkedList<E>{

	@Override
	public synchronized void push(E e) {
		// TODO Auto-generated method stub
		super.push(e);
	}
	
	@Override
	public synchronized E poll() {
		// TODO Auto-generated method stub
		return super.poll();
	}
}
