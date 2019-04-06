XSLT is a language used to transform XML documents into other XML / text / HTML documents. The XSLT processor available within a 
JRE (java runtime environment), can be used to do XSLT transformations using the APIs provided by the JRE. The XSLT processor available 
within a JRE, implements version 1.0 of the XSLT language.

The java code provided in this project, uses the XML StAX parser available within the JRE (Oracle's JRE 1.8.0 has been used in this 
project) to read an XML input document. The java code provided in this project, also uses the XSLT 1.0 processor available within the 
mentioned JRE.

The code provided in this project, illustrates a way to do XSLT 1.0 transformations on very large XML input documents. XSLT 1.0 
transformations on very large XML input documents is usually very difficult to accomplish by various other means (for e.g, when the XML 
input is provided to an XSLT 1.0 processor via a DOM object or via SAX events). A large XML file (of about 683 MB) available at, 
http://aiweb.cs.washington.edu/research/projects/xmltk/xmldata/www/repository.html#pir (psd7003.xml) was used to test the code provided 
in this project.
