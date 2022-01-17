! Spring 2021 Revisions by Jackson Williams

! This program executes pow as a test program using the LC 2200 calling convention
! Check your registers ($v0) and memory to see if it is consistent with this program

!Test for addi
!addi $at, $zero, 1
!addi $v0, $zero, 2
!addi $a0, $zero, 3
!addi $a1, $zero, 4
!addi $a2, $zero, 5
!addi $t0, $zero, 6
!addi $t1, $zero, 7
!addi $t2, $zero, 8
!addi $s0, $zero, 9
!addi $s1, $zero, 10
!addi $s2, $zero, 11
!addi $k0, $zero, 12
!addi $sp, $zero, 13
!addi $fp, $zero, 14
!addi $ra, $zero, 15
!halt

!Test for add
!add
!addi $v0, $v0, 2
!add $v0, $v0, $v0
!addi $at, $at, 3
!add $v0, $v0, $at
!halt

!Test for nand
!addi $at, $zero, 1
!addi $v0, $zero, 1
!nand $a1, $at, $v0
!addi $ra, $zero, 5
!halt

!Test for lw & lea
! lea $a1, EXP                            
! lw $a1, 0($a1)
! addi $at, $zero, 1
! EXP:    .fill 8
! halt

!Test for sw
! lea $a0, ANS
! addi $v0, $zero, 5                           
! sw $v0, 0($a0)
! halt
! ANS:	.fill 0 
!Test for beq & blt. Change a1 and a2 registers. Make them equal and not. 
! addi $a1, $a1, 1
! addi $a2, $a2, 0
! blt $a1, $a2, ELSE
! beq $zero, $zero, DONE
! ELSE: addi $v0, $v0, 5
! HALT
! DONE: addi $v0, $v0, 3
! HALT


!Test for beq & blt. Change a1 and a2 registers. Make them equal and not. 
! addi $a1, $a1, 7
! addi $a2, $a2, 8
! beq $a1, $a2, DONE          !not equal, so should have 5 in v0
! beq $zero, $zero, ELSE      !equal, should have 3 in v0
! halt
! ELSE: addi $v0, $v0, 5
! DONE: addi $v0, $zero, 3
! halt

!Test for jalr
! lea $at, MULT                           
! jalr $ra, $at
! addi $s1, $zero, 10
! MULT: addi $v0, $v0, 5
! jalr $zero, $ra
! HALT


!Test for inc
!addi $v0, $v0, 5
!inc $v0
!halt
































