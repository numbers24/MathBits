#include <stdio.h>
#include <stdlib.h>
#include <time.h>


int checkPrime(long int i, long int *prime)
{
//checks to see if number is prime. if the number is not a multiple of the set of prime numbers, it will be added to the prime number set itself
    
    while(*prime)
    {
       if(i/ *(prime-1)<*prime)
       return 1;
       if(!(i%*prime))
       return 0;
       prime++;
    }
    return 1;
}
void main()
{
    clock_t start;
    clock_t end;
    long int range;
    scanf("%ld", &range);
    start=clock();
    long int *prime = malloc((range/10)*sizeof(long int));
    long int *front = prime;
    long int i=2;
    *prime=i++;
    *++prime=i++;
    for(i=6;i<range;i+=6)
    {
        if(checkPrime(i-1, front+1))
            *++prime=i-1;
        if(checkPrime(i+1, front+1))
            *++prime=i+1;
    }
    end = clock();
    i=0;
    while(*front)
    printf("%ld\n",*front++,i++);
    printf("%ld primes\n",i);
    printf("%f seconds\n",(double)(end - start)/CLOCKS_PER_SEC);
}

