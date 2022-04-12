package com.umleditor;

// Issue 000001: JavaFX 'does not export com.sun.javafx.scene.layout to unnamed module' error
// TODO: Fix Issue 000001

/**
 * Construct upon AppRunner to allow running the program
 * when packaged to .jar.
 * Also as workaround for Issue 000001.
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class MainRunner {
   public static void main(String[] args) {
        AppRunner.startApp(args);
   }
}
