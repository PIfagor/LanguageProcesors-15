/**
 *
 * @author Student
 */
package parser;

import static parser.TokenKind.*;

import java.nio.file.LinkOption;
import java.nio.file.attribute.PosixFilePermission;

public class Parser {

	/** The lexical analyzer with which tokens are scanned. */
	private LookaheadScanner scanner;

	/** Whether a parser error has been found. */
	private boolean isInError;

	/** Wheter we have recovered from a parser error. */
	private boolean isRecovered;

	public Parser(LookaheadScanner scanner) {
		this.scanner = scanner;
		isInError = false;
		isRecovered = true;
		scanner.next(); // Prime the pump
	}

	/**
	 * Has a parser error occurred up to now?
	 * 
	 * @return true or false.
	 */

	public boolean errorHasOccurred() {
		return isInError;
	}

	private boolean see(TokenKind sought) {
		return (sought == scanner.token().kind());
	}

	private boolean have(TokenKind sought) {
		if (see(sought)) {
			scanner.next();
			return true;
		} else {
			return false;
		}
	}

	private void mustBe(TokenKind sought) {
		if (scanner.token().kind() == sought) {
			scanner.next();
			isRecovered = true;
		} else if (isRecovered) {
			isRecovered = false;
			reportParserError("%s found where %s sought", scanner.token()
					.image(), sought.image());
		} else {
			while (!see(sought) && !see(EOF)) {
				scanner.next();
			}
			if (see(sought)) {
				scanner.next();
				isRecovered = true;
			}
		}
	}

	/**
	 * Report a syntax error.
	 * 
	 * @param message
	 *            message identifying the error.
	 * @param args
	 *            related values.
	 */
	private void reportParserError(String message, Object... args) {
		isInError = true;
		isRecovered = false;
		System.err
				.printf("%s:%d: ", scanner.fileName(), scanner.token().line());
		System.err.printf(message, args);
		System.err.println();
	}

	/**
	 * Parse a statement.
	 * 
	 * <pre>
	 *   statement ::= IF parExpression statement [ELSE statement]
	 *               | WHILE parExpression statement 
	 *               | RETURN [expression] SEMI
	 *               | SEMI
	 * </pre>
	 */
	public void statement() {
		if (have(IF)) {
			parExpression();
			statement();
			// mustBe(THEN);
			if (have(ELSE)) {
				statement();
			}
		} else if (have(WHILE)) {
			parExpression();
			statement();

		} else {
			mustBe(SEMI);
		}
	}

	private boolean flag = false;

	/**
	 * Parse a define.
	 * 
	 * <pre>
	 *   define ::= VAR IDENTIFIER
	 *              		 | COMMA  
	 *               		 | COLON 
	 *             				| ARRAY LBRACK INT_LITERAL DOT DOT INT_LITERAL RBRACK
	 *             			 | TYPE SEMI
	 *             		| BEGIN
	 * 
	 * </pre>
	 */
	public void define() {

		if (have(VAR)) {
			flag = true;
			define();

		} else if (flag) {

			if (have(IDENTIFIER)) {
				if (have(COMMA))
					define();
				// mustBe(IDENTIFIER);
				else if (have(COLON)) {
					if (have(ARRAY))
						arrayInit();
					if (isType()) {
						mustBe(SEMI);
						define();
					} else
						mustBe(COLON);
				}
			} else
				mustBe(BEGIN);
		} else
			mustBe(VAR);
	}

	private boolean isType() {
		boolean flug = see(INTEGER) || see(CHAR) || see(REAL);
		if (flug) {
			if (see(INTEGER))
				have(INTEGER);
			else if (see(CHAR))
				have(CHAR);
			else if (see(REAL))
				have(REAL);
		}

		return flug;
	}

	private void arrayInit() {
		mustBe(LBRACK);
		demisionOfArray();
		mustBe(RBRACK);
		mustBe(OF);
		// define();
	}

	private void demisionOfArray() {
		mustBe(INT_LITERAL);
		mustBe(DOT);
		mustBe(DOT);
		mustBe(INT_LITERAL);
	}

	/**
	 * Parse a parenthesized expression.
	 * 
	 * <pre>
	 *   parExpression ::= LPAREN expression RPAREN
	 * </pre>
	 */
	private void parExpression() {
		mustBe(LPAREN);
		expression();
		mustBe(RPAREN);
	}

	/**
	 * An expression.
	 * 
	 * <pre>
	 *   expression ::= assignmentExpression
	 * </pre>
	 */
	private void expression() {
		assignmentExpression();
	}

	/**
	 * Parse an assignment expression.
	 * 
	 * <pre>
	 *   assignmentExpression ::= 
	 *       conditionalAndExpression
	 *           [( ASSIGN
	 *            | PLUS_ASSIGN
	 *            )
	 *            assignmentExpression]
	 * </pre>
	 */
	private void assignmentExpression() {
		conditionalAndExpression();
		if (have(ASSIGN)) {
			assignmentExpression();
		} else {
		}
	}

	/**
	 * Parse a conditional-and expression.
	 * 
	 * <pre>
	 *   conditionalAndExpression ::= equalityExpression
	 *                                  {LAND equalityExpression}
	 * </pre>
	 */
	private void conditionalAndExpression() {
		boolean more = true;
		equalityExpression();
		while (more) {
			if (have(LAND)) {
				equalityExpression();
			} else {
				more = false;
			}
		}
	}

	/*
	 * Parse an equality expression.
	 * 
	 * <pre> equalityExpression ::= qualifiedIdentifier {EQUAL
	 * qualifiedIdentifier} </pre>
	 */
	private void equalityExpression() {
		boolean more = true;
		qualifiedIdentifier();
		while (more) {
			if (have(EQUAL)) {
				qualifiedIdentifier();
			} else {
				more = false;
			}
		}
	}

	/**
	 * Parse a qualified identifier.
	 * 
	 * <pre>
	 *   qualifiedIdentifier ::= IDENTIFIER {DOT IDENTIFIER}
	 * </pre>
	 */
	private void qualifiedIdentifier() {
		mustBe(IDENTIFIER);
		while (have(DOT)) {
			mustBe(IDENTIFIER);
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */

}
