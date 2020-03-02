package MathBits;

public class series {

	public static void seriesGen()
	{
		String series = "";
		String formula = "1/1+n";
		for(int i = 0; i<100;i++)
		{
			String temp = formula.substring(0,formula.indexOf("n")) + i;
			series+= temp + ", ";
		}
		System.out.println(series);
	}
	public static void main(String[] args)
	{
		seriesGen();
	}
	
}
