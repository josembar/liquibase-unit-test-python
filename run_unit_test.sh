#!/bin/bash

rm -rf venv

python3 -m venv venv

source venv/bin/activate

pip -r install requirements.txt

python -m unittest mysql_unitest.py

deactivate