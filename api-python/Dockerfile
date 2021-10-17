FROM python:alpine

WORKDIR /usr/src/pipelines

COPY . .

RUN pip install .

ENTRYPOINT [ "python", "main.py" ]