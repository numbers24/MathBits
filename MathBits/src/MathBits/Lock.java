package MathBits;

public class Lock {

	long keyA;
	long keyB;
	long encryption;
	boolean unlocked;
	
	public Lock(long keyA, long keyB)
	{
		this.keyA= keyA;
		this.keyB= keyB;
		this.encryption= keyA*keyB;
		this.unlocked=false;
	}
	
	public void unlock(Lock lock, long A, long B)
	{
		for(int i=0;i<10;i++)
		{
			if(A*B== lock.encryption && A!= lock.encryption && B!= lock.encryption)
				{
					System.out.println("Correct Password!");
					lock.unlocked = true;
				}
			else
			{
				System.out.println("Incorrect Password! Try Again.");
				System.out.println("Attemps left: " + (10-i));
			}
		}
	}
}
