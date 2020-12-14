;; =============================================================
;; CS 2110 - Fall 2020
;; Project 3 - To Uppercase
;; =============================================================
;; Name: Kevin Huynh
;; =============================================================

.orig x3000
LD R1, STRING ;; loads starting address of the String onto R1
LD R2, LOWER_A ;; loads R2 with the ASCII value A
LD R3, LOWER_Z ;; loads R3 with the ASCII value Z
LD R5, TOUPPER ;; loads R5 with the difference between lower case and upper case values
LOOP
LDR R0, R1, 0 ;; R0 = the first charater in the String
BRz QUIT ;; exits loop if it reaches the end of the loop 
ADD R4, R0, R2 ;; checks if R0 is lower case or uppercase
BRzp UPPERCASE ;; 
RETURN
OUT
ADD R1, R1, 1 ;; increments to the next address of the string
BRnzp LOOP ;
UPPERCASE ;
ADD R4, R0, R3 ;; R4 = R0 + R3
BRp RETURN
ADD R0, R0, R5 ;; R0 = R0 + R5
BRnzp RETURN ;;
QUIT
HALT

TOUPPER     .fill -32
LOWER_A     .fill -97
LOWER_Z     .fill -122

STRING      .fill x4000
RESULT      .blkw 1
.end

.orig x4000
.stringz "hAhA"
.end
