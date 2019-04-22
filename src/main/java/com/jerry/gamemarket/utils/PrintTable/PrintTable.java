package com.jerry.gamemarket.utils.PrintTable;

import java.util.List;

/**
 * @author 叶俊晖
 * @date 2019/4/1 0001 14:31
 */
public interface PrintTable {
    //获取每行字符串
    StringBuffer getPrint(List<String> e, int[] max);
    //单元格内容长度
    int getLength(String s);
    //获取边线
    StringBuffer getLine(int maxLength);
    //获取补充空格
    StringBuffer getBackup(int length);
    //获取单元格内容
    List<List<String>> getContent(Object list);
    //检查是否有子集合，有则返回所有子集合
    List<String> getSonList(String s);
    //获取属性名
    List<String> getProps(String data);
    //获取具体数据
    List<String> getOneData(String data);
    //打印表格
    void printTable(List<List<String>> datas);
    //执行打印
    void doPrintTable(Object list);
}
