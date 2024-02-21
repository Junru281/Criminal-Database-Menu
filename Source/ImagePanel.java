/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author andrew.abel, Junru
 */
public class ImagePanel extends JPanel {

    private final Image myImage;
    private final int myX, myY;
    private final int myWidth, myHeight;
    private final Person id;
    
    public ImagePanel(Person dispID,
                        int myX, 
                        int myY, 
                        int myWidth, 
                        int myHeight) {
        
        // Constructor for image panel
        this.id = dispID;
        this.myX = myX;
        this.myY = myY;
        this.myWidth = myWidth;
        this.myHeight = myHeight;
        this.myImage = dispID.getPhoto();
        int r = (int)(Math.random()* 255 +1);
        int g = (int)(Math.random()* 255 +1);
        int b = (int)(Math.random()* 255 +1);
        setBackground(new Color(r,g,b));
        System.out.println(r+" " +g +" " + b +" ");
       
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        
        int gap = 10;
        
        // Draw the wanted poster
        // the title of department
        Font depar = new Font("DIN Condensed", Font.BOLD, 35);
        FontMetrics metrics1 = g.getFontMetrics(depar); // get the fontMetrics
        int x1 = myWidth/2 - metrics1.stringWidth("POLICE DEPARTMENT")/2;
        int y1 = metrics1.getHeight();
        g.setFont(depar);
        g.setColor(Color.BLACK);
        g.drawString("POLICE DEPARTMENT", x1, y1);
        
        // city of liberty
        Font CoL = new Font("DIN Condensed", Font.PLAIN, 25);
        FontMetrics metricsCoL = g.getFontMetrics(CoL); // get the fontMetrics
        int xCoL = myWidth/2 - metricsCoL.stringWidth("CITY OF LIBERTY")/2;
        int yCoL = y1 + metricsCoL.getHeight();
        g.setFont(CoL);
        g.setColor(Color.BLACK);
        g.drawString("CITY OF LIBERTY", xCoL, yCoL);
        
        g.setColor(Color.BLACK);
        g.drawLine(0, yCoL+gap, 900, yCoL+gap);
        
        // two straight line
        int xline1 = 3*myWidth/10;
        g.setColor(Color.BLACK);
        g.drawLine(xline1, 0, xline1 , yCoL+gap);
        
        int xline2 = myWidth - 3*myWidth/10;
        g.setColor(Color.BLACK);
        g.drawLine(xline2, 0, xline2 , yCoL+gap);
        
        
        // Oinfo left
        Font unit = new Font("DIN Condensed", Font.PLAIN, 20);
        FontMetrics metricsunit = g.getFontMetrics(unit); // get the fontMetrics
        int xunit1 = xline1/2 - metricsunit.stringWidth("DETECTIVE BUREAU")/2;
        int xunit2 = xline1/2 - metricsunit.stringWidth("ARTIST UNIT")/2;
        int yunit = y1;
        g.setFont(unit);
        g.setColor(Color.BLACK);
        g.drawString("DETECTIVE BUREAU", xunit1, yunit);
        g.drawString("ARTIST UNIT", xunit2, yCoL);
        
        // Oinfo right
        int xunit3 = (xline2 + myWidth)/2 - metricsunit.stringWidth("PLEASE POST IN A")/2;
        int xunit4 = (xline2 + myWidth)/2 - metricsunit.stringWidth("CONSPICUOUS PLACE")/2;
        g.drawString("PLEASE POST IN A", xunit3, yunit);
        g.drawString("CONSPICUOUS PLACE", xunit4, yCoL);
        

        // the title wanted
        String text = "WANTED";
        Font wanted = new Font("Phosphate", Font.BOLD, 80);
        FontMetrics metricsWanted = g.getFontMetrics(wanted); // get the fontMetrics
        int x2 = myWidth/2 - metricsWanted.stringWidth(text)/2;
        int y2 = metricsWanted.getHeight()+yCoL- 2*gap ;
        g.setFont(wanted);
        g.setColor(Color.red);
        g.drawString(text, x2, y2);

        // the crime
        Font crime = new Font("Chalkduster", Font.BOLD, 40);
        FontMetrics metrics3 = g.getFontMetrics(crime); // get the fontMetrics
        int x3 = myWidth/2 - metrics3.stringWidth(id.getCrimes())/2;
        int y3 = metrics3.getHeight() + y2 ;
        g.setFont(crime);
        g.setColor(Color.red);
        g.drawString(id.getCrimes(), x3, y3);
       
        int x33 = metrics3.stringWidth(id.getCrimes());
        g.setColor(Color.black);
        g.drawRoundRect(x3, y2 + gap, x33, 50, 20, 20);
        
        // image
        int imWidth = 275;
        int imHeight = 275;
        g.drawImage(myImage, myWidth/2-imWidth/2, y3+gap*2, imWidth, imHeight, this);    

        // Id message
        Font ID = new Font("Krungthep", Font.ITALIC, 30);
        FontMetrics metrics4 = g.getFontMetrics(ID); // get the fontMetrics
        int xID = myWidth/2- metrics4.stringWidth("ID: "+id.getIdCode())/2;
        int yID = y3 + gap*2 +imHeight + metrics4.getHeight();
        g.setFont(ID);
        g.setColor(Color.BLUE);
        g.drawString("ID: "+id.getIdCode(), xID, yID);
        
        // the table
        int edge = 100;int width = 200; int height = 50;
        int y4 = yID + gap*2;
        for(int i = edge; i <= myWidth - edge - width; i= i+ width){
            for (int j = y4; j< y4+ 2*height; j = j + height){
                g.setColor(Color.BLACK);
                g.drawRect(i, j, width, height);
            }
        }
        
        //message in the table---NickName
        Font caption = new Font("Krungthep", Font.ITALIC, 10);
        FontMetrics metrics5 = g.getFontMetrics(caption); // get the fontMetrics
        int xcaptionNN = edge;
        int ycaptionNN = y4 + gap;
        g.setFont(caption);
        g.setColor(Color.BLUE);
        g.drawString("Nick Name:", xcaptionNN, ycaptionNN);
        
        Font tb = new Font("Krungthep", Font.ITALIC, 25);
        FontMetrics metrics6 = g.getFontMetrics(tb); // get the fontMetrics
        int xNN = edge + width/2 -metrics6.stringWidth(id.getNickname())/2;
        int yNN = y4 + 3*height/4;
        g.setFont(tb);
        g.setColor(Color.BLUE);
        g.drawString(id.getNickname(), xNN, yNN);

        // FirstName
        int xcaptionFN = edge + width;
        int ycaptionFN = y4 + gap;
        g.setFont(caption);
        g.setColor(Color.BLUE);
        g.drawString("First Name:", xcaptionFN, ycaptionFN);
        
        int xFN = edge + 3*width/2 - metrics6.stringWidth(id.getFirstName())/2;
        int yFN = y4 + 3*height/4;
        g.setFont(tb);
        g.setColor(Color.BLUE);
        g.drawString(id.getFirstName(), xFN, yFN);
        
        // lastName
        int xcaptionLN = xcaptionFN + width;
        int ycaptionLN = y4 + gap;
        g.setFont(caption);
        g.setColor(Color.BLUE);
        g.drawString("Family Name:", xcaptionLN, ycaptionLN);
        
        int xLN = edge + 5*width/2 - metrics6.stringWidth(id.getFamilyName())/2;
        int yLN = y4 + 3*height/4;
        g.setFont(tb);
        g.setColor(Color.BLUE);
        g.drawString(id.getFamilyName(), xLN, yLN);
        
        // Age
        int xcaptionAG = edge;
        int ycaptionAG = y4 + height + gap;
        g.setFont(caption);
        g.setColor(Color.BLUE);
        g.drawString("Age:", xcaptionAG, ycaptionAG);
        
        int xAG = edge + width/2 -metrics6.stringWidth(id.getAgeinYears()+"")/2;
        int yAG = y4 + height + 3*height/4;
        g.setFont(tb);
        g.setColor(Color.BLUE);
        g.drawString(id.getAgeinYears()+"", xAG, yAG);
        
        // nationality
        int xcaptionNA = edge + width;
        int ycaptionNA = y4 + height + gap;
        g.setFont(caption);
        g.setColor(Color.BLUE);
        g.drawString("Nationality:", xcaptionNA, ycaptionNA);
        
        int xNA = edge + 3*width/2 - metrics6.stringWidth(id.getNationality())/2;
        int yNA = y4 + height +3*height/4;
        g.setFont(tb);
        g.setColor(Color.BLUE);
        g.drawString(id.getNationality(), xNA, yNA);
        
        // reward
        int xcaptionRW = xcaptionNA + width;
        int ycaptionRW = y4 + height + gap;
        g.setFont(caption);
        g.setColor(Color.BLUE);
        g.drawString("Reward: ", xcaptionRW, ycaptionRW);
        
        int xRW = edge + 5*width/2 - metrics6.stringWidth(id.getReward()+"")/2;
        int yRW = y4 + height +3*height/4;
        g.setFont(tb);
        g.setColor(Color.BLUE);
        g.drawString("$ "+id.getReward(), xRW, yRW);
        
        // contact
        String hl = "LIBERTY CITY POLICE DEPARTMENT";
        String textline1 = "Notify the " + hl +" with any information regarding the above subject.";
        Font contactWD = new Font("Copperplate", Font.PLAIN, 30);
        Font contactHL = new Font("Copperplate", Font.BOLD, 30);
        FontMetrics metrics7 = g.getFontMetrics(contactWD); // get the fontMetrics
        FontMetrics metrics8 = g.getFontMetrics(contactHL); // get the fontMetrics
        int xWD1 = myWidth/2- metrics4.stringWidth(textline1)/2;
        int yWD1 = yRW + metrics7.getHeight();
        g.setFont(contactWD);
        g.setColor(Color.BLACK);
        g.drawString(textline1, xWD1, yWD1);
        
    }
    
    // Getters, do not need to change
     @Override
    public int getX() {
        return myX;
    }

    @Override
    public int getY() {
        return myY;
    }

    @Override
    public int getWidth() {
        return myWidth;
    }

    @Override
    public int getHeight() {
        return myHeight;

    }
}
