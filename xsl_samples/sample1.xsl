<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"    
                version="1.0">
                
    <xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>                               
    
    <xsl:template match="/ProteinEntry">
       <!-- create a ProteinEntry element with an attribute, but it is otherwise empty --> 
       <ProteinEntry id="{@id}"/>
    </xsl:template>
    
</xsl:stylesheet>