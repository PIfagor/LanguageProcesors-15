package parser;

/**
 * 
 * @author Student
 */
enum TokenKind {
	// EOF("<EOF>"), ABSTRACT("abstract"), BOOLEAN("boolean"), CHAR("char"),
	// CLASS(
	// "class"), ELSE("else"), EXTENDS("extends"), FALSE("false"), IF("if"),
	// IMPORT(
	// "import"), INSTANCEOF("instanceof"), INT("int"), NEW("new"), NULL(
	// "null"), PACKAGE("package"), PRIVATE("private"), PROTECTED(
	// "protected"), PUBLIC("public"), RETURN("return"), STATIC("static"),
	// SUPER(
	// "super"), THIS("this"), TRUE("true"), VOID("void"), WHILE("while"), PLUS(
	// "+"), ASSIGN("="), DEC("--"), EQUAL("=="), GT(">"), INC("++"), LAND(
	// "&&"), LE("<="), LNOT("!"), MINUS("-"), PLUS_ASSIGN("+="), STAR("*"),
	// LPAREN(
	// "("), RPAREN(")"), LCURLY("{"), RCURLY("}"), LBRACK("["), RBRACK(
	// "]"), SEMI(";"), COMMA(","), DOT("."), IDENTIFIER("<IDENTIFIER>"),
	// INT_LITERAL(
	// "<INT_LITERAL>"), CHAR_LITERAL("<CHAR_LITERAL>"), STRING_LITERAL(
	// "<STRING_LITERAL>");
	EOF("<EOF>"),
	BOOLEAN("boolean"),
	BEGIN("begin"),
	CHAR("char"),
	CLASS("class"),
	ELSE("else"),
	END("end"),
	FALSE("false"),
	IF("if"),
	THEN("then"),
	INTEGER("integer"),
	NULL("nil"),
	PRIVATE("private"),
	PROCEDURE("procedure"),
	PUBLIC("public"),
	REAL("real"),
	TRUE("true"),
	WHILE("while"),
	
	VAR("var"),
	TYPE("<TYPE_OF_INDETIFIER"),
	ARRAY("array"),
	OF("of"),
	
	ASSIGN(":="),
	PLUS("+"), MINUS("-"), STAR("*"),
	EQUAL("="), GT(">"), LE("<="),
	LAND("AND"), LNOT("NOT"), 
	
	
	
	LPAREN("("), RPAREN(")"),
	LCURLY("{"), RCURLY("}"),
	LBRACK("["), RBRACK("]"), 
	
	SEMI(";"), COMMA(","), DOT("."), COLON(":"),
	
	
	
	IDENTIFIER("<IDENTIFIER>"),
	INT_LITERAL("<INT_LITERAL>"),
	CHAR_LITERAL("<CHAR_LITERAL>"),
	STRING_LITERAL(	"<STRING_LITERAL>");

	/** The token's string representation. */
	private String image;

	/**
	 * Construct an instance TokenKind given its string representation.
	 * 
	 * @param image
	 *            string representation of the token.
	 */

	private TokenKind(String image) {
		this.image = image;
	}

	/**
	 * Return the image of the token.
	 * 
	 * @return the token's image.
	 */

	public String image() {
		return image;
	}

	/**
	 * Return the string representation of the token.
	 * 
	 * @return the token's string representation.
	 */

	public String toString() {
		return image;
	}

}