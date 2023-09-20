module com.kurucmichal.sudoku {
  requires javafx.controls;
  requires javafx.fxml;

  opens com.kurucmichal.sudoku to javafx.fxml;
  exports com.kurucmichal.sudoku;
}