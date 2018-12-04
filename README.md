addArchivesDetail


author   int
title   string
content   string 

state  0/1  暂存/提交
receiver   2018|2019

findArchivesDetailByAuthor
author是当前登陆的人
int author int pageNow  //当前页数
返回 ArchivesDetail数组

checkArchivesDetail
url：/uanager/checkArchivesDetail
参数：archivesDetail 改变state 3通过/4未通过
只有管理员可以审核

deleteArchivesDetail
url:/deleteArchivesDetail
参数：archives的id号

readArchives
url:/readArchives
参数：当前用户id，阅读公文对应id

sendArchives
url：/sendArchives
参数：ArchivesDetail