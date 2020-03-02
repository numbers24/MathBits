package PrimeBits;
import java.io.*;
import java.util.*;

public class PrimeFinder {
	
	public static void makeTable(Scanner scan, ArrayList<Long> primeList)
	{
		System.out.println("This will take " + (primeList.size()*primeList.size()) + " amounts of work. Are you sure?");
		String answer = scan.next();
		scan.nextLine();
		if(answer.equals("yes"))
		{		
			System.out.println("This might take a while...");
			
			PrintStream PrimeTable = null;
			PrintStream console = System.out;
			try {
				PrimeTable = new PrintStream(new FileOutputStream("PrimeTable.txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.setOut(PrimeTable);
			
			for(long i : primeList)
			{
				for(long j : primeList)
				{
					System.out.print(i*j+" ");
				}
				System.out.println();
			}

			System.setOut(console);
			System.out.println("Done!");
		}

	}
	public static void reRange(ArrayList<Long> primeList)
	{
		long A;
		long B;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter lower range:");
		A = scan.nextLong();
		scan.nextLine();
		System.out.println("Enter upper range:");
		B = scan.nextLong();
		scan.nextLine();
	}
	public static PrimeList makeList()
	{
		Scanner scan = new Scanner(System.in);
		long n;
		
		System.out.println("Find primes up to what number?");
		n = scan.nextLong();
		System.out.println("This might take a while...");
		
		PrimeList prime = new PrimeList(n);
		
		System.out.println("Done!");
		System.out.println("Amount of Primes: " + prime.primePer + ", " + prime.primeRatio);
		
		return prime;
	}
	public static boolean isPrime(ArrayList<Long> primeList, long i)
	{
		if(Math.sqrt(i)%1==0)
			return false;
		for(int k=0; k<primeList.size();k++)
		{
			long j = primeList.get(k);
			
			if(primeList.indexOf(j)>0)
			if((i)/primeList.get(k-1) < j)
				break;
			
			if(i%j==0&&i!=j)
				return false;
		}
		return true;
	}
	
	public static void main(String [] args)
	{
		PrimeList prime = makeList();
		ArrayList<Long> primeList= prime.list;
		System.out.println();
		while(true)
		{
			Scanner scan = new Scanner(System.in);
			System.out.println("What would you like to do?");
			System.out.println("0.) Quit");
			System.out.println("1.) Create New Prime List");
			System.out.println("2.) Create Prime Table");
			System.out.println("3.) Check if Prime");
			System.out.println("4.) Print");
			
			String ans = scan.next();
			scan.nextLine();
			
			if(ans.equals("0"))
				break;
			
			if(ans.equals("1"))
			{
				prime = makeList();
				primeList = prime.list;
			}
			
			if(ans.equals("2"))
			{
				if(!primeList.isEmpty())
				makeTable(scan,primeList);
				else
				System.out.println("You need to generate a prime list first!");
			}
			if(ans.equals("3"))
			{
				if(!primeList.isEmpty())
					{	System.out.println("Enter number:");
						long i = scan.nextLong();
						if(i>primeList.get(primeList.size()-1)||i<primeList.get(0))
						{
							System.out.println("Number out of range.");
							System.out.println("Would you like to find out if it is prime anyway? It might take a while.");
							ans = scan.next();
							scan.nextLine();
							if(ans.indexOf('y')!=-1)
							{
								System.out.println("Test");
							}
						}
						else
						{
						boolean isPrime = isPrime(primeList, i);
						if(isPrime)
							System.out.println("Yes, this is a prime number");
						else
							System.out.println("No, this is not a prime number.");
						}
					}
					else
					System.out.println("You need to generate a prime list first!");
			
				System.out.println("");
			}
			if(ans.equals("4"))
			{
				prime.print(primeList);
				System.out.println("The PrimeList has been printed to PrimeList.txt");
			}
			
			System.out.println();
		}
	}
}