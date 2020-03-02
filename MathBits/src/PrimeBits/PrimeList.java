package PrimeBits;

import java.io.*;
import java.util.ArrayList;
import java.util.Timer;

import MathBits.Node;

public class PrimeList {

	ArrayList<Long> list;
	long primeCount;
	String primeRatio;
	String primePer;
	Node front;
	
	
	public PrimeList(long n)
	{
		this.list=new ArrayList<Long>();
		gP(n, list);
		this.primeCount=list.size();
		this.primePer = primeCount + "/" + (n);
		this.primeRatio=(double)list.size()/(double)(n)*100 + "%";
	}
	
	private void gP(long n, ArrayList<Long> primeList)
	{
		primeList.add((long)2);
		primeList.add((long)3);
		for(long i=6;i<n;i+=6)
		{
			prime(i-1, primeList);
			prime(i+1, primeList);
		}
	}
	
	private void prime(long i, ArrayList<Long> primeList)
	{
		if(Math.sqrt(i)%1==0)return;
		for(int k=1; k<primeList.size();k++)
		{
			long j = primeList.get(k);
			
			if((i)/primeList.get(k-1) < j)
			{
				primeList.add(i);
				return;
			}
			
			if(i%j==0)return;
		}
	}
	public void reRange(long A, long B, ArrayList<Long> primeList)
	{
		for(int i=0;i<primeList.size();i++)
		{
			if(primeList.get(i)<A)
				primeList.remove(i);
			
			if(primeList.get(primeList.size()-1)>B)
				primeList.remove(i);
		}
		
		primeCount=list.size();
		primePer = primeCount + "/" + (B-A);
		primeRatio=(double)list.size()/(double)(B-A)*100 + "%";
	}
	public void print(ArrayList<Long> primeList)
	{
		PrintStream PrimeList = null;
		try {
			PrimeList = new PrintStream(new FileOutputStream("PrimeList.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintStream console = System.out;
		System.setOut(PrimeList);
		for(long i : primeList)
			System.out.println(i);
		
		System.setOut(console);
	}
}

