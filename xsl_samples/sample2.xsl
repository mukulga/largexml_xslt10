<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"    
                version="1.0">
                
    <xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>                               
    
    <xsl:template match="/ProteinEntry">
       <ProteinEntry id="{@id}">
          <!-- deep copy only the header element below ProteinEntry element -->
          <xsl:copy-of select="header"/>
       </ProteinEntry>
    </xsl:template>
    
</xsl:stylesheet>