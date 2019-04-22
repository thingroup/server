package com.jerry.gamemarket.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author 叶俊晖
 * @date 2019/3/30 0030 19:38
 */
public class TableTest2 {
    public static void main(String[] args){
        List<List<String>> lists=new ArrayList<>();
        String[] name={"叶","李","陈","张"};
        String[] gender={"男","女"};
        String[] hobby={"吃饭","睡觉","打机","发呆"};
        String[] properties={"姓名","年龄","成绩","性别","爱好"};
        Random random=new Random();
        int[] max=new int[5];
        for(int x=0;x<max.length;x++){
            max[x]=getLength(properties[x]);
        }
        List<String> list=new ArrayList<>();
        for (String x:properties){
            list.add(x);
        }
        lists.add(list);
        for(int i=0;i<15;i++){
            List<String> stringList=new ArrayList<>();
            stringList.add((name[random.nextInt(4)])+""+i);
            stringList.add((random.nextInt(13)+18)+"");
            stringList.add((random.nextInt(41)+60)+"");
            stringList.add(gender[random.nextInt(2)]);
            stringList.add(hobby[random.nextInt(4)]);
            for(int j=0;j<5;j++){
                int length=getLength(stringList.get(j));
                if(length>max[j]){
                    max[j]=length;
                }
            }
            lists.add(stringList);
        }
        StringBuffer buffer=new StringBuffer();
        buffer.append("+");
        for (int x:max) {
            System.out.println(x);
            buffer.append(getLine(x));
            buffer.append("+");
        }
        List<StringBuffer> listList=new ArrayList<>();
        listList=lists.stream().map(e->print(e,max)).collect(Collectors.toList());
        for (StringBuffer s:listList) {
            System.out.println(buffer);
            System.out.println(s);
        }
        System.out.println(buffer);
    }

    static StringBuffer print(List<String> e,int[] max){
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

    static int getLength(String s){
        int length=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)>='0'&&s.charAt(i)<='9'){
                length++;
            }else if(s.charAt(i)>='a'&&s.charAt(i)<='z'){
                length++;
            }else if(s.charAt(i)>='A'&&s.charAt(i)<='Z'){
                length++;
            }else {
                //汉字
                length+=2;
            }
        }
        return length;
    }

    static StringBuffer getLine(int maxLength){
        StringBuffer buffer=new StringBuffer();
        for (int i=0;i<maxLength;i++){
            buffer.append("-");
        }
        return buffer;
    }

    static String getBackup(int length){
        String result="";
        for(int i=0;i<length;i++){
            result+=" ";
        }
        return result;
    }
}