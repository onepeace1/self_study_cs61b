package gitlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Repository {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** What users actually see on directory, note that inside files are real files not reference **/
    /** key is hashcode of File **/
    private HashMap<String,File> files;
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    /** Where changed files are staged when git add is called,note that just references are written **/
    public static final File stage_add= join(GITLET_DIR,"stage_add");
    /** Where removed files are staged when git rm is called, note that just references are written **/
    public static final File removal=join(GITLET_DIR,"stage_rm");
    /** Where added files are stored , note that inside blobs are real files not reference **/
    public static final File blobs=join(GITLET_DIR,"blobs");
    /** Where commits are stored **/
    public static final File commit=join(GITLET_DIR,"commit");

    /** All should be directory since we need subfiles to distinguish from each ohter **/
    public void SetupPersistence() {
        GITLET_DIR.mkdir();
        commit.mkdir();
        blobs.mkdir();
        stage_add.mkdir();
        removal.mkdir();
     /**
        try {
            stage_add.createNewFile();
            removal.createNewFile();
        }

        catch(IOException error) {
            error.printStackTrace();

        }
     **/
    }

    /** make new file inside stage_add **/
    public void addFile(String filename) {
        File stage= join(blobs,hash);
        stage.createNewFile();
        writeContents(stage,filenam);

    }
}
