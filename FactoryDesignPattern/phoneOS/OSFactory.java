package phoneOS;

public class OSFactory 
{
	public OS getInstance(String s)
	{
		if(s.equals("apple"))
		{
			return new Apple();
		}
		else if(s.equals("windows"))
		{
				return new Windows();
		}
		return new Android();
	}
}
