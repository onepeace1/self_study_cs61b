package gitlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    /** Same as index file inside git which stores files
     * that will be the state of next commit. Note that this file
     * initally stores state of cur commit.
     * **/
    public static final File Index=join(GITLET_DIR,"index_add");
    /** For storing removed files after "rm" is called. This
     * directory is needed since Index doesn't store removed files
     * which don't consist of next commit.
     */
    public static final File Removal=join(GITLET_DIR,"index_rm");

    /** Same as refs dir inside git. THis stores branches dir(not other things such as
     * tags since gitlet don't need those).
     */
    public static final File Refs=join(GITLET_DIR,"refs");
    /**same as refs/heads dir inside git. This stores local branches.**/
    public static final File local_branches=join(Refs,"heads");
    /**For remote branches**/
    public static final File remote_branches=join(Refs,"remotes");
    /** same as HEAD file inside dir. This file contains info what branch does HEAD points**/
    public static final File HEAD=join(GITLET_DIR,"HEAD");


    /* TODO: fill in the rest of this class. */
    public static void setUp() {
        CWD.mkdir();
    }

    public static void initialize() throws IOException {
        if(GITLET_DIR.isDirectory()) {
            new GitletException("git already exists");

        }
        GITLET_DIR.mkdir();
        objects.mkdir();
        Refs.mkdir();
        local_branches.mkdir();
        remote_branches.mkdir();

        Index.createNewFile();
        Removal.createNewFile();
        HEAD.createNewFile();

        /** create hashmap for storing staged files and removed files**/
        writeObject(Index,new objects(type.Index));
        writeObject(Removal,new objects(type.Index));
        /** intial HEAD points master branch**/
        writeContents(HEAD,"master");
    }

    public static void addBlob(String filename) {
        /** working tree must have file**/
        if(!FileExists(filename)) new GitletException("no file having filename as"+filename);
        File stagedFile=join(GITLET_DIR,filename);
        String hashcode=sha1(stagedFile);

        /** check whether cur commit has same file(and content) **/
        if(alreadyExists(filename,hashcode)) new GitletException("already exists in current commit");

        /** write contents to object dir**/
        addobjects(filename,stagedFile,hashcode);
        /** change index and delete file from removal if tracked**/
        changeIndex(filename,hashcode);


    }

    public static boolean FileExists(String filename) {
        File check=join(CWD,filename);

        return check.isFile();
    }

    public static boolean alreadyExists(String filename,String hashcode) {
        String commitID=getHeadCommitID();
        File commitFile=join(objects,commitID);
        objects commit=readObject(commitFile, objects.class);

        if(commit.getT()!=type.commits) return false;

        if(!commit.getState().containsValue(hashcode)) return false;

        return true;
    }
    /**if different version of file exists in stage area or file is staged as removal, correct those**/
    public static void change_illegally_staged(String filename,String hashcode) {
        objects addMap=readObject(Index,objects.class);
        objects rmMap=readObject(Removal,objects.class);
        if(addMap.getState().containsValue(hashcode)) addMap.getState().put(filename,hashcode);
        if(rmMap.getState().containsValue(hashcode)) rmMap.getState().remove(filename,hashcode);
    }

    public static boolean addobjects(String filename,File stagedFile,String hashcode) {
        File newBlob=join(objects,hashcode);
        if(newBlob.isFile()) return false;

        String content=readContentsAsString(stagedFile);
        objects Blob=new objects(type.blobs,filename,content);
        writeObject(newBlob,Blob);
    }
    public static String changeIndex(String filename,String hashcode) {
        return readObject(Index,objects.class).changeNode(filename,hashcode);
    }

    public static String getHeadCommitID() {
        File branch=join(local_branches,readContentsAsString(HEAD));
        return readContentsAsString(branch);
    }

    public static void setHead(String branchName) {
        writeContents(HEAD,branchName);
    }

    public static void add_branch(String name) {


        File branch=join(local_branches,name);
        if(branch.isFile()) new GitletException(name+"branch alrready exists");
        try {
            branch.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String cur_commit_id=getHeadCommitID();

        writeContents(branch,cur_commit_id);

    }

    public static void rm_branch(String name) {
        File branch=join(local_branches,name);

        if(!branch.isFile()) new GitletException("no branch named"+name);
        restrictedDelete(branch);

    }

    public static void get_log() {
        String cur_commit_id=getHeadCommitID();
        File cur_commit_file=join(objects,cur_commit_id);
        objects cur_commitID=readObject(cur_commit_file, objects.class);
        while(cur_commitID.Parent_num()!=0) {
            print();
            cur_commitID=cur_commitID.ParentsID;
        }
    }

    public static void get_global_log() {
        List<String> commitIDList=plainFilenamesIn(objects);

        for(String commitID:commitIDList) {
            String cur_commit_id=getHeadCommitID();
            File cur_commit_file=join(objects,cur_commit_id);
            objects cur_commitID=readObject(cur_commit_file, objects.class);

            if(cur_commitID.getT().equals("commits")) {
                print();
                cur_commitID = cur_commitID.getFirstParent();
            }
        }
    }


}
