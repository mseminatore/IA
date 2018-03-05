/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Color;

/**
 *
 * @author semimat19
 */
public class BoardVisuals extends javax.swing.JPanel {
    private Tictactoe parent;
    MyButton buttons[];
    /**
     * Creates new form BoardVisuals
     */
    public BoardVisuals(Tictactoe parent) {
        initComponents();
        this.parent = parent;
        this.setSize(800, 800);
        buttons = new MyButton[81];
        this.setBackground(Color.gray);
        

        // add all the buttons!
//        int index = 0;
        int boardNum;
        int boardSpace;
        
        for (int boardRow = 0; boardRow < 3 ; boardRow++) {
            for (int boardCol = 0; boardCol < 3; boardCol++) {
                for (int rows = 0; rows < 3; rows++) {
                    for (int cols = 0; cols < 3; cols++) {
                        boardNum = 3 * boardRow + boardCol;
                        boardSpace = 3 * rows + cols;
                        buttons[boardNum * 9 + boardSpace] = new MyButton(boardNum, boardSpace);
                        buttons[boardNum * 9 + boardSpace].setLocation(130 + boardCol * 175 + 55 * cols, 130 + boardRow * 175 + 55 * rows);
//                        MyButton btn = new MyButton(boardNum, boardSpace);
                        this.add(buttons[boardNum * 9 + boardSpace]);
                        buttons[boardNum * 9 + boardSpace].setText(" ");
                        buttons[boardNum * 9 + boardSpace].setSize(50, 50);
                        

                        // TODO - set button position
//                        btn.setLocation(130 + boardCol * 175 + 55 * cols, 130 + boardRow * 175 + 55 * rows);
                        buttons[boardNum * 9 + boardSpace].addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                myButtonActionPerformed(evt);
                            }
                        });
                        buttons[boardNum * 9 + boardSpace].setVisible(true);
                    }
                }            
            }
        }
    }

    public void myButtonActionPerformed(java.awt.event.ActionEvent evt) {
        MyButton btn = (MyButton)evt.getSource();
        if(parent.buttonClicked(btn.getBoard(), btn.getBoardSpace()) == true){
            btn.setText(Character.toString(parent.getPlayer()));
            btn.setEnabled(false);
        }
    }
    
    public void setButtonsColorWhite(){
        for (int i = 0; i < 81; i++) {
            buttons[i].setBackground(Color.white);
        }
    }
    
    public void setButtonsColorWinner(char player){
        if(player == 'X'){
            for (int i = 0; i < 81; i++) {
                buttons[i].setBackground(Color.DARK_GRAY);
            }
        }
        if(player == 'O'){
            for (int i = 0; i < 81; i++) {
                buttons[i].setBackground(Color.LIGHT_GRAY);
            }
        }
    }
    
    public void setBoardColor(int board, char player){
        if(player == 'X'){
            for (int i = 9 * board; i < 9 * board + 9; i++) {
                buttons[i].setBackground(Color.DARK_GRAY);
            }
        }
        if(player == 'O'){
            for (int i = 9 * board; i < 9 * board + 9; i++) {
                buttons[i].setBackground(Color.LIGHT_GRAY);
            }
        }
    }
    
    public void setButtonColor(int board, int boardSpace, char player){
        if(player == 'X'){
            buttons[board * 9 + boardSpace].setBackground(Color.DARK_GRAY);
        }
        if(player == 'O'){
            buttons[board * 9 + boardSpace].setBackground(Color.LIGHT_GRAY);
        }
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
