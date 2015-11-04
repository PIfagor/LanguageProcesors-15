package generic;

import jminusminus.CLEmitter;
import static jminusminus.CLConstants.*;

import java.util.ArrayList;

/**
 * This class programatically generates
 * the class file for the following Java
 * application using CLEmitter:
 * 
 * public class SetMax { 
 * 
 * 	private static int getMax(int a, int b, int c)
 * 	{ if (a * > b && b > c)
 * 		 { c = a; }
 * 	  else
 * 		 { c = b; }
 *   return c; 
 *  }
 * 
 * 	public static void main( String[] args ) 
 * 	{ 
 * 		int a = Int.parseInt( args[ 0 ] );
 * 		int b = Int.parseInt( args[ 1 ] );
 * 		int c = Int.parseInt( args[ 2 ] );
 * 		System.out.println(SetMax.getMax(a,b,c) ); 
 * 	} 
 * }
 */

public class GenSetMax {
	public static void main(String[] args) {
		CLEmitter e = new CLEmitter(true);
		GenSetMax.genClassesAndAtributes(e);
		GenSetMax.genGetMax(e);
		GenSetMax.genMain(e);
		e.write();
	}

	public static void genGetMax(CLEmitter e) {
		ArrayList<String> accessFlags = new ArrayList<String>();
		accessFlags.add("private");
		accessFlags.add("static");
		e.addMethod(accessFlags, "getMax", "(III)I", null, true);

		// if (a>b)
		e.addNoArgInstruction(ILOAD_0);
		e.addNoArgInstruction(ILOAD_1);
		e.addBranchInstruction(IF_ICMPLT, "falseLabel");
		// and if (b>c)
		e.addNoArgInstruction(ILOAD_1);
		e.addNoArgInstruction(ILOAD_2);
		e.addBranchInstruction(IF_ICMPLT, "falseLabel");
		// then
		e.addNoArgInstruction(ILOAD_0);
		e.addNoArgInstruction(ISTORE_3);
		e.addNoArgInstruction(ILOAD_3);
		e.addNoArgInstruction(IRETURN);
		// else
		e.addLabel("falseLabel");
		e.addNoArgInstruction(ILOAD_1);
		e.addNoArgInstruction(ISTORE_3);
		e.addNoArgInstruction(ILOAD_3);
		e.addNoArgInstruction(IRETURN);
	}

	public static void genClassesAndAtributes(CLEmitter e) {
		ArrayList<String> accessFlags = new ArrayList<String>();
		accessFlags.add("public");
		e.addClass(accessFlags, "SetMax", "java/lang/Object", null, true);
	}

	public static void genMain(CLEmitter e) {
		ArrayList<String> accessFlags = new ArrayList<String>();
		accessFlags.add("public");
		accessFlags.add("static");
		e.addMethod(accessFlags, "main", "([Ljava/lang/String;)V", null, true);

		// Block of reading of numbers
		e.addNoArgInstruction(ALOAD_0);
		e.addNoArgInstruction(ICONST_0);
		e.addNoArgInstruction(AALOAD);
		e.addMemberAccessInstruction(INVOKESTATIC, "java/lang/Integer",
				"parseInt", "(Ljava/lang/String;)I");
		e.addNoArgInstruction(ISTORE_1);

		e.addNoArgInstruction(ALOAD_0);
		e.addNoArgInstruction(ICONST_1);
		e.addNoArgInstruction(AALOAD);
		e.addMemberAccessInstruction(INVOKESTATIC, "java/lang/Integer",
				"parseInt", "(Ljava/lang/String;)I");
		e.addNoArgInstruction(ISTORE_2);

		e.addNoArgInstruction(ALOAD_0);
		e.addNoArgInstruction(ICONST_2);
		e.addNoArgInstruction(AALOAD);
		e.addMemberAccessInstruction(INVOKESTATIC, "java/lang/Integer",
				"parseInt", "(Ljava/lang/String;)I");
		e.addNoArgInstruction(ISTORE_3);
		// ending of reading numbers

		// invoke method getMax and print result
		e.addMemberAccessInstruction(GETSTATIC, "java/lang/System", "out",
				"Ljava/io/PrintStream;");
		e.addReferenceInstruction(NEW, "java/lang/StringBuffer");
		e.addNoArgInstruction(DUP);
		e.addMemberAccessInstruction(INVOKESPECIAL, "java/lang/StringBuffer",
				"<init>", "()V");
		e.addLDCInstruction("c before: ");
		e.addMemberAccessInstruction(INVOKEVIRTUAL, "java/lang/StringBuffer",
				"append", "(Ljava/lang/String;)Ljava/lang/StringBuffer;");
		e.addNoArgInstruction(ILOAD_3);
		e.addMemberAccessInstruction(INVOKEVIRTUAL, "java/lang/StringBuffer",
				"append", "(I)Ljava/lang/StringBuffer;");
		e.addLDCInstruction("\nc after: ");
		e.addMemberAccessInstruction(INVOKEVIRTUAL, "java/lang/StringBuffer",
				"append", "(Ljava/lang/String;)Ljava/lang/StringBuffer;");

		e.addNoArgInstruction(ILOAD_1);
		e.addNoArgInstruction(ILOAD_2);
		e.addNoArgInstruction(ILOAD_3);
		e.addMemberAccessInstruction(INVOKESTATIC, "SetMax", "getMax", "(III)I");
		// e.addNoArgInstruction(ICONST_2);

		e.addMemberAccessInstruction(INVOKEVIRTUAL, "java/lang/StringBuffer",
				"append", "(I)Ljava/lang/StringBuffer;");
		e.addMemberAccessInstruction(INVOKEVIRTUAL, "java/lang/StringBuffer",
				"toString", "()Ljava/lang/String;");
		e.addMemberAccessInstruction(INVOKEVIRTUAL, "java/io/PrintStream",
				"println", "(Ljava/lang/String;)V");
		e.addNoArgInstruction(RETURN);
	}

}
