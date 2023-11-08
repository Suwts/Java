/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlynhadat.File;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import quanlynhadat.Models.Account;


public class AccountFile {
    private static final String FILE_NAME ="ACCOUNT.txt";
    public static void ghifile(List<Account> dsac) throws IOException{
        ObjectOutputStream oos = null;
        try{
            oos = new ObjectOutputStream( new FileOutputStream("ACCOUNT.txt"));
            for(int i = 0 ; i < dsac.size(); i++){
                oos.writeObject(dsac.get(i));
            }
        }catch (IOException ex){
            System.out.println(ex);
        }catch(Exception e){
            System.out.println(e);
        }finally{
            if(oos != null){
                oos.close();
      
            }
        }
    }  
public static List<Account> docfile() throws IOException{
        ArrayList<Account> list = new ArrayList<Account>();
        ObjectInputStream ois = null;
        try{
            ois = new ObjectInputStream( new FileInputStream("ACCOUNT.txt"));
            while(true){
                 Account a = (Account) ois.readObject();
                 list.add(a);
            }
        }catch (IOException ex){
            System.out.println(ex);
        }catch(Exception e){
            System.out.println("e");
        }finally{
            if( ois != null){
                ois.close();
            }
        }
        return list;
    }
public  static  int readAutoID() throws IOException{
    ObjectInputStream ois = null;
    int id = 0;
    try{
        ois = new ObjectInputStream(new FileInputStream("ID.txt"));
        try {
            id = (int)ois.readObject();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }catch(IOException ex){
        System.out.println(ex);
    }
    return id;
}
public  static  void writeAutoID(int id) throws IOException{
    ObjectOutputStream ois = null;
    try{
        ois = new ObjectOutputStream(new FileOutputStream("ID.txt"));
        ois.writeObject(id);
    }catch(IOException ex){
        System.out.println(ex);
    }
}
}

    
    
