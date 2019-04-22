package com.jerry.gamemarket.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 叶俊晖
 * @date 2019/3/30 0030 19:38
 */
public class TableTest {
    public static void main(String[] args){
        List<Person> lists=new ArrayList<>();
        char[] name={'叶','李','陈','张'};
        char[] gender={'男','女'};
        String[] hobby={"吃饭","睡觉","打机","发呆"};
        String[] properties={"姓名","年龄","成绩","性别","爱好"};
        Random random=new Random();
        int[] max=new int[5];
        for(int i=0;i<15;i++){
            Person person=new Person();
            person.setAge(random.nextInt(13)+18);
            person.setGender(gender[random.nextInt(2)]);
            person.setHobby(hobby[random.nextInt(4)]);
            person.setMark(random.nextInt(41)+60);
            person.setName((name[random.nextInt(4)])+""+i);
            lists.add(person);
            if(person.getName().length()>max[0]){
                max[0]=person.getName().length();
            }
            if((person.getAge()+"").length()>max[1]){
                max[1]=(person.getAge()+"").length();
            }
            if((person.getMark()+"").length()>max[2]){
                max[2]=(person.getMark()+"").length();
            }
            if(person.getHobby().length()>max[4]){
                max[4]=person.getName().length();
            }
        }
        max[3]=1;
        String tbTop="+";
        String topName="|";
        for(int i=0;i<5;i++){
            if(max[i]>2){
                int less=(max[i]+2)/2;
                properties[i]=getBackup(less)+ properties[i] +getBackup(less);
            }else{
                max[i]=2;
            }
            for(int j=0;j<properties[i].length();j++){
                tbTop+="-";
            }
            topName+=properties[i]+"|";
            tbTop+="+";
        }
        System.out.println(tbTop);
        System.out.println(topName);
        System.out.println(tbTop);
        for (Person person:lists) {
            String content="|";
            if(person.getName().length()>max[0]){
                max[0]=person.getName().length();
            }
            int less=getBackupCount(person.getName().length(),max[0]);
            content=content+person.getName()+getBackup(less)+"|";
            less=getBackupCount((person.getAge()+"").length(),max[1]);
            content=getBackup(less)+content+person.getAge()+getBackup(less)+"|";
            less=getBackupCount((person.getMark()+"").length(),max[2]);
            content=getBackup(less)+content+person.getMark()+getBackup(less)+"|";
            less=getBackupCount((person.getGender()+"").length(),max[3]);
            content=getBackup(less)+content+person.getGender()+getBackup(less)+"|";
            less=getBackupCount(person.getHobby().length(),max[4]);
            content=getBackup(less)+content+person.getHobby()+getBackup(less)+"|";
            System.out.println(content);
            System.out.println(tbTop);
        }
    }

    static int getBackupCount(int length,int max){
        int less=(length+max)/2;
        if(max>length){
            return less;
        }
        return 0;
    }

    static String getBackup(int length){
        String result="";
        for(int i=0;i<length;i++){
            result+=" ";
        }
        return result;
    }
}
class Person{
    String name;
    Character gender;
    Integer age;
    Integer mark;
    String hobby;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
