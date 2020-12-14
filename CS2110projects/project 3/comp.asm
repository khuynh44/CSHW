;; =============================================================
;; CS 2110 - Fall 2020
;; Project 3 - Compare
;; =============================================================
;; Name:Kevin Huynh
;; =============================================================

.orig x3000
comp
LD R1, A ; R1 = A
LD R2, B ; R2 = B
AND R3, R3, 0 ; clears R3
NOT R2, R2 ; 
ADD R2, R2, 1 ; -(R2)
ADD R1, R1, R2 ; R1 = R1 + R2
BRp 6 
BRn 2
ST R3, RESULT ; RESULT = R3
HALT
ADD R3, R3, -1 ; R3 = R3 -1
ST	R3,	RESULT ; RESULT = R3
HALT
ADD R3, R3, 1 ; R3 = R3 + 1
ST	R3, RESULT ; RESULT = R3
HALT
A       .fill 5
B       .fill 7
RESULT  .blkw 1
.end
