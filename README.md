# Cache-Calculator
A Java application of a cache calculator that specifies the three cache mapping methods of direct, associative, and set-associative.

The program requires the following inputs:
1) Main memory in the format 𝐴 × E^2 , where A and E are the user inputs
2) Cache blocks as a base-2 value
3) Bytes in a Cache block as a base-2 value
4) k-way set associative value where k is a base-2 value (e.g., 2, 4)
5) Memory address in base-16

## Program Requirements

What it should do:
* Calculate and display the Main memory in bytes.
* Convert and display the Memory address in base-2.
* Direct Mapping
* Calculate the Tag, Line and Word bit sizes
* Display the binary Memory address in the Tag, Line and Word bit fields
* Associative Mapping
* Calculate the Tag and Word bit sizes
* Display the binary Memory address in the Tag and Word bit fields
* Set-Associative Mapping
* Calculate the Tag, Set and Word bit sizes
* Display the binary Memory address in the Tag, Set and Word bit fields

What it should verify:

* Cache blocks, Bytes and k are a base-2 value. If not, the program should display appropriate message and terminate.
* Memory address is a base-16 value. If not, the program should display appropriate message and terminate.
* Memory address size is not exceeding Main memory size. If yes, the program should display appropriate message and terminate
