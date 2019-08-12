package com.me.szzc.utils;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.print.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bbfang on 2019/8/8.
 */
@Component
public class PrintUtil implements Printable {
    //打印的总页数
    private int pageSize = 1;

    //打印的纸张宽度
    private double paperW = 0;

    //打印的纸张高度
    private double paperH = 0;

    //打印类型

    //打印内容
    private List<Map<String, Object>> list;

    //实现java.awt.print.Printable接口的打印方法
    //pageIndex:打印的当前页，此参数是系统自动维护的，不需要手动维护，系统会自动递增
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex >= pageSize) {
            //退出打印
            return Printable.NO_SUCH_PAGE;
        } else {
            Graphics2D g2 = (Graphics2D) graphics;
            g2.setColor(Color.BLUE);
            Paper p = new Paper();
            //此处的paperW和paperH是从目标打印机的进纸规格中获取的，实际针式打印机的可打印区域是有限的，
            //距纸张的上下左右1inch(英寸)的中间的距形框为实际可打印区域，超出范围的内容将不会打印出来(没有设置偏移的情况)
            //如果设置偏移量，那么超出的范围也是可以打印的，这里的pageW和pageH我是直接获取打印机的进纸规格的宽和高
            //也可以手动指定，从是如果手动指定的宽高和目标打印机的进纸规格相差较大，将会默认以A4纸为打印模版
            p.setImageableArea(0, 0, paperW, paperH);// 设置可打印区域
            p.setSize(paperW, paperH);// 设置纸张的大小
            pageFormat.setPaper(p);
            drawCurrentPageText(g2, pageFormat);//调用打印内容的方法
            return PAGE_EXISTS;
        }
    }

    // 打印内容
    private void drawCurrentPageText(Graphics2D g2, PageFormat pf) {
        Font font = null;
        //此处打印一句话，打印开始位置是(200,200),表示从pf.getPaper()中座标为(200,200)开始打印
        //此处200的单位是1/72(inch)，inch:英寸，所以这里的长度，在测量后需要进行转换
        for (Map<String, Object> map : list) {
            //设置打印的字体
            font = new Font((String) map.get("fontName"), Font.BOLD, (Integer) map.get("fontSize"));
            g2.setFont(font);// 设置字体
            String data = (String) map.get("data");
            int x = (int) map.get("x");
            int y = (int) map.get("y");
            g2.drawString(data, x, y);
        }
    }

    //连接打印机，弹出打印对话框
    //打印类型：printType  0-纵向打印 1-横向打印
    public void starPrint(int printType, List<Map<String, Object>> dataList) {
        list = dataList;
        try {
            //创建一个打印任务
            PrinterJob prnJob = PrinterJob.getPrinterJob();
            PageFormat pageFormat = new PageFormat();
            //设置打印方向，LANDSCAPE为横向，PORTRAIT为纵向,打印方向默认为纵向
            if (printType == 0) {
                pageFormat.setOrientation(PageFormat.PORTRAIT);
            } else {
                pageFormat.setOrientation(PageFormat.LANDSCAPE);
            }

            prnJob.setPrintable(this);
            //弹出打印对话框，也可以选择不弹出打印提示框，直接打印
            if (!prnJob.printDialog()) {
                return;
            }
            //获取所连接的目标打印机的进纸规格的宽度，单位：1/72(inch)
            paperW = prnJob.getPageFormat(null).getPaper().getWidth();
            //获取所连接的目标打印机的进纸规格的宽度，单位：1/72(inch)
            paperH = prnJob.getPageFormat(null).getPaper().getHeight();
            System.out.println("paperW:" + paperW + ";paperH:" + paperH);
            //启动打印工作
            prnJob.print();
        } catch (PrinterException ex) {
            ex.printStackTrace();
            System.err.println("打印错误：" + ex.toString());
        }

    }

}
