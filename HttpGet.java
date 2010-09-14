ʹ��Java������ʵ��HTTP�ļ��Ķ�������  
 
����ʱ�䣺2007.07.27 06:30     ��Դ��������    ���ߣ�dxaw 

���� 

�����û����ܻ��������������������վ�Ϸ���һ���ܺõ���Դ�����������Դ�Ƿֳ��˺ܶ���ļ���ŵģ������������浽���أ�ֻ�п��û������������ɱ��棬�����Դ���˼���������ǧ�����Ǽ�ֱ�Ǹ����ѡ� 

��Internet�Ϻܶ����Դ�ֳɶ���ļ����ʱ�������ļ���������һ���Ĺ���ģ�������ˣ����ǾͿ����ó�������������Դ����ȫ���ء� 

1. ����֪ʶ 

��Internet�ϣ�����Ҫ������վ�ϵ�ĳ����Դ�����ǻ���һ��URL��Uniform Resource Locator��������һ����������Դ��λ�����������صĹ����������²���: 

����1:�ͻ��˷�����������һ��URL 

����2:����������URL������ָ������Դ����һ�����������ͻ� 

����3:�ͻ��˽����������������е����ݴ浽�ļ� 

2. �������ӵĽ��� 

Java�ṩ�˶�URL���ʺʹ������������ĵ�API�����ǿ��Ժ����׵���ɶ���������Դ�Ĵ�ȡ,����Ĵ���ξ�����˶�һ����վ����Դ���з���: 


......
destUrl="http://www.ebook.com/java/������001.zip";
url = new URL(destUrl);
httpUrl = (HttpURLConnection) url.openConnection();
//����ָ����������Դ
httpUrl.connect();
//��ȡ����������
bis = new BufferedInputStream(httpUrl.getInputStream());
......
 

3. �����ķ��� 

Java ��ͨ���������������������ķ����Ѿ������˽�֪�������ˡ�����Ͳ��ٶ������ˣ����ʵ�JAVA��������: 


//���ô���������
System.getProperties().put("proxySet", "true");
System.getProperties().put("proxyHost", "10.154.134.110");
System.getProperties().put("proxyPort", "8080");
 

4. ������Դ�ı��� 

���Ͻ��У������Ѿ���ȡ��ָ��������Դ��������������������Ҫ��ɵľ��Ƕ�ȡ�������е��������ݣ������䱣�����ļ��С�ʾ������: 


......
fos = new FileOutputStream(fileName);
if (this.DEBUG) 
System.out.println
("���ڻ�ȡ����[" + destUrl + "]������...\n���䱣��Ϊ�ļ�[" + fileName +"]");

//�����ļ�
while ( (size = bis.read(buf)) != -1)
fos.write(buf, 0, size);
......
 

�����ʾ������ͽ�������Դ�����ݱ��浽�˱���ָ�����ļ��С� 

5. �����嵥 


import java.io.*;
import java.net.*;
import java.util.*;

/**
* ��p��Title: ���˿�����API��/p��
* ��p��Description: ��ָ����HTTP������Դ�ڱ������ļ���ʽ��ţ�/p��
* ��p��Copyright: Copyright (c) 2004��/p��
* ��p��Company: NewSky��/p��
* @author MagicLiao
* @version 1.0
*/
public class HttpGet {

�� public final static boolean DEBUG = true;//������
�� private static int BUFFER_SIZE = 8096;//��������С
�� private Vector vDownLoad = new Vector();//URL�б�
�� private Vector vFileList = new Vector();//���غ�ı����ļ����б�

�� /**
�� * ���췽��
�� */
�� public HttpGet() {}

�� /**
�� * ��������б�
�� */
�� public void resetList() {
���� vDownLoad.clear();
���� vFileList.clear();
�� }

�� /**
�� * ���������б���
�� *
�� * @param url String
�� * @param filename String
�� */

public void addItem(String url, String filename) {
�� vDownLoad.add(url);
�� vFileList.add(filename);
}

�� /**
�� * �����б�������Դ
�� */
public void downLoadByList() {
�� String url = null;
�� String filename = null;

�� //���б�˳�򱣴���Դ
�� for (int i = 0; i �� vDownLoad.size(); i++) {
���� url = (String) vDownLoad.get(i);
���� filename = (String) vFileList.get(i);

���� try {
������ saveToFile(url, filename);
���� }
���� catch (IOException err) {
������ if (DEBUG) {
�������� System.out.println("��Դ[" + url + "]����ʧ��!!!");
������ }
���� }
�� }

�� if (DEBUG) {
���� System.out.println("�������!!!");
�� }
}

/**
* ��HTTP��Դ����Ϊ�ļ�
*
* @param destUrl String
* @param fileName String
* @throws Exception
*/
public void saveToFile(String destUrl, String fileName)throws IOException{
�� FileOutputStream fos = null;
�� BufferedInputStream bis = null;
�� HttpURLConnection httpUrl = null;
�� URL url = null;
�� byte[] buf = new byte[BUFFER_SIZE];
�� int size = 0;

�� //��������
�� url = new URL(destUrl);
�� httpUrl = (HttpURLConnection) url.openConnection();
�� //����ָ������Դ
�� httpUrl.connect();
�� //��ȡ����������
�� bis = new BufferedInputStream(httpUrl.getInputStream());
�� //�����ļ�
�� fos = new FileOutputStream(fileName);

�� if (this.DEBUG) 
��System.out.println
("���ڻ�ȡ����[" + destUrl + "]������...\n���䱣��Ϊ�ļ�[" + fileName + "]");

�� //�����ļ�
�� while ( (size = bis.read(buf)) != -1) 
���� fos.write(buf, 0, size);

�� fos.close();
�� bis.close();
�� httpUrl.disconnect();
}

/**
* ���ô���������
*
* @param proxy String
* @param proxyPort String
*/
public void setProxyServer(String proxy, String proxyPort) {
�� //���ô��������� 
�� System.getProperties().put("proxySet", "true");
�� System.getProperties().put("proxyHost", proxy);
�� System.getProperties().put("proxyPort", proxyPort);
}

/**
* ������֤�û���������
*
* @param uid String
* @param pwd String
*/
public void setAuthenticator(String uid, String pwd) {
Authenticator.setDefault(new MyAuthenticator(uid, pwd));
}

/**
* ������(���ڲ���)
*
* @param argv String[]
*/
public static void main(String argv[]) {
�� HttpGet oInstance = new HttpGet();
�� try {
�� //���������б����˴��û�����д���Լ����������������б���
�� oInstance.addItem("http://www.ebook.com/java/������001.zip","./������1.zip");
�� oInstance.addItem("http://www.ebook.com/java/������002.zip","./������2.zip");
�� oInstance.addItem("http://www.ebook.com/java/������003.zip","./������3.zip");
�� oInstance.addItem("http://www.ebook.com/java/������004.zip","./������4.zip");
�� oInstance.addItem("http://www.ebook.com/java/������005.zip","./������5.zip");
�� oInstance.addItem("http://www.ebook.com/java/������006.zip","./������6.zip");
�� oInstance.addItem("http://www.ebook.com/java/������007.zip","./������7.zip");
�� //��ʼ����
�� oInstance.downLoadByList();
�� }
�� catch (Exception err) {
���� System.out.println(err.getMessage());
�� }
}
}
 
 