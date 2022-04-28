package gitlet;

// TODO: any imports you need here

import java.io.File;
import java.util.Date; // TODO: You'll likely use this in this class
import java.util.HashMap;

import static gitlet.Utils.join;

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Commit {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** Where commits are stored **/
    public static final File commit=join(Repository.GITLET_DIR,"commit");
    /**
     * The message of this Commit.
     */
    private String message;
    /**
     * When this commit was made
     **/
    private String timestamp;
    /** hashcodes of parent commits, Note that it is not Commit type **/
    private String parent1_id;
    private String parent2_id;
    private transient Commit parent1=null;
    private transient Commit parent2=null;
    /** hashcodes of blobs commit references.Note that it is not File type. **/
    private HashMap<String,String> Blobs_id;

    private transient HashMap<String,File> Blobs_ref;

    public Commit(String message,String[] parent) {
        this.message=message;

    }

    private
    public String getParent() {
        return
    }




}
