package Factory;
import phoneOS.*;
import phoneOS.OS;

public class FactoryMain
{
	public static void main(String[] args) throws ClassNotFoundException
	{
		
		OSFactory o=new OSFactory();
		OS o1=o.getInstance(args[0]);
		o1.info();
	}
}
