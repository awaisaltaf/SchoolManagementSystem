package gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class SwingProgressBarExample extends JPanel {

  JProgressBar pbar;

  static final int MY_MINIMUM = 0;

  static final int MY_MAXIMUM = 20;

  public SwingProgressBarExample() {
    pbar = new JProgressBar();
    pbar.setMinimum(MY_MINIMUM);
    pbar.setMaximum(MY_MAXIMUM);
    pbar.setForeground(Color.GREEN);
    add(pbar);
  }

  public void updateBar(int newValue) {
	  
    pbar.setValue(newValue);
  }

  public static void main(String args[]) {

    final SwingProgressBarExample it = new SwingProgressBarExample();

    JFrame frame = new JFrame("Progress Bar Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(it);
    frame.pack();
    frame.setVisible(true);

    for (int i = MY_MINIMUM; i <= MY_MAXIMUM; i++) {
      final int percent = i;
      try {
        SwingUtilities.invokeLater(new Runnable() {
          public void run() {
            it.updateBar(percent);
          }
        });
        java.lang.Thread.sleep(100);
      } catch (InterruptedException e) {
        ;
      }
    }
  }
}

           
         