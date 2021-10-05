FROM r-base

COPY ./*.r /usr/local/bin/src/myscripts/
WORKDIR /usr/local/bin/src/myscripts

RUN Rscript -e "install.packages('RMariaDB')"
RUN Rscript -e "install.packages('lubridate')"
RUN Rscript -e "install.packages('jsonlite')"


CMD ["Rscript", "geracao_eventos.r"]

