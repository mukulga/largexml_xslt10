package org.xml.stax.examples;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamResult;

public class TransformBigXML {

	private static String XML_PROLOG = "<?xml version=\"1.0\"?>";
	private static String ROOT_ELEMENT = "ProteinDatabase";
	private static String TRANSFORM_TRIGGER_ELEMENT = "ProteinEntry";

	public static void main(String[] args) {

		if (args.length != 3) {
			System.out.println("Usage:\n");
			System.out.println("java TransformBigXML <inpXMLFile> <stylesheetFile> <outXMLFile>");
			System.exit(0);
		}
		long startTime = System.nanoTime();
		try {
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			System.out.println("Streaming XMLInputFactory implementation: " + inputFactory.getClass().getName());
			FileInputStream styleis = new FileInputStream(args[1]);
			XMLStreamReader stylesheet = inputFactory.createXMLStreamReader(styleis);
			Source style = new StAXSource(stylesheet);
			TransformerFactory tf = TransformerFactory.newInstance();
			System.out.println("TransformerFactory implementation: " + tf.getClass().getName());
			Transformer transformer = tf.newTransformer(style);
			FileInputStream fis = new FileInputStream(args[0]);
			XMLStreamReader reader = inputFactory.createXMLStreamReader(fis);

			File outFile = new File(args[2]);
			Files.deleteIfExists(outFile.toPath());

			while (reader.hasNext()) {
				if (reader.getEventType() == XMLStreamReader.START_ELEMENT) {
					if (ROOT_ELEMENT.equals(reader.getLocalName())) {
						FileOutputStream fos = new FileOutputStream(outFile, true);
						byte[] startBytes = (XML_PROLOG + "\n<" + ROOT_ELEMENT + ">\n").getBytes();
						fos.write(startBytes);
						fos.flush();
						fos.close();
					} else if (TRANSFORM_TRIGGER_ELEMENT.equals(reader.getLocalName())) {
						FileOutputStream fos = new FileOutputStream(outFile, true);
						Source source = new StAXSource(reader);
						StreamResult result = new StreamResult(fos);
						transformer.transform(source, result);
						fos.flush();
						fos.close();
					}
				} else if (reader.getEventType() == XMLStreamReader.END_ELEMENT) {
					if (ROOT_ELEMENT.equals(reader.getLocalName())) {
						FileOutputStream fos = new FileOutputStream(outFile, true);
						byte[] endBytes = ("</" + ROOT_ELEMENT + ">").getBytes();
						fos.write(endBytes);
						fos.flush();
						fos.close();
					}
				}

				reader.next();
			}
		} 
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		long duration_secs = duration / 1000000000; // execution duration in seconds
		System.out.println("\nExecution time : " + duration_secs / 60 + " mins " + duration_secs % 60 + " secs");

	} // main

} // class TransformBigXML
