;; =============================================================
;; CS 2110 - Fall 2020
;; Project 3 - Modulus
;; =============================================================
;; Name:Kevin Huynh
;; =============================================================

.orig x3000

LD R1, A ;; Loads R1 with value 5
BRn ABS1 ;; checks if value in R1 is negative 
RET1 
LD R2, B ;; Loads R2 with value 7
BRp ABS2 ;; checks if value in R2 is postive
RET2
AND	R3,	R3,	0 ;; clears R3 to be utilized
LOOP
ADD R3, R1, R2 ;; checks condition a >= b
BRn JUMP ;; if a >= b is false jump to last instruction
ADD R1, R1, R2 ;; if a >= b is false is true do a = a - b
BRnzp LOOP ;; loop back
JUMP
ST R1, RESULT ;; Store value of A into RESULT in memory
HALT
ABS1
NOT R1, R1 ;; Does absolute value of value in R1
ADD R1, R1, 1 ;; Does absolute value of value in R1
BRnzp RET1 ;; returns back to original program
ABS2 
NOT R2, R2 ;; Makes the value in R2 negative
ADD R2, R2, 1 ;; Makes the value in R2 negative
BRnzp RET2 ;; returns back to original program
A       .fill 5
B       .fill 7
RESULT  .blkw 1

.end
