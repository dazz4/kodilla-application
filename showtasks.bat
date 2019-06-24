call runcrud.bat

set url="http://localhost:8080/crud/v1/task/getTasks"
start chrome %url%
if "%ERRORLEVEL%" == "0" goto fail
echo Error while opening browser

:fail
echo.
echo There were errors