cd %~dp0
SET /P xml= Please enter xml name:

if exist "%~dp0%xml%" (
    mvn test -DsuiteXmlFile="%~dp0%xml%"
) else (
    echo "file doesn't exist"
)

