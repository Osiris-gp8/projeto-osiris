FROM r-base

COPY ./*.r /usr/local/bin/src/myscripts/
COPY ./*.sh /usr/local/bin/src/myscripts/
WORKDIR /usr/local/bin/src/myscripts

RUN apt update
RUN apt install -y libmariadb-dev
RUN Rscript -e "install.packages('RMariaDB')"
RUN Rscript -e "install.packages('lubridate')"
RUN Rscript -e "install.packages('jsonlite')"
RUN chmod +x ./wait-for-it.sh

ENTRYPOINT ["bash"]

CMD ["./wait-for-it.sh", "127.0.0.1:3306", "-s", "--", "sh", "run-scripts.sh"]