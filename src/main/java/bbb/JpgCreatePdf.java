package bbb;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
 
 
 
/*
 * 绘制主界面以及处理按钮事件类——Jiemian_main
 * 
 * */
class JpgCreatePdf extends JFrame{
 
private static final long serialVersionUID = 1657254256189721759L;
 
 
final private String shuoming = "";
private String dir_open = "";
private String dir_save = "";
 
public JFrame jf = null;
private JPanel jp = null;
private JButton jb_open = null;
private JButton jb_save =null;
private JButton jb_ok = null;
private JTextField jt_dir_open = null;
private JTextField jt_dir_save = null;
private JLabel jl_dir_open = null;
private JLabel jl_dir_save = null;
private JLabel jl_lujing_open = null;
private JLabel jl_lujing_save = null;
private JTextField jtf = null;
 
public JpgCreatePdf(){
 
jf = new JFrame("将图片组合成PDF文件");
jp = new JPanel();
jp.setLayout(null);
 
/*标签*/
jl_dir_open = new JLabel("请选择图片组所在的文件夹：");
jl_dir_save = new JLabel("请选择PDF的合成位置：");
jl_lujing_open = new JLabel("路径：");
jl_lujing_save = new JLabel("路径：");
jl_dir_open.setBounds(50, 50, 200, 20);
jl_dir_save.setBounds(420, 50, 200, 20);
jl_lujing_open.setBounds(50, 80, 40, 20);
jl_lujing_save.setBounds(420, 80, 40, 20);
jp.add(jl_dir_open);
jp.add(jl_dir_save);
jp.add(jl_lujing_open);
jp.add(jl_lujing_save);
 
/*按钮*/
jb_open = new JButton("浏览");
jb_save = new JButton("浏览");
jb_ok = new JButton("开始合成");
jb_open.setBounds(230, 80, 65, 20);
jb_save.setBounds(600, 80, 65, 20);
jb_ok.setBounds(310, 165, 90, 30);
jb_open.addActionListener(new Open());
jb_save.addActionListener(new Save());
jb_ok.addActionListener(new Begin());
jp.add(jb_open);
jp.add(jb_save);
jp.add(jb_ok);
 
/*单行文本（路径显示）*/
jt_dir_open = new JTextField();
jt_dir_save = new JTextField();
jt_dir_open.setBounds(90, 80, 130, 20);
jt_dir_save.setBounds(460, 80, 130, 20);
jt_dir_open.setEditable(false);
jt_dir_save.setEditable(false);
jp.add(jt_dir_open);
jp.add(jt_dir_save);
 
/*文本框（使用说明）*/
jtf = new JTextField(shuoming);
jtf.setBounds(50,225, 615, 50);
jtf.setEnabled(false);
jp.add(jtf);
 
/*主框*/
jf.add(jp);
jf.setSize(715,315);
jf.setResizable(false);
jf.setLocationRelativeTo(null);
jf.setVisible(true);
jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
}
 
 
/*获取图片路径的按钮事件处理*/
private class Open implements ActionListener{
 
public void actionPerformed(ActionEvent e){
 
Lujing_get lujing_get = new Lujing_get(jf);
//创建Lujing_get对象，获取图片组路径
dir_open = lujing_get.open_get();
jt_dir_open.setText(dir_open);
}
}
 
/*获取选择保存PDF所生成位置的路径的按钮事件处理*/
private class Save implements ActionListener{
 
public void actionPerformed(ActionEvent e){
 
Lujing_get lujing_get = new Lujing_get(jf);
//创建Lujing_get对象，获取选择保存PDF所生成位置的路径
dir_save = lujing_get.save_get();
jt_dir_save.setText(dir_save);
}
}
 
/*合成PDF的按钮事件处理*/
private class Begin implements ActionListener{
 
public void actionPerformed(ActionEvent e){
 
/*准确处理所获取的路径*/
if(dir_save.equals("") || dir_open.equals("")){
 
JOptionPane.showMessageDialog(jf, "请输入图片组的路径及PDF的保存路径","警告",JOptionPane.WARNING_MESSAGE);
 
}else{
 
File_deal fd = new File_deal(dir_open);
//创建File_deal对象，将图片组路径下的所有图片文件准确加载
 
if(fd.files() != null){
 
/*准确处理PDF的命名规则（去掉后缀名）*/
if(dir_save.lastIndexOf(".") != (-1))
dir_save = dir_save.substring(0, dir_save.lastIndexOf("."));
 
WM_CreatPDF pdf_creat = new WM_CreatPDF(fd.files(),dir_save);
//创建WM_CreatPDF对象，生成PDF
 
try {
pdf_creat.creatPDF();
//合成PDF文件
} catch (DocumentException e1) {
// TODO 自动生成的 catch 块
e1.printStackTrace();
} catch (IOException e1) {
// TODO 自动生成的 catch 块
e1.printStackTrace();
}
 
JOptionPane.showMessageDialog(jf,"已合成PDF文件，所在位置："+dir_save+".pdf",
"完成",JOptionPane.PLAIN_MESSAGE);
 
}else 
JOptionPane.showMessageDialog(jf, "该文件夹下没有图片格式文件（.jpg/.png/.bmp/.tif）！",
"警告",JOptionPane.WARNING_MESSAGE);
 
} 
}
}
 
}
 
 
 
class Lujing_get {
 
private JFrame jf = null;
 
public Lujing_get(JFrame jf){
 
this.jf = jf;
}
 
public String open_get(){
 
String dir = "";
 
JFileChooser jfc = new JFileChooser();
//创建“选择文件浏览器”对象
jfc.setDialogTitle("请选择图片组所在的文件夹");
jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
int returnVal = jfc.showOpenDialog(jf);
if(JFileChooser.APPROVE_OPTION == returnVal){
 
dir = jfc.getSelectedFile().toString();
 
}
 
return dir; //返回路径
}
 
public String save_get(){
 
String dir = "";
 
JFileChooser jfc = new JFileChooser();
//创建“选择文件浏览器”对象
jfc.setDialogTitle("请选择PDF的合成位置");
FileNameExtensionFilter filter = new FileNameExtensionFilter("pdf","PDF");
jfc.setFileFilter(filter);
int returnVal = jfc.showSaveDialog(jf);
if(JFileChooser.APPROVE_OPTION == returnVal){
 
dir = jfc.getSelectedFile().toString();
}
 
return dir; //返回路径
}
}
 
 
 
/*
 *获取图片文件
 * 
 * */
class File_deal{
 
private String dir_open = "";
 
public File_deal(String dir_open){
 
this.dir_open = dir_open;
}
 
public File[] files(){
 
File f = new File(dir_open);
File fs[] = f.listFiles(new PhotoFileFilter());

List<File> fileList = Arrays.asList(fs);
Collections.sort(fileList, new Comparator<File>() {
    public int compare(File o1, File o2) {
        if (o1.isDirectory() && o2.isFile())
            return -1;
        if (o1.isFile() && o2.isDirectory())
            return 1;
        return o1.getName().compareTo(o2.getName());
    }
});

fs = fileList.toArray(new File[fileList.size()]);

if(fs.length != 0)
 
return fs;
else
return null;
} 
}
 
 
 
/*
 * 
 * 文件过滤器，将所在目录下的图片格式文件返回
 * */
class PhotoFileFilter implements FileFilter{
 
 
public boolean accept(File file) {
// TODO 自动生成的方法存根
 
if(file.isDirectory())
return false;
else{
 
String name = file.getName();
if(name.endsWith(".jpg") || name.endsWith(".png"))
return true;
else
if(name.endsWith(".bmp") || name.endsWith(".tif"))
return true;
else
return false;
}
}
}
 
 
 
/*
 * 生成PDF文件（此类要外部引用“itextpdf.jar”，下载地址为：http://www.java2s.com/Code/Jar/i/itextpdf.htm）
 * 
 * */
class WM_CreatPDF {
 
 
final private float A4_weight = 595-60;
//标准A4的宽
final private float A4_height = 842-60;
//标准A4的高
private File[] files = null; 
private String dir_save ="";
 
public WM_CreatPDF(File[] files,String dir_save){
 
this.files = files;
this.dir_save = dir_save;
}
 
public void creatPDF() throws DocumentException, IOException{
 
Document document = new Document(PageSize.A4,30,30,30,30);
//创建文档容器
PdfWriter.getInstance(document,new FileOutputStream(dir_save+".pdf"));
//创建编写器（PDF类型）
document.open();
//打开容器
 
float percent=100;
float w,h;
for(int i=0;i<files.length;i++){
 
Image img = Image.getInstance(files[i].getCanonicalPath());
 
/*处理图片缩放比例*/
w = img.getWidth();
h = img.getHeight();
System.out.println(w+"xxxxxxxxxx"+h);
if((w > A4_weight)&&(h < A4_height))
percent = (A4_weight*100)/w ;
else
if((w < A4_weight)&&(h > A4_height))
percent = (A4_height*100)/h;
else
if((w > A4_weight)&&(h > A4_height)){
 
 
percent = (A4_weight*100)/w ;
h = (h*percent)/100;
if(h > A4_height)
percent = (A4_height*100)/h;
}
System.out.println(percent/2);
img.scalePercent(percent);

/*document.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));*/
document.newPage();

document.add(img);
}
 
document.close();
//关闭容器
}
 
 
public static void main(String[] args) {
// TODO 自动生成的方法存根
 
new JpgCreatePdf();
 
 
}
 
 
}