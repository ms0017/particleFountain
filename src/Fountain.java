//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Project 2: Fountain
// Files:           P2ParticleFounatin.jar
// Course:          CS 300, Spring '19
//
// Author:          Sheriff Isaaka
// Email:           issaka@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:   Don Catarello (name of your pair programming partner)
// Partner Email:   catarello@wisc.edu(email address of your programming partner)
// Partner Lecturer's Name: Mouna Kacen(name of your partner's lecturer)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X__ Write-up states that pair programming is allowed for this assignment.
//   _X__ We have both read and understand the course Pair Programming Policy.
//   _X__ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


import java.util.Random;

public class Fountain {
 /* 
  * creating a number of static values which 
  * will be shared and used by most of the methods
  * below
  *
  */
  private static Particle[] particles;
  private static int positionX;
  private static int positionY;
  private static int startColor;
  private static int endColor;
  private static Random randGen = new Random();

  /*
   * @param: the maximum age a particle can be
   * This method removes particles after a specified age
   */
  private static void removeOldParticles(int maxAge) {
    //iterating through the array
    for (int i = 0; i < particles.length; i++) {
      if (particles[i] != null)
        //making sure particle isn't null
        if (particles[i].getAge() > maxAge) {
          particles[i] = null;
       //sets the specific index to null if it has
      // reached the maximum age
        }
    }

  }
  /*
   * @param posX: the mouse's x position
   * @param posY: the mouse's y position
   * This method checks the coordinates of the mouse
   */
  public static void mousePressed(int x, int y) {
  //updates the particles position with the position of the mouse
    positionX = x;
    positionY = y;
  }
  /*
   * @param key: the character passed/typed in
   * method checks for a screenshot command
   */
  public static void keyPressed(char key) {
    // checks is the character passed in is a 'p'
    if (key == 'p')
      // if character is a 'p', then take a screenshot
      Utility.save("screenshot.png");
  }
/*
 * @param: receives the index of the particle to update
 * this method updates all the circles with various specifications
 */
  private static void updateParticle(int index) {
    // receives the value passed and stores in an array
    Particle temp = particles[index];
    // setting and increasing the velocity of the particles
    temp.setVelocityY(temp.getVelocityY() + 0.3F);
    //creating a circle with the specifications below
    Utility.circle(temp.getPositionX(), temp.getPositionY(), temp.getSize());
    //setting the positions of both the y and x coordinates
    temp.setPositionX(temp.getPositionX() + temp.getVelocityX());
    temp.setPositionY(temp.getPositionY() + temp.getVelocityY());
    // fills the circle with the specified colors
    Utility.fill(temp.getColor(), temp.getTransparency());
    //returns the exact mixture of color to be used
    Utility.color(23, 141, 235);
    //increases the age of the particle every time to enable easy removal
    temp.setAge(temp.getAge() + 1);
  }
  /*
   * @param: the number of new particles to be created
   */
  private static void createNewParticles(int newParticles) {
    // a for loop to to iterate through my array

    int count = 0;
    for (int i = 0; i < particles.length; i++) {
      if (count < newParticles && particles[i] == null) {
        count++;
    //if the for loop is null then replace it with a new particle array
        particles[i] = new Particle();
     //updating the x and y positions of the new arrays
     //also updating the size, color, velocities, age, and transparency
        particles[i].setPositionX(Fountain.positionX + randGen.nextFloat()*6 - 3);
        particles[i].setPositionY(Fountain.positionY + randGen.nextFloat()*6 - 3);
        particles[i].setSize(randGen.nextFloat()* 7 + 4);
        particles[i].setColor(Utility.lerpColor(startColor, endColor, randGen.nextFloat()));
        particles[i].setVelocityX(randGen.nextFloat()*2 -1);
        particles[i].setVelocityY(randGen.nextFloat()*-5 -5);
        particles[i].setAge(randGen.nextInt(41));
        particles[i].setTransparency(randGen.nextInt(96)+ 32);
        Utility.fill(particles[i].getColor(), particles[i].getTransparency());
        Utility.circle(particles[i].getPositionX(), particles[i].getPositionY(), particles[i].getSize());
       
      }
    }
  }
  /*
   * @param:
   * this method serves as the base for the program to run effectively
   */
  public static void setup() {
    //the first to be created with specificities
    Particle temp = new Particle(400, 300, 10, Utility.color(255, 255, 255));
    // assigning my array to a length of 800
    particles = new Particle[800];
    // particles[0] = temp;
    // setting both velocities to enable the gravitational effect
    temp.setVelocityX(-6);
    temp.setVelocityY(-6);
    //setting the position of particles
    positionX = 400;
    positionY = 300;
    //setting the start and end colors
    startColor = Utility.color(23, 141, 235);
    endColor = Utility.color(23, 200, 255);
  }
/*
 * @param: 
 * This method calls on other methods to make changes to
 * particles and also sets the background color
 */
  public static void update() {
    //calling for 10 particles to be run at each time
    createNewParticles(10);
    //sets the background color
    Utility.background(Utility.color(235, 213, 186));
    for (int i = 0; i < particles.length; i++) {
      //making sure array isn't null and calling the method
      //responsible for updating new particles
      if (particles[i] != null) {
        updateParticle(i);
      }
    }
    // calling the method responsible for removing aged particles
    removeOldParticles(80);
  }
  /*
   * @param: runs the entire program
   */
  public static void main(String[] args) {
    // calling on the program to run
    Utility.runApplication();
  }

}
