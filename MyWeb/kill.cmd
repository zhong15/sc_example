@echo off

for %%i in ("%cd%") do set curr_dir=%%~ni

for /f %%a in ('jps "-v" ^| find "%curr_dir%"') do taskkill /F /PID %%a
