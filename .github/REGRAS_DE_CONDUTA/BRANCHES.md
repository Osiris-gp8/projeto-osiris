# Regras de conduta sobre branches

## __main__

- A branch main tem a proposta de ser a principal do projeto;
- Mantém a última versão estável do projeto, ou seja, a última versão onde todos componentes estão funcionando;
- Nela, somente o integrante __NOME__ tem a permissão de aceitar _Merge Requests_;
- Não fazer alterações diretamente na __main__;
- É a partir da __main__ que será feito o deploy da aplicação em produção;

## __develop__

- É a branch criada a partir da main;
- Mantém a versão mais recente do projeto;
- Nela vamos subir novos componentes e novas funcionalidades além de correção de bugs;
- É a partir dela que outras branches devem ser criadas;
- Somente essa branch pode ser "mergeada" com a main;
- Nela, qualquer integrante pode aceitar o __Merge Request__, desde que não seja o mesmo que abriu;

## __feature/*__

- Branch criada a partir da devolop para desenvolvimento de novas funcionalidades;
- Deve ser mergeada com a develop;

## __refactor/*__

- Branch criada a partir da develop para refatoração de alguma parte do projeto;
- Deve ser mergeada com a develop;

## __bugfix/*__

- Branch criada a partir da develop para conserto de bugs;
- Deve ser mergeada com a develop;

## __hotfix/*__

- Branch criada a partir da main, quando há algum bug grave, que afeta bastante alguma funcionalidade;
- Deve ser mergeada com a main;