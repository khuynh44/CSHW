;; =============================================================
;; CS 2110 - Fall 2020
;; Project 3 - Alternating Sum
;; =============================================================
;; Name:
;; =============================================================

.orig x3000
AND R2, R2, 0 ;; clears R2 register to be utilized
LD R0, LENGTH ;; loads the length of the array onto R0
BRz QUIT ;; if length is zero, it stores zero as result
LD R1, ARRAY ;; loads the index of first array onto R1
LOOP
AND R3, R1, 1 ;; R3 = R1 & 1
BRz EVEN ;; if R3 == 0, R3 is even
BRp ODD ;; if R3 == 1, R3 is odd
RETURN
ADD R1, R1, 1 ;; increment R1
ADD R0, R0, -1 ;; decrement R0
BRp LOOP ;; Branch to loop if positive
QUIT
ST R2, RESULT ;; store R2 into RESULT
HALT
EVEN
LDR R3, R1, 0 ;; Loads R3 with R1
ADD R2, R2, R3 ;; R2 = R2 + R3
BRnzp RETURN ;; Branch back to main 
ODD
LDR R3, R1, 0 ;; Loads R3 with R1
NOT R3, R3 ;; -(R3)
ADD R3, R3, 1 ; R3 = R3 + 1
ADD R2, R2, R3 ; R2 = R2 + R3
BRnzp RETURN ; Branch back to main

LENGTH      .fill 4
ARRAY       .fill x7000
RESULT      .blkw 1

.end


.orig x7000
    .fill 1
    .fill 2
    .fill 3
    .fill 4
.end
