!============================================================
! CS 2200 Homework 2 Part 2: Tower of Hanoi
!
! Apart from initializing the stack,
! please do not edit mains functionality.
!============================================================

main:
    add     $zero, $zero, $zero     ! TODO: Here, you need to get the address of the stack
                                    ! using the provided label to initialize the stack pointer.
    lea     $sp, stack             ! load the label address into $sp and in the next instruction,
    lw      $sp, 0($sp)             ! use $sp as base register to load the value (0xFFFF) into $sp.


    lea     $at, hanoi              ! loads address of hanoi label into $at

    lea     $a0, testNumDisks2      ! loads address of number into $a0
    lw      $a0, 0($a0)             ! loads value of number into $a0

    jalr    $ra, $at                ! jump to hanoi, set $ra to return addr
    halt                            ! when we return, just halt

hanoi:
    add     $zero, $zero, $zero 
    addi    $sp, $sp, -1            ! TODO: perform post-call portion of
    sw      $t0, 0($sp)             ! the calling convention. Make sure to
    addi    $sp, $sp, -1 
    sw      $ra, 0($sp)             ! save any registers you will be using!
    addi    $sp, $sp, -1            
    sw      $fp, 0($sp) 
    addi    $fp, $sp, 0

    add     $zero, $zero, $zero     ! TODO: Implement the following pseudocode in assembly:
    addi    $t0, $zero, 1
    beq     $a0, $t0, base          ! IF ($a0 == 1)
                                    !    GOTO base
                                    ! ELSE
                                    !    GOTO else

else:
                                    ! TODO: perform recursion after decrementing
    addi    $a0, $a0, -1            ! the parameter by 1. Remember, $a0 holds the
    jalr    $ra, $at                ! parameter value.
    add     $v0, $v0, $v0           ! TODO: Implement the following pseudocode in assembly:
    addi    $v0, $v0, 1             ! $v0 = 2 * $v0 + 1
    beq     $zero, $zero, teardown            ! RETURN $v0

base:
                                     ! TODO: Return 1
    addi     $v0, $zero, 1
teardown:
    lw      $fp, 0($sp)             ! TODO: perform pre-return portion
    addi    $sp, $sp, 1
    lw      $ra, 0($sp)             
    addi    $sp, $sp, 1  
    lw      $t0, 0($sp)             
    addi    $sp, $sp, 1
                                    ! of the calling convention
    jalr    $zero, $ra              ! return to caller



stack: .word 0xFFFF                 ! the stack begins here


! Words for testing \/

! 1
testNumDisks1:
    .word 0x0001

! 10
testNumDisks2:
    .word 0x000a

! 20
testNumDisks3:
    .word 0x0014
