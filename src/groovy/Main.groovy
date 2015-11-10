def dslDef = new File('ChessPiece.groovy').text

def dsl = new File('chessPieces.dsl').text

def script = """

${dslDef}

${dsl}

"""

new GroovyShell().evaluate(script)