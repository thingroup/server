package com.jerry.gamemarket.utils.PrintTable;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author 叶俊晖
 * @date 2019/3/30 0030 19:38
 */
@Service
public class PrintTableImpl implements PrintTable{

    @Override
    public StringBuffer getPrint(List<String> e, int[] max) {
        StringBuffer buffer1=new StringBuffer();
        buffer1.append("|");
        for(int i=0;i<max.length;i++){
            if(getLength(e.get(i))<max[i]){
                int substract=max[i]-getLength(e.get(i));
                if(substract%2!=0){
                    if(substract>1){
                        int a=substract/2;
                        buffer1.append(getBackup(a)).append(e.get(i)).append(getBackup(a+1));
                    }else{
                        buffer1.append(e.get(i)).append(getBackup(substract));
                    }
                }else{
                    int a=(substract)/2;
                    buffer1.append(getBackup(a)).append(e.get(i)).append(getBackup(a));
                }
            }else {
                buffer1.append(e.get(i));
            }
            buffer1.append("|");
        }
        return buffer1;
    }

    @Override
    public int getLength(String s) {
        int length=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)>=0&&s.charAt(i)<=127){
                length++;
            } else {
                //其它文字
                length+=2;
            }
        }
        return length;
    }

    @Override
    public StringBuffer getLine(int maxLength) {
        StringBuffer buffer=new StringBuffer();
        for (int i=0;i<maxLength;i++){
            buffer.append("-");
        }
        return buffer;
    }

    @Override
    public StringBuffer getBackup(int length) {
        StringBuffer backUp=new StringBuffer();
        for (int i=0;i<length;i++){
            backUp.append(" ");
        }
        return backUp;
    }

    @Override
    public List<List<String>> getContent(Object list) {
        String s=list.toString();
        List<List<String>> result=new ArrayList<>();
        String title=new String();
        String contents=new String();
        //获取表名
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('&&i>0){
                title=s.substring(1,i);
                contents=s.substring(i,s.length()-1);
                break;
            }
        }
        List<String> datas=getData(contents,title);
        result.add(getProps(datas.get(0)));
        for (String data:datas) {
            result.add(getOneData(data));
        }
        return result;
    }

    @Override
    public List<String> getSonList(String s) {
        List<String> sonlist=new ArrayList<>();
        for (int i=0;i<s.length();i++){
            if(s.charAt(i)=='['){
                int j=i+1;
                while (s.charAt(j)!=']'){
                    j++;
                }
                sonlist.add(s.substring(i,j+1));
            }
        }
        return sonlist;
    }

    @Override
    public List<String> getProps(String data) {
        List<String> list=new ArrayList<>();
        String[] props=data.split(",");
        for(String prop:props){
            String[] propName=prop.split("=");
            list.add(propName[0]);
        }
        return list;
    }

    @Override
    public List<String> getOneData(String data) {
        List<String> list=new ArrayList<>();
        String[] props=data.split(",");
        for(String prop:props){
            int j=0;
            if(prop.length()>0){
                while(prop.charAt(j)!='='){
                    j++;
                }
                j++;
                list.add(prop.substring(j));
            }
        }
        return list;
    }

    @Override
    public void printTable(List<List<String>> datas) {
        if(datas==null){
            return;
        }
        StringBuffer line=new StringBuffer();
        line.append("+");
        int[] max=new int[datas.get(0).size()];
        for(int i=0;i<datas.size();i++){
            for(int j=0;j<max.length;j++){
                int length=getLength(datas.get(i).get(j));
                if(length>max[j]){
                    max[j]=length;
                }
            }
        }
        for(int x:max){
            line.append(getLine(x));
            line.append("+");
        }
        List<StringBuffer> table=datas.stream().map(e->getPrint(e,max)).collect(Collectors.toList());
        for(StringBuffer row:table){
            System.out.println(line);
            System.out.println(row);
        }
        System.out.println(line);
    }

    @Override
    public void doPrintTable(Object list) {
        printTable(getContent(list));
    }

    static List<String> getData(String s,String title){
        List<String> list=new ArrayList<>();
        int checkpoint=1;
        for(int i=0;i<s.length();i++){
            String data=new String();
            if(s.charAt(i)==')'){
                data=s.substring(checkpoint,i);
                checkpoint=i+title.length()+4;
                list.add(data);
            }
        }
        return list;
    }
}