import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class World extends JPanel
{
    private Cell[][] world;
    volatile private boolean mouseDown = false;
    private Random rand = new Random(); 
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseDown = true;
            initThread();
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseDown = false;
        }
    }

    volatile private boolean isRunning = false;
    private synchronized boolean checkAndMark() {
        if (isRunning) return false;
        isRunning = true;
        return true;
    }

    private void initThread() {
        if (checkAndMark()) {
            new Thread() {
                public void run() {
                    do {
                        //do something
                    } while (mouseDown);
                    isRunning = false;
                }
            }.start();
        }
    }

    public World(int size)
    {
        world = new Cell[size][size];
        setLayout(new GridLayout(size,size));
        for(int r = 0; r<world.length;r++){
            for(int c=0; c<world[r].length;c++){
                world[r][c] = new Cell();
                add(world[r][c]);
                world[r][c].addActionListener(
                    new ActionListener ()

                    {
                        @Override
                        public void actionPerformed(ActionEvent e){
                            Cell btn = (Cell)e.getSource();
                            btn.changeState();

                        }
                    }
                );

            }
        }
    }

    /**
     * Rules of Conway's Game of Life
     * 
     * If a live cell has fewer than 2 or more than 3 neighbours it dies.
     * If a dead cell had exactly 3 neighbours, it comes to life.
     * 
     * 
     */
    public void step(){
        //System.out.println("step");
        //display();
        Cell[][] copy = new Cell[world.length][world.length];
        for(int r = 0; r<world.length;r++){
            for(int c=0; c<world[r].length;c++){
                copy [r][c] = new Cell();
                copy[r][c].setState(world[r][c].isAlive());
            }
        }

        for(int r = 0; r<world.length;r++){
            for(int c=0; c<world[r].length;c++){ 
                int n =  getNumberOfLiveNeighbours(r,c);
                if(world[r][c].isAlive()){
                    if(n<2||n>3){
                        copy[r][c].setState(false);
                    }
                }else{
                    if(n==3){
                        copy[r][c].setState(true);
                    }
                }
            }
        }
        for(int r = 0; r<world.length;r++){
            for(int c=0; c<world[r].length;c++){
                world[r][c].setState(copy[r][c].isAlive());
            }
        }
    }

    private int getNumberOfLiveNeighbours(int row, int column){
        int total = 0;
        for(int r=row-1;r<=row+1;r++){
            if(r>=0&&r<world.length){
                for(int c=column-1;c<=column+1;c++){
                    if(c>=0 &&c<world[r].length){
                        if( !(r==row && c==column)){
                            if(world[r][c].isAlive()){
                                total++;
                            }
                        }
                    }
                }
            }
        }
        return total;
    }

    public void display(){
        System.out.println("********************************");
        for(int r = 0; r<world.length;r++){
            for(int c=0; c<world[r].length;c++){
                System.out.print(getNumberOfLiveNeighbours(r,c)+" ");
            }
            System.out.println();
        }
        System.out.println("********************************");

    }

    public void pattern(){
        int r = rand.nextInt(3);
        reset();
        if(r==0){

            for(int row = 0; row<world.length;row++){
                for(int col=0; col<world[r].length;col++){
                    if(row%2==0&&col%2!=0){
                        world[row][col].changeState();
                    }
                    if(col%2==0&&row%2!=0){
                        world[row][col].changeState();
                    }
                }
            }
        }else if (r==1){
            for(int row = 0; row<world.length;row++){
                for(int col=0; col<world[r].length;col++){
                    if((row==4&&col==4)||(row==5&&col==5)||(row==6&&col==6)||
                    (row==7&&col==7)||(row==8&&col==8)||(row==4&&col==8)||
                    (row==5&&col==7)||(row==7&&col==5)||(row==8&&col==4)||
                    (row==5&&col==9)||(row==6&&col==10)||(row==7&&col==11)||
                    (row==8&&col==12)||(row==4&&col==12)||(row==5&&col==11)||
                    (row==7&&col==9)||(row==4&&col==17)||(row==5&&col==18)||(row==6&&col==19)||
                    (row==7&&col==20)||(row==8&&col==21)||(row==4&&col==21)||
                    (row==5&&col==20)||(row==7&&col==18)||(row==8&&col==17)||
                    (row==5&&col==22)||(row==6&&col==23)||(row==7&&col==24)||
                    (row==8&&col==25)||(row==4&&col==25)||(row==5&&col==24)||
                    (row==7&&col==22)||(row==21&&col==4)||(row==22&&col==5)||(row==23&&col==6)||
                    (row==24&&col==7)||(row==25&&col==8)||(row==21&&col==8)||
                    (row==22&&col==7)||(row==24&&col==5)||(row==25&&col==4)||
                    (row==22&&col==9)||(row==23&&col==10)||(row==24&&col==11)||
                    (row==25&&col==12)||(row==21&&col==12)||(row==22&&col==11)||
                    (row==24&&col==9)||(row==21&&col==17)||(row==22&&col==18)||(row==23&&col==19)||
                    (row==24&&col==20)||(row==25&&col==21)||(row==21&&col==21)||
                    (row==22&&col==20)||(row==24&&col==18)||(row==25&&col==17)||
                    (row==22&&col==22)||(row==23&&col==23)||(row==24&&col==24)||
                    (row==25&&col==25)||(row==21&&col==25)||(row==22&&col==24)||
                    (row==24&&col==22)){
                        world[row][col].changeState();
                    }
                }

            }
        }else if (r==2){
            for(int row = 0; row<world.length;row++){
                for(int col=0; col<world[r].length;col++){
                    if((row==0&&col==0)||(row==1&&col==1)||(row==2&&col==2)||(row==3&&col==3)||(row==4&&col==4)||(row==5&&col==5)||(row==6&&col==6)||
                    (row==7&&col==7)||(row==8&&col==8)||(row==9&&col==9)||
                    (row==10&&col==10)||(row==11&&col==11)||(row==12&&col==12)||
                    (row==13&&col==13)||(row==14&&col==14)||(row==15&&col==15)||
                    (row==16&&col==16)||(row==17&&col==17)||(row==18&&col==18)||
                    (row==19&&col==19)||(row==20&&col==20)||(row==21&&col==21)||
                    (row==22&&col==22)||(row==23&&col==23)||(row==24&&col==24)||
                    (row==25&&col==25)||(row==26&&col==26)||(row==27&&col==27)||
                    (row==28&&col==28)||(row==29&&col==29)||(row==0&&col==29)||
                    (row==1&&col==28)||(row==2&&col==27)||(row==3&&col==26)||
                    (row==4&&col==25)||(row==5&&col==24)||(row==6&&col==23)||
                    (row==7&&col==22)||(row==8&&col==21)||(row==9&&col==20)||
                    (row==10&&col==19)||(row==11&&col==18)||(row==12&&col==17)||
                    (row==13&&col==16)||(row==14&&col==15)||(row==15&&col==14)||
                    (row==16&&col==13)||(row==17&&col==12)||(row==18&&col==11)||
                    (row==19&&col==10)||(row==20&&col==9)||(row==21&&col==8)||
                    (row==22&&col==7)||(row==23&&col==6)||(row==24&&col==5)||
                    (row==25&&col==4)||(row==26&&col==3)||(row==27&&col==2)||
                    (row==28&&col==1)||(row==29&&col==0)){
                        world[row][col].changeState();
                    }
                    
                }
            }
        }
    }

    public void reset(){
        for(int r = 0; r<world.length;r++){
            for(int c=0; c<world[r].length;c++){
                world[r][c].setState(false);
            }
        }
    }
}
