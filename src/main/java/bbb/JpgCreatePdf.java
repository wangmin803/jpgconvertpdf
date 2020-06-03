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
 * �����������Լ�����ť�¼��ࡪ��Jiemian_main
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
 
jf = new JFrame("��ͼƬ��ϳ�PDF�ļ�");
jp = new JPanel();
jp.setLayout(null);
 
/*��ǩ*/
jl_dir_open = new JLabel("��ѡ��ͼƬ�����ڵ��ļ��У�");
jl_dir_save = new JLabel("��ѡ��PDF�ĺϳ�λ�ã�");
jl_lujing_open = new JLabel("·����");
jl_lujing_save = new JLabel("·����");
jl_dir_open.setBounds(50, 50, 200, 20);
jl_dir_save.setBounds(420, 50, 200, 20);
jl_lujing_open.setBounds(50, 80, 40, 20);
jl_lujing_save.setBounds(420, 80, 40, 20);
jp.add(jl_dir_open);
jp.add(jl_dir_save);
jp.add(jl_lujing_open);
jp.add(jl_lujing_save);
 
/*��ť*/
jb_open = new JButton("���");
jb_save = new JButton("���");
jb_ok = new JButton("��ʼ�ϳ�");
jb_open.setBounds(230, 80, 65, 20);
jb_save.setBounds(600, 80, 65, 20);
jb_ok.setBounds(310, 165, 90, 30);
jb_open.addActionListener(new Open());
jb_save.addActionListener(new Save());
jb_ok.addActionListener(new Begin());
jp.add(jb_open);
jp.add(jb_save);
jp.add(jb_ok);
 
/*�����ı���·����ʾ��*/
jt_dir_open = new JTextField();
jt_dir_save = new JTextField();
jt_dir_open.setBounds(90, 80, 130, 20);
jt_dir_save.setBounds(460, 80, 130, 20);
jt_dir_open.setEditable(false);
jt_dir_save.setEditable(false);
jp.add(jt_dir_open);
jp.add(jt_dir_save);
 
/*�ı���ʹ��˵����*/
jtf = new JTextField(shuoming);
jtf.setBounds(50,225, 615, 50);
jtf.setEnabled(false);
jp.add(jtf);
 
/*����*/
jf.add(jp);
jf.setSize(715,315);
jf.setResizable(false);
jf.setLocationRelativeTo(null);
jf.setVisible(true);
jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
}
 
 
/*��ȡͼƬ·���İ�ť�¼�����*/
private class Open implements ActionListener{
 
public void actionPerformed(ActionEvent e){
 
Lujing_get lujing_get = new Lujing_get(jf);
//����Lujing_get���󣬻�ȡͼƬ��·��
dir_open = lujing_get.open_get();
jt_dir_open.setText(dir_open);
}
}
 
/*��ȡѡ�񱣴�PDF������λ�õ�·���İ�ť�¼�����*/
private class Save implements ActionListener{
 
public void actionPerformed(ActionEvent e){
 
Lujing_get lujing_get = new Lujing_get(jf);
//����Lujing_get���󣬻�ȡѡ�񱣴�PDF������λ�õ�·��
dir_save = lujing_get.save_get();
jt_dir_save.setText(dir_save);
}
}
 
/*�ϳ�PDF�İ�ť�¼�����*/
private class Begin implements ActionListener{
 
public void actionPerformed(ActionEvent e){
 
/*׼ȷ��������ȡ��·��*/
if(dir_save.equals("") || dir_open.equals("")){
 
JOptionPane.showMessageDialog(jf, "������ͼƬ���·����PDF�ı���·��","����",JOptionPane.WARNING_MESSAGE);
 
}else{
 
File_deal fd = new File_deal(dir_open);
//����File_deal���󣬽�ͼƬ��·���µ�����ͼƬ�ļ�׼ȷ����
 
if(fd.files() != null){
 
/*׼ȷ����PDF����������ȥ����׺����*/
if(dir_save.lastIndexOf(".") != (-1))
dir_save = dir_save.substring(0, dir_save.lastIndexOf("."));
 
WM_CreatPDF pdf_creat = new WM_CreatPDF(fd.files(),dir_save);
//����WM_CreatPDF��������PDF
 
try {
pdf_creat.creatPDF();
//�ϳ�PDF�ļ�
} catch (DocumentException e1) {
// TODO �Զ����ɵ� catch ��
e1.printStackTrace();
} catch (IOException e1) {
// TODO �Զ����ɵ� catch ��
e1.printStackTrace();
}
 
JOptionPane.showMessageDialog(jf,"�Ѻϳ�PDF�ļ�������λ�ã�"+dir_save+".pdf",
"���",JOptionPane.PLAIN_MESSAGE);
 
}else 
JOptionPane.showMessageDialog(jf, "���ļ�����û��ͼƬ��ʽ�ļ���.jpg/.png/.bmp/.tif����",
"����",JOptionPane.WARNING_MESSAGE);
 
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
//������ѡ���ļ������������
jfc.setDialogTitle("��ѡ��ͼƬ�����ڵ��ļ���");
jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
int returnVal = jfc.showOpenDialog(jf);
if(JFileChooser.APPROVE_OPTION == returnVal){
 
dir = jfc.getSelectedFile().toString();
 
}
 
return dir; //����·��
}
 
public String save_get(){
 
String dir = "";
 
JFileChooser jfc = new JFileChooser();
//������ѡ���ļ������������
jfc.setDialogTitle("��ѡ��PDF�ĺϳ�λ��");
FileNameExtensionFilter filter = new FileNameExtensionFilter("pdf","PDF");
jfc.setFileFilter(filter);
int returnVal = jfc.showSaveDialog(jf);
if(JFileChooser.APPROVE_OPTION == returnVal){
 
dir = jfc.getSelectedFile().toString();
}
 
return dir; //����·��
}
}
 
 
 
/*
 *��ȡͼƬ�ļ�
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
 * �ļ���������������Ŀ¼�µ�ͼƬ��ʽ�ļ�����
 * */
class PhotoFileFilter implements FileFilter{
 
 
public boolean accept(File file) {
// TODO �Զ����ɵķ������
 
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
 * ����PDF�ļ�������Ҫ�ⲿ���á�itextpdf.jar�������ص�ַΪ��http://www.java2s.com/Code/Jar/i/itextpdf.htm��
 * 
 * */
class WM_CreatPDF {
 
 
final private float A4_weight = 595-60;
//��׼A4�Ŀ�
final private float A4_height = 842-60;
//��׼A4�ĸ�
private File[] files = null; 
private String dir_save ="";
 
public WM_CreatPDF(File[] files,String dir_save){
 
this.files = files;
this.dir_save = dir_save;
}
 
public void creatPDF() throws DocumentException, IOException{
 
Document document = new Document(PageSize.A4,30,30,30,30);
//�����ĵ�����
PdfWriter.getInstance(document,new FileOutputStream(dir_save+".pdf"));
//������д����PDF���ͣ�
document.open();
//������
 
float percent=100;
float w,h;
for(int i=0;i<files.length;i++){
 
Image img = Image.getInstance(files[i].getCanonicalPath());
 
/*����ͼƬ���ű���*/
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
//�ر�����
}
 
 
public static void main(String[] args) {
// TODO �Զ����ɵķ������
 
new JpgCreatePdf();
 
 
}
 
 
}