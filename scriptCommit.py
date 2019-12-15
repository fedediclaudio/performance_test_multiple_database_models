
import csv
import random

records=100020
print("Making %d records\n" % records)

fieldnames=['id','hash','message','id_user']
writer = csv.DictWriter(open("mysql_commit_1m.csv", "w"), fieldnames=fieldnames)

hashh=['ashlakak', 'fkjsfa', 'qoeieiq', 'lololo', 'titititin', 'pumpumpum']
message=['holamundo', 'now you see me now you dont', 'vaso', 'people like boss', 'me asusta morir','me gusta america y a america le gusto']

writer.writerow(dict(zip(fieldnames, fieldnames)))
for i in range(20, records):
  writer.writerow(dict([
    ('id', i),
    ('hash', random.choice(hashh)),
    ('message', random.choice(message)),
    ('id_user', random.randrange(100,200))]))