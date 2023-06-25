# Object_Oriented_Programming_in_Java_Specialization_by_Duke_University
This repo contains the solutions to the assignments in [Object Oriented Programming in Java Specialization offered by Duke University](https://www.coursera.org/specializations/object-oriented-programming) at coursera<br>

<font color="red"> Note you will find the files used to test these classes in the official site of [dukelearntoprogram](https://www.dukelearntoprogram.com//course2/files.php) </font>
# COURSE 1
* COURSE 1
    * [Week 1](./COURSE%201/Week%201)
    * [Week 2](./COURSE%201/Week%202)
    * [Week 3](./COURSE%201/Week%203)
    * [Week 4](./COURSE%201/Week%204)
# COURSE 2


* COURSE 2
  * [Week 1](./COURSE%202/Week%201)
    * [CaesarCipher Class](./COURSE%202/Week%201/CaesarCipher.java)
        - <span style="color: red;">The Function encrypt: Uses the Ceaser cipher Algorithm to encrypt a message</span>
            - inputs:
                * input: 
                    A String message you want to encrypt.
                * key: 
                    An integer number which represents the number of place for the shift.
            - output:
                * Is an encrypted message according to the Ceaser Cipher Algortihm.
        - <span style="color: red;">The Function encryptTwoKeys: Uses the Ceaser cipher Algorithm to encrypt a message with two keys</span>
            - inputs
                - input:
                    A String message you want to encrypt.
                - key1:
                    An integer number used to encrypt the letters at the odd number location(even index).
                - key2:
                    An integer number used to encrypt the letters at the even number location(odd index).
            - output:
                - Is an encrypted message according to the Ceaser Cipher Algortihm.
        - <span style="color: red;">The Function testCaesar: A function tests the encrypt function</span>
        - <span style="color: red;">The Function testEncryptTwoKeys: A function tests the encryptTwoKeys function</span>
        - <span style="color: red;">The Function quizAnswer: A function prints the results of the questions in the quiz</span>
    * [WordPlay Class](./COURSE%202/Week%201/WordPlay.java)
        - <span style="color: red;">The Function isVowel: Checks if the input char is a Vowel.</span>
            * inputs:
                - ch: Input Character that you want to check.
            * output:
                - true: if ch is a vowel.
                - false: if ch is not a vowel.
        - <span style="color: red;">The Function replaceVowels: replaces all occurrences of the vowels in a string with a given character.</span>
            * inputs:
                * phrase:
                    A string message you want to modify.
                * ch:
                    The letter that you want to replace the vowels with.
            * output:
                - is a string represents the modified message.
        - <span style="color: red;">The Function emphasize:  return a String that is the input string but with the Char ch (upper- or lowercase) replaced by ‘*’ if it is in an odd number location in the string, or ‘+’ if it is in an even number location in the string</span>
            * inputs:
                * phrase:
                    A string message you want to modify.
                * ch:
                    The letter that you want to replace.
            * output:
                - is a string represents the modified message.
        - <span style="color: red;">The Function testIsVowel: A function tests the isVowel function</span>
        - <span style="color: red;">The Function testReplaceVowels: A function tests the replaceVowels function</span>
        - <span style="color: red;">The Function testEmphasize: A function tests the emphasize function</span>