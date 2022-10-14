package ald;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class CheckoutController {
    private ArrayList<String> user_infos_array;
    static String user_file_path;
    public CheckoutController(){
        user_file_path = "E:\\java\\leecode\\ald\\users.txt";
        try{
            String[] user_infos = get_user_info();
            user_infos_array= new ArrayList<>(Arrays.asList(user_infos));
        }catch (IOException e){
            System.out.println();
        }

    }
    public static void main(String[] args) {
        CheckoutController checkoutController = new CheckoutController();
        try{
            String tom = checkoutController.register("jack", "788");
            checkoutController.save_user_info();
            System.out.println(tom);
            checkoutController.alter_pwd("cps","456");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String register(String userName,String email) throws IOException {
        int userRe = checkUserName(userName);
        int emailRe = checkUserName(email);
        if(userRe == 0 && emailRe == 0){
            String temporaryPossWord = getTemporaryPossWord();
            user_infos_array.add(userName+":"+email+":"+temporaryPossWord+":0");
            save_user_info();
            return "Your temporary poss word is "+temporaryPossWord+"\nplease remember it.";
        }
        if(userRe == 1){
            return "Your user name is already exits!";
        }
        return "";
    }
    public static String[] get_user_info() throws IOException {
        StringBuilder user_string = new StringBuilder();
        InputStream is = new FileInputStream(user_file_path);
        byte[] data = new byte[2048];//缓存数组--8*1024
        int length;
        while((length = is.read(data))!=-1) {
            user_string.append(new String(data, 0, length));
        }
        String[] user_list = user_string.toString().split(",");
        is.close();
        for(int i=0;i<user_list.length;++i){
            user_list[i] = user_list[i].trim();
        }
        return user_list;
    }
    public void save_user_info(){
        PrintStream stream=null;
        //String string = user_infos_array.toString();
        String string = user_infos_array.toString();
        string = string.substring(1,string.length()-1);
        try {
            stream=new PrintStream(user_file_path);//写入的文件path
            stream.print(string);//写入的字符串
            user_infos_array = new ArrayList<>(Arrays.asList(get_user_info()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void alter_pwd(String userName,String newPwd){
        for(int i=0;i<user_infos_array.size();++i){
            String a = user_infos_array.get(i);
            if (user_infos_array.get(i).split(":")[0].trim() .equals(userName)){
                String[] userInfo = user_infos_array.get(i).split(":");
                user_infos_array.set(i,userInfo[0]+":"+userInfo[1]+":"+newPwd+":"+"1");
                break;
            }
        }
        save_user_info();
    }
    public int checkUserName(String userName){
        try{
            String[] user_info = get_user_info();
            for(String users:user_info){
                String thisUserName = users.split(":")[0];
                if (thisUserName.equals(userName)){
                    return 1;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    public int checkEmail(String email){
        return 0;
    }
    public String getTemporaryPossWord(){
        int pswd = new Random().nextInt(999999);
        return Integer.toString(pswd);
    }
}
