package engine;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class VariableTesting {

	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		int a = 0;
		Integer b = Integer.valueOf(0);
		AtomicInteger c = new AtomicInteger(0);
		Incrementator i  = new Incrementator(a, b, c);
		long Tminus = System.currentTimeMillis();
		
		while (true) {
			
			if (System.currentTimeMillis() - Tminus > 1000) {
				// Obtener directamente la variable sin tener que acceder a las que estan almacenadas dentro del nuevo proceso
				System.out.println(a);
				System.out.println(b);
				System.out.println(c);
				
				// Asi es como se podria obtener un int en otro proceso
				System.out.println(i.getInt());
				Tminus = System.currentTimeMillis();
			}
		}

	}


}
class Incrementator implements Runnable {

	int a;
	Integer b;
	AtomicInteger c;
	private Thread thread;
	
	protected Incrementator(int a, Integer b, AtomicInteger c) {
		thread = new Thread(this);
		this.a = a;
		this.b = b;
		this.c = c;
		thread.start();
	}
	
	protected int getInt() {
		return a;
	}
	
	protected Integer getInteger() {
		return b;
	}
	
	protected AtomicInteger getAtomic() {
		return c;
	}
	
	@Override
	public void run() {

		while(true) {
			a++;
			b = Integer.valueOf(b.intValue()+1);
			c.addAndGet(1);
			try {
				Thread.sleep(32);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}