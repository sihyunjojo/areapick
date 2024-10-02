# hadoop 아래의 폴더 부터 ubuntu 환경에서 파일 구조와 동일 

-> usr/local/아래에 이러한 파일 설정을 함.

hadoop/usr/local/hadoop/etc/hadoop 파일 아래의
1. yarn-site.xml
3. hdfs-site.xml
4. core-site.xml
5. maperd-site.xml
6. hadoop-env.sh  
파일 수정


```
start-all.sh
# 위 명령어는 아래 2개를 포함
start-dfs.sh
start-yarn.sh
```
```bash
spark-submit --master yarn --deploy-mode client /path/to/your_app.py
```
```
spark-submit --master yarn --deploy-mode cluster /path/to/you
```


# hadoop에서 DB로 파일을 넣기 위해 db의 connector jar 파일 필요
