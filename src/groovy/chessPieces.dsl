chessPieces = []

chessPiece1 =
newChessPiece.to.todo {
color "white"
posLetter "A"
posNumber 1
}
chessPieces << chessPiece1

chessPiece2 =
newChessPiece.to.setColor('black').setPosition('B1')
chessPieces << chessPiece2

chessPiece3 =
newChessPiece.to.setColor('white').setPosition('C3')
chessPieces << chessPiece3

 chessPieces.each {
      it.printlnChess()
}
