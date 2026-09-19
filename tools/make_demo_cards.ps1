# Wrapper so Windows users can run the same generator as `python tools/make_demo_cards.py`.
$ErrorActionPreference = "Stop"
Set-Location (Split-Path $PSScriptRoot -Parent)
python tools/make_demo_cards.py
