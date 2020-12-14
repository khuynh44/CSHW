;; =============================================================
;; CS 2110 - Fall 2020
;; Project 3 - RPN Calculator 
;; =============================================================
;; Name: Kevin Huynh
;; =============================================================


.orig x3000
HALT

;; DO NOT add anything before this line

;; !!!! PLEASE READ !!!!!
;; - The specification for each subroutine can be found before the corresponding
;;   label for the subroutine. It contains information about the input register(s),
;;   output register(s), and the operation that needs to be carried out
;; 
;; - For some subroutines, we ask that you follow the pseudocode provided in the pdf.
;;   You are welcome to deviate from it, but we won't be able to help you in case you
;;   run into issues with the autograder 
;; 
;; - To test your subroutines, we highly recommend that you to use complx before
;;	 running the autograder. Details on how to run subroutines in complx can be found
;;   in the project pdf. We have covered this in lab too, so feel free to rewatch one
;;   of the lab recordings on this. Doing it this way will save you a TON of time.


;; Performs addition between two integers `a` and `b`
;;
;; input:		R0 = a, R1 = b
;; output:		R2 = a + b
my_add
	ADD R2, R0, R1 ;
	RET

;; Performs subtraction between two integers `a` and `b`
;;
;; input:		R0 = a, R1 = b
;; output:		R2 = a - b
my_sub
	NOT R1, R1 ;
	ADD R1, R1, 1 ;
	ADD R2, R0, R1 ;
	RET

;; Performs multiplication between two integers `a` and `b`
;; 
;; input:		R0 = a, R1 = b
;; output:		R2 = a * b
;; 
;; !!!!! IMPORTANT !!!!!
;; FOLLOW THE PSEDUOCODE PROVIDED IN THE PROJECT PDF 
my_mul
	AND R2, R2, 0;
	AND R3, R3, 0;
	ADD R1, R1, 0;
	BRzp LOOP
	NOT R1, R1 ;
	ADD R1, R1, 1 ;
	ADD R3, R3, 1 ;
	LOOP
	ADD R1, R1, 0 ;
	BRnz QUIT
	ADD R2, R2, R0 ;
	ADD R1, R1, -1 ;
	BRnzp LOOP
	QUIT
	ADD R3, R3, 0
	BRz PASS
	NOT R2, R2 ;
	ADD R2, R2, 1;
	PASS
	RET


;; Performs **integer** division between two integers `a` and `b`
;; 
;; input:		R0 = a, R1 = b
;; output:		R2 = a / b
;; examples:	
;;				 4   /   2	=  2
;;				 16  /	 4	=  4
;;				 95  /	 5	=  19 
;;
;;				 8  /   3	=  2	(not 2.6667)
;;				-8  /   3	= -2	(not -2.6667)
;;				 8  /  -3	= -2	(not -2.6667)
;;
;;				 5  /  10	=  0	(not 0.5)
;;				 5  / -10	=  0	(not -0.5)
;;				-5  / -10	=  0	(not -0.5)
;;
;; !!!!! IMPORTANT !!!!!
;; FOLLOW THE PSEDUOCODE PROVIDED IN THE PROJECT PDF 
my_div
	AND R2, R2, 0;
	AND R3, R3, 0;
	ADD R0, R0, 0;
	BRzp PASS1
	ADD R3, R3, -1;
	NOT R0, R0;
	ADD R0, R0, 1;
	PASS1
	ADD R1, R1, 0
	BRzp PASS2
	ADD R3, R3, 1;
	NOT R1, R1;
	ADD R1, R1, 1;
	PASS2
	NOT R1, R1;
	ADD R1, R1, 1;
	LOOP1
	ADD R4, R0, R1;
	BRn QUIT1
	ADD R2, R2, 1;
	ADD R0, R0, R1; 
	BRnzp LOOP1
	QUIT1
	ADD R3, R3, 0
	BRz PASS3
	NOT R2, R2;
	ADD R2, R2, 1;
	PASS3
	RET




;; Evaluates an arithmetic expression that is in RPN form
;;
;; ## inputs			
;; R0 = Base address of a string that contains an arithmetic
;; 	 expression in postfix notation 
;; 
;; ## outputs
;; R1 = The result of computing the arithmetic expression
;; 
;; ## input constraints
;;
;; - Your postfix expression will only consist of **postive single digit** integers
;;   and operators. It won't contain illegal characters, parentheses, negative
;;   numbers, multiple-digit numbers, floating point numbers, or heck, even complex
;;   numbers. Just pure good old **positive single digit** integers!
;; 
;; - You will always get a valid postix expression. 
;; 
;; ## implementation details
;;
;; Let's say we want to calculate (1-(5*8+2)). Since our calculator works on
;; postfix notation, we will need to convert this infix expression to postfix.
;; Doing this will give us (1 5 8 * 2 + -). The string version of this expression
;; is "158*2+-". Note that there are no spaces between the digits. Remember, your
;; expression will only consist of **postive single digit** integers, so you don't
;; have to worry about supporting negative integers, or multiple-digit integers.
;; 
;; The subroutine `rpn` will receive the base address of the string "158*2+-".
;; For example, if your string is stored starting at memory location 0x4000, `rpn`
;; will receive 0x4000 through the R0 register.
;; 
;; You will now need to evaluate this expression by iterating over the string, and
;; carrying out the operations outlined in the "Background" section of the project
;; pdf
;; 
;; Finally, once you are done, you will need to save the final result in the
;; register R0.
;;
;; ## hints
;; - There are some useful labels at the bottom of this file. For example, we have
;;   given you a label that refers to the ASCII value of '+'. You will find this
;;   useful when trying to determine which operation to carry out. We have also
;;   provided you the ASCII value of the character '0'.
;; - There are some test postfix strings starting at x4000. You can access these via
;;	 EXP_LOC, and can use these strings when trying to develop the calculator initially
;; - Figure out how to run subroutines in complx. It will save you a TON of time
;;   during debugging!

rpn
    ST R7, TEMP
	AND R1, R1, 0 ; clear out R1
	STI R1, STACK ; store 0 are the botton of stack
	LD R6, STACK ; R6 = Stack pointer
	LD R0, EXP_LOC ; R0 = index of string
	LOOP2
	LD R4, ZERO ; R4 = -48
	LDR R2, R0, 0 ; Loads R2 with Character at index R0
	BRz QUIT2
	ADD R5, R2, R4 ; checks if R5 is number or operator by R5 = R2 + R4
	BRn OP
	ADD R6, R6, -1;
	STR R5, R6, 0 ; push R5 into stack
	ADD R0, R0, 1 ; 
	BRnzp LOOP2
	OP
	LD R1, STACK ; puts beginning stack address onto R1
	NOT R1, R1 ; makes it negative
	ADD R1, R1, 1 ; makes it negative
	ADD R1, R1, R6 ; check if it is just an operator and no numbers
	BRz QUIT2
	ST R0, EXP_LOC
	LDR R1, R6, #0 ; Pop stack and store in R1
	ADD  R6, R6, 1 ;
	LDR R0, R6, 0 ; Pop stack and store in R0
	ADD  R6, R6, 1 ;
	LD R5, PLUS ; R5 = -43
	ADD R5, R2, R5 ; checks which operator R2 contains by R5 = R2 + R5
	BRzp ELSE
	JSR my_mul
	BRnzp RETURN
	ELSE
	ADD R5, R5, 0
	BRp ELSE1
	JSR my_add
	BRnzp RETURN
	ELSE1
	ADD R5, R5, -2 ; checks if R5 is subtract
	BRp ELSE2
	JSR my_sub
	BRnzp RETURN
	ELSE2
	JSR my_div
	RETURN
	ADD R6, R6, -1
	STR R2, R6, 0 ; push R2(output) onto stack
	LD R0, EXP_LOC
	ADD R0, R0, 1 ; R0 = R0 + 1
	BRnzp LOOP2
	QUIT2
	LDR R1, R6, 0 ;
	LD R7, TEMP ;
	RET
;; some useful labels 

;; ASCII value for important characters
PLUS	.fill -43	;; +
MINUS	.fill x2D	;; -
MULT	.fill x2A	;; *
DIVIDE	.fill x2F	;; /
ZERO	.fill -48	;; 0
TEMP    .fill 1
;; initial stack pointer
STACK	.fill xf000
EXP_LOC .fill x4000
.end

.orig x4000
.stringz "34+"		;; expected output => R1 = 7
;; EXP	.stringz "54-3+"	;; expected output => R1 = 2 
;; EXP	.stringz "58*42/-"	;; expected output => R1 = 38 
.end
