<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="1.0">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>participant</title>
            </head>
            <body>
                <h1>participant's view</h1>
                <p/>
                <a href="/api/v2/participants">link to all participants</a>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="ParticipantDTO">
        <div>
            <p>
                <div>ID:
                    <xsl:value-of select="id"/>
                </div>
            </p>
            <p>
                <div>Name:
                    <xsl:value-of select="name"/>
                </div>
            </p>
            <p>
                <div>Age:
                    <xsl:value-of select="age"/>
                </div>
            </p>
            <p>
                <div>Academic Degree:
                    <xsl:value-of select="academicDegree"/>
                </div>
            </p>
        </div>
    </xsl:template>
</xsl:stylesheet>