import java.util.LinkedList;

public class TicTacToeEnumerations {


  // YOUR CODE HERE
  int rows;
  int columns;
  int sizeToWin;

  /**
   * The list of lists of all generated games
   */
  LinkedList<LinkedList<TicTacToe>> allGames;


  /**
   * A constructor where you can specify the dimensions
   * of your game as rows x coluns grid, and a sizeToWin
   * to analyze.
   *
   * @param aNumRows the number of lines in the game
   * @param aNumColumns the number of columns in the game
   * @param aSizeToWin the number of cells that must be aligned to win.
   */
  public TicTacToeEnumerations(int aNumRows, int aNumColumns, int aSizeToWin) {

    // YOUR CODE HERE
    rows = aNumRows;
    columns = aNumColumns;
    sizeToWin = aSizeToWin;
  }

  }

  /**
   * Generate a list of lists of all games, storing the
   * result in the member variables `allGames`.
   */
  public LinkedList<LinkedList<TicTacToe>> generateAllGames() {

    // YOUR CODE HERE
    LinkedList<LinkedList<TicTacToe>> allGames;

		allGames = new LinkedList<LinkedList<TicTacToe>>();

		// start with the empty game
		allGames.add(new LinkedList<TicTacToe>());
		allGames.get(0).add(new TicTacToe(rows,columns,sizeToWin));

		//build the new games by adding the next moves to the
		// previously built games
		for(int i=1; i<= rows*columns; i++) {
			LinkedList<TicTacToe> newList;
			newList = new LinkedList<TicTacToe>();
			allGames.add(newList);
			boolean atLeastOnePlaying = false;
			for(TicTacToe game: allGames.get(i-1)){
				if(game.gameState == GameState.PLAYING) {
					for(int j = 0; j < rows*columns; j++) {
						if(game.valueAt(j) == CellValue.EMPTY) {
							TicTacToe newGame = game.cloneNextPlay(j);
							//checking that this game was not already found
							boolean isNew = true;
							for(TicTacToe existingGame: allGames.get(i)){
								if(newGame.equals(existingGame)){
									isNew = false;
									break;
								}
							}
							if(isNew) {
								newList.add(newGame);
								if(newGame.gameState == GameState.PLAYING) {
									atLeastOnePlaying = true;
								}
							}
						}
					}
				}

			}

			if(!atLeastOnePlaying) {
				break;
			}
		}

    return allGames;
  }

  public String toString() {
    if (allGames == null) {
      return "No games generated.";
    }

    StringBuilder s = new StringBuilder();

    int numGames = 0;
    int numXWin = 0;
    int numOWin = 0;
    int numDraw = 0;
    for (int i=0; i<allGames.size(); i++) {
      LinkedList<TicTacToe> games = allGames.get(i);
      int numStillPlaying = 0;
      for (TicTacToe g : games) {
        numGames += 1;
        switch (g.gameState) {
        case PLAYING:
          numStillPlaying += 1;
          break;
        case XWIN:
          numXWin += 1;
          break;
        case OWIN:
          numOWin += 1;
          break;
        case DRAW:
          numDraw += 1;
          break;
        }
      }
      s.append("======= level "+ i +" =======: ");
      s.append(games.size() + " element(s) (");
      s.append(numStillPlaying + " still playing)\n");
    }

    s.append("that's "+ numGames +" games\n");
    s.append(numXWin + " won by X\n");
    s.append(numOWin + " won by O\n");
    s.append(numDraw + " draw");
    return s.toString();
  }

}
