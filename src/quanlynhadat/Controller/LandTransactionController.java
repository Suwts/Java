/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlynhadat.Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import quanlynhadat.File.LandTransactionFile;
import quanlynhadat.Models.Transaction;

public class LandTransactionController {

    // Lấy toàn bộ danh sách giao dịch
    public static List<Transaction> getAllTransaction() throws IOException {
        List<Transaction> list = new ArrayList();
        LandTransactionFile landfile = new LandTransactionFile();
        list = landfile.readLandTransaction();
        return list;
    }

    // Lấy toàn bộ danh sách giao dịch theo idUser
    public static List<Transaction> getAllTransactionByUser(int idUser) throws IOException {
        List<Transaction> fulllist = new ArrayList<Transaction>();
        List<Transaction> list = new ArrayList<Transaction>();
        LandTransactionFile landfile = new LandTransactionFile();
        fulllist = landfile.readLandTransaction();
        for (int i = 0; i < fulllist.size(); i++) {
            if (fulllist.get(i).getId() == idUser) {
                list.add(fulllist.get(i));
            }
        }
        return list;
    }

    public static Transaction getTransactionByUser(int idUser, int t_id) throws IOException {
        Transaction trs = null;
        List<Transaction> list = new ArrayList<Transaction>();
        list = getAllTransaction();
        try {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId() == idUser && list.get(i).getT_id() == t_id) {
                    trs = list.get(i);
                    break;
                }
            }
            return trs;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public static boolean CreateNewTransaction(Transaction trs) throws IOException {
        try {
            LandTransactionFile landfile = new LandTransactionFile();
            List<Transaction> listtransac = new ArrayList<>();
            listtransac = getAllTransaction();
            listtransac.add(trs);
            landfile.writeLandTransaction(listtransac);
            return true;
        } catch (IOException ex) {
            System.out.println(ex);
            return false;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean updateTransaction(Transaction trs) throws IOException {
        List<Transaction> list = new ArrayList<Transaction>();
        list = getAllTransaction();
        try {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getT_id() == trs.getT_id()) {
                    list.set(i, trs);
                    break;
                }
            }
            LandTransactionFile.writeLandTransaction(list);
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean deleteTransaction(int idUser, int t_id) {
        List<Transaction> list = new ArrayList<Transaction>();
        try {
            list = getAllTransaction();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        try {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId() == idUser && list.get(i).getT_id() == t_id) {
                    list.remove(i);
                    break;
                }
            }
            LandTransactionFile.writeLandTransaction(list);
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }

    // Lấy toàn bộ danh sách giao dịch theo năm
    public static List<Transaction> getAllTransactionByYear(String year) {
        List<Transaction> fulllist = new ArrayList<Transaction>();
        List<Transaction> list = new ArrayList<>();
        try {
            fulllist = getAllTransaction();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        try {
            SimpleDateFormat sd = new SimpleDateFormat("yyyy");
            for (int i = 0; i < fulllist.size(); i++) {
                String y = sd.format(fulllist.get(i).getT_date());
                if (y.compareToIgnoreCase(year) == 0) {
                    list.add(fulllist.get(i));
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    // Lấy toàn bộ danh sách giao dịch theo tháng năm
    public static List<Transaction> getAllTransactionByMonthYear(String year, String month) {
        List<Transaction> fulllist = new ArrayList<Transaction>();
        List<Transaction> list = new ArrayList<>();
        String[] arrchar = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}; //xu ly simpledateformat thang < 10
        try {
            fulllist = getAllTransaction();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        for (int i = 0; i < arrchar.length; i++) {
            if (arrchar[i].compareToIgnoreCase(month) == 0) {
                month = "0" + month;
                break;
            }
        }
        try {
            SimpleDateFormat sdYear = new SimpleDateFormat("yyyy");
            SimpleDateFormat sdMonth = new SimpleDateFormat("MM");  // tra ve gia tri 01, 02 voi nhung thang < 10
            for (int i = 0; i < fulllist.size(); i++) {
                String y = sdYear.format(fulllist.get(i).getT_date());
                String m = sdMonth.format(fulllist.get(i).getT_date());

                if(y.compareToIgnoreCase(year) == 0 && m.compareToIgnoreCase(month) == 0){
                    list.add(fulllist.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
