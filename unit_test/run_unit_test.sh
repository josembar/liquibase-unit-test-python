#!/bin/bash

rm -rf venv

python3 -m venv venv

source venv/bin/activate

pip install -r requirements.txt

echo "Unit testing will start..."

python -m unittest mysql_unittest.py