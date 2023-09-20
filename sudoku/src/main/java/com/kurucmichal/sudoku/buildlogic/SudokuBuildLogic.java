package com.kurucmichal.sudoku.buildlogic;

import com.kurucmichal.sudoku.computationlogic.GameLogic;
import com.kurucmichal.sudoku.persistence.LocalStorageImpl;
import com.kurucmichal.sudoku.problemdomain.IStorage;
import com.kurucmichal.sudoku.problemdomain.SudokuGame;
import com.kurucmichal.sudoku.userinterface.IUserInterfaceContract;
import com.kurucmichal.sudoku.userinterface.logic.ControlLogic;
import java.io.IOException;

public class SudokuBuildLogic {
  public static void build(IUserInterfaceContract.View userInterface) throws IOException {
    SudokuGame initialState;
    IStorage storage = new LocalStorageImpl();

    try {
      //will throw if no game data is found in local storage

      initialState = storage.getGameData();
    } catch (IOException e) {

      initialState = GameLogic.getNewGame();
      //this method below will also throw an IOException
      //if we cannot update the game data. At this point
      //the application is considered unrecoverable
      storage.updateGameData(initialState);
    }

    IUserInterfaceContract.EventListener uiLogic = new ControlLogic(storage, userInterface);
    userInterface.setListener(uiLogic);
    userInterface.updateBoard(initialState);
  }

}
