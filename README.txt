PrimeBits is a series of projects that take a range between 0 and n and find all the prime numbers in between.
This project was started mainly for experiance in optimizing code. I wanted to see how quickly the program could find prime numbers in a large enough range.
PrimeBits started in oracle and was later adapted into C.
PrimeBits will not only find the set of primes between 0 and n, but it will also print out the set on PrimeList.txt, and a times table on PrimeTable.txt which grows exponentially in complexity and is headed with a warning.
While the C adaptation does not have these features, it is consistantly faster than the Oracle version.
The Oracle version slows down around 1 billion while the C adaptation processes 1-10 billion quickly and slows down around 100 billion.
Since the algorithm interludes on multiples of 6, there can be primes that go above the range by accident.