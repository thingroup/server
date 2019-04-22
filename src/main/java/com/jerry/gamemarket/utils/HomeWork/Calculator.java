package com.jerry.gamemarket.utils.HomeWork;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author 叶俊晖
 * @date 2019/4/1 0001 21:02
 */
public class Calculator {

    static List<CalHistory> history=new ArrayList<>();

    static Double ANS=null;

    public static void main(String[] args){
        System.out.println("********************");
        System.out.println("\n     小小计算器");
        System.out.println("\n********************");
        System.out.println("\n使用说明：");
        System.out.println("1.支持指数运算暂不支持对数运算");
        System.out.println("2.支持带括号运算（请输入完整式子，暂不支持右括号自动补全）");
        System.out.println("3.计算结果自动保留3位小数");
        System.out.println("4.输入ans查看上次计算结果");
        System.out.println("5.输入 history/历史 查看近期所有计算历史记录");
        System.out.println("6.输入 equation/方程 进入解方程模式");
        System.out.println("7.输入exit退出");
        System.out.println("\n********************");
        Scanner scanner=new Scanner(System.in);
        while(true){
            String equals="";
            System.out.print("请输入您要计算的式子：");
            equals=scanner.next();
            int func=functions(equals);
            switch (func){
                case -1:
                    System.out.println("谢谢使用");
                    return;
                case 1:
                    if(!history.isEmpty()){
                        new printTB(history);
                    }else{
                        System.out.println("Sorry! 没有任何记录~~");
                    }
                    break;
                case 2:
                    if(ANS!=null){
                        System.out.println("ANS = "+ANS);
                    }else{
                        System.out.println("ANS = 0");
                    }
                    break;
                case 3:
                    chooseEquation();
                    break;
                case 0:
                    //计算
                    try{
                        String result=equals+" = "+calBracket(equals);
                        CalHistory calHistory=new CalHistory();
                        calHistory.setEquals(result);
                        calHistory.setTime(new Timestamp(System.currentTimeMillis()));
                        history.add(calHistory);
                        System.out.printf(equals+" = %.3f\n",calBracket(equals));
                        ANS=calBracket(equals);
                    }catch (Exception e){
                        System.out.println("请输入正确的式子");
                    }
                    System.out.println("--------------------------");
                    break;
            }
        }
    }

    //功能方法
    static int functions(String equals){
        equals=equals.toLowerCase();
        switch (equals){
            case "exit":
                //退出
                return -1;
            case "history":
                //查看历史记录
                return 1;
            case "历史":
                //查看历史记录
                return 1;
            case "ans":
                //查看上一个计算结果
                return 2;
            case "方程":
                //解方程
                return 3;
            case "equation":
                //解方程
                return 3;
        }
        return 0;
    }

    //一元二次方程
    static void aX2_bX_c(){
        System.out.println("请输入您要计算的方程(请化为标准式:ax^2+bx+c=0)");
        System.out.println("格式如下：");
        System.out.println("a b c");
        Scanner sc=new Scanner(System.in);
        try{
            String[] numbers=new String[3];
            for(int i=0;i<numbers.length;i++){
                numbers[i]=sc.next();
            }
            double a= Double.parseDouble(numbers[0]);
            double b= Double.parseDouble(numbers[1]);
            double c= Double.parseDouble(numbers[2]);
            double delta=b*b-4*a*c;
            if(b>=0){
                System.out.println(a+"X^2 + "+b+"X +"+c+" = 0");
            }else{
                System.out.println(a+"X^2 "+b+"X +"+c+" = 0");
            }
            if(delta<0){
                System.out.println("方程无解");
                return;
            }else if(delta==0){
                System.out.println("解得 ：X1 = X2 = "+(-b)/(2*a));
            }else{
                double res=Math.pow(delta,0.5);
                System.out.println("解得 ：X1 = "+(-b+res)/(2*a)+" , X2 = "+(-b-res)/(2*a));
            }
        }catch (Exception e){
            System.out.println("请按要求输入");
        }
    }

    //解方程功能选择
    static void chooseEquation(){
        try{
            System.out.println("请选择您需要计算的方程类型（输入0-3） ：");
            System.out.println("1.一元二次方程");
            System.out.println("2.二元一次方程组");
            System.out.println("3.三元一次方程组");
            System.out.println("0.返回");
            Scanner sc=new Scanner(System.in);
            int n=sc.nextInt();
            switch (n){
                case 1:
                    aX2_bX_c();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 0:
                    return;
            }
        }catch (Exception e){
            System.out.println("请输入正确的式子");
        }
    }

    //基本运算
    static Double calculator(String symbol,double x,double y){
        if(symbol.equalsIgnoreCase("*")){
            return x*y;
        }
        if(symbol.equalsIgnoreCase("/")){
            return x/y;
        }
        if(symbol.equalsIgnoreCase("%")){
            return x%y;
        }
        if(symbol.equalsIgnoreCase("+")){
            return x+y;
        }
        if(symbol.equalsIgnoreCase("-")){
            return x-y;
        }
        return null;
    }

    //判断是否是数字
    static boolean isNumber(String q){
        if(q.length()>1){
            //负数 多位数
            return true;
        }else if(q.charAt(0)>='0'&&q.charAt(0)<='9'){
            //正个位数
            return true;
        }
        return false;
    }

    //运算规则
    static Double calRule(String equals){
        Stack<String> eq=subEquals(equals);
        Stack<Double> numbers=new Stack<>();
        Stack<String> symbols=new Stack<>();
        String higherSymbol2="*/%";
        //先处理高优先级运算符
        if(equals.contains("^")){
            Stack<String> save=new Stack<>();
            while (!eq.empty()){
                String content=eq.pop();
                if(content.contains("^")){
                    //指数运算
                    double x= Double.parseDouble(save.pop());
                    double y= Double.parseDouble(eq.pop());
                    double re=Math.pow(x,y);
                    save.push(re+"");
                }else{
                    save.push(content);
                }
            }
            eq=reserveStackStr(save);
        }
        double first=0;
        double second=0;
        String symbol="";
        double finalResult=0;
        while (!eq.empty()){
            String content=eq.pop();
            if(isNumber(content)){
                numbers.push(Double.parseDouble(content));
            }else{
                //符号
                if(higherSymbol2.contains(content)){
                    //高优先级运算符2
                    first=numbers.pop();
                    second=Double.parseDouble(eq.pop());
                    double res=calculator(content,first,second);
                    if(!eq.empty()||!symbols.empty()){
                        numbers.push(res);
                    }else if(symbols.empty()&&eq.empty()){
                        //没有加减法，直接返回连乘结果
                        return res;
                    }
                }else {
                    //低优先级
                    symbols.push(content);
                }
            }
        }
        StringBuffer simpleEq=new StringBuffer();
        numbers=reserveStackDouble(numbers);
        int mid=0;
        //简化后的式子进行求和运算
        while (!numbers.empty()) {
            finalResult+=numbers.pop();
        }
        return finalResult;
    }

    //带括号运算
    static Double calBracket(String equals){
        Stack<String> SubEquals=subEquals(equals);
        Stack<String> Equals=new Stack<>();
        Stack<String> mid=new Stack<>();
        double finalResult=0;
        String content="";
        String middle="";
        while (!SubEquals.empty()){
            content=SubEquals.pop();
            if(content.contains("(")){
                String check="";
                if(!Equals.empty()){
                    //左括号不在式子最左边
                    check=Equals.pop();
                    Equals.push(check);
                    if(isNumber(check)){
                        //是数字,补充一个乘号
                        Equals.push("*");
                    }
                    Equals.push(content);
                }else{
                     //左括号在式子最左边
                    Equals.push(content);
                }
            }else if(isNumber(content)){
                //数字
                Equals.push(content);
            }else if(content.contains(")")){
                while (!Equals.empty()){
                    middle=Equals.pop();
                    if(middle.contains("(")){
                        break;
                    }else{
                        mid.push(middle);
                    }
                }
                Equals.push(calRule(getEquals(mid))+"");
                //检查)右边是否要补*
                if(!SubEquals.empty()){
                    content=SubEquals.pop();
                    if(isNumber(content)){
                        SubEquals.push("*");
                    }
                    //将内容放回
                    SubEquals.push(content);
                }
            }else{
                Equals.push(content);
            }
        }
        String result=getEquals(reserveStackStr(Equals));
        return calRule(result);
    }

    //分割式子
    static Stack<String> subEquals(String equals){
        int eqIndex=0;
        StringBuffer number=new StringBuffer();
        Stack<String> Equals=new Stack<>();
        while(eqIndex<equals.length()){
            char q=equals.charAt(eqIndex);
            if(isNumber(q+"")){
                int i=eqIndex;
                while(isNumber(equals.charAt(i)+"")){
                    number.append(equals.charAt(i));
                    i++;
                    if(i==equals.length()){
                        break;
                    }
                }
                Equals.push(number+"");
                number=new StringBuffer();
                eqIndex=i;
            }else{
                if(q=='-'){
                    //处理负数
                    int i=eqIndex+1;
                    number.append(q);
                    while(isNumber(equals.charAt(i)+"")){
                        number.append(equals.charAt(i));
                        i++;
                        if(i==equals.length()){
                            break;
                        }
                    }
                    //检查上一个负号是否是数字，若是补充+，否则直接放入式子栈
                    String comfirm="";
                    if(!Equals.empty()){
                        comfirm=Equals.pop();
                        Equals.push(comfirm);
                    }
                    Equals.push(number+"");
                    number=new StringBuffer();
                    eqIndex=i;
                }else if(q=='.'){
                    //带小数点
                    if(!Equals.empty()){
                        String content=Equals.pop();
                        if(isNumber(content)){
                            //弹出数字
                            number.append(content);
                        }else {
                            //符号左侧补0
                            if(content.charAt(0)=='-'){
                                number.append(content).append(0);
                            }else{
                                //将符号放回
                                Equals.push(content);
                                number.append(0);
                            }
                        }
                        int i=eqIndex+1;
                        number.append(q);
                        while(isNumber(equals.charAt(i)+"")){
                            number.append(equals.charAt(i));
                            i++;
                            if(i==equals.length()){
                                break;
                            }
                        }
                        if(i==eqIndex+1){
                            //小数点后没有数字，右边补0
                            number.append(0);
                        }
                        Equals.push(number+"");
                        number=new StringBuffer();
                        eqIndex=i;
                    }
                }else if(q=='a'||q=='A'){
                    //输入ANS
                    if(equals.charAt(eqIndex+1)=='N'||equals.charAt(eqIndex+1)=='n'){
                        if(equals.charAt(eqIndex+2)=='S'||equals.charAt(eqIndex+2)=='s'){
                            if(ANS!=null){
                                Equals.push(ANS+"");
                                eqIndex+=2;
                            }else{
                                Equals.push("0");
                                eqIndex+=2;
                            }
                        }
                    }
                }else{
                    //一般符号
                    Equals.push(q+"");
                    eqIndex++;
                }
            }
        }
        return reserveStackStr(Equals);
    }

    //反转栈
    static Stack<String> reserveStackStr(Stack<String> Equals){
        Stack<String> res=new Stack<>();
        while (!Equals.empty()){
            res.push(Equals.pop());
        }
        return res;
    }
    static Stack<Double> reserveStackDouble(Stack<Double> Equals){
        Stack<Double> res=new Stack<>();
        while (!Equals.empty()){
            res.push(Equals.pop());
        }
        return res;
    }

    //生成式子
    static String getEquals(Stack<String> equals){
        StringBuffer buffer=new StringBuffer();
        while (!equals.empty()){
            buffer.append(equals.pop());
        }
        return String.valueOf(buffer);
    }

    //打印格式化式子
    static String getRegularEQ(String equals){
        Stack<String> eq=subEquals(equals);
        StringBuffer buffer=new StringBuffer();
        while (!eq.empty()){
            buffer.append(eq.pop()).append(' ');
        }
        return buffer+"";
    }

}
class CalHistory{
    String equals;
    Timestamp time;

    public String getEquals() {
        return equals;
    }

    public void setEquals(String equals) {
        this.equals = equals;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}

class printTB{
     List<CalHistory> calHistoryList=new ArrayList<>();

    public List<CalHistory> getCalHistoryList() {
        return calHistoryList;
    }

    public void setCalHistoryList(List<CalHistory> calHistoryList) {
        this.calHistoryList = calHistoryList;
    }

    public printTB(List<CalHistory> historyList){
        print(historyList);
    }

    static void print(List<CalHistory> historyList){
        String title1="式子";
        String title2="记录时间";
        int maxLen=4;
        for(int i=0;i<historyList.size();i++){
            int len=historyList.get(i).getEquals().length();
            if(len>maxLen){
                maxLen=len;
            }
        }
        int maxLen2=8;
        for(int i=0;i<historyList.size();i++){
            int len=(historyList.get(i).getTime()+"").length();
            if(len>maxLen2){
                maxLen2=len;
            }
        }
        String line=getline(maxLen,maxLen2);
        System.out.println(line);
        printTitle(maxLen,title1,4);
        printTitle(maxLen2,title2,7);
        System.out.println("|");
        System.out.println(line);
        for(int i=0;i<historyList.size();i++){
            printTitle(maxLen,historyList.get(i).getEquals(),historyList.get(i).getEquals().length());
            printTitle(maxLen2, String.valueOf(historyList.get(i).getTime()),(historyList.get(i).getTime()+"").length());
            System.out.println("|");
            System.out.println(line);
        }
    }

    static void printBackUp(int n){
        for(int i=0;i<n;i++){
            System.out.print(" ");
        }
    }

    static void printTitle(int maxLen,String title,int start){
        int a1=maxLen-start;
        System.out.print("|");
        if(a1%2==0){
            printBackUp(a1/2);
            System.out.print(title);
            printBackUp(a1/2);
        }else{
            printBackUp(a1/2);
            System.out.print(title);
            printBackUp(a1/2+1);
        }
    }

    static String getline(int len1,int len2){
        StringBuffer line=new StringBuffer();
        line.append("+");
        for(int i=0;i<len1;i++){
            line.append("-");
        }
        line.append("+");
        for (int i=0;i<len2;i++){
            line.append("-");
        }
        line.append("+");
        return line+"";
    }
}