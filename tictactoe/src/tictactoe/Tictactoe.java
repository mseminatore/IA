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
public class Tictactoe {
    Board[] globalBoard;
    int boardIndex;
    char player;
    
    public Tictactoe(){
        globalBoard = new Board[9];
        for (int boardIndex = 0; boardIndex < 9; boardIndex++) {
            
            globalBoard[boardIndex] = new Board();
            
        }
    }
    
    public void start(){
        boolean done = false;
        System.out.println("Welcome to Ultimate Tictactoe!");
        player ='x';
        printTictactoe();
        while(done == false){
            move();
            printTictactoe();
            if(checkWin()== 'x'){
                done = true;
                System.out.println("Congrats x!");
            }
            if(checkWin()== 'o'){
                done = true;
                System.out.println("Congrats o!");
            }
            
            switchPlayer();
            
        }
    }
    
    
    public void printTictactoe(){
        for (int  boardIndex = 0; boardIndex < 9; boardIndex++) {
            globalBoard[boardIndex].print();
        }
    }
    
    public void switchPlayer(){
        if(player == 'x'){
            player = 'o';
            return;
        }
        
        if(player =='o'){
            player = 'x';
            return;
        }
    }
    
    public void move(){
        System.out.println("Where would you like to move?");
        Scanner sc = new Scanner(System.in);
        int localBoard = sc.nextInt();
        int localBoardSpace = sc.nextInt();
        if(globalBoard[localBoard].boardState[localBoardSpace] == ' '){
            globalBoard[localBoard].boardState[localBoardSpace] = player;  
        }
        else{
            System.out.println("Invalid move");
        }
        
    }
    
    public char checkWin(){
        char globalWin = ' ';
        if(horizontalGlobalWin() == 'x')
            globalWin = 'x';
        if(horizontalGlobalWin() == 'o')
            globalWin = 'o';
        
        if(verticalGlobalWin() == 'x')
            globalWin = 'x';
        if(verticalGlobalWin() == 'o')
            globalWin = 'o';
        
        if(diagGlobalWin() == 'x')
            globalWin = 'x';
        if(diagGlobalWin() == 'o')
            globalWin = 'o';
        return globalWin;
    }
    
    public char verticalGlobalWin(){
        char vertGlobalWin = ' ';
        for (int col = 0; col < 3; col++) {
            if(globalBoard[col].checkBoardWin() == 'x' && globalBoard[col+3].checkBoardWin() == 'x' && globalBoard[col+6].checkBoardWin() =='x'){
                vertGlobalWin='x';   
            }
        }
        for (int col = 0; col < 3; col++) {
            if(globalBoard[col].checkBoardWin()=='o' && globalBoard[col+3].checkBoardWin()=='o' && globalBoard[col+6].checkBoardWin()=='o'){
                vertGlobalWin='o';   
            } 
        }
        return vertGlobalWin;
    }
    
    public char horizontalGlobalWin(){
        char horGlobalWin = ' ';
        for (int row = 0; row < 3; row++) {
            if(globalBoard[3*row].checkBoardWin() == 'x' && globalBoard[3*row+1].checkBoardWin() == 'x' && globalBoard[3*row+2].checkBoardWin() =='x'){
                horGlobalWin='x';   
            }
        }
        for (int row = 0; row < 3; row++) {
            if(globalBoard[3*row].checkBoardWin()=='o' && globalBoard[3*row+1].checkBoardWin()=='o' && globalBoard[3*row+2].checkBoardWin()=='o'){
                horGlobalWin='o';   
            } 
        }
        return horGlobalWin;
    }
    
    public char diagGlobalWin(){
        char diagGlobalWin = ' ';
        if(globalBoard[0].checkBoardWin() =='x' && globalBoard[4].checkBoardWin() =='x'&& globalBoard[8].checkBoardWin() =='x'){
            diagGlobalWin = 'x';
        }
        if(globalBoard[0].checkBoardWin() =='o' && globalBoard[4].checkBoardWin() =='o'&& globalBoard[8].checkBoardWin() =='o'){
            diagGlobalWin = 'o';
        }
        if(globalBoard[2].checkBoardWin() =='x' && globalBoard[4].checkBoardWin() =='x'&& globalBoard[6].checkBoardWin() =='x'){
            diagGlobalWin = 'x';
        }
        if(globalBoard[2].checkBoardWin() =='o' && globalBoard[4].checkBoardWin() =='o'&& globalBoard[6].checkBoardWin() =='o'){
            diagGlobalWin = 'o';
        }
        
        return diagGlobalWin;
        
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Tictactoe tictactoe = new Tictactoe();
        tictactoe.start();
        
    }
    
}
