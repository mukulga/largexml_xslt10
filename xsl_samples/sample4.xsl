<?xml version="1.0" encoding="UTF-8"?>
<!--
   An identity stylesheet. The output of this stylesheet is same as the XML input document.
-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"    
                version="1.0">
                
    <xsl:output method="xml" indent="yes"/>                               
        
    <xsl:template match="node() | @*">
       <xsl:copy>
          <xsl:apply-templates select="node() | @*"/>
       </xsl:copy>
    </xsl:template>
    
</xsl:stylesheet>