//package generic;

import jminusminus.CLEmitter;
import static jminusminus.CLConstants.*;

import java.util.ArrayList;

/**
 * This class programatically generates the class file for the following Java
 * application using CLEmitter:
 * 
 * public class SetMax {
 *  private static int getMax(int a, int b, int c) {
 *   if (a > b && b > c)
 *      { c = a; }
 *    else
 *      { c = b; }
 *      return c; 
 *    }
 * 
 * public static void main( String[] args )
 *  { 
 *   int a = Int.parseInt( args[ 0 ] );
 *    int b = Int.parseInt( args[ 1 ] );
 *     int c = Int.parseInt( args[ 2 ] );
 *      System.out.println(SetMax.getMax(a,b,c) );
 *   }
 *  }
 */

public class GenSetMax {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        CLEmitter e = new CLEmitter(true);
        ArrayList<String> accessFlags = new ArrayList<String>();

        // Add SetMax class
        accessFlags.add("public");
        e.addClass(accessFlags, "SetMax", "java/lang/Object", null, true);

        // Add getMax() method to SetMax
        accessFlags.clear();
        accessFlags.add("private");
        accessFlags.add("static");
        e.addMethod(accessFlags, "getMax", "([I)I", null, true);
        
        e.addNoArgInstruction(ILOAD_1);
        e.addNoArgInstruction(ILOAD_2);
        e.addBranchInstruction(IF_ICMPGT, "falseLabel");
        e.addNoArgInstruction(ILOAD_1);
        e.addNoArgInstruction(ISTORE_3);
        e.addNoArgInstruction(ILOAD_3);
        e.addNoArgInstruction(IRETURN);
        e.addLabel("falseLabel");
        e.addNoArgInstruction(ILOAD_2);
        e.addNoArgInstruction(ISTORE_3);
        e.addNoArgInstruction(ILOAD_3);
        e.addNoArgInstruction(IRETURN);
        
        // Add main() method to SetMax
        accessFlags.clear();
        accessFlags.add("public");
        accessFlags.add("static");
        e.addMethod(accessFlags, "main", "([Ljava/lang/String;)V", null, true);
        
        // Block of reading of numbers
        e.addNoArgInstruction(ALOAD_0);
        e.addNoArgInstruction(ICONST_0);
        e.addNoArgInstruction(AALOAD);
        e.addMemberAccessInstruction(INVOKESTATIC, "java/lang/Integer",
                "parseInt", "(Ljava/lang/String;)I");
        
        //create array
        // e.addNoArgInstruction(LDC);
        e.addNoArgInstruction(ICONST_3);
        e.addNoArgInstruction(NEWARRAY);
        e.addNoArgInstruction(ASTORE_1);
        //end of creating array
        
        e.addNoArgInstruction(ICONST_0);
        e.addNoArgInstruction(AALOAD);
        
        e.addNoArgInstruction(ISTORE_1);
        
        e.addNoArgInstruction(ALOAD_0);
        e.addNoArgInstruction(ICONST_1);
        e.addNoArgInstruction(AALOAD);
        e.addMemberAccessInstruction(INVOKESTATIC, "java/lang/Integer",
                "parseInt", "(Ljava/lang/String;)I");
        e.addNoArgInstruction(ISTORE_2);
        
        e.addMemberAccessInstruction(GETSTATIC, "java/lang/System", "out",
                "Ljava/io/PrintStream;");
        e.addMemberAccessInstruction(INVOKESTATIC, "SetMax", "getMax",
                "([I)I");
        e.addMemberAccessInstruction(INVOKEVIRTUAL, "java/io/PrintStream",
                "println", "(Ljava/lang/String;)V");
        e.addNoArgInstruction(RETURN);

        // Write HelloWorld.class to file system
        e.write();

	}

	public static int getMax(int a, int b, int c) {
		if (a > b && b > c) {
			c = a;
		} else {
			c = b;
		}
		return c;
	}
}
