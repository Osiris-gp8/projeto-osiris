#! /bin/bash
echo "Beginning execution"
Rscript geracao_eventos.r
Rscript geracao_metas.r
Rscript geracao_acessos.r
