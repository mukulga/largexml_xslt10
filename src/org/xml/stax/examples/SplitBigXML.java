/*
 * This code is licensed under Apache License, Version 2.0.
 * 
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Author : Mukul Gandhi
 */
package org.xml.stax.examples;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamResult;

/*
 * A java program, that can split a very big XML input document into smaller
 * XML documents around repeating element boundaries. 
 */
public class SplitBigXML {
	
	private static String TRANSFORM_TRIGGER_ELEMENT = "ProteinEntry";

	public static void main(String[] args) {

		if (args.length != 3) {
			System.out.println("Usage:\n");
			System.out.println("java SplitBigXML <inpXMLFile> <stylesheetFile> <outDir>");
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

			int outFileCount = 0; // a running integer appended to the output file name, making the file name unique
			                      // within the set of output files generated.
            char fileSeparatorChar = File.separatorChar;
            
			while (reader.hasNext()) {
				if (reader.getEventType() == XMLStreamReader.START_ELEMENT) {
					if (TRANSFORM_TRIGGER_ELEMENT.equals(reader.getLocalName())) {
						outFileCount++;
						FileOutputStream fos = new FileOutputStream(args[2] + fileSeparatorChar + "outFile_" + outFileCount + ".xml");
						Source source = new StAXSource(reader);
						StreamResult result = new StreamResult(fos);
						transformer.transform(source, result);						
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

} // class SplitBigXML
