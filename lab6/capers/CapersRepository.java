package capers;

import java.io.File;
import java.io.IOException;

import static capers.Utils.*;

/** A repository for Capers 
 * @author TODO
 * The structure of a Capers Repository is as follows:
 *
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 *    - dogs/ -- folder containing all of the persistent data for dogs
 *    - story -- file containing the current story
 *
 * TODO: change the above structure if you do something different.
 */
public class CapersRepository {
    /** Current Working Directory. */
    static final File CWD = new File(System.getProperty("user.dir"));

    /** Main metadata folder. */
    static final File CAPERS_FOLDER = Utils.join(CWD,".CAPERS");

    static final File STORY=Utils.join(CAPERS_FOLDER,"STORY");
    /**
     * Does required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure (you do not have to follow):
     *
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    - story -- file containing the current story
     */

    //why not static final for story?
    public static void setupPersistence() throws IOException {
        //File STORY=Utils.join(CAPERS_FOLDER,"STORY");
        CAPERS_FOLDER.mkdir();
        Dog.DOG_FOLDER.mkdir();

        try {
            STORY.createNewFile();
        }
        catch(IOException error) {
            error.printStackTrace();
        }

    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) {
        String stored=Utils.readContentsAsString(STORY);
        Utils.writeContents(STORY,stored+text+"\r\n");
    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {
        Dog new_dog=new Dog(name,breed,age);
        new_dog.saveDog();
        // TODO
    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {
        Dog happy=Dog.fromFile(name);
        happy.haveBirthday();
        File current=Utils.join(Dog.DOG_FOLDER,name);
        Utils.writeObject(current,happy);
        // TODO
    }

    public static void main(String[] args) throws IOException {
        String ex="I am legend";

        setupPersistence();
        writeStory(ex);

        String exis=readContentsAsString(STORY);
        System.out.println(exis);
    }
}
