![Logo](logo.gif)
[![Build Status](https://travis-ci.org/idealista/format-preserving-encryption-java.svg?branch=master)](https://travis-ci.org/idealista/format-preserving-encryption-java)
# fpe - Format Preserving Encryption Implementation in Java

Format-preserving encryption (FPE) is designed for data that is not necessarily binary. In particular, given any finite set of symbols, like the decimal numerals, a method for FPE transforms data that is formatted as a sequence of the symbols in such a way that the encrypted form of the data has the same format, including the length, as the original data. Thus, an FPE-encrypted SSN would be a sequence of nine decimal digits.

An implementation of the NIST approved Format Preserving Encryption (FPE) in Java.

[NIST Recommendation SP 800-38G](http://nvlpubs.nist.gov/nistpubs/SpecialPublications/NIST.SP.800-38G.pdf)


## Installation

Check [requirements](#requirements) section before installation


You can pull it from the central Maven repositories:

```xml
<dependency>
  <groupId>com.idealista</groupId>
  <artifactId>format-preserving-encryption</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Features

* Out of the box working algorithm with an easy API
* Custom Domain (any subset of character could be used)
* Custom Pseudo Random Function (_cipher algorithm_)

## Example Usage

### Input data

During Format Preserving Encryption object creation, input data shall meet the following requirements:

- radix ∈ [ 2 .. 2<sup>16</sup> ]
- radix<sup>minlen</sup >= 100
- 2 <= minlen < maxlen <= 2^32
- key is an AES Key, must be 16, 24 or 32 bytes length

If default tweak option is used:

- tweak length should be lower that tweakMaxLength

### Code

```java
// with default values
FormatPreservingEncryption formatPreservingEncryption = FormatPreservingEncryptionBuilder
        .ff1Implementation()
        .withDefaultDomain()
        .withDefaultPseudoRandomFunction(anyKey)
        .withDefaultLengthRange()
        .build();
    
//with custom inputs
FormatPreservingEncryption formatPreservingEncryption = FormatPreservingEncryptionBuilder
        .ff1Implementation()
        .withDomain(new BasicAlphabetDomain())
        .withPseudoRandomFunction(new DefaultPseudoRandomFunction(anyKey))
        .withLengthRange(new LengthRange(2, 20))
        .build();

//usage
String cipherText = formatPreservingEncryption.encrypt(aText, aTweak);
String plainText = formatPreservingEncryption.decrypt(aText, aTweak);
```

### Custom Inputs

#### Domain

[GenericDomain](src/main/java/com/idealista/fpe/config/GenericDomain.java) represents the easiest implementation of a domain.  A valid domain should be able to transform text input to numeral string and numeral string to text.

The _domain_ of an instance has two elements:

- *Alphabet*: A subset of characters that are valid to create a text input for an instance.
- *Transformers*: Functions (Class) that are able to transform text to numeral string or numeral string to text.

The [default domain](src/main/java/com/idealista/fpe/config/basic/BasicAlphabet.java) includes the lower case letters of the English alphabet

#### Pseudo Random Function (PRF)

A given designated cipher function. By default AES-CBC with 128, 192 or 256 based on the input key is used.

#### Input text length

The minimum length of a text for a given domain is defined using the rules at the start of this section. Although the maximum length is not defined, you must be aware of performance issues when using a very large text.  

## Requirements

The library has been tested with _Apache Maven 3.3.3_ and _JDK 1.6-1.7_. Newer versions of _Apache Maven/JDK_ should work but could also present issues.

Usage of Java Cryptography Extension (JCE) requires to download an install Policy Files for target java distribution: [1.6](http://www.oracle.com/technetwork/es/java/javase/downloads/jce-6-download-429243.html), [1.7](http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html), [1.8](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html)

## Design choices

- FF1Algorithm is a _pure_ implementation without checking, input data is checked during object creation or before invoke the algorithm. Be awere of this when using the library and use the `FormatPreservingEncryptionBuilder` class.
- Every input data error throws an `IllegalArgumentException`

## TODO

* Implement FF3

## License 

Read [LICENSE.txt](LICENSE.txt) attached to the project

## Contribution

Read [CONTRIBUTION.md](.github/CONTRIBUTING.md)
