@echo off

for %%i in ("%cd%") do set curr_dir=%%~ni

for /f %%a in ('jps "-v" ^| find "%curr_dir%"') do taskkill /F /PID %%a

start /b mvn spring-boot:run -Dspring-boot.run.profiles=1
start /b mvn spring-boot:run -Dspring-boot.run.profiles=2
