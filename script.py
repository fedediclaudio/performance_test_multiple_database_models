
import csv
import random

records=1000020
print("Making %d records\n" % records)

fieldnames=['id','name','email']
writer = csv.DictWriter(open("mysql_user_1m.csv", "w"), fieldnames=fieldnames)

names=['Deepak', 'Sangeeta', 'Geetika', 'Anubhav', 'Sahil', 'Akshay']
email=['@yahoo.com', '@gmail.com', '@hotmail.com', '@outlook.com']

writer.writerow(dict(zip(fieldnames, fieldnames)))
for i in range(100, records):
  writer.writerow(dict([
    ('id', i),
    ('name', random.choice(names)),
    ('email', random.choice(names) + random.choice(email))]))