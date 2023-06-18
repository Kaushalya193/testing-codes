import java.awt.*;        // Using AWT container and component classes
import java.awt.event.*;  // Using AWT event classes and listener interfaces

// An AWT program inherits from the top-level container java.awt.Frame
public class AWTCounterExternal extends Frame {
   private Label lblCount;    // Declare a Label component
   private TextField tfCount; // Declare a TextField component
   private Button btnCount;   // Declare a Button component
   private int count = 0;     // Counter's value

   // Constructor to setup GUI components and event handlers
   public AWTCounterExternal () {
      setLayout(new FlowLayout());
         // "super" Frame, which is a Container, sets its layout to FlowLayout to arrange
         // the components from left-to-right, and flow to next row from top-to-bottom.

      lblCount = new Label("Counter");  // construct the Label component
      add(lblCount);                    // "super" Frame container adds Label component

      tfCount = new TextField(count + "", 10); // construct the TextField component with initial text
      tfCount.setEditable(false);       // set to read-only
      add(tfCount);                     // "super" Frame container adds TextField component

      btnCount = new Button("Count");   // construct the Button component
      add(btnCount);                    // "super" Frame container adds Button component

      MyExternalBtnListener listener = new MyExternalBtnListener();
      btnCount.addActionListener(listener);
         // "btnCount" is the source object that fires an ActionEvent when clicked.
         // The source add an instance of MyExternalBtnListener as an ActionEvent listener,
         //   which provides an ActionEvent handler called actionPerformed().
         // Clicking "btnCount" invokes actionPerformed().

      setTitle("AWT Counter");  // "super" Frame sets its title
      setSize(250, 100);        // "super" Frame sets its initial window size

      // For inspecting the Container/Components objects
      // System.out.println(this);
      // System.out.println(lblCount);
      // System.out.println(tfCount);
      // System.out.println(btnCount);
      setVisible(true);         // "super" Frame shows
      // System.out.println(this);
      // System.out.println(lblCount);
      // System.out.println(tfCount);
      // System.out.println(btnCount);
   }

   // The entry main() method
   public static void main(String[] args) {
      // Invoke the constructor to setup the GUI, by allocating an instance
      AWTCounterExternal app = new AWTCounterExternal();
         // or simply "new AWTCounter();" for an anonymous instance
   }
}

// We write an external class as the Button's ActionEvent listener.
// This class must implement ActionListener interface and override
// the actionPerformed() method.
class MyExternalBtnListener implements ActionListener {
   // ActionEvent handler - Called back upon button-click.
   @Override
   public void actionPerformed(ActionEvent evt) {
      System.out.println("You clicked the button!");

     // Cannot access variables count and tfCount!!!
     // ++count; // Increase the counter value
     // tfCount.setText(count + ""); // Convert int to String
   }
}