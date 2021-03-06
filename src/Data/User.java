package Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class User {

    private String username;
    private String password;
    private List<Integer> downloads;
    private List<Integer> uploads;
    public ReentrantLock userLock;

    public User(String name, String password){
        this.username = name;
        this.password = password;
        this.downloads = new ArrayList<>();
        this.uploads = new ArrayList<>();
        userLock = new ReentrantLock();
    }

    public User(String name, String password, List<Integer> downloads, List<Integer> uploads){
        this.username = name;
        this.password = password;
        this.downloads = new ArrayList<>(downloads);
        this.uploads = new ArrayList<>(uploads);
    }

    public String getName(){
        return username;
    }

    public List<Integer> getDownloads(){
        synchronized (this) {
            return new ArrayList<>(downloads);
        }
    }

    public List<Integer> getUploads(){
        synchronized (this) {
            return new ArrayList<>(uploads);
        }
    }

    public void addDownload(int id){
        lock();
        downloads.add(id);
        unlock();
    }

    public void addUpload(int id){
        lock();
        uploads.add(id);
        unlock();
    }

    public boolean authentication(String password){
        return password.equals(this.password);
    }


    public void lock(){
        userLock.lock();
    }

    public void unlock(){
        userLock.unlock();
    }
}
