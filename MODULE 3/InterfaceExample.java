// Interface
interface Playable {
    void play();
}

// Implementing class 1
class Guitar implements Playable {
    public void play() {
        System.out.println("Playing the guitar 🎸");
    }
}

// Implementing class 2
class Piano implements Playable {
    public void play() {
        System.out.println("Playing the piano 🎹");
    }
}

// Main class to test the interface
public class InterfaceExample {
    public static void main(String[] args) {
        // Instantiate and call play()
        Playable myGuitar = new Guitar();
        Playable myPiano = new Piano();

        myGuitar.play();  // Output: Playing the guitar 🎸
        myPiano.play();   // Output: Playing the piano 🎹
    }
}
