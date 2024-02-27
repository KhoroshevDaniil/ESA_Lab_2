<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="1.0">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>paper</title>
            </head>
            <body>
                <h1>paper's view</h1>
                <p/>
                <a href="/api/v2/papers">link to all papers</a>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="PaperDTO">
        <div>
            <p>
                <div>ID:
                    <xsl:value-of select="id"/>
                </div>
            </p>
            <p>
                <div>Title:
                    <xsl:value-of select="title"/>
                </div>
            </p>
            <p>
                <div>Year:
                    <xsl:value-of select="year"/>
                </div>
            </p>
            <p>
                <div>Author:
                    <xsl:value-of select="authorName"/>
                </div>
            </p>
        </div>
    </xsl:template>
</xsl:stylesheet>