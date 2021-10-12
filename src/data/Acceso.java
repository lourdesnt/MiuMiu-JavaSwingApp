/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Lourdes
 */
public class Acceso {
    static Document doc;
    
    public static int abrirXML(File fichero){
        doc = null;
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            factory.setValidating(false);
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(fichero);
            return 0;
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    
    public static String[] procesar(Node node){
        String[] datos = new String[8];
        Node temp = null;
        int c = 0;
        NodeList nodos = node.getChildNodes();
        for(int i=0; i<nodos.getLength(); i++){
            temp = nodos.item(i);
            if(temp.getNodeType()==Node.ELEMENT_NODE){
                datos[c] = temp.getChildNodes().item(0).getNodeValue();
                c++;
            }
        }
        return datos;
    }
    
    public static String[] obtenerDatos(){
        String[] datosNodos = null;
        Node node;
        Node raiz = doc.getFirstChild();
        NodeList hijos = raiz.getChildNodes();
        for(int i=0; i<hijos.getLength(); i++){
            node = hijos.item(i);
            if(node.getNodeType()==Node.ELEMENT_NODE){
                datosNodos = procesar(node);
            }
        }
        return datosNodos;
    }
    
    public static int guardarDatos(String nombre, float hambre, float suciedad, float fuerza, float energia, float felicidad, float exp, int nivel){
        try {
            Node miumiu = doc.getElementsByTagName("miumiu").item(0);
            Element miumiuElement = (Element) miumiu;
                
            miumiuElement.getElementsByTagName("nombre").item(0).setTextContent(nombre);
            miumiuElement.getElementsByTagName("hambre").item(0).setTextContent(String.valueOf(hambre));
            miumiuElement.getElementsByTagName("suciedad").item(0).setTextContent(String.valueOf(suciedad));
            miumiuElement.getElementsByTagName("fuerza").item(0).setTextContent(String.valueOf(fuerza));
            miumiuElement.getElementsByTagName("energia").item(0).setTextContent(String.valueOf(energia));
            miumiuElement.getElementsByTagName("felicidad").item(0).setTextContent(String.valueOf(felicidad));
            miumiuElement.getElementsByTagName("exp").item(0).setTextContent(String.valueOf(exp));
            miumiuElement.getElementsByTagName("nivel").item(0).setTextContent(String.valueOf(nivel));
              
            return 0;
        } catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    
//    public int aniadirDOM(String nombre, float hambre, float suciedad, float fuerza, float energia, float felicidad, float exp, int nivel){
//        return aniadir(doc, nombre, hambre, suciedad, fuerza, energia, felicidad, exp, nivel);
//    }
   
    public static void sobreescribir() {
	try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(new File("src/data/save.xml"));

            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            transformer.transform(domSource, streamResult);
	} catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
	}
    }
   
    
}
