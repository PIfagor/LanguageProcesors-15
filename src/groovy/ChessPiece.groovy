class ChessPiece {
	def color
	def posLetter
	def posNumber
	
	def todo(closure) {
		println "in closure"
		closure()
		//println this.color
		//println this.posLetter
		//println this.posNumber
		return this;
	}
	
	def move (direction) {
		switch (direction) {
			case 'up':
				posNumber = (posNumber > 7)? posNumber : posNumber + 1;
				break;
			case 'down':
				posNumber = (posNumber < 2)? posNumber : posNumber - 1;
				break;
			case 'left':
				posLetter = (((int) posLetter) <= ((int) 'a'))? posLetter : (char) (((int) posLetter) - 1);
			case 'right': 
				posLetter = (((int) posLetter) >= ((int) 'h'))? posLetter : (char) (((int) posLetter) + 1);		
		}
		return this;	
	}
	
	def setPosition(position) {
		posLetter = position.charAt(0);
		posNumber = Character.toString(position.charAt(1)).toInteger();
		return this;
	}
	
	def getPosition(position) {
		def pos = [];
		pos.push(posLetter);
		pos.push(posNumber);
		return pos.join();
	}
	
	def setColor(c) {
		color = c;
		return this;
	}
	
	def getTo() {
		this
	  }
	
	def printlnChess(){
		println 'Color:' + this.color
		println this.posLetter
		println this.posNumber
		return this;
	}
}

def getNewChessPiece() {
	chessPiece = new ChessPiece()
}

def methodMissing(String name, args) {
	chessPiece.metaClass.getMetaProperty(name).setProperty(chessPiece, args)
}
