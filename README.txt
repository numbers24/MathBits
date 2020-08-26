PrimeBits is a series of projects that take a range between 0 and n and find all the prime numbers in between.
This project was started mainly for learning how to optimize run times. I wanted to see how quickly the program could find prime numbers in a large enough range.

PrimeBits started in Java and was later adapted into C.
If you want to run the C version, run prime.exe (recomended)
If you want to run the Java version, run PrimeBits/src/PrimeBits/PrimeFinder.java


PrimeBits will not only find the set of primes between 0 and n, but it will also print out the set on PrimeList.txt, and a times table on PrimeTable.txt which grows exponentially in complexity and is headed with a warning.
While the C adaptation only prints the numbers onto a list, it is consistantly faster than the Oracle version.
The Oracle version slows down around 1 billion while the C adaptation processes 1-10 billion quickly and slows down around 100 billion.
Since the algorithm interludes on multiples of 6, there can be primes that go above the range by accident.

Here is how the optimized algorithm I developed for this code works (This is explicatley the C version, which is refined from the Java version)

Rules
-Prime numbers (i.e. 2,3,5,7...) cannot be devisable by any other number. Therefore, a prime number should never return 0 when you modulate it unless it is modulated by itself or 1 (7%7,7%1)
-If prime numbers make up the multiples for all other numbers, you will only need to check if a number is prime by seeing if is a multiple of all prime numbers less than it
-If a number is not divisable by the current prime number, the amount of primes that you will need to check will now be whatever is less than the number in question divided by the current prime number
  *For example. Consider 53.
  *If 53 is not divisable by 2, you now will only need to check up to prime numbers less than [53/2]
  *This is because with every increasing prime number, the size of it's division will never be bigger than the one before it. (i.e. n/2 > n/3) So, every number above [53/2] is too big to be 53's multiple.
-All prime numbers besides 2 and 3 are a 6n+1 or 6n-1 : https://primes.utm.edu/notes/faq/six.html
  
Alorithm (Given range r)
-Start by adding 2 and 3 to to the prime list
-For every 6n+1 and 6n-1 that is < r
  -For the prime list, starting at 3
    -if the number/previous prime is < the current prime, return posative
    -if number%prime = 0 then return negative
  
  
