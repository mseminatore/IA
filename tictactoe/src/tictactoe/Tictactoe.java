/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.*;

/**
 *
 * @author matts_000
 */
public class Tictactoe extends javax.swing.JFrame {

    private BoardVisuals boardPanel;
    private MenuPanel menuPanel;
    private boolean onePlayer;
    private boolean won;

    Board[] globalBoard;
    public static int currentBoard;
    private char player;
    public static final char PLAYERX = 'X';
    public static final char PLAYERO = 'O';
    public static final char EMPTY = ' ';

    
    //is the game won
    public boolean getWon() {
        return won;
    }

    //set the game won
    public void setWon(boolean won) {
        this.won = won;
    }

    //set the next board to be played
    public void setCurrentBoard(int board) {
        currentBoard = board;
    }

    //find the current board
    public int getCurrentBoard() {
        return currentBoard;
    }

    //either x or o
    public void setPlayer(char player) {
        this.player = player;
    }

    public char getPlayer() {
        return player;
    }

    //essentially enables computer play
    public void setOnePlayer(boolean onePlayer) {
        this.onePlayer = onePlayer;
    }

    //is it one player?
    public boolean getOnePlayer() {
        return onePlayer;
    }

    //make all boards blank
    public void resetAllBoards() {
        for (int i = 0; i < 9; i++) {
            globalBoard[i].reset();
        }
    }

    //set up all objects and visuals
    public Tictactoe() {
        // Set default close operation
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);

        globalBoard = new Board[9];

        boardPanel = new BoardVisuals(this);
        menuPanel = new MenuPanel(this);

        this.setSize(600, 600);
        this.setResizable(false);

        showMenu();

        this.add(boardPanel);
        this.add(menuPanel);

        pack();
        this.setVisible(true);

        for (int boardIndex = 0; boardIndex < 9; boardIndex++) {
            globalBoard[boardIndex] = new Board(this);
        }

        boardPanel.setButtonsColorWhite();

    }

    //
    // check whether move is legal
    //check whether board is won 
    //next players turn
    //
    //
    //
    public boolean buttonClicked(int board, int boardSpace) {
//        System.out.println(board);
//        System.out.println(boardSpace);

        if (globalBoard[board].validMove(boardSpace) == true) {
            makeMove(board, boardSpace);

            return true;
        } else {
//            System.out.println("Invalid move");
            return false;
        }
    }

    //upper level logic for AI
    //win - block - random
    public void computerMove(int board) {

        if (globalBoard[board].completeOToWin() == true) {
            switchPlayer();
            return;
        }
        //System.out.println("no win");
        if (globalBoard[board].blockX() == true) {
            switchPlayer();
            return;
        }
        //System.out.println("no win or block found");
        int space = globalBoard[board].findRandomSpace();
        makeMove(board, space);
        switchPlayer();

    }

    
    //process for making move and checking for a game win
    public void makeMove(int board, int boardSpace) {
        globalBoard[board].boardState[boardSpace] = player;
        boardPanel.setButtonText(board, boardSpace);
        boardPanel.setButtonColor(board, boardSpace, player);
        if (globalBoard[board].checkBoardWin() != EMPTY) {
            boardPanel.setBoardColor(board, globalBoard[board].checkBoardWin());
            boardPanel.setBoardButtonOutline(board, globalBoard[board].checkBoardWin());

            if (checkWin() != EMPTY) {
                endGame();
                return;
            }
        }

        
        //prepare for the next turn by disabling all boards and enabling legal ones
        disableAllBoards();
        currentBoard = boardSpace;
        if (globalBoard[boardSpace].checkBoardWin() == EMPTY) {
            enableBoard(boardSpace);

        } else {
            enableAllBoards();
        }

    }

    //the game is won, so disable all moves and show the menu button
    public void endGame() {
        setWon(true);
        //System.out.println("Congrats" + checkWin());
        boardPanel.setButtonsColorWinner(checkWin());
        boardPanel.setMenuButtonVisible(true);
        boardPanel.resetButtonOutline();
        disableAllBoards();
    }

    //show the menu panel
    public void showMenu() {
        menuPanel.setVisible(true);
        boardPanel.setVisible(false);
        setWon(false);
        player = PLAYERX;
    }

    //show the board panel
    public void showBoard() {
        boardPanel.setVisible(true);
        menuPanel.setVisible(false);
        boardPanel.setMenuButtonVisible(false);
    }

    //used during text version, prints board
    public void printTictactoe() {
        for (int boardIndex = 0; boardIndex < 9; boardIndex++) {
            globalBoard[boardIndex].print();
        }
    }

    //hopefully self explanatory
    public void switchPlayer() {
        if (player == PLAYERX) {
            player = PLAYERO;

            return;
        }

        if (player == PLAYERO) {
            player = PLAYERX;
            return;
        }

    }

    public void enableAllBoards() {
        for (int boardIndex = 0; boardIndex < 9; boardIndex++) {
//            if (globalBoard[boardIndex].finished == false) {
            globalBoard[boardIndex].enabled = true;
//            }
            boardPanel.setAllEnabledOutline();
        }
    }

    public void disableAllBoards() {
        for (int boardIndex = 0; boardIndex < 9; boardIndex++) {
            globalBoard[boardIndex].enabled = false;

        }
        boardPanel.resetButtonOutline();
    }

    //enables the board at the space last played, following the rules of the game
    public void enableBoard(int boardIndex) {
        globalBoard[boardIndex].enabled = true;
        boardPanel.setEnabledOutline(boardIndex);

    }

    
    //are there 3 boards in a line that are won by the same player?
    //did someone win the game?
    //if so, return winner
    public char checkWin() {
        char globalWin = EMPTY;
        if (horizontalGlobalWin() == PLAYERX) {
            globalWin = PLAYERX;
        }
        if (horizontalGlobalWin() == PLAYERO) {
            globalWin = PLAYERO;
        }

        if (verticalGlobalWin() == PLAYERX) {
            globalWin = PLAYERX;
        }
        if (verticalGlobalWin() == PLAYERO) {
            globalWin = PLAYERO;
        }

        if (diagGlobalWin() == PLAYERX) {
            globalWin = PLAYERX;
        }
        if (diagGlobalWin() == PLAYERO) {
            globalWin = PLAYERO;
        }
        return globalWin;
    }

    //checks for three boards in a column won by same player
    public char verticalGlobalWin() {
        char vertGlobalWin = EMPTY;
        for (int col = 0; col < 3; col++) {
            if (globalBoard[col].checkBoardWin() == PLAYERX && globalBoard[col + 3].checkBoardWin() == PLAYERX && globalBoard[col + 6].checkBoardWin() == PLAYERX) {
                vertGlobalWin = PLAYERX;
            }
        }
        for (int col = 0; col < 3; col++) {
            if (globalBoard[col].checkBoardWin() == PLAYERO && globalBoard[col + 3].checkBoardWin() == PLAYERO && globalBoard[col + 6].checkBoardWin() == PLAYERO) {
                vertGlobalWin = PLAYERO;
            }
        }
        return vertGlobalWin;
    }

    //checks for three boards in a row won by same player
    public char horizontalGlobalWin() {
        char horGlobalWin = EMPTY;
        for (int row = 0; row < 3; row++) {
            if (globalBoard[3 * row].checkBoardWin() == PLAYERX && globalBoard[3 * row + 1].checkBoardWin() == PLAYERX && globalBoard[3 * row + 2].checkBoardWin() == PLAYERX) {
                horGlobalWin = PLAYERX;
            }
        }
        for (int row = 0; row < 3; row++) {
            if (globalBoard[3 * row].checkBoardWin() == PLAYERO && globalBoard[3 * row + 1].checkBoardWin() == PLAYERO && globalBoard[3 * row + 2].checkBoardWin() == PLAYERO) {
                horGlobalWin = PLAYERO;
            }
        }
        return horGlobalWin;
    }

    //checks for three boards in a diagonal won by same player
    public char diagGlobalWin() {
        char diagGlobalWin = EMPTY;
        if (globalBoard[0].checkBoardWin() == PLAYERX && globalBoard[4].checkBoardWin() == PLAYERX && globalBoard[8].checkBoardWin() == PLAYERX) {
            diagGlobalWin = PLAYERX;
        }
        if (globalBoard[0].checkBoardWin() == PLAYERO && globalBoard[4].checkBoardWin() == PLAYERO && globalBoard[8].checkBoardWin() == PLAYERO) {
            diagGlobalWin = PLAYERO;
        }
        if (globalBoard[2].checkBoardWin() == PLAYERX && globalBoard[4].checkBoardWin() == PLAYERX && globalBoard[6].checkBoardWin() == PLAYERX) {
            diagGlobalWin = PLAYERX;
        }
        if (globalBoard[2].checkBoardWin() == PLAYERO && globalBoard[4].checkBoardWin() == PLAYERO && globalBoard[6].checkBoardWin() == PLAYERO) {
            diagGlobalWin = PLAYERO;
        }

        return diagGlobalWin;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Tictactoe tictactoe = new Tictactoe();

    }

}
