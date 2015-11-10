 #  private static int getMax(int a, int b, int c)
 #   { if (a  > b && b > c)
 #        { c = a; }
 #     else
 #        { c = b; }
 #    return c; 
 #   }
 #  
 #   public static void main( String[] args ) 
 #   { 
 #       int a = Int.parseInt( args[ 0 ] );
 #       int b = Int.parseInt( args[ 1 ] );
 #       int c = Int.parseInt( args[ 2 ] );
 #       System.out.println(SetMax.getMax(a,b,c) ); 
 #   } 
 #  }


  .data
  str1:  .asciiz  "\nEnter 3 numbers:\n"
  str2:  .asciiz  "The C is: \n"

	.text

    .globl setMax
      setMax:
      subu $sp, $sp, 40
        sw $ra, 32($sp)
        sw $s0, 16($sp)         # a
        move $s0, $a1
        sw $s1, 20($sp)         # b
        move $s1, $a2
        sw $s2, 24($sp)         # c
        move $s2, $a3
        sw $s3, 28($sp)         # temporary
        
        ble $s0, $s1, TOELSE # if (a > b)
        ble $s1, $s2, TOELSE # and if (b > c)
        #THEN
        move $s2, $s0

        j UNDERRETURN
        TOELSE:
        move $s2, $s1 
    
        UNDERRETURN:
        move $v0, $s2 
        lw $ra, 32($sp)
        lw $s0, 16($sp)
        lw $s1, 20($sp)
        lw $s2, 24($sp)
        lw $s3, 28($sp)
        addiu $sp, $sp, 40
        jr $ra

     .globl main
      main:
         subu $sp, $sp, 24
         sw $ra, 16($sp)

         li  $v0, 4         #4 for print_sting
         la  $a0, str1      #print string
         syscall            #invoke syscall - 4
         
         #read first int 
         li   $v0, 5        #5 read int
         syscall
         add  $a1, $v0, $zero #put int in temp
         #read second int 
         li   $v0, 5
         syscall
         add  $a2, $v0, $zero
         #read third int 
         li   $v0, 5
         syscall
         add  $a3, $v0, $zero

         li   $v0, 4        #4 for print_sting
         la  $a0, str2
         syscall

         jal setMax         # setMax(a, b, c)
         move $a0, $v0      #Move fact result to $a0
         li   $v0, 1       #1 for print int  

         syscall

         lw $ra, 16($sp)
         addiu $sp, $sp, 24
         jr $ra
    