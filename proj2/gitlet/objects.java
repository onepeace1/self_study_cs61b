package gitlet;

// TODO: any imports you need here

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

/** Represents a gitlet objects.
 *  Objects represent blobs and commits. I intially made blobs dir and commit dir
 *  separately to since blobs and commit object have different structure(e.g. commit has metadata such as timestamp).
 *  But after noticed that git just incorporate them into one ohject.
 *  For blobs, objects store data(not reference) and corresponding filename(whose version does this blob represents).
 *  Gitlet actually doesn't need filename, but users might want to see filename.
 *  For Commits, objects store message,timestamp, parentID, and HashMap which key is filename and value is hashcode of blobs.
 *  For hashmap, one could use this also for stage area(index and index rm) when storing filename-hashcode map.
 *
 *  Some could argue this all-in-one strategy has problem of which users can't distinguish blobs and commits,
 *  but just having another type variable solves this issue.
 *
 *
 *
 *  @author TODO
 */

enum type {
    blobs,
    commits,
    //trees,
    Index
}
public class objects implements Serializable {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** Same as objects dir inside git. This stores blobs and commits, but not
     * trees since gitlet doesn't need tree object.
     */
    public static final File objects=join(GITLET_DIR,"objects");

    private type T;
    /**actual data**/
    private String data;
    /**name of file which blob represents its version**/
    private String filename;
    /** The message of commit. */
    private String message;
    /** When did this commit was created**/
    private String timestamp;
    /** parentID of this commit**/
    private LinkedList<String> parentID;
    /** mapping filename to blobID which is the path to place file data is stored**/
    private HashMap<String,String> state;

    public objects(type T) {
        this.T=T;
    }
    public objects(type T,String filename,String data) {
        this.T=T;
        this.filename=filename;
        this.data=data;
    }
    public type getT() {
        return T;
    }

    public String getParentID() {
        return parentID.getFirst();
    }

    public String getData() {
        return data;
    }

    public HashMap<String,String> getState() { return new HashMap<>(state); }

    /**Just change one node of state,such as when add is called
     * Note that put method rewrite value if key already exists**/
    public String changeNode(String filename,String hashcode) {
        return state.put(filename,hashcode);

    }
    /**remove node,such as when rm is called**/
    public String removeNode(String filename,String hashcode) {
        if(state.containsKey(filename)) return state.remove(filename);
        return null;
    }
    /** change entirely, such as when commit command is called**/
    public void changeState(HashMap<String,String> next) {
        state=new HashMap<>(next);
    }
    /* TODO: fill in the rest of this class. */
    public void saveObjects() {
        File newObject=join(objects,sha1(this));
        writeobject(newObject,this);
    }
    public objects fromFile(String filename) {
        File cur=join(objects,filename);
        return readobject(cur,objects.class);
    }

}
