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
    /** key is name of File not hashcode **/
    private HashMap<String,File> files;
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    /** Where changed files are staged when git add is called,note that just references are written **/
    public static final File stage_add= join(GITLET_DIR,"stage_add");
    /** Where removed files are staged when git rm is called, note that just references are written **/
    public static final File removal=join(GITLET_DIR,"stage_rm");
    /** Where added files are stored , note that inside blobs are real files not reference **/
    public static final File blobs=join(GITLET_DIR,"blobs");

    /**Do we need to have variables for stage area to store blobs..? I think yes since
     * when git ends after add, we just store hashmap but during runtime it is fast to have reference
     * when commit. But can't we search from blobs with )(1))?**/
    /** All should be directory since we need subfiles to distinguish from each ohter **/
    public static void SetupPersistence() {
        GITLET_DIR.mkdir();
        Commit.commit.mkdir();
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

    /** put files which target commit references to cwd files**/
    public static void putFiles(String commit_id) {}
    /** put target file to cwd file, change current file if already exists **/
    public static void putFile(String blob_filename) {}
    /** put target file inside target commit_id to cwd file, chnage current file if already exists **/
    public static void putFile(String blob_filename,String commit_id) {}
    /** store file reference pointing blob named filename inside stage area **/
    public static void staged(String filename) {}
    /** store actual file named filename inside stage area **/
    public static void make_blobs(String blob_id) {}
    /** remove file named filename from wherever has its info so that next commit doesn't have info about it **/
    public static void removal(String filename) {}
    /** make next commit **/
    public static void make_commit(String message) {}


    public static void addFile(File )
}
