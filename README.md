[XSLT](https://www.w3.org/TR/xslt/all/) is a language used to transform XML documents into other XML / text / HTML documents. The XSLT processor available within a 
JRE (java runtime environment), can be used to do XSLT transformations using the XSLT transformation APIs provided by the JRE. The XSLT processor available within a JRE, implements version 1.0 of the XSLT language.

The java code provided in this project, uses the XML StAX parser available within the JRE (Oracle's JRE 1.8.0 has been used in this 
project) to read an XML input document. The java code provided in this project, also uses the XSLT 1.0 processor available within the 
mentioned JRE.

The code provided in this project, illustrates a way to do XSLT 1.0 transformations on very large XML input documents, using the typical 
RAM and CPU resources available on most of current workstations. XSLT 1.0 transformations on very large XML input documents is usually very difficult to accomplish by various other means (for e.g, when the XML input is provided to an XSLT 1.0 processor via a DOM object or via SAX events). A large XML file (of about 683 MB) available at, http://aiweb.cs.washington.edu/research/projects/xmltk/xmldata/www/repository.html#pir (psd7003.xml) was used to test the code provided 
in this project [related information at point 5) in **Notes:** section at bottom of this page].

The following performance characteristics were measured by this project:

**1) Running the program, TransformBigXML**

This program can be used to transform one large XML input document, into one XML document output.

XML input file size (same for all the XSLT transforms mentioned below) == 683 MB

Output characteristics, for one of the runs of XSLT transformation:

1st transform (sample1.xsl) runtime : 4 mins 30 secs, output file size == 7.25 MB

2nd transform (sample2.xsl) runtime : 4 mins 39 secs, output file size == 64.9 MB

3rd transform (sample3.xsl) runtime : 5 mins 19 secs, output file size == 662 MB

**2) Running the program, SplitBigXML**

This program can be used to split one large XML input document, into multiple small XML output documents.

The same XML input file was used, as program 1) mentioned above.

Output characteristics, for one of the runs of XSLT transformation:

sample4.xsl : 4 mins 33 secs

No memory usage spikes were observed, during above test runs. The program run time measured as mentioned above, grows linearly with the
number of XML sibling related elements (which are 262525, in the XML document input used).

**Notes:**
1) The line referring the DTD at the top of XML input document was commented, before using the XML input document.
2) There is 15-20% CPU usage increase, due to antimalware service executable that additionally runs on the windows platform when the sample programs are run. If the user wouldn't have this issue, the XSLT transformation time will be even less than mentioned above.
3) There is some serialization code written in the java program posted (in the file TransformBigXML), along with using the serialization implementation available within the XSLT processor.
4) Other than the java version 1.8.0 that was used to test this implementation, certain lower versions of java language, and also higher
versions of java language can also be used to run the programs provided in this repository.
5) The large sample XML document (psd7003.xml) used within this project, was used principly only because it was large in size (and it was discovered by a naive web search), and not in any way because it belongs to a specific domain. The developer of this project, doesn't claim any knowleadge about the domain of large sample XML document used in this project.
6) It seems that, the results observed in this project are encouraging for doing XSLT 1.0 transformations on large XML input documents.
