/*
Nombres: Giuliano Bardecio [256113] y Vittorio Caiafa [252295]
*/

package consolalinux;

import java.util.*;

public class System_ {
    private ArrayList<User_> userList;
    private ArrayList<Folder_> foldersList;
    private User_ loggedUser;
    
    public System_() {
        userList = new ArrayList<>();
        foldersList = new ArrayList<>();
        loggedUser = new User_();
    }
    
    public ArrayList<User_> getUserList(){
        return userList;
    }
    
    public ArrayList<Folder_> getFolderList(){
        return foldersList;
    }
    
    public User_ getLoggedUser(){
        return this.loggedUser;
    }
    
    public void addUser(User_ user){
        userList.add(user);
    }
    
    public void addFolder(Folder_ folder){
        foldersList.add(folder);
    }
    
    public void setLoggedUser(User_ user){
        loggedUser = user;
    }
}