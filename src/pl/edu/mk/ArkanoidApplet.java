package pl.edu.mk;


import javax.swing.JApplet;

public class ArkanoidApplet extends JApplet {

	private static final long serialVersionUID = -7767201603332164559L;

public void init() {
	   

       setSize(800,600);
       ArkanoidGame ag=new ArkanoidGame();
       add(ag);
   

   }
}