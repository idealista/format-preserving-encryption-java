# fpe - Format Preserving Encryption Implementation in Java


An implementation of the NIST approved Format Preserving Encryption (FPE) FF1 algorithms in Java.

[NIST Recommendation SP 800-38G](http://nvlpubs.nist.gov/nistpubs/SpecialPublications/NIST.SP.800-38G.pdf)

## Installation


## Features


## Example Usage

During FF1 object creation, input data shall meet the following requirements:

- radix ∈ [ 2 .. 2^6 ]
- radix ^ minlen >= 100
- 2 <= minlen < maxlen <= 2^32
- key is an AES Key, must be 16, 24 or 32 bytes length

If default tweak option is used:

- tweakMaxLength should be lower that maxlen
- tweak length should be lower that tweakMaxLength





## Requirements

The library has been tested with _Apache Maven 3.3.3_ and _JDK 1.6-1.7_. Newer versions of _Apache Maven/JDK_ should work but could also present issues.

## Design choices

- FF1Algorithm is a _pure_ implementation without checking, evey check is performance in ..

## TODO

* Implement FF3

## License 

Read [LICENSE.txt](LICENSE.txt) attached to the project⏎
