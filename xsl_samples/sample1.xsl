<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"    
                version="1.0">
                
    <xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>                               
    
    <xsl:template match="/ProteinEntry">
       <ProteinEntry id="{@id}"/>
    </xsl:template>
    
</xsl:stylesheet>