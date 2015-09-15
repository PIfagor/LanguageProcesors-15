import java.io.*;
import java.text.ParseException;
import java.util.Hashtable;

public class LexicalAnalyzer {

	Hashtable<String, Integer> reserved = new Hashtable<String, Integer>();

	public enum Token {
		KEYWORD, IDENTIFIER, OPERATOR, CONSTANT, SYMBOL, BLANK, INTEGER, REAL, BOOLEAN, CHARACTER, STRING
	}

	InputStream is;
	int curChar;
	int curPos;
	int curLine;
	Token curToken;
	TokenInfo info;
	boolean endOfFile;
	
	public LexicalAnalyzer(InputStream is) throws ParseException {
		this.is = is;
		curPos = 0;

		reserved.put("integer", Token.INTEGER.ordinal());
		reserved.put("real", Token.REAL.ordinal());
		reserved.put("bolean", Token.BOOLEAN.ordinal());
		reserved.put("character", Token.CHARACTER.ordinal());
		reserved.put("string", Token.STRING.ordinal());

		reserved.put("div", Token.OPERATOR.ordinal());
		reserved.put("mod", Token.OPERATOR.ordinal());

		reserved.put("var", Token.KEYWORD.ordinal());
		reserved.put("end", Token.KEYWORD.ordinal());
		reserved.put("if", Token.KEYWORD.ordinal());
		reserved.put("then", Token.KEYWORD.ordinal());
		reserved.put("function", Token.KEYWORD.ordinal());
		reserved.put("begin", Token.KEYWORD.ordinal());
		reserved.put("exit", Token.KEYWORD.ordinal());
		nextChar();
	}

	private boolean isBlank(int c) {
		return c == ' ' || c == '\r' || c == '\n' || c == '\t';
	}

	private boolean isOperator(int c) {
		return c == '*' || c == '/' || c == '-' || c == '+' || c == '>'
				|| c == '<' || c == '&' || c == '|' || c == '!' || c == '~'
				|| c == '=' || c == ':';
	}

	private boolean isSymbol(int c) {
		return c == ',' || c == '.' || c == ':' || c == ';'|| c == '('|| c == ')';
	}

	private boolean isCharacter(int c) {
		return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'));
	}

	private boolean isQuotes(int c) {
		return c == '\"';
	}

//	private boolean isDataType(String s) {
//		return s.toLowerCase().equals("integer")
//				|| s.toLowerCase().equals("real")
//				|| s.toLowerCase().equals("bolean")
//				|| s.toLowerCase().equals("character")
//				|| s.toLowerCase().equals("string");
//	}

	private void nextChar() throws ParseException {
		curPos++;
		
		try {
			if (is.available() != 0)
				curChar = is.read();
			else 
				endOfFile = true;
			if (curChar == '\n')
				curLine++;
		} catch (IOException e) {
			throw new ParseException(e.getMessage(), curPos);
		}
	}

	public void nextToken() throws ParseException {
		if(!endOfFile) {
		String temp = "";

		while (isBlank(curChar)) {
			nextChar();
			curToken = Token.BLANK;
		}
		//work with string in file
		if (isQuotes(curChar)) {
			curToken = Token.STRING;
			while (isQuotes(curChar)) {
				temp += (char) curChar;
				nextChar();
			}
			info  = new TokenInfo(temp,Token.STRING , curLine);
		    temp="";
			return;
		}
		
		while (isCharacter(curChar)) {

			temp += (char) curChar;
			curToken = Token.IDENTIFIER;
			nextChar();
		}

		if (!temp.equals("")) {
			Integer index = reserved.get(temp.toLowerCase());
			if (index != null)
				info  = new TokenInfo(temp, Token.values()[index] , curLine);
			else 
				info  = new TokenInfo(temp, Token.IDENTIFIER , curLine);
			return;
		}
		
		if (curChar >= '0' && curChar <= '9') {
			
			curToken = Token.CONSTANT;
			while (curChar >= '0' && curChar <= '9') {
				temp += (char) curChar;
				nextChar();
			}
			info  = new TokenInfo(temp,Token.CONSTANT , curLine);
		    temp="";
			return;
			
		} else if (isOperator(curChar)) {
			curToken = Token.OPERATOR;
			while (isOperator(curChar)) {
				temp += (char) curChar;
				nextChar();
			}
			info  = new TokenInfo(temp,Token.OPERATOR , curLine);
		    temp="";
			return;
		} else if (isSymbol(curChar)) {
			curToken = Token.SYMBOL;
			temp += (char) curChar;
			info  = new TokenInfo(temp,Token.SYMBOL , curLine);
		}

		// System.out.println((char) curChar);
		nextChar();
		}
	}
	
	public TokenInfo getInfo () {
		return info;
	}
	
	public boolean notEndOfFile() {
		return !endOfFile;
	}
	
	public class TokenInfo {
		String data;
		Token type;
		int line;

		
		 public TokenInfo(String temp, Token string, int curLine) {
			 	data = temp;
				type = string;
				line  = curLine;
		}

		@Override
		public String toString() {
			String res = "================";
			res +=  "\n Token info:";
			res += "\n Token: " + data;
			res += "\n Token type: " +type;
			res += "\n Line: "  + line;
			res += "\n ==============";
			return res;
		}
		
	}
	
	
	
	
	//useless
	public Token curToken() {
		return curToken;
	}

	//useless
	public int curPos() {
		return curPos;
	}
	
	
	

	
	public static void main(String[] args) throws ParseException, IOException {

		//String str = "var a, b: integer; c:integer;";
		//InputStream is = new ByteArrayInputStream(str.getBytes());
		
		InputStream is = new FileInputStream("res/testing2.txt");
		
		LexicalAnalyzer la = new LexicalAnalyzer(is);
		
		
		PrintWriter writer = new PrintWriter("res/result2.txt", "UTF-8");
		
		
		
		
		while (la.notEndOfFile()) {
			la.nextToken();
			System.out.println(la.getInfo());
			writer.println(la.getInfo());
		}
		
		is.close();
		writer.close();
	}
	
	
	
	
	
}
