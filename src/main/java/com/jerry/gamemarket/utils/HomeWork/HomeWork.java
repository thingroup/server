package com.jerry.gamemarket.utils.HomeWork;

import java.util.Scanner;

/**
 * @author 叶俊晖
 * @date 2019/4/2 0002 21:05
 */
public class HomeWork {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n=-1;
        boolean isExit=false;
        do{
            System.out.print("请输入一个正整数（输入0退出）: ");
            n=scanner.nextInt();
            triangle_main(n,-1,-1,n);
            triangle_main(n,1,-3,1);
        }while(!isExit);

    }

    //输出半三角（高，符号，左空格相关，顶角位置）
    static void triangle_main(int q,int w,int e,int head){
        int i=0;int j=0;int a=0;int k=0;int n=0;
        int x=0;int m=0;int r=0;int judge=0;
        n=q;x=n;
        if(w>0){
            judge=n-1;
            k=4*n-3;
        }else{
            judge=1;
            k=2*n*n-2*n+1;
        }
        for(a=judge;a>=1&&a<=n;a-=w){
            r=(2*a-3)*(2*n-3)+2*(a-1)*(n-2);
            for(i=1;i<=x;i++){
                print_space(k);
                System.out.print("*");
                k--;
                if(i==1){
                    if(a==1){
                        System.out.println();
                    }else{
                        m=r;
                        print_space(m);
                        m-=2;
                        System.out.println("*");
                    }
                    continue;
                }else {
                    print_space(2*i-3);
                    System.out.print("*");
                    if(a==1){
                        System.out.println();
                        continue;
                    }
                    print_space(m);
                    System.out.print("*");
                    print_space(2*i-3);
                    System.out.println("*");
                }
                m-=2;
            }
            r=m+4;
            m=2*n-5;
            k+=2;
            for (i=1;i<=n-1;i++){
                print_space(k);
                System.out.print("*");
                k++;
                print_space(m);
                if(m>0){
                    System.out.print("*");
                    if(a>1){
                        print_space(r);
                        if(i<n-1){
                            System.out.print("*");
                        }
                        print_space(m);
                        System.out.println("*");
                    }else if(a==1){
                        System.out.println();
                    }
                }else{
                    if(a==1){
                        r+=2;
                        m-=2;
                        System.out.println();
                        continue;
                    }
                    print_space(r);
                    System.out.println("*");
                }
                r+=2;
                m-=2;
            }
            if(a==head){
                k=n;
            }else {
                k=k+w*(2*n+e);
            }
        }
    }

    static void print_space(int n){
        for(int i=0;i<n;i++){
            System.out.print(" ");
        }
    }
}
