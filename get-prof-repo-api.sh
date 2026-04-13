#!/bin/bash

profrepurl=https://github.com/ProfNpc/api.git

echo $profrepurl

#pega o remote do aluno no formato "origin  https://github.com/<userio-github>/repo-aluno.git (fetch)"
alunorepourl=`git remote -v | head -1`
#Remove "origin" de alunorepourl
alunorepourl=${alunorepourl/origin/}
#Remove "(fetch)" de alunorepourl
alunorepourl=${alunorepourl/"(fetch)"/}

echo $alunorepourl

#Renomeia o branch main
git switch main

numoldmain=`git branch | grep old-main | wc -l`
nomebranch=`echo old-main${numoldmain}`


git branch -m ${nomebranch}

#Cria um novo branch main(local) a partir de profrepo
git checkout --orphan main

git reset --hard

numproforigin=`git remote -v | grep prof-origin | wc -l`
if [ "$numproforigin" -eq "0" ]; then
	git remote add prof-origin ${profrepurl}
fi


git pull prof-origin main

#Agora remove a referencia para o repo remoto profrepo
#deixando apenas origin apontando para alunorepo
git remote remove prof-origin

#git remote add origin ${alunorepourl}

#Sobreescreve todo o conteudo do repositorio remoto com o conteudo que veio de profrepo
git push --force origin main


#git branch -D old-main


