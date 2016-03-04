# Problem-182
Problem 182 from Projecteuler.net 

The prompt for this problem (provided by Project Euler's Archives):

## RSA Encryption
The RSA encryption is based on the following procedure:

Generate two distinct primes *p* and *q*.
Compute *n*=*pq* and *φ*=(*p*-1)(*q*-1).
Find an integer *e*, 1<*e*<*φ*, such that gcd(*e*,*φ*)=1.

A message in this system is a number in the interval [0,*n*-1].
A text to be encrypted is then somehow converted to messages (numbers in the interval [0,*n*-1]).
To encrypt the text, for each message, *m*, *c*=*m*<sup>*e*</sup> mod *n* is calculated.

To decrypt the text, the following procedure is needed: calculate *d* such that *ed*=1 mod *φ*, then for each encrypted message, *c*, calculate *m*=*c*<sup>*d*</sup> mod *n*.

There exist values of *e* and *m* such that *m*<sup>*e*</sup> mod *n*=*m*.
We call messages *m* for which *m*<sup>*e*</sup> mod *n*=*m* unconcealed messages.

An issue when choosing *e* is that there should not be too many unconcealed messages. 
For instance, let *p*=19 and *q*=37.
Then _n_=19\*37=703 and _φ_=18\*36=648.
If we choose *e*=181, then, although gcd(181,648)=1 it turns out that all possible messages
*m* (0≤*m*≤*n*-1) are unconcealed when calculating *m*<sup>*e*</sup> mod *n*.
For any valid choice of e there exist some unconcealed messages.
It's important that the number of unconcealed messages is at a minimum.

Choose *p*=1009 and *q*=3643.
Find the sum of all values of *e*, 1<*e*<*φ*(1009,3643) and gcd(*e*,*φ*)=1, so that the number of unconcealed messages for this value of *e* is at a minimum.

### First Version

With the first version of the program, I wrote a brute force method that would test every possible message for each exponent *e*, count the number of unconcealed messages, find the minimum number of unconcealed messages, and sum all the exponents assosciated with the minimum number of unconcealed messages. This method quickly became unrealistic as the program would have to perform hundreds of millions of calculations.

### Second Version

After some experimentation with the first program, I discovered that the minimum number of unconcealed messages was 9, and revised my first code to search for only exponents which produce 9 unconcealed messages. Although this revision reduced the number of calculations required by the program, the runtime was still unrealistic and further revisions were made.

### Third Version

After some more experimentation with both previous programs and through the use of the chinese remainder theorem, I was able to show that if certain messages ({3028, 3642, 28721, 33210, 73282, 116575}) were unconcealed by an exponent then I could gaurentee that the number of unconcealed messages for that exponent was greater than 9. From there I assumed that if these messages were concealed under an exponent, then the number of unconcealed messages was equal to nine. I revised my code to check only those six messages. With the number of calculations required drastically reduced, program was able to complete in a timely manner and produced the correct answer, indicating that my assumption was correct.

## Final Version

After having created a function program, I decided to research the topic and discovered a theorm which stated that the minimum number of unconcealed messages for a RSA encryption using primes *p, q* and exponent *e* is equal to (GCD(*e,p*) + 1) \* (GCD(*e,q*) + 1). Using this theorem I was able to write a program that could efficiently calculate the minimum number of unconcealed messages for each exponent, determine the minimum number of unconcealed messages for the set of exponents, and then sum those exponents. From there, I adapted the program to allow for user input of the primes used in the RSA encryption. Also the program was adapted to export a text file with the relevant RSA encryption information and a list of all exponents that would produce the least amount of unconcealed messages.
