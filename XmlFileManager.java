XML������
package com.company.cpc.offlinelog.dao;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
//��Ҫ����castor.jar�ļ�
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.InputSource;
import com.zte.ecc.util.tracer.Debug;
/**
 *  �� �� ��:XmlFileManager
 *  ����ժҪ��������XML������
 */
public class XmlFileManager {
 /**
  * �������ƣ�objectListToXMLString
  * ����ժҪ���Ѷ�������XML
  * @param mappingXMLString ӳ���XML��
  * @param containerClass   ������ 
  * @return String ����XML
  * @throws IOException
  * @throws MappingException
  * @throws MarshalException
  * @throws ValidationException
  */
 public static String objectListToXMLString(
  String mappingXMLString,
  Object containerClass)
  throws IOException, MappingException, MarshalException, ValidationException {
  if (containerClass == null) {
   Debug.println("containerClass  is NULL!!!!!");
   return "";
  }
  //׼��Mapping
  Mapping mapping = new Mapping();
  Reader reader = new StringReader(mappingXMLString);
  InputSource is = new InputSource(reader);
  mapping.loadMapping(is);
  //׼��Writer
  StringWriter writer = new StringWriter();
  Marshaller marshaller = new Marshaller(writer);
  //��ʼ����
  marshaller.setMapping(mapping);
  marshaller.setEncoding("gb2312");
  marshaller.marshal(containerClass);
  StringBuffer bf = writer.getBuffer();
  writer.close();
  return bf.toString();
 }
 /**
  * 
  * �������ƣ�XmlToObjectList
  * ����ժҪ����XML����ɶ���
  * @param mappingXMLString ӳ���XML��
  * @param xmlString �������ݵ�XML��
  * @return Object
  * @throws IOException
  * @throws MappingException
  * @throws MarshalException
  * @throws ValidationException
  */
 public static Object XmlToObjectList(
  String mappingXMLString,
  String xmlString)
  throws IOException, MappingException, MarshalException, ValidationException {
  //׼��Mapping
  StringReader mapingReader = new StringReader(mappingXMLString);
  InputSource is = new InputSource(mapingReader);
  Mapping mapping = new Mapping();
  mapping.loadMapping(is);
  //׼��Reader
  Reader reader = new StringReader(xmlString);
  //��ʼ���� 
  Unmarshaller unmarshaller = new Unmarshaller(mapping);
  Object containerClass = unmarshaller.unmarshal(reader);
  reader.close();
  return containerClass;
 }
 /**
  * 
  * �������ƣ�saveToXMLFile
  * ����ժҪ���Ѷ�������XML�ļ�
  * @param xmlFileName �ļ���
  * @param mappingFileName  ӳ���ļ���
  * @param containerClass  
  * @throws IOException
  * @throws MappingException
  * @throws MarshalException
  * @throws ValidationException
  */
 public static void saveToXMLFile(
  String xmlFileName,
  String mappingFileName,
  Object containerClass)
  throws IOException, MappingException, MarshalException, ValidationException {
  if (containerClass == null) {
   Systen.out.println("containerClass  is NULL!!!!!");
   return;
  }
  //׼��Mapping 
  Mapping mapping = new Mapping();
  mapping.loadMapping(mappingFileName);
  //׼��Writer
  File file = new File(xmlFileName);
  Writer writer = new FileWriter(file);
  Marshaller marshaller = new Marshaller(writer);
  //��ʼ����
  marshaller.setMapping(mapping);
  marshaller.setEncoding("gb2312");
  marshaller.marshal(containerClass);
  writer.close();
 }
 /**
  * 
  * �������ƣ�loadFromXMLFile
  * ����ժҪ����XML�ļ�����ɶ���
  * @param xmlFileName
  * @param mappingFileName
  * @return
  * @throws IOException
  * @throws MappingException
  * @throws MarshalException
  * @throws ValidationException
  */
 public static Object loadFromXMLFile(
  String xmlFileName,
  String mappingFileName)
  throws IOException, MappingException, MarshalException, ValidationException {
  //׼��Mapping
  Mapping mapping = new Mapping();
  mapping.loadMapping(mappingFileName);
  //׼��Reader
  Reader reader = new FileReader(xmlFileName);
  //��ʼ���� 
  Unmarshaller unmarshaller = new Unmarshaller(mapping);
  Object containerClass = unmarshaller.unmarshal(reader);
  reader.close();
  return containerClass;
 }
 /**
  * 
  * �������ƣ�readerToString
  * ����ժҪ����Reader���е����ݱ�Ϊ�ַ���
  * @param reader
  * @param bfferSize
  * @return
  */
 public static String readerToString(Reader reader, int bfferSize) {
  StringBuffer sb = new StringBuffer();
  char[] b = new char[bfferSize];
  int n = 0;
  try {
   while ((n = reader.read(b)) > 0) {
    System.out.println("read:" + n);
    sb.append(b, 0, n);
   }
  } catch (IOException e) {
   // TODO �Զ����� catch ��
   e.printStackTrace();
  }
  return sb.toString();
 }
 /**
  * �������ƣ�objectListToXMLString
  * ����ժҪ���Ѷ�������XML
  * @param mappingFileName ӳ���ļ���
  * @param containerClass   ������ 
  * @return String ����XML
  * @throws IOException
  * @throws MappingException
  * @throws MarshalException
  * @throws ValidationException
  */
 public static String objectListToXMLStr(
  String mappingFileName,
  Object containerClass)
  throws IOException, MappingException, MarshalException, ValidationException {
  if (containerClass == null) {
   Debug.println("containerClass  is NULL!!!!!");
   return "";
  }
  
  //׼��Mapping
  Mapping mapping = new Mapping();
  mapping.loadMapping(mappingFileName);
  //׼��Writer
  StringWriter writer = new StringWriter();
  Marshaller marshaller = new Marshaller(writer);
  
  //��ʼ����
  marshaller.setMapping(mapping);
  marshaller.setEncoding("gb2312");
  marshaller.marshal(containerClass);
  StringBuffer bf = writer.getBuffer();
  writer.close();
  
  return bf.toString();
 }
 /**
  * 
  * �������ƣ�XmlToObjectList
  * ����ժҪ����XML����ɶ���
  * @param mappingFileName ӳ���ļ���
  * @param xmlString �������ݵ�XML��
  * @return
  * @throws IOException
  * @throws MappingException
  * @throws MarshalException
  * @throws ValidationException
  */
 public static Object XmlStrToObjectList(
  String mappingFileName,
  String xmlString)
  throws IOException, MappingException, MarshalException, ValidationException {
  //׼��Mapping
  Mapping mapping = new Mapping();
  mapping.loadMapping(mappingFileName);
  //׼��Reader
  Reader reader = new StringReader(xmlString);
  //��ʼ���� 
  Unmarshaller unmarshaller = new Unmarshaller(mapping);
  Object containerClass = unmarshaller.unmarshal(reader);
  reader.close();
  return containerClass;
 }
 /**
  * �������ƣ�XmlToObjectList
  * ����ժҪ���õ���Դ�ļ��ľ���·���ļ���
  * @param sResourceName ��Դ����
  * @return String
  */
 public static String getResourceFilePath(String sResourceName) {
  if (!sResourceName.startsWith("/")) {
   sResourceName = "/" + sResourceName;
  }
  java.net.URL classUrl = XmlFileManager.class.getResource(sResourceName);
  if (classUrl == null) {
   System.out.println(
    "\nResource '"
     + sResourceName
     + "' not found in \n'"
     + System.getProperty("java.class.path")
     + "'");
   return null;
  } else {
   System.out.println(
    "\nResource '"
     + sResourceName
     + "' found in \n'"
     + classUrl.getFile()
     + "'");
   return classUrl.getFile();
  }
 }
}
