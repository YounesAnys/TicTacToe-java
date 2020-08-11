public class HumanPlayer implements Player {

  public boolean play(TicTacToe game) {
   if(game.numRounds == game.numRows*game.numColumns){
     throw new IllegalArgumentException("Game is finished already!");
   }

   boolean success = false;

   while(!success) {
         System.out.println(game);
         System.out.print(game.nextPlayer() + " to play: ");
         String answer = Utils.readLine();
         int value;

         try {
             value = Integer.parseInt(answer)-1;
           } catch (NumberFormatException e) {
             value = -1;
           }
           if(value < 0 || value >= (game.numRows*game.numColumns)){
             System.out.println("The value should be between 1 and" 
                   + (game.numRows*game.numColumns));
           } else if(game.valueAt(value) != CellValue.EMPTY) {
             System.out.println("This cell has already been played");
           } else {
             game.play(value);
             success = true;
           }
   }

   return success;

 }

}
