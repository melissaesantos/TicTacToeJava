
//this imports all classes and interfaces from AWT package which contains classes for
//creating windows,drawings,graphics, handline events, and managing things like buttons, etc.
import java.awt.*;

//handling events such as ' ActionListener and mouse Listener'
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    // making of the game board
    int boardwidth  = 600;
    int boardheight = 650;// 50px top panel to display whose turn it is and displays winner
    JFrame frame = new JFrame("Tic-Tac-Toe");
    //add panels for our text
    JLabel textlabel  = new JLabel();
    JPanel textPanel = new JPanel();
   

    //Creating the board
    JPanel boardPanel = new JPanel();

    //use a 2d array to create the tiles where we can place the x and o
    JButton[][] board = new JButton[3][3];

    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns =0;

    

    void checkWinner(){
        //horizontal check
        for(int i =0; i < 3;i++){
            //we can continue you here because you cant win if the first place is empty
            if(board[i][0].getText() == "")continue;

            if(board[i][0].getText() == board[i][1].getText() && board[i][1].getText()== board[i][2].getText()){
                for(int j =0; j<3;j++){
                    setWinner(board[i][j]);{

                    }
                }
                gameOver = true;
                return;
            }
           
        }
        //vertical check
        for(int c =0; c < 3; c++){
            if(board[0][c].getText() == "")continue;

            if(board[0][c].getText() == board[1][c].getText() && board[1][c].getText() == board[2][c].getText() ){
                for(int m =0;m<3;m++){
                    setWinner(board[m][c]);
                }
                gameOver=true;
                return;
            }

        }
        //diagonal check
        if(board[0][0].getText()!= "" &&
            board[0][0].getText()==board[1][1].getText() 
            && board[1][1].getText()== board[2][2].getText()){
            for(int i =0; i < 3;i++){
                setWinner(board[i][i]);
            }
            gameOver= true;
            return;
        }
        //anti-diagonally
        if(board[0][2].getText()!= "" &&
            board[0][2].getText()==board[1][1].getText() 
            && board[1][1].getText()== board[2][0].getText()){
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver= true;
            return;
        }
        if(turns == 9){
            for(int r =0; r <3; r++){
                for(int c =0; c < 3; c++){
                    setTie(board[r][c]);
                }
            }
            gameOver= true;
            return;
        }
        
    }
    void setWinner(JButton tile){
        tile.setForeground(Color.blue);
        textlabel.setText(currentPlayer+ " is the winner!WAHOO");

    }
    void setTie(JButton tile){
        tile.setForeground(Color.orange);
        tile.setBackground(Color.GRAY);
        textlabel.setText("TIE");
    }

    TicTacToe(){
        frame.setVisible(true);
        frame.setSize(boardwidth,boardheight);
        //opens window at the center of the screen
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        //when we press to exit the window our program terminates
        //closing out the game and ending it
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //creating the panels where it says TIC TAC TOE
        textlabel.setBackground(Color.PINK);
        //font color
        textlabel.setForeground(Color.white);
        textlabel.setFont(new Font("Times New Roman", Font.BOLD, 50));
        //centers the text
        textlabel.setHorizontalAlignment(JLabel.CENTER);
        textlabel.setText("Tic-Tac-Toe");
        textlabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textlabel);
        frame.add(textPanel,BorderLayout.NORTH);
        //makes the 3x3 board 
        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.PINK);
        //npow add it to the main frame
        frame.add(boardPanel);
        


        /// ADDING THE BUTTONS TO THE BOARD
        for(int i =0; i < 3;i++){
            for(int j =0 ; j < 3 ; j++){
                JButton tile = new JButton();
                board[i][j] = tile;
                boardPanel.add(tile);
                tile.setBackground(Color.PINK);
                tile.setForeground(Color.BLACK);
                tile.setFont(new Font("Times New Roman", Font.BOLD,120));
                tile.setFocusable(false);
                //now we need to add the event handler so the move only shows up when the player presses the button
                //adding action listener for each tile 
                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        if(gameOver) return;
                        //we need to know where this action is coming from
                        //ans: the buttons
                        JButton tile = (JButton) e.getSource();//note we know it is coming from a 
                        //button so we could cast it to be a button

                        //lets fix the over writing of the tile issue
                        if(tile.getText()== ""){
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if(!gameOver){
                                 //lets alternate
                                currentPlayer = currentPlayer == playerX ? playerO: playerX;    
                                //If currentPlayer is equal to playerX, then playerO is assigned to currentPlayer.
                                //If  currentPlayer is not equal to playerX, then playerX is assigned to currentPlayer
                                textlabel.setText(currentPlayer + "'s turn!");
                            }
                           
                        }
                    }        
                });  
            }
            
        }
        
   

    }
    void restartGame() {
        // Reset all tiles
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setText("");
                board[i][j].setForeground(Color.BLACK); // Reset text color
                board[i][j].setBackground(Color.PINK); // Reset background color
            }
        }
        // Reset game state variables
        currentPlayer = playerX;
        gameOver = false;
        turns = 0;
        textlabel.setText(currentPlayer+ "'s turn!"); // Reset text label
    }

    public static void main(String[] args) {
        // Create an instance of TicTacToe
        TicTacToe game = new TicTacToe();

        // Add restart button
        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.restartGame(); // Restart the game when the restart button is clicked
            }
        });

        // Create a panel for the restart button
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(restartButton);

        // Add components to the frame
        game.frame.add(buttonPanel, BorderLayout.SOUTH);
    }
    

}


