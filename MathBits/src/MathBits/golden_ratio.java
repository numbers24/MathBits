package MathBits;

public class golden_ratio {

	public static double recurse(int i)
	{
		if(i==0)
		return 1/2;
		
		return 1/(1+recurse(i-1));
	}
	public static void main(String[]args)
	{
		System.out.println(recurse(10000));
	}
}
