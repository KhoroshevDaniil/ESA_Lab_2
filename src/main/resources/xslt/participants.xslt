<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="1.0">
    <xsl:output method="html" indent="yes" />
    <xsl:template match="/">
        <html>
            <head>
                <title>participants</title>
            </head>
            <body>
                <h1>All participants</h1>
                <a href="/">link to index</a>
                <table>
                    <thead>
                        <tr>
                            <td>№</td>
                            <td>ID</td>
                            <td>Name</td>
                            <td>Age</td>
                            <td>Academic Degree</td>
                        </tr>
                    </thead>
                    <xsl:apply-templates/>
                </table>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="ArrayList">
        <tbody>
            <xsl:for-each select="item">
                <tr>
                    <xsl:variable name="i" select="position()"/>
                    <td>
                        <xsl:value-of select="$i" />
                    </td>
                    <td>
                        <xsl:value-of select="id" />
                    </td>
                    <td>
                        <xsl:element name="a">
                            <xsl:attribute name="href">
                                /participants/
                                <xsl:value-of select="concat('participants/', id)"/>
                            </xsl:attribute>
                            <xsl:value-of select="name"/>
                        </xsl:element>
                    </td>
                    <td>
                        <xsl:value-of select="age"/>
                    </td>
                    <td>
                        <xsl:value-of select="academicDegree"/>
                    </td>
                </tr>
            </xsl:for-each>
        </tbody>
    </xsl:template>
</xsl:stylesheet>